package com.shurjopay.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shurjopay.constants.ShurjopayStatus;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Shurjopay authentication response.
 * 
 * @author Al - Amin
 * @since 2022-06-13
 */
@Data @Accessors(chain = true)
public class ShurjopayToken implements Serializable {
	private static final long serialVersionUID = 1960827183789531739L;

	/** JWT token to authenticate merchant.*/
	private String token;

	/** Secret Payment URL by which customer can pay.*/
	@JsonProperty("execute_url")
	private String spPaymentApi;

	/** JWT token type. i.e. Bearer, Basic etc.*/
	@JsonProperty("token_type")
	private String tokenType;

	/** ShurjoPay reserved status code. See more.. {@link ShurjopayStatus}*/
	@JsonProperty("sp_code")
	private String spStatusCode;

	/** 
	 * JWT token created time.
	 * Shurjopay provides date time in BST time zone.
	 */
	@JsonProperty("token_create_time")
	private String tokenCreatedTime;

	/** JWT token expired time in second.*/
	@JsonProperty("expires_in")
	private Integer expiredTimeInSecond;

	/** Merchant's registered store id on shurjopay system.*/
	@JsonProperty("store_id")
	private Integer storeId;

	/** Shurjopay status message. See more.. {@link ShurjopayStatus}*/
	private String message;
}