package bd.com.shurjopay.plugin.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * This model is used internally to authenticate merchant for accessing shurjoPay's APIs.
 * 
 * @author Al - Amin
 * @since 2022-06-13
 */
@Data
@Accessors(chain = true)
public class ShurjopayToken implements Serializable {
	private static final long serialVersionUID = 1960827183789531739L;

	private String token;

	/** e.g. https://engine.shurjopayment.com/api/secret-pay */
	@JsonProperty("execute_url")
	private String spPaymentApi;

	@JsonProperty("token_type")
	private String tokenType;

	@JsonProperty("sp_code")
	private String spStatusCode;

	/** shurjoPay gives time in BST time zone */
	@JsonProperty("token_create_time")
	private String tokenCreatedTime;

	@JsonProperty("expires_in")
	private Integer expiredTimeInSecond;

	@JsonProperty("store_id")
	private Integer storeId;

	private String message;
}