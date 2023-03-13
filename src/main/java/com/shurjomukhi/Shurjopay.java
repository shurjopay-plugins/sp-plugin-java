package com.shurjomukhi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shurjomukhi.model.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Supplier;

/**
 * shurjopay service with payment functionalities.
 * <p>
 * {@code authenticate()} method provides functionality to authenticate authorized merchant.</p><p>
 * {@code makePayment()} method provides functionality to submit a payment.</p><p>
 * {@code verifyPayment()} method provides functionality to verify a submitted payment.</p><p>
 * {@code checkPaymentStatus()} method provides functionality to check a verified payment status.</p>
 * 
 * @author Al - Amin
 * @since 2022-06-13
 */
@Slf4j
public class Shurjopay {

	/** Merchant login response model instance.<br> See more.. {@link ShurjopayToken} */
	private ShurjopayToken authToken;
	
	/** Shurjopay properties holder model instance.<br> See more.. {@link ShurjopayConfig} */
	private final ShurjopayConfig spConfig;
	
	/** Shurjopay status code.*/
	private String spCode;

	/**
	 * Instantiates Shurjopay with shurjopay's configurations. which are loaded from {@code shurjopay.properties}
	 *
	 * @throws ShurjopayException if shurjopay.properties file not found for instantiating Shurjopay.
	 */
	public Shurjopay() throws ShurjopayException{
		super();
		this.spConfig = getShurjoPayConfig();
	}

	/**
	 * Instantiates auto-warble bean for spring plugin with shurjopay's configurations.
	 * which are loaded from default spring properties e.g. {@code application.properties} or {@code application.yml}
	 *
	 * @param config Shurjopay properties provided by shurjopay author.
	 */
	public Shurjopay(ShurjopayConfig config) {
		super();
		this.spConfig = config;
	}

	/**
	 * Authenticates merchant to perform a payment process
	 *
	 * @return authToken Shurjopay login response. See more.. {@link ShurjopayToken}
	 * @throws ShurjopayException Unauthorized exception if username/password is wrong or not provided.
	 */
	private ShurjopayToken authenticate() throws ShurjopayException{
		try {
			Map<String, String> shurjoPayTokenMap = new HashMap<>();
			shurjoPayTokenMap.put("username", spConfig.getUsername());
			shurjoPayTokenMap.put("password", spConfig.getPassword());

			String requestBody = prepareReqBody(shurjoPayTokenMap);
			HttpRequest request = postRequest(requestBody, "get_token", false);
			HttpResponse<Supplier<ShurjopayToken>> response = getClient().send(request, new JsonBodyHandler<>(ShurjopayToken.class));
			authToken = response.body().get();
			spCode = authToken.getSpStatusCode();

			if (!spCode.equals("200")) throw new ShurjopayException("Code: "+ spCode +" Message: " + ShurjopayStatus.messageByCode(spCode));
			log.info("Merchant authentication successful!");

			return authToken;
		} catch (InterruptedException | IOException e) {
			throw new ShurjopayException("Error occurred when sending authentication request.", e);
		}
	}

	/**
	 * Initiates payment to shurjopay payment gateway
	 *
	 * @param payload Payment request {@link PaymentReq} to initiate a payment
	 * @return Payment response object contains redirect URL to reach payment page, order id to verify order in shurjoPay.
	 * @throws ShurjopayException If any mandatory field is missed, exception occurred.
	 */
	public PaymentRes makePayment(PaymentReq payload) throws ShurjopayException {
		try {
			PaymentRes paymentRes;
			if (isAuthenticationRequired()) authToken = authenticate();
			
			String requestBody = prepareReqBody(getDefaultInfo(payload));
			HttpRequest request = postRequest(requestBody, "secret-pay", false);
			HttpClient client = getClient();
			HttpResponse<Supplier<PaymentRes>> response = client.send(request, new JsonBodyHandler<>(PaymentRes.class));
			paymentRes = response.body().get();
			if(Objects.isNull(paymentRes.getPaymentUrl()) || paymentRes.getPaymentUrl().isBlank()) throw new ShurjopayException("One or more mandatory field(s) not provided in request payload.");
			
			log.info("Payment URL has been generated.");
	
			return paymentRes;
		} catch (IOException | InterruptedException e) {
			log.error("Error occurred when sending make payment request.", e);
			throw new ShurjopayException("Error occurred when sending make payment request.", e);
		}
	}

	/**
	 * Verifies an initiated payment after back transaction.
	 * 
	 * @param spTxnId Shurjopay transaction id which is retrieved from call-back URL.
	 * @return order object if order verified successfully
	 * @throws ShurjopayException Verifying payment related exception
	 */
	public VerifiedPayment verifyPayment(String spTxnId) throws ShurjopayException {
		try {
			if (isAuthenticationRequired()) authToken = authenticate();
			
			Map<String, String> verifiedPaymentReq = new HashMap<>();
			verifiedPaymentReq.put("order_id", spTxnId);

			String requestBody = prepareReqBody(verifiedPaymentReq);
			HttpRequest request = postRequest(requestBody, "verification", true);
			
			HttpResponse<Supplier<VerifiedPayment[]>> response = getClient().send(request, new JsonBodyHandler<>(VerifiedPayment[].class));
			VerifiedPayment verifiedPaymentRes = response.body().get()[0];
			
			spCode = verifiedPaymentRes.getSpStatusCode();
			if (!spCode.equals(ShurjopayStatus.SHURJOPAY_SUCCESS.code())) throw new ShurjopayException("Code: "+ spCode +" Message: " + ShurjopayStatus.messageByCode(String.valueOf(spCode)));
			
			log.info("shurjopay status for Verify Payment: {}", verifiedPaymentRes.getSpStatusMsg());
			
			return verifiedPaymentRes;
		} catch (IOException | InterruptedException e) {
			log.error("Error occurred when sending verify payment request.", e);
			throw new ShurjopayException("Error occrued when sending verify payment request.", e);
		}
	}

