package com.shurjopay.plugin.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckoutRes implements Serializable{
	private static final long serialVersionUID = -4989310354723281491L;
	
	@JsonProperty("checkout_url")
	private String checkoutUrl;
	
	private String amount;
	
	private String currency;
	
	@JsonProperty("sp_order_id")
	private String spOrderId;
	
	@JsonProperty("customer_order_id")
	private String customerOrderId;
	
	@JsonProperty("customer_name")
	private String customerName;
	
	@JsonProperty("customer_address")
	private String customerAddr;
	
	@JsonProperty("customer_phone")
	private String customerPhn;
	
	@JsonProperty("customer_city")
	private String customerCity;
	
	@JsonProperty("customer_email")
	private String customerEmail;
	
	@JsonProperty("client_ip")
	private String clintIp;
	
	private String intent;
	
	private String transactionStatus;
	
	@JsonProperty("sp_code")
	private Integer spCode;
	
	private String message;

	public String getCheckoutUrl() {
		return checkoutUrl;
	}

	public void setCheckoutUrl(String checkoutUrl) {
		this.checkoutUrl = checkoutUrl;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSpOrderId() {
		return spOrderId;
	}

	public void setSpOrderId(String spOrderId) {
		this.spOrderId = spOrderId;
	}

	public String getCustomerOrderId() {
		return customerOrderId;
	}

	public void setCustomerOrderId(String customerOrderId) {
		this.customerOrderId = customerOrderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddr() {
		return customerAddr;
	}

	public void setCustomerAddr(String customerAddr) {
		this.customerAddr = customerAddr;
	}

	public String getCustomerPhn() {
		return customerPhn;
	}

	public void setCustomerPhn(String customerPhn) {
		this.customerPhn = customerPhn;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getClintIp() {
		return clintIp;
	}

	public void setClintIp(String clintIp) {
		this.clintIp = clintIp;
	}

	public String getIntent() {
		return intent;
	}

	public void setIntent(String intent) {
		this.intent = intent;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	
	public Integer getSpCode() {
		return spCode;
	}

	public void setSpCode(Integer spCode) {
		this.spCode = spCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "CheckoutRes [checkoutUrl=" + checkoutUrl + ", amount=" + amount + ", currency=" + currency
				+ ", spOrderId=" + spOrderId + ", customerOrderId=" + customerOrderId + ", customerName=" + customerName
				+ ", customerAddr=" + customerAddr + ", customerPhn=" + customerPhn + ", customerCity=" + customerCity
				+ ", customerEmail=" + customerEmail + ", clintIp=" + clintIp + ", intent=" + intent
				+ ", transactionStatus=" + transactionStatus + ", spCode=" + spCode + ", message=" + message + "]";
	}

}
