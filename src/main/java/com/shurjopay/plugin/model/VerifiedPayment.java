package com.shurjopay.plugin.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * This model is used for response when user want to verify order using order id.
 * It will represent response of verify order.
 * 
 * @author Al - Amin
 * @since 2022-06-16
 */
@Data
@Accessors(chain = true)
public class VerifiedPayment implements Serializable{
	private static final long serialVersionUID = 1970085707716491151L;
	
	private Long id;
	
	@JsonProperty("order_id")
	private String orderId;
	
	private String currency;
	private Double amount;
	
	@JsonProperty("payable_amount")
	private Double payableAmount;
	
	@JsonProperty("discsount_amount")
	private Double discountAmount;
	
	@JsonProperty("disc_percent")
	private Double discpercent;
	
	@JsonProperty("usd_amt")
	private Double usdAmt;
	
	@JsonProperty("usd_rate")
	private Double usdRate;
	
	@JsonProperty("recived_amount")
	private Double receivedAmt;
	
	@JsonProperty("card_holder_name")
	private String cardHolder;
	
	@JsonProperty("card_number")
	private String cardNumber;
	
	@JsonProperty("phone_no")
	private String phoneNo;
	
	@JsonProperty("bank_trx_id")
	private String bankTxnId;
	
	@JsonProperty("invoice_no")
	private String invoiceNo;
	
	@JsonProperty("bank_status")
	private String bankStatus;
	
	@JsonProperty("customer_order_id")
	private String customerOrderId;
	
	@JsonProperty("sp_code")
	private String spStatusCode;
	
	@JsonProperty("sp_message")
	private String spStatusMsg;
	
	@JsonProperty("name")
	private String customerName;
	
	@JsonProperty("email")
	private String customerEmail;
	
	@JsonProperty("address")
	private String customerAddress;	
	
	@JsonProperty("city")
	private String customerCity;
	
	/** Sometime customer have to send additional data like studentId 
	 * or any other information which have not any field given by shurjoPay.
	 * value1, value2, value3, value4 is used for customer's additional info if needed
	 */
	private String value1;	
	private String value2;	
	private String value3;	
	private String value4;
	
	@JsonProperty("transaction_status")
	private String txnStatus;
	
	/** e.g. EBL Visa, Bkash, EBL Master etc. */
	@JsonProperty("method")
	private String paymentMethod;
	
	@JsonProperty("date_time")
	private String txnTime;
}