	/**
	 * Checks shurjopay token is expired or not.
	 *
	 * @return boolean - true if authenticated otherwise false
	 */
	private boolean isAuthenticationRequired() {
		return !((Objects.nonNull(authToken) && !isTokenExpired(authToken)));
	}

	/**
	 * Instantiates HttpClient
	 *
	 * @return HttpClient - {@link HttpClient}
	 */
	private HttpClient getClient() {
		return HttpClient.newHttpClient();
	}
	
	/**
	 * Converts object to JSON object string and prepares request body
	 *
	 * @param shurjoPayReq various type of request object.
	 * @return a JSON string.
	 */
	private String prepareReqBody(Object shurjoPayReq) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(shurjoPayReq);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Object mapping failed due to mapping PaymentReq. Please check!", e.getCause());
		}
	}

	/**
	 * Checks expiration of shurjopay JWT token
	 * 
	 * @param shurjoPayTokenRes Shurjopay authentication response. See more.. {@link ShurjopayToken}
	 * @return {@code true} if token is expired, otherwise {@code false}
	 */
	private boolean isTokenExpired(ShurjopayToken shurjoPayTokenRes) {
		DateTimeFormatter format = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("yyyy-MM-dd hh:mm:ssa").toFormatter(Locale.US);

		LocalDateTime createdAt = LocalDateTime.parse(shurjoPayTokenRes.getTokenCreatedTime(), format);
		int diff = (int) ChronoUnit.SECONDS.between(createdAt, LocalDateTime.now());
		
		return shurjoPayTokenRes.getExpiredTimeInSecond() <= diff;
	}
	
	/**
	 * Prepares Payment request object with default values such as <code>
	 * Return URL, Cancel URL, JWT token, Client IP address, Merchant Store Id </code>
	 *
	 * @param paymentReq {@link PaymentReq}
	 * @return {@link PaymentReq} with shurjoPay's default values
	 */
	public PaymentReq getDefaultInfo(PaymentReq paymentReq) {
		String callBackUrl = spConfig.getCallbackUrl();
		paymentReq
			.setReturnUrl(callBackUrl)
			.setCancelUrl(callBackUrl)
			.setAuthToken(authToken.getToken())
			.setStoreId(authToken.getStoreId());
		
		try {
			paymentReq.setClientIp(InetAddress.getLocalHost().getHostAddress());
			
		} catch (UnknownHostException e) {
			log.warn("Client IP does not found. Setting default ip address..", e);
			paymentReq.setClientIp("127.0.0.1");
		}
		
		return paymentReq;
	}

	/**
	 * Sends request to shurjopay
	 *
	 * @param httpBody String JSON body.
	 * @param endPoint shurjopay endpoint
	 * @param isAuthHead true if needed "Authorization" header otherwise false
	 * @return HttpRequest successful http request.
	 */
	private HttpRequest postRequest(String httpBody, String endPoint, boolean isAuthHead) {
		var request = HttpRequest.newBuilder(URI.create(spConfig.getApiBaseUrl().concat(endPoint)));
		request.POST(HttpRequest.BodyPublishers.ofString(httpBody));
		request.header("Content-Type", "application/json").build();
		if(isAuthHead) request.header("Authorization", getFormattedToken(authToken.getToken(), authToken.getTokenType()));
		return request.build();
	}

	/**
	 * Concatenates Bearer prefix to shurjopay JWT token
	 *
	 * @param token shurjopay JWT token.
	 * @param tokenType token type accepted to shurjopay is Bearer.
	 * @return JWT authentication token with Bearer prefix.
	 */
	private String getFormattedToken(String token, String tokenType) {
		return tokenType.concat(" ").concat(token);
	}
	
	/**
	 * Sets up shurjopay configuration and prepare ShurjopayConfig
	 *
	 * @return ShurjopayConfig with merchant configuration provided by shurjopay.
	 * @throws ShurjopayException if the properties file not found then throw exception.
	 */
	private ShurjopayConfig getShurjoPayConfig() throws ShurjopayException {
		Properties spProps = PropertiesReader.instance().getProperties();
		if (Objects.isNull(spProps)) throw new ShurjopayException("shurjopay.properties is missing in resource path");
		
		return new ShurjopayConfig()
				   .setUsername(spProps.getProperty("SP_USER"))
				   .setPassword(spProps.getProperty("SP_PASS"))
				   .setApiBaseUrl(spProps.getProperty("SHURJOPAY_API"))
				   .setCallbackUrl(spProps.getProperty("SP_CALLBACK"));
	}
}
