package com.shurjomukhi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * Shurjopay initiating payment request payload.
 * 
 * @author Al - Amin
 * @since 2022-06-16
 */
@Data @Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AllArgsConstructor @NoArgsConstructor
public class PaymentReq extends Payment {

	/** Prefix of a merchant.*/
	private String prefix;

	/** JWT token to authenticate merchant.*/
	@JsonProperty("token")
	private String authToken;

	/** Callback URL to return verify payment id.*/
	@JsonProperty("return_url")
	private String returnUrl;

	/** Callback URL to cancel a shurjoPay payment*/
	@JsonProperty("cancel_url")
	private String cancelUrl;

	/** Merchant's registered store id */
	@JsonProperty("store_id")
	private int storeId;

	/** Payment amount in BDT/USD currency.*/
	private double amount;

	/** Customer's valid postal code.*/
	@JsonProperty("customer_post_code")
	private String customerPostCode;
}
