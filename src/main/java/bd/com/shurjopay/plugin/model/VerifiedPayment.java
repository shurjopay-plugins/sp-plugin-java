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
 * This model is used for response when user want to verify order using order id.
 * It will represent response of verify order.
 * 
 * @author Al - Amin
 * @since 2022-06-16
 */
@Data @Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AllArgsConstructor @NoArgsConstructor
public class VerifiedPayment extends Payment{
	
	/**
	 * shurjoPay system generated id.
	 */
	private Long id;
	
	/**
	 * shurjoPay generates a transaction id against a payment.
	 */
	@JsonProperty("order_id")
	private String spTxnId;
	
	/**
	 * Payment amount in BDT/USD currency.
	 */
	private Double amount;
	
	/**
	 * Payable  amount after calculation.
	 */
	@JsonProperty("payable_amount")
	private Double payableAmount;
	
	/**
	 * Discount amount for the merchant.
	 */
	@JsonProperty("discsount_amount")
	private Double discountAmount;
	
	/**
	 * Discount percent(%) for the merchant.
	 */
	@JsonProperty("disc_percent")
	private Double discpercent;
	
	/**
	 * USD amount in USD currency.
	 */
	@JsonProperty("usd_amt")
	private Double usdAmt;
	
	/**
	 * USD dollar rate in current scenario. 
	 */
	@JsonProperty("usd_rate")
	private Double usdRate;
	
	/**
	 * Received amount.
	 */
	@JsonProperty("received_amount")
	private Double receivedAmt;
	
	/**
	 * Card holder name.
	 */
	@JsonProperty("card_holder_name")
	private String cardHolder;
	
	/**
	 * Card number in front of debit/credit or other card.
	 */
	@JsonProperty("card_number")
	private String cardNumber;
	
	/**
	 * Card holder's contact number
	 */
	@JsonProperty("phone_no")
	private String phoneNo;
	
	/**
	 * Bank transaction id.
	 */
	@JsonProperty("bank_trx_id")
	private String bankTxnId;
	
	/**
	 * shurjoPay's generated invoice number.
	 */
	@JsonProperty("invoice_no")
	private String invoiceNo;
	
	/**
	 * Bank transaction status. see detail {@link ShurjopayStatus}
	 */
	@JsonProperty("bank_status")
	private String bankStatus;
	
	/**
	 * shurjoPay's won status code. see details {@link ShurjopayStatus}
	 */
	@JsonProperty("sp_code")
	private String spStatusCode;
	
	/**
	 * shurjoPay's status message. see details {@link ShurjopayStatus}
	 */
	@JsonProperty("sp_message")
	private String spStatusMsg;
	
	/** Sometime customer have to send additional data like studentId 
	 * or any other information which have not any field given by shurjoPay.
	 * value1 is used for customer's additional info if needed
	 */
	private String value1;
	
	/** Sometime customer have to send additional data like studentId 
	 * or any other information which have not any field given by shurjoPay.
	 * value2 is used for customer's additional info if needed
	 */
	private String value2;
	
	/** Sometime customer have to send additional data like studentId 
	 * or any other information which have not any field given by shurjoPay.
	 * value3 is used for customer's additional info if needed
	 */
	private String value3;
	
	/** Sometime customer have to send additional data like studentId 
	 * or any other information which have not any field given by shurjoPay.
	 * value4 is used for customer's additional info if needed
	 */
	private String value4;
	
	/**
	 * Bank transaction status. See detail {@link ShurjopayStatus}
	 */
	@JsonProperty("transaction_status")
	private String txnStatus;
	
	/** e.g. EBL Visa, Bkash, EBL Master etc. */
	@JsonProperty("method")
	private String paymentMethod;
	
	/**
	 * shurjoPay's transaction time.
	 */
	@JsonProperty("date_time")
	private String txnTime;
}
