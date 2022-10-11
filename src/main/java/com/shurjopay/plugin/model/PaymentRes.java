package com.shurjopay.plugin.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * After making a payment with shurjoPay this model will be the response object
 * @author Al - Amin
 * @since 2022-06-16
 */
public class PaymentRes implements Serializable{
	private static final long serialVersionUID = -4989310354723281491L;
	
	@JsonProperty("checkout_url")
	private String paymentUrl;
	
	private String amount;
	private String currency;
	
	@JsonProperty("sp_order_id")
	private String spOrderId;
	
	@JsonProperty("customer_order_id")
	private String customerOrderId;
	
	@JsonProperty("customer_name")
	private String customerName;
	
	@JsonProperty("customer_address")
	private String customerAddress;
	
	@JsonProperty("customer_phone")
	private String customerPhone;
	
	@JsonProperty("customer_city")
	private String customerCity;
	
	@JsonProperty("customer_email")
	private String customerEmail;
	
	/** Shipping related fields are used to get information of Ecommerce's transactions */
	@JsonProperty("shipping_address")
	private String shippingAddress;
	
	@JsonProperty("shipping_city")
	private String shippingCity;
	
	@JsonProperty("shipping_country")
	private String shippingCountry;
	
	@JsonProperty("received_person_name")
	private String shippingReceiverName;
	
	@JsonProperty("shipping_phone_number")
	private String shippingPhone;
	
	@JsonProperty("client_ip")
	private String clientIp;
	
	/** This field is used for presenting payment category. e.g. sale */
	@JsonProperty("intent")
	private String paymentCategory;
	
	/** Transaction status of shurjoPay. e.g. Initiated, Failed, Canceled */
	private String transactionStatus;
	
	@JsonProperty("sp_code")
	private Integer spCode;
	
	private String message;

	public String getPaymentUrl() {
		return paymentUrl;
	}

	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
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

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
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

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getPaymentCategory() {
		return paymentCategory;
	}

	public void setPaymentCategory(String paymentCategory) {
		this.paymentCategory = paymentCategory;
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
}
