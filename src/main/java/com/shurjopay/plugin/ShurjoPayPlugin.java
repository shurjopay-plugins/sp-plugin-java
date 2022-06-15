package com.shurjopay.plugin;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shurjopay.plugin.enums.EndPoints;
import com.shurjopay.plugin.model.CheckoutReq;
import com.shurjopay.plugin.model.CheckoutRes;
import com.shurjopay.plugin.model.ShurjoPayToken;
import com.shurjopay.plugin.model.VerifiedOrder;

/**
 * 
 * @author Al - Amin
 * @since 2022-06-13
 */
public class ShurjoPayPlugin {
	Logger logger = Logger.getLogger(ShurjoPayPlugin.class.getName());
	Properties spProps = PropertiesReader.instance().getProperties();
	String basePath = getProperty("shurjopay-api");
	String authString;
	ShurjoPayToken authToken;

	/**
	 * Return authorization token for shurjoPay payment gateway system. Setup
	 * shurjopay.properties file .
	 * 
	 * @return authentication details with active token
	 */
	public ShurjoPayToken authenticate() {

		Map<String, String> tokenReq = new HashMap<>();
		tokenReq.put("username", getProperty("username"));
		tokenReq.put("password", getProperty("password"));
		HttpClient client = getClient();

		try {
			String requestBody = prepareReqBody(tokenReq);
			HttpRequest request = postRequest(requestBody, EndPoints.GET_TOKEN.getValue());

			HttpResponse<Supplier<ShurjoPayToken>> response = client.send(request,
					new JsonBodyHandler<>(ShurjoPayToken.class));
			authToken = response.body().get();

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		if (authToken.getMessage().equals("Ok")) {
			logger.log(Level.INFO, "Authentication token has been generated successfully");
			return authToken;
		} else {
			logger.log(Level.FINE, "Username or password is invalid or not existed");
			return null;
		}
	}

	public CheckoutRes makePayment(CheckoutReq req) {
		if (Objects.isNull(authToken) || isTokenExpired(authToken))
			authToken = authenticate();

		HttpClient client = getClient();

		try {
			String requestBody = prepareReqBody(req);

			HttpRequest request = postRequest(requestBody, EndPoints.SECRET_PAY.getValue());

			HttpResponse<Supplier<CheckoutRes>> response = client.send(request,
					new JsonBodyHandler<>(CheckoutRes.class));
			return response.body().get();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<VerifiedOrder> verifyOrder(String orderId, String token, String tokenType) {
		if (Objects.isNull(authToken) || isTokenExpired(authToken))
			authToken = authenticate();

		String basePath = getProperty("shurjopay-api");
		HttpClient client = getClient();

		try {
			Map<String, String> orderMap = new HashMap<>();
			orderMap.put("order_id", orderId);

			String requestBody = prepareReqBody(orderMap);

			String authToken = getFormattedToken(token, tokenType);

			HttpRequest request = HttpRequest.newBuilder(URI.create(basePath.concat(EndPoints.VERIFICATION.getValue())))
					.header("Authorization", authToken).POST(HttpRequest.BodyPublishers.ofString(requestBody))
					.header("Content-Type", "application/json").build();

			HttpResponse<Supplier<VerifiedOrder[]>> response = client.send(request,
					new JsonBodyHandler<>(VerifiedOrder[].class));

			return Arrays.asList(response.body().get());

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<VerifiedOrder> getPaymentStatus(String orderId, String token, String tokenType) {
		if (Objects.isNull(authToken) || isTokenExpired(authToken))
			authToken = authenticate();

		String basePath = getProperty("shurjopay-api");

		HttpClient client = getClient();

		try {
			Map<String, String> orderMap = new HashMap<String, String>();
			orderMap.put("order_id", orderId);

			String requestBody = prepareReqBody(orderMap);
			String authToken = getFormattedToken(token, tokenType);

			HttpRequest request = HttpRequest.newBuilder(URI.create(basePath.concat(EndPoints.PMNT_STAT.getValue())))
					.header("Authorization", authToken).POST(HttpRequest.BodyPublishers.ofString(requestBody))
					.header("Content-Type", "application/json").build();

			HttpResponse<Supplier<VerifiedOrder[]>> response = client.send(request,
					new JsonBodyHandler<>(VerifiedOrder[].class));
			return Arrays.asList(response.body().get());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}

	}

	private HttpClient getClient() {
		return HttpClient.newHttpClient();
	}

	private String getProperty(String key) {
		return spProps.getProperty(key);
	}

	private String getFormattedToken(String token, String tokenType) {
		return tokenType.concat(" ").concat(token);
	}

	private String prepareReqBody(Object object) {
		String requestBody = null;

		try {
			ObjectMapper mapper = new ObjectMapper();
			requestBody = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return requestBody;
	}

	/**
	 * Checking expiration of token
	 * @param {@link ShurjoPayPlugin}
	 * @return true if token is expired, otherwise return false
	 */
	private boolean isTokenExpired(ShurjoPayToken authOb) {
		DateTimeFormatter format = new DateTimeFormatterBuilder().parseCaseInsensitive()
				.appendPattern("yyyy-MM-dd hh:mm:ssa").toFormatter(Locale.US);

		LocalDateTime createdAt = LocalDateTime.parse(authOb.getTokenCreateTime(), format);
		int diff = (int) ChronoUnit.SECONDS.between(createdAt, LocalDateTime.now());

		if (authOb.getExpiresIn() < diff)
			return true;

		return false;
	}

	private HttpRequest postRequest(String httpBody, String endPoint) {

		return HttpRequest.newBuilder(URI.create(basePath.concat(endPoint)))
				.POST(HttpRequest.BodyPublishers.ofString(httpBody)).header("Content-Type", "application/json").build();
	}
}
