package com.shurjomukhi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shurjomukhi.constants.ShurjopayStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * After verifying payment response.
 * @author Al - Amin
 * @since 2022-06-16
 */
@Data @Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AllArgsConstructor @NoArgsConstructor
public class VerifiedPayment extends Payment{
	
	/** Shurjopay system generated id.*/
	private Long id;
	
	/** ShurjoPay transaction id against a payment transaction.*/
	@JsonProperty("order_id")
	private String spTxnId;
	
	/** Payment amount in BDT/USD currency.*/
	private Double amount;
	
	/** Payable  amount after calculation.*/
	@JsonProperty("payable_amount")
	private Double payableAmount;
	
	/** Discount amount for the merchant.*/
	@JsonProperty("discsount_amount")
	private Double discountAmount;
	
	/** Discount percent(%) for the merchant.*/
	@JsonProperty("disc_percent")
	private Double discpercent;
	
	/** USD amount in USD currency.*/
	@JsonProperty("usd_amt")
	private Double usdAmt;
	
	/** USD dollar rate in current scenario.*/
	@JsonProperty("usd_rate")
	private Double usdRate;
	
	/** Received amount.*/
	@JsonProperty("received_amount")
	private Double receivedAmt;
	
	/** Card holder name.*/
	@JsonProperty("card_holder_name")
	private String cardHolder;
	
	/** Card number of debit/credit or other card.*/
	@JsonProperty("card_number")
	private String cardNumber;
	
	/** Card holder's contact number*/
	@JsonProperty("phone_no")
	private String phoneNo;
	
	/** Bank transaction id.*/
	@JsonProperty("bank_trx_id")
	private String bankTxnId;
	
	/** Shurjopay invoice number against a payment.*/
	@JsonProperty("invoice_no")
	private String invoiceNo;
	
	/** Bank transaction status.*/
	@JsonProperty("bank_status")
	private String bankStatus;
	
	/** Shurjopay reserved status code. See more.. {@link ShurjopayStatus}*/
	@JsonProperty("sp_code")
	private String spStatusCode;
	
	/** Shurjopay status message. see more.. {@link ShurjopayStatus}*/
	@JsonProperty("sp_message")
	private String spStatusMsg;
	
	/** Bank transaction status.*/
	@JsonProperty("transaction_status")
	private String txnStatus;
	
	/** e.g. EBL Visa, Bkash, EBL Master etc. */
	@JsonProperty("method")
	private String paymentMethod;
	
	/** Shurjopay transaction time.*/
	@JsonProperty("date_time")
	private String txnTime;
	
	/** Sometime customer want to send additional information
	 * value1 is used for customer's additional information if needed.
	 */
	private String value1;
	
	/** Sometime customer have to send additional information.
	 * value2 is used for customer's additional information if needed.
	 */
	private String value2;
	
	/** Sometime customer have to send additional information.
	 * value3 is used for customer's additional information if needed.
	 */
	private String value3;
	
	/** Sometime customer have to send additional information.
	 * value4 is used for customer's additional information if needed.
	 */
	private String value4;
}
