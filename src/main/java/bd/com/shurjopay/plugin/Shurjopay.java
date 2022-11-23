package bd.com.shurjopay.plugin;

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

import bd.com.shurjopay.plugin.constants.Endpoints;
import bd.com.shurjopay.plugin.constants.ShurjopayConfigKeys;
import bd.com.shurjopay.plugin.constants.ShurjopayStatus;
import bd.com.shurjopay.plugin.model.PaymentReq;
import bd.com.shurjopay.plugin.model.PaymentRes;
import bd.com.shurjopay.plugin.model.ShurjopayConfig;
import bd.com.shurjopay.plugin.model.ShurjopayToken;
import bd.com.shurjopay.plugin.model.VerifiedPayment;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Plug-in service to provide shurjoPay get way services.
 * 
 * @author Al - Amin
 * @since 2022-06-13
 */
@Slf4j
public class Shurjopay {

	private ShurjopayToken authToken;
	private ShurjopayConfig spConfig;
	private static final String AUTH_SUCCESS_CODE = "200";
	private static final String DEFAULT_IP = "127.0.0.1";
	private static final String WHITE_SPACE = " ";
	private static final String AUTH_KEYWORD = "Authorization";
	private String spCode;

	
	public Shurjopay() {
		super();
		this.spConfig = getShurjoPayConfig();
	}

	public Shurjopay(ShurjopayConfig config) {
		super();
		this.spConfig = config;
	}

