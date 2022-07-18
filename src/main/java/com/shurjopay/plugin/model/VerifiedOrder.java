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
public class VerifiedOrder implements Serializable{
	private static final long serialVersionUID = 1970085707716491151L;
	
	private Long id;
	
	@JsonProperty("order_id")
	private String orderId;
	
	private String currency;
	private Integer amount;
	
	@JsonProperty("payable_amount")
	private Integer payableAmount;
	
	@JsonProperty("discsount_amount")
	private Integer discountAmount;
	
	@JsonProperty("disc_percent")
	private Integer discpercent;
	
	@JsonProperty("usd_amt")
	private Integer usdAmt;
	
	@JsonProperty("usd_rate")
	private Integer usdRate;
	
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
	private String statusCode;
	
	@JsonProperty("sp_massage")
	private String statusMsg;
	
	private String name;	
	private String email;	
	private String address;	
	private String city;
	private String value1;	
	private String value2;	
	private String value3;	
	private String value4;
	
	@JsonProperty("transaction_status")
	private String txnStatus;
	
	private String method;
	
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getPayableAmount() {
		return payableAmount;
	}

	public void setPayableAmount(Integer payableAmount) {
		this.payableAmount = payableAmount;
	}

	public Integer getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Integer discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Integer getDiscpercent() {
		return discpercent;
	}

	public void setDiscpercent(Integer discpercent) {
		this.discpercent = discpercent;
	}

	public Integer getUsdAmt() {
		return usdAmt;
	}

	public void setUsdAmt(Integer usdAmt) {
		this.usdAmt = usdAmt;
	}

	public Integer getUsdRate() {
		return usdRate;
	}

	public void setUsdRate(Integer usdRate) {
		this.usdRate = usdRate;
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

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getTxnTime() {
		return txnTime;
	}

	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}

	@Override
	public String toString() {
		return "VerifiedOrder [id=" + id + ", orderId=" + orderId + ", currency=" + currency + ", amount=" + amount
				+ ", payableAmount=" + payableAmount + ", discountAmount=" + discountAmount + ", discpercent="
				+ discpercent + ", usdAmt=" + usdAmt + ", usdRate=" + usdRate + ", cardHolder=" + cardHolder
				+ ", cardNumber=" + cardNumber + ", phoneNo=" + phoneNo + ", bankTxnId=" + bankTxnId + ", invoiceNo="
				+ invoiceNo + ", bankStatus=" + bankStatus + ", customerOrderId=" + customerOrderId + ", statusCode="
				+ statusCode + ", statusMsg=" + statusMsg + ", name=" + name + ", email=" + email + ", address="
				+ address + ", city=" + city + ", value1=" + value1 + ", value2=" + value2 + ", value3=" + value3
				+ ", value4=" + value4 + ", txnStatus=" + txnStatus + ", method=" + method + ", txnTime=" + txnTime
				+ "]";
	}
	
}
