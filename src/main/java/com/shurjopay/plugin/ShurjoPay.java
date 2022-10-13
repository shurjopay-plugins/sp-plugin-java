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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Supplier;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shurjopay.plugin.model.PaymentReq;
import com.shurjopay.plugin.model.PaymentRes;
import com.shurjopay.plugin.model.ShurjoPayConfig;
import com.shurjopay.plugin.model.ShurjoPayToken;
import com.shurjopay.plugin.model.VerifiedPayment;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Plug-in service to provide shurjoPay get way services.
 * 
 * @author Al - Amin
 * @since 2022-06-13
 */
@Slf4j
public class ShurjoPay {

	private ShurjoPayToken authToken;
	private ShurjoPayConfig spConfig = getShurjoPayConfig();

	/**
	 * Return authorization token for shurjoPay payment gateway system. Setup
	 * shurjopay.properties file .
	 * 
	 * @return authentication details with valid token
	 * @throws IllegalAccessException
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws IllegalAccessException
	 */
	private ShurjoPayToken authenticate() throws IllegalAccessException, IOException, InterruptedException {
		Map<String, String> shurjoPayTokenReq = new HashMap<>();
		shurjoPayTokenReq.put("username", spConfig.getUsername());
		shurjoPayTokenReq.put("password", spConfig.getPassword());

			HttpClient client = getClient();
			String requestBody = prepareReqBody(shurjoPayTokenReq);
			HttpRequest request = postRequest(requestBody, Endpoints.TOKEN.getEndpointValue());
			HttpResponse<Supplier<ShurjoPayToken>> response = client.send(request, new JsonBodyHandler<>(ShurjoPayToken.class));
			authToken = response.body().get();

		if (authToken.getSpStatusCode().equals("200")) {
			log.info("Merchant authentication successful!");
		} else {
			throw new IllegalAccessException("Invalid User name or Password due to shurjoPay authentication.");
		}
		
		return authToken;
	}

	/**
	 * This method is used for making payment.
	 * 
	 * @param Payment request object. See the shurjoPay version-2 integration documentation(beta).docx for details.
	 * @return Payment response object contains redirect URL to reach payment page, order id to verify order in shurjoPay.
	 */
	public PaymentRes makePayment(PaymentReq req) {
		try {
			if (Objects.isNull(authToken)) authToken = authenticate();
			if (isTokenExpired(authToken)) authToken = authenticate();
			
			HttpClient client = getClient();
			String callBackUrl = spConfig.getCallbackApi();
			req.setReturnUrl(callBackUrl);
			req.setCancelUrl(callBackUrl);
			req.setAuthToken(authToken.getToken());
			req.setStoreId(authToken.getStoreId());

			String requestBody = prepareReqBody(req);
			HttpRequest request = postRequest(requestBody, Endpoints.MAKE_PMNT.getEndpointValue());
			HttpResponse<Supplier<PaymentRes>> response = client.send(request, new JsonBodyHandler<>(PaymentRes.class));
			log.info("Payment URL has been generated by shurjoPay.");
			
			return response.body().get();
		} catch (IOException | InterruptedException | IllegalAccessException e) {
			log.error("Payment request failed.", e);
			
			return null;
		}
	}

	/**
	 * This method is used for verifying order by order id which could be get by payment response object
	 * 
	 * @param orderId
	 * @return order object if order verified successfully
	 */
	public VerifiedPayment verifyPayment(String orderId) {
		try {
			if (Objects.isNull(authToken)) authToken = authenticate();
			if (isTokenExpired(authToken)) authToken = authenticate();
			
			HttpClient client = getClient();
			Map<String, String> verifiedPaymentReq = new HashMap<>();
			verifiedPaymentReq.put("order_id", orderId);

			String requestBody = prepareReqBody(verifiedPaymentReq);
			HttpRequest request = postRequest(requestBody, Endpoints.VERIFIED_ORDER.getEndpointValue(), true);
			
			HttpResponse<Supplier<VerifiedPayment[]>> response = client.send(request, new JsonBodyHandler<>(VerifiedPayment[].class));
			VerifiedPayment verifiedPaymentRes = response.body().get()[0];
			log.info("shurjopay status for Verify Payment: {}", verifiedPaymentRes.getSpStatusMsg());
			
			return verifiedPaymentRes;
		} catch (IOException | InterruptedException | IllegalAccessException e) {
			log.error("Payment verification failed", e);
			
			return null;
		}
	}