	/**
	 * Return authentication token for shurjoPay payment gateway system. 
	 * Setup shurjopay.properties file.
	 * 
	 * @return authentication details with valid token
	 * @throws ShurjopayException while merchant username and password is invalid.
	 */
	private ShurjopayToken authenticate() throws ShurjopayException{
		try {
		Map<String, String> shurjoPayTokenReq = new HashMap<>();
		shurjoPayTokenReq.put("username", spConfig.getUsername());
		shurjoPayTokenReq.put("password", spConfig.getPassword());

			String requestBody = prepareReqBody(shurjoPayTokenReq);
			HttpRequest request = postRequest(requestBody, Endpoints.TOKEN.title());
			HttpResponse<Supplier<ShurjopayToken>> response = getClient().send(request, new JsonBodyHandler<>(ShurjopayToken.class));
			authToken = response.body().get();
			spCode = authToken.getSpStatusCode();

		if (!spCode.equals(AUTH_SUCCESS_CODE)) throw new ShurjopayException("Code: "+ spCode +" Message: " + ShurjopayStatus.statusByCode(spCode));
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
	 * This method is used for making payment.
	 * 
	 * @param Payment request object. See the shurjoPay version-2 integration documentation(beta).docx for details.
	 * @return Payment response object contains redirect URL to reach payment page, order id to verify order in shurjoPay.
	 * @throws ShurjopayException while merchant username and password is invalid.
	 * @throws ShurjopayPaymentException while {@link PaymentReq} is not prepared properly or {@link HttpClient} exception
	 */
	public PaymentRes makePayment(PaymentReq req) throws ShurjopayException {
		PaymentRes paymentRes;
		try {
			if (isAuthenticationRequired()) authToken = authenticate();
			
			String requestBody = prepareReqBody(getDefaultInfo(req));
			HttpRequest request = postRequest(requestBody, Endpoints.MAKE_PMNT.title());
			HttpResponse<Supplier<PaymentRes>> response = getClient().send(request, new JsonBodyHandler<>(PaymentRes.class));
			
			paymentRes = response.body().get();
			int spCode = paymentRes.getSpCode();
			if(paymentRes.getPaymentUrl().isBlank()) throw new ShurjopayException("Code: "+ spCode +" Message: " + ShurjopayStatus.statusByCode(String.valueOf(spCode)));
			
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
	 * This method is used for verifying order by order id which could be get by payment response object
	 * 
	 * @param orderId
	 * @return order object if order verified successfully
	 * @throws ShurjopayException while merchant user name and password is invalid.
	 * @throws ShurjopayVerificationException while order id is invalid or payment is not initiated properly or {@link HttpClient} exception
	 */
	public VerifiedPayment verifyPayment(String orderId) throws ShurjopayException {
		try {
			if (isAuthenticationRequired()) authToken = authenticate();
			
			Map<String, String> verifiedPaymentReq = new HashMap<>();
			verifiedPaymentReq.put("order_id", orderId);

			String requestBody = prepareReqBody(verifiedPaymentReq);
			HttpRequest request = postRequest(requestBody, Endpoints.VERIFIED_ORDER.title(), true);
			
			HttpResponse<Supplier<VerifiedPayment[]>> response = getClient().send(request, new JsonBodyHandler<>(VerifiedPayment[].class));
			VerifiedPayment verifiedPaymentRes = response.body().get()[0];
			
			spCode = verifiedPaymentRes.getSpStatusCode();
			if (!spCode.equals(ShurjopayStatus.SHURJOPAY_SUCCESS.code())) throw new ShurjopayException("Code: "+ spCode +" Message: " + ShurjopayStatus.statusByCode(String.valueOf(spCode)));
			
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
	 * This method is used for checking successfully paid order status by order id which could be get after verifying order
	 * 
	 * @param orderId
	 * @return order object if order verified successfully.
	 * @throws ShurjopayException while merchant user name and password is invalid.
	 * @throws ShurjopayPaymentStatusException while order id is invalid or payment is not initiated properly or {@link HttpClient} exception
	 */
	public VerifiedPayment checkPaymentStatus(String orderId) throws ShurjopayException {
		try {
			if (isAuthenticationRequired()) authToken = authenticate();
			
			Map<String, String> orderMap = new HashMap<String, String>();
			orderMap.put("order_id", orderId);
			
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
	
	private boolean isAuthenticationRequired() {
		return !((Objects.nonNull(authToken) && !isTokenExpired(authToken)));
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
	 * @param {@link Shurjopay}
	 * @return true if token is expired, otherwise return false
	 */
	private boolean isTokenExpired(ShurjopayToken shurjoPayTokenRes) {
		DateTimeFormatter format = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("yyyy-MM-dd hh:mm:ssa").toFormatter(Locale.US);

		LocalDateTime createdAt = LocalDateTime.parse(shurjoPayTokenRes.getTokenCreatedTime(), format);
		int diff = (int) ChronoUnit.SECONDS.between(createdAt, LocalDateTime.now());
		
		return shurjoPayTokenRes.getExpiredTimeInSecond() <= diff ? true : false;
	}
	
	/**
	 * This method is used to prepare Payment request object with default values such as
	 * <code>
	 * Return URL, Cancel URL, JWT token, Client IP address, Merchant Store Id
	 * </code>
	 * @param {@link PaymentReq}
	 * @return {@link PaymentReq} with shurjoPay's default values
	 */
	private PaymentReq getDefaultInfo(PaymentReq paymentReq) {
		String callBackUrl = spConfig.getCallbackUrl();
		paymentReq.setReturnUrl(callBackUrl);
		paymentReq.setCancelUrl(callBackUrl);
		paymentReq.setAuthToken(authToken.getToken());
		paymentReq.setStoreId(authToken.getStoreId());
		
		try {
			paymentReq.setClientIp(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			log.warn("Client ip address is not found. Setting default ip address..", e);
			paymentReq.setClientIp(DEFAULT_IP);
		}
		
		return paymentReq;
	}

	private HttpRequest postRequest(String httpBody, String endPoint) {
		
		return HttpRequest.newBuilder(URI.create(spConfig.getApiBaseUrl().concat(endPoint)))
						  .POST(HttpRequest.BodyPublishers.ofString(httpBody)).header("Content-Type", "application/json").build();
	}

	private HttpRequest postRequest(String httpBody, String endPoint, boolean isAuthHead) {
		
		return HttpRequest.newBuilder(URI.create(spConfig.getApiBaseUrl().concat(endPoint)))
						  .header(AUTH_KEYWORD, getFormattedToken(authToken.getToken(), authToken.getTokenType()))
						  .POST(HttpRequest.BodyPublishers.ofString(httpBody)).header("Content-Type", "application/json").build();
	}

	private String getFormattedToken(String token, String tokenType) {
		
		return tokenType.concat(WHITE_SPACE).concat(token);
	}
	
	private ShurjopayConfig getShurjoPayConfig() {
		Properties spProps = PropertiesReader.instance().getProperties();
		
		ShurjopayConfig spConfig = new ShurjopayConfig();
		spConfig.setUsername(spProps.getProperty(ShurjopayConfigKeys.SP_USER.name()));
		spConfig.setPassword(spProps.getProperty(ShurjopayConfigKeys.SP_PASS.name()));
		spConfig.setApiBaseUrl(spProps.getProperty(ShurjopayConfigKeys.SHURJOPAY_API.name()));
		spConfig.setCallbackUrl(spProps.getProperty(ShurjopayConfigKeys.SP_CALLBACK.name()));
		
		return spConfig;
	}
}
