package com.shurjopay.plugin.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This model is used for response when user want to verify order using order id.
 * It will represent response of verify order.
 * 
 * @author Al - Amin
 * @since 2022-06-16
 */
public class VerifiedPaymentRes implements Serializable{
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
	
	@JsonProperty("sp_massage")
	private String spStatusMsg;
	
	@JsonProperty("name")
	private String customerName;
	
	@JsonProperty("email")
	private String customerEmail;
	
	@JsonProperty("address")
	private String customerAddress;	
	
	@JsonProperty("city")
	private String customerCity;
	
	/** Sometime customer have to send additional data like studentId or any other information which have not any field given by shurjoPay.
	 * value1, value2, value3, value4 is used for customer's additional info if needed*/
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getPayableAmount() {
		return payableAmount;
	}

	public void setPayableAmount(Double payableAmount) {
		this.payableAmount = payableAmount;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Double getDiscpercent() {
		return discpercent;
	}

	public void setDiscpercent(Double discpercent) {
		this.discpercent = discpercent;
	}

	public Double getUsdAmt() {
		return usdAmt;
	}

	public void setUsdAmt(Double usdAmt) {
		this.usdAmt = usdAmt;
	}

	public Double getUsdRate() {
		return usdRate;
	}

	public void setUsdRate(Double usdRate) {
		this.usdRate = usdRate;
	}

	public Double getReceivedAmt() {
		return receivedAmt;
	}

	public void setReceivedAmt(Double receivedAmt) {
		this.receivedAmt = receivedAmt;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getBankTxnId() {
		return bankTxnId;
	}

	public void setBankTxnId(String bankTxnId) {
		this.bankTxnId = bankTxnId;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getBankStatus() {
		return bankStatus;
	}

	public void setBankStatus(String bankStatus) {
		this.bankStatus = bankStatus;
	}

	public String getCustomerOrderId() {
		return customerOrderId;
	}

	public void setCustomerOrderId(String customerOrderId) {
		this.customerOrderId = customerOrderId;
	}

	public String getSpStatusCode() {
		return spStatusCode;
	}

	public void setSpStatusCode(String spStatusCode) {
		this.spStatusCode = spStatusCode;
	}

	public String getSpStatusMsg() {
		return spStatusMsg;
	}

	public void setSpStatusMsg(String spStatusMsg) {
		this.spStatusMsg = spStatusMsg;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	public String getValue4() {
		return value4;
	}

	public void setValue4(String value4) {
		this.value4 = value4;
	}

	public String getTxnStatus() {
		return txnStatus;
	}

	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getTxnTime() {
		return txnTime;
	}

	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}

	@Override
	public String toString() {
		return "VerifiedPaymentRes [id=" + id + ", orderId=" + orderId + ", currency=" + currency + ", amount=" + amount
				+ ", payableAmount=" + payableAmount + ", discountAmount=" + discountAmount + ", discpercent="
				+ discpercent + ", usdAmt=" + usdAmt + ", usdRate=" + usdRate + ", receivedAmt=" + receivedAmt
				+ ", cardHolder=" + cardHolder + ", cardNumber=" + cardNumber + ", phoneNo=" + phoneNo + ", bankTxnId="
				+ bankTxnId + ", invoiceNo=" + invoiceNo + ", bankStatus=" + bankStatus + ", customerOrderId="
				+ customerOrderId + ", spStatusCode=" + spStatusCode + ", spStatusMsg=" + spStatusMsg
				+ ", customerName=" + customerName + ", customerEmail=" + customerEmail + ", customerAddress="
				+ customerAddress + ", customerCity=" + customerCity + ", value1=" + value1 + ", value2=" + value2
				+ ", value3=" + value3 + ", value4=" + value4 + ", txnStatus=" + txnStatus + ", paymentMethod="
				+ paymentMethod + ", txnTime=" + txnTime + "]";
	}
}