	/**
	 * This method is used for checking successfully paid order status by order id which could be get after verifying order
	 * 
	 * @param orderId
	 * @return order object if order verified successfully
	 */
	public VerifiedPayment checkPaymentStatus(String orderId) {
		try {
			if (Objects.isNull(authToken)) authToken = authenticate();
			if (isTokenExpired(authToken)) authToken = authenticate();
			
			HttpClient client = getClient();
			Map<String, String> orderMap = new HashMap<String, String>();
			orderMap.put("order_id", orderId);
			
			String requestBody = prepareReqBody(orderMap);
			HttpRequest request = postRequest(requestBody, Endpoints.PMNT_STAT.getEndpointValue(), true);
			HttpResponse<Supplier<VerifiedPayment[]>> response = client.send(request, new JsonBodyHandler<>(VerifiedPayment[].class));
			log.info("Checking payment status...");

			return response.body().get()[0];
		} catch (IOException | InterruptedException | IllegalAccessException e) {
			log.error("A successful Payment verification get the payment status", e);
			
			return null;
		}
	}

	private HttpClient getClient() {
		return HttpClient.newHttpClient();
	}

	private String prepareReqBody(Object shurjoPayReq) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(shurjoPayReq);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Object mapping failed due to mapping PaymentReq. Please check!", e.getCause());
		}
	}

	/**
	 * Checking expiration of token
	 * 
	 * @param {@link ShurjoPay}
	 * @return true if token is expired, otherwise return false
	 */
	private boolean isTokenExpired(ShurjoPayToken shurjoPayTokenRes) {
		DateTimeFormatter format = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("yyyy-MM-dd hh:mm:ssa").toFormatter(Locale.US);

		LocalDateTime createdAt = LocalDateTime.parse(shurjoPayTokenRes.getTokenCreatedTime(), format);
		int diff = (int) ChronoUnit.SECONDS.between(createdAt, LocalDateTime.now());
		
		return shurjoPayTokenRes.getExpiredTimeInSecond() <= diff ? true : false;
	}

	private HttpRequest postRequest(String httpBody, String endPoint) {
		return HttpRequest.newBuilder(URI.create(spConfig.getApiBaseUrl().concat(endPoint)))
						  .POST(HttpRequest.BodyPublishers.ofString(httpBody)).header("Content-Type", "application/json").build();
	}

	private HttpRequest postRequest(String httpBody, String endPoint, boolean isAuthHead) {
		return HttpRequest.newBuilder(URI.create(spConfig.getApiBaseUrl().concat(endPoint)))
						  .header("Authorization", getFormattedToken(authToken.getToken(), authToken.getTokenType()))
						  .POST(HttpRequest.BodyPublishers.ofString(httpBody)).header("Content-Type", "application/json").build();
	}

	private String getFormattedToken(String token, String tokenType) {
		return tokenType.concat(" ").concat(token);
	}
	
	protected ShurjoPayConfig getShurjoPayConfig() {
		Properties spProps = PropertiesReader.instance().getProperties();
		ShurjoPayConfig spConfig = new ShurjoPayConfig();
		
		spConfig.setUsername(spProps.getProperty(ShurjoPayConfigKeys.SP_USER.name()));
		spConfig.setPassword(spProps.getProperty(ShurjoPayConfigKeys.SP_PASS.name()));
		spConfig.setApiBaseUrl(spProps.getProperty(ShurjoPayConfigKeys.SHURJOPAY_API.name()));
		spConfig.setCallbackApi(spProps.getProperty(ShurjoPayConfigKeys.SP_CALLBACK.name()));
		
		return spConfig;
	}
}
