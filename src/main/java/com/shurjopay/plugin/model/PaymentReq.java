package com.shurjopay.plugin.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This model is used for making payment.
 * Model is not required to set returnUrl and cancelUrl. For this purpose,
 * set callback-url value in shurjopay.properties. It will be used for concerned fields.
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
	private String customerAddr;

	@JsonProperty("customer_phone")
	private String customerPhn;

	@JsonProperty("customer_city")
	private String customerCity;

	@JsonProperty("customer_post_code")
	private String customerPostCode;

	@JsonProperty("client_ip")
	private String clintIp;

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

	public String getCustomerPostCode() {
		return customerPostCode;
	}

	public void setCustomerPostCode(String customerPostCode) {
		this.customerPostCode = customerPostCode;
	}

	public String getClintIp() {
		return clintIp;
	}

	public void setClintIp(String clintIp) {
		this.clintIp = clintIp;
	}

	@Override
	public String toString() {
		return "CheckoutReq [prefix=" + prefix + ", authToken=" + authToken + ", returnUrl=" + returnUrl
				+ ", cancelUrl=" + cancelUrl + ", storeId=" + storeId + ", amount=" + amount + ", orderId=" + orderId
				+ ", currency=" + currency + ", customerName=" + customerName + ", customerAddr=" + customerAddr
				+ ", customerPhn=" + customerPhn + ", customerCity=" + customerCity + ", customerPostCode="
				+ customerPostCode + ", clintIp=" + clintIp + "]";
	}

}
