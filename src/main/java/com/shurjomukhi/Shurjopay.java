package com.shurjomukhi;

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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Supplier;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shurjomukhi.constants.Endpoints;
import com.shurjomukhi.constants.ShurjopayConfigKeys;
import com.shurjomukhi.constants.ShurjopayStatus;
import com.shurjomukhi.model.PaymentReq;
import com.shurjomukhi.model.PaymentRes;
import com.shurjomukhi.model.ShurjopayConfig;
import com.shurjomukhi.model.ShurjopayToken;
import com.shurjomukhi.model.VerifiedPayment;

import lombok.extern.slf4j.Slf4j;

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

	/**
	 * Merchant login response model instance.<br>
	 * See more.. {@link ShurjopayToken}
	 */
	private ShurjopayToken authToken;
	
	/**
	 * Shurjopay properties holder model instance.<br>
	 * See more.. {@link ShurjopayConfig}
	 */
	private ShurjopayConfig spConfig;
	
	/** Merchant authentication success status code.*/
	private static final String AUTH_SUCCESS_CODE = "200";
	
	/** Default IP address.*/
	private static final String DEFAULT_IP = "127.0.0.1";
	
	/** Blank space constant for programmatic use.*/
	private static final String WHITE_SPACE = " ";
	
	/** JWT token header name.*/
	private static final String AUTH_KEYWORD = "Authorization";
	
	/** Shurjopay status code.*/
	private String spCode;

	/**
	 * Instantiates Shurjopay with shurjopay's configurations.
	 * which are loaded from {@code shurjopay.properties}
	 */
	public Shurjopay() {
		super();
		this.spConfig = getShurjoPayConfig();
	}

	/**
	 * Instantiates auto-warble bean for spring plugin with shurjopay's configurations.
	 * which are loaded from default spring properties e.g. {@code application.properties} or {@code application.yml}
	 * @param config Shurjopay properties provided by shurjopay author.
	 */
	public Shurjopay(ShurjopayConfig config) {
		super();
		this.spConfig = config;
	}

	/**
	 * Authenticates merchant to perform a payment process.
	 * @return authToken Shurjopay login response. See more.. {@link ShurjopayToken}
	 * @throws ShurjopayException Unauthorized exception if username/password is wrong or not provided.
	 */
	private ShurjopayToken authenticate() throws ShurjopayException{
		try {
		Map<String, String> shurjoPayTokenMap = new HashMap<>();
		shurjoPayTokenMap.put("username", spConfig.getUsername());
		shurjoPayTokenMap.put("password", spConfig.getPassword());

			String requestBody = prepareReqBody(shurjoPayTokenMap);
			HttpRequest request = postRequest(requestBody, Endpoints.TOKEN.title());
			HttpResponse<Supplier<ShurjopayToken>> response = getClient().send(request, new JsonBodyHandler<>(ShurjopayToken.class));
			authToken = response.body().get();
			spCode = authToken.getSpStatusCode();

		if (!spCode.equals(AUTH_SUCCESS_CODE)) throw new ShurjopayException("Code: "+ spCode +" Message: " + ShurjopayStatus.messageByCode(spCode));
		log.info("Merchant authentication successful!");
		
		return authToken;
		}
		catch (InterruptedException e) {
			
			throw new ShurjopayException("Error occrued when sending authentication request.", e);
		}
		catch (IOException e) {
			
			throw new ShurjopayException("Error occrued when sending authentication request.", e);
		}
		
		
	}

	/**
	 * Initiates payment to shurjopay payment gateway
	 * @param payload Payment request {@link PaymentReq} to initiate a payment
	 * @return Payment response object contains redirect URL to reach payment page, order id to verify order in shurjoPay.
	 * @throws ShurjopayException If any mandatory field is missed, exception occurred.
	 */
	public PaymentRes makePayment(PaymentReq payload) throws ShurjopayException {
		try {
			PaymentRes paymentRes;
			if (isAuthenticationRequired()) authToken = authenticate();
			
			String requestBody = prepareReqBody(getDefaultInfo(payload));
			HttpRequest request = postRequest(requestBody, Endpoints.MAKE_PMNT.title());
			HttpClient client = getClient();
			HttpResponse<Supplier<PaymentRes>> response = client.send(request, new JsonBodyHandler<>(PaymentRes.class));
			paymentRes = response.body().get();
			if(Objects.isNull(paymentRes.getPaymentUrl()) || paymentRes.getPaymentUrl().isBlank()) throw new ShurjopayException("One or more mandatory field(s) not provided in request payload.");
			
			log.info("Payment URL has been generated.");
	
			return paymentRes;
		} catch (IOException e) {
			
			log.error("Error occrued when sending make payment request.", e);
			throw new ShurjopayException("Error occrued when sending make payment request.", e);
		} catch (InterruptedException e) {
			
			log.error("Error occrued when sending make payment request.", e);
			throw new ShurjopayException("Error occrued when sending make payment request.", e);
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
			HttpRequest request = postRequest(requestBody, Endpoints.VERIFIED_PMNT.title(), true);
			
			HttpResponse<Supplier<VerifiedPayment[]>> response = getClient().send(request, new JsonBodyHandler<>(VerifiedPayment[].class));
			VerifiedPayment verifiedPaymentRes = response.body().get()[0];
			
			spCode = verifiedPaymentRes.getSpStatusCode();
			if (!spCode.equals(ShurjopayStatus.SHURJOPAY_SUCCESS.code())) throw new ShurjopayException("Code: "+ spCode +" Message: " + ShurjopayStatus.messageByCode(String.valueOf(spCode)));
			
			log.info("shurjopay status for Verify Payment: {}", verifiedPaymentRes.getSpStatusMsg());
			
			return verifiedPaymentRes;
		} catch (IOException e) {
			
			log.error("Error occrued when sending verify payment request.", e);
			throw new ShurjopayException("Error occrued when sending verify payment request.", e);
		} catch (InterruptedException e) {
			
			log.error("Error occrued when sending verify payment request.", e);
			throw new ShurjopayException("Error occrued when sending verify payment request.", e);
		}
	}

	/**
	 * Checks status of a successfully verified or initiated payment.
	 * 
	 * @param spTxnId
	 * @return order object if order verified successfully.
	 * @throws ShurjopayException
	 */
	public VerifiedPayment checkPaymentStatus(String spTxnId) throws ShurjopayException {
		try {
			if (isAuthenticationRequired()) authToken = authenticate();
			
			Map<String, String> orderMap = new HashMap<String, String>();
			orderMap.put("order_id", spTxnId);
			
			String requestBody = prepareReqBody(orderMap);
			HttpRequest request = postRequest(requestBody, Endpoints.PMNT_STAT.title(), true);
			HttpResponse<Supplier<VerifiedPayment[]>> response = getClient().send(request, new JsonBodyHandler<>(VerifiedPayment[].class));
			log.info("Checking payment status...");

			return response.body().get()[0];
		} catch (IOException e) {
			
			log.error("Error occrued when fetching payment status request.", e);
			throw new ShurjopayException("Error occrued when fetching payment status request.", e);
		} catch (InterruptedException e) {
			
			log.error("Error occrued when fetching payment status request.", e);
			throw new ShurjopayException("Error occrued when fetching payment status request.", e);
		}
	}
	
	/**
	 * Checks shurjopay token is expired or not.
	 * @return boolean
	 */
	private boolean isAuthenticationRequired() {
		return !((Objects.nonNull(authToken) && !isTokenExpired(authToken)));
	}

	/**
	 * Instantiates HttpClient
	 * @return HttpClient
	 */
	private HttpClient getClient() {
		return HttpClient.newHttpClient();
	}
	
	/**
	 * Converts object to JSON object string and prepares request body.
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
		
		return shurjoPayTokenRes.getExpiredTimeInSecond() <= diff ? true : false;
	}
	
	/**
	 * Prepares Payment request object with default values such as
	 * <code>
	 * Return URL, Cancel URL, JWT token, Client IP address, Merchant Store Id
	 * </code>
	 * @param paymentReq
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
			paymentReq.setClientIp(DEFAULT_IP);
		}
		
		return paymentReq;
	}

	/**
	 * Sends request to shurjopay.
	 * @param httpBody String JSON body.
	 * @param endPoint shurjopay endpoint
	 * @return HttpRequest successful http request.
	 */
	private HttpRequest postRequest(String httpBody, String endPoint) {
		
		return HttpRequest.newBuilder(URI.create(spConfig.getApiBaseUrl().concat(endPoint)))
						  .POST(HttpRequest.BodyPublishers.ofString(httpBody)).header("Content-Type", "application/json").build();
	}

	/**
	 * Sends request to shurjopay.
	 * @param httpBody String JSON body.
	 * @param endPoint shurjopay endpoint
	 * @return HttpRequest successful http request.
	 */
	private HttpRequest postRequest(String httpBody, String endPoint, boolean isAuthHead) {
		
		return HttpRequest.newBuilder(URI.create(spConfig.getApiBaseUrl().concat(endPoint)))
						  .header(AUTH_KEYWORD, getFormattedToken(authToken.getToken(), authToken.getTokenType()))
						  .POST(HttpRequest.BodyPublishers.ofString(httpBody)).header("Content-Type", "application/json").build();
	}

	/**
	 * Concatenates Bearer prefix to shurjopay JWT token.
	 * @param token shurjopay JWT token.
	 * @param tokenType token type accepted to shurjopay is Bearer.
	 * @return JWT authentication token with Bearer prefix.
	 */
	private String getFormattedToken(String token, String tokenType) {
		
		return tokenType.concat(WHITE_SPACE).concat(token);
	}
	
	/**
	 * Sets up shurjopay configuration and prepare ShurjopayConfig 
	 * @return ShurjopayConfig with merchant configuration provided by shurjopay
	 */
	private ShurjopayConfig getShurjoPayConfig() {
		Properties spProps = PropertiesReader.instance().getProperties();
		
		return new ShurjopayConfig()
				   .setUsername(spProps.getProperty(ShurjopayConfigKeys.SP_USER.name()))
				   .setPassword(spProps.getProperty(ShurjopayConfigKeys.SP_PASS.name()))
				   .setApiBaseUrl(spProps.getProperty(ShurjopayConfigKeys.SHURJOPAY_API.name()))
				   .setCallbackUrl(spProps.getProperty(ShurjopayConfigKeys.SP_CALLBACK.name()));
	}
}
