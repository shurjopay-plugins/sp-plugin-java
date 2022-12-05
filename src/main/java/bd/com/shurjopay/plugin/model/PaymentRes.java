package bd.com.shurjopay.plugin.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import bd.com.shurjopay.plugin.constants.ShurjopayStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * After making a payment with shurjoPay this model will be the response object
 * @author Al - Amin
 * @since 2022-06-16
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRes extends Payment{
	
	/**
	 * Secret Payment URL by which customer can pay.
	 */
	@JsonProperty("checkout_url")
	private String paymentUrl;
	
	/**
	 * Payment amount in BDT/USD currency.
	 */
	private String amount;
	
	/**
	 * shurjoPay generates a transaction id against a payment.
	 */
	@JsonProperty("sp_order_id")
	private String spOrderId;
	
	/** 
	 * This field is used for presenting payment category. e.g. sale
	 */
	@JsonProperty("intent")
	private String paymentCategory;
	
	/**
	 *  Transaction status of shurjoPay. e.g. Initiated, Failed, Canceled etc.
	 */
	private String transactionStatus;
	
	/**
	 * shurjoPay's won status code. see details {@link ShurjopayStatus}
	 */
	@JsonProperty("sp_code")
	private int spCode;
	
	/**
	 * shurjoPay's status message. see details {@link ShurjopayStatus}
	 */
	private String message;
}
