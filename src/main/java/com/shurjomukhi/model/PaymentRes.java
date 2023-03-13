package com.shurjomukhi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shurjomukhi.ShurjopayStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * Shurjopay initiated payment response.
 * @author Al - Amin
 * @since 2022-06-16
 */
@Data @Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AllArgsConstructor @NoArgsConstructor
public class PaymentRes extends Payment{
	
	/** Secret Payment URL by which customer can pay.*/
	@JsonProperty("checkout_url")
	private String paymentUrl;
	
	/** Payment amount in BDT/USD currency.*/
	private String amount;
	
	/** shurjoPay generates a transaction id against a payment.*/
	@JsonProperty("sp_order_id")
	private String spTxnId;
	
	/** Shurjopay payment category. e.g. sale, e-commerce*/
	@JsonProperty("intent")
	private String paymentCategory;
	
	/** Transaction status of shurjopay. e.g. Initiated, Failed, Canceled etc.*/
	private String transactionStatus;
	
	/** Shurjopay reserved status code. See more.. {@link ShurjopayStatus}*/
	@JsonProperty("sp_code")
	private int spCode;
}
