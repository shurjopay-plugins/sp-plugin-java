package com.shurjopay.plugin;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Supplier;

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
	Properties spProps = PropertiesReader.instance().getProperties();
	String basePath = getProperty("shurjopay-api");
	String authString;
	ShurjoPayToken authToken;

	/**
	 * Return authorization token for shurjoPay payment gateway system. Setup
	 * shurjopay.properties file .
	 * 
	 * @return
	 */
	public ShurjoPayToken authenticate() {

		Map<String, String> tokenReq = new HashMap<>();
		tokenReq.put("username", getProperty("username"));
		tokenReq.put("password", getProperty("password"));
		HttpClient client = getClient();

		try {
			ObjectMapper mapper = new ObjectMapper();
			String httpBody = mapper.writeValueAsString(tokenReq);
			HttpRequest request = postRequest(httpBody, EndPoints.GET_TOKEN.getValue());

			HttpResponse<Supplier<ShurjoPayToken>> response = client.send(request,
					new JsonBodyHandler<>(ShurjoPayToken.class));
			authToken = response.body().get();
			return authToken;
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

	public CheckoutRes makePayment(CheckoutReq req) {
		// check for existence of token. if not found authenticate first.
		
		HttpClient client = getClient();

		try {
			ObjectMapper mapper = new ObjectMapper();
			String httpBody = mapper.writeValueAsString(req);

			HttpRequest request = postRequest(httpBody, EndPoints.SECRET_PAY.getValue());

			HttpResponse<Supplier<CheckoutRes>> response = client.send(request,
					new JsonBodyHandler<>(CheckoutRes.class));
			return response.body().get();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

	private HttpRequest postRequest(String httpBody, String endPoint) {
		
		return HttpRequest.newBuilder(URI.create(basePath.concat(endPoint)))
				.POST(HttpRequest.BodyPublishers.ofString(httpBody))
				.header("Content-Type", "application/json").build();
	}

	public List<VerifiedOrder> verifyOrder(String orderId, String token, String tokenType) {
		// check for existence of token. if not found authenticate first.

		String basePath = getProperty("shurjopay-api");
		HttpClient client = getClient();

		try {
			Map<String, String> orderMap = new HashMap<>();
			orderMap.put("order_id", orderId);
			ObjectMapper mapper = new ObjectMapper();
			String httpBody = mapper.writeValueAsString(orderMap);

			String authToken = getFormattedToken(token, tokenType);

			HttpRequest request = HttpRequest.newBuilder(URI.create(basePath.concat(EndPoints.VERIFICATION.getValue())))
					.header("Authorization", authToken).POST(HttpRequest.BodyPublishers.ofString(httpBody))
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
		// check for existence of token. if not found authenticate first.

		String basePath = getProperty("shurjopay-api");

		HttpClient client = getClient();

		try {
			Map<String, String> orderMap = new HashMap<String, String>();
			orderMap.put("order_id", orderId);
			ObjectMapper mapper = new ObjectMapper();
			String httpBody = mapper.writeValueAsString(orderMap);
			String authToken = getFormattedToken(token, tokenType);

			HttpRequest request = HttpRequest.newBuilder(URI.create(basePath.concat(EndPoints.PMNT_STAT.getValue())))
					.header("Authorization", authToken).POST(HttpRequest.BodyPublishers.ofString(httpBody))
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
//		return PropertiesReader.instance().getProperties().getProperty(key);
	}

	private String getFormattedToken(String token, String tokenType) {
		return tokenType.concat(" ").concat(token);
	}
}
