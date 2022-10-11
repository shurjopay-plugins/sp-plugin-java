package com.shurjopay.plugin.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This model is used for making payment.
 * Model is not required to set returnUrl and cancelUrl.
 * For this purpose, set callback-URL in shurjopay.properties. It will be used for concerned fields.
 * 
 * @author Al - Amin
 * @since 2022-06-16
 */
public class PaymentReq implements Serializable {
	private static final long serialVersionUID = 4191752321718444127L;

	private String prefix;

	@JsonProperty("token")
	private String authToken;

	@JsonProperty("return_url")
	private String returnUrl;

	@JsonProperty("cancel_url")
	private String cancelUrl;

	@JsonProperty("store_id")
	private Integer storeId;

	private Double amount;

	@JsonProperty("order_id")
	private String orderId;

	private String currency;

	@JsonProperty("customer_name")
	private String customerName;

	@JsonProperty("customer_address")
	private String customerAddress;

	@JsonProperty("customer_phone")
	private String customerPhone;

	@JsonProperty("customer_city")
	private String customerCity;

	@JsonProperty("customer_post_code")
	private String customerPostCode;
	
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

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getCancelUrl() {
		return cancelUrl;
	}

	public void setCancelUrl(String cancelUrl) {
		this.cancelUrl = cancelUrl;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	public String getCustomerPostCode() {
		return customerPostCode;
	}

	public void setCustomerPostCode(String customerPostCode) {
		this.customerPostCode = customerPostCode;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getShippingCity() {
		return shippingCity;
	}

	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}

	public String getShippingCountry() {
		return shippingCountry;
	}

	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}

	public String getShippingReceiverName() {
		return shippingReceiverName;
	}

	public void setShippingReceiverName(String shippingReceiverName) {
		this.shippingReceiverName = shippingReceiverName;
	}

	public String getShippingPhone() {
		return shippingPhone;
	}

	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	@Override
	public String toString() {
		return "PaymentReq [prefix=" + prefix + ", authToken=" + authToken + ", returnUrl=" + returnUrl + ", cancelUrl="
				+ cancelUrl + ", storeId=" + storeId + ", amount=" + amount + ", orderId=" + orderId + ", currency="
				+ currency + ", customerName=" + customerName + ", customerAddress=" + customerAddress
				+ ", customerPhone=" + customerPhone + ", customerCity=" + customerCity + ", customerPostCode="
				+ customerPostCode + ", customerEmail=" + customerEmail + ", shippingAddress=" + shippingAddress
				+ ", shippingCity=" + shippingCity + ", shippingCountry=" + shippingCountry + ", shippingReceiverName="
				+ shippingReceiverName + ", shippingPhone=" + shippingPhone + ", clientIp=" + clientIp + "]";
	}
}
