package bd.com.shurjopay.plugin.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import bd.com.shurjopay.plugin.constants.ShurjopayStatus;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * This model is used internally to authenticate merchant for accessing shurjoPay's APIs.
 * 
 * @author Al - Amin
 * @since 2022-06-13
 */
@Data @Accessors(chain = true)
public class ShurjopayToken implements Serializable {
	private static final long serialVersionUID = 1960827183789531739L;

	/**
	 *  JWT token to authenticate merchant.
	 */
	private String token;

	/**
	 * Secret Payment URL by which customer can pay.
	 */
	@JsonProperty("execute_url")
	private String spPaymentApi;

	/**
	 * JWT token type. i.e. Bearer, Basic etc.
	 */
	@JsonProperty("token_type")
	private String tokenType;

	/**
	 * shurjoPay's won status codes. see details {@link ShurjopayStatus}
	 */
	@JsonProperty("sp_code")
	private String spStatusCode;

	/** 
	 * JWT token created time.
	 * shurjoPay gives time in BST time zone 
	 */
	@JsonProperty("token_create_time")
	private String tokenCreatedTime;

	/**
	 * JWT token's expired time in second.
	 */
	@JsonProperty("expires_in")
	private Integer expiredTimeInSecond;

	/**
	 * Merchant's registered store id.
	 */
	@JsonProperty("store_id")
	private Integer storeId;

	/**
	 * shurjoPay's status message. see details {@link ShurjopayStatus}
	 */
	private String message;
}