package com.shurjopay.plugin.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VerifiedOrder implements Serializable{
	private static final long serialVersionUID = 1970085707716491151L;
	
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
	
	private String method;
	
	@JsonProperty("sp_massage")
	private String spMsg;
	
	@JsonProperty("sp_code")
	private String spCode;
	
	private String name;	
	private String email;	
	private String address;	
	private String city;
	private String value1;	
	private String value2;	
	private String value3;	
	private String value4;
	
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
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getSpMsg() {
		return spMsg;
	}
	public void setSpMsg(String spMsg) {
		this.spMsg = spMsg;
	}
	public String getSpCode() {
		return spCode;
	}
	public void setSpCode(String spCode) {
		this.spCode = spCode;
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
	@Override
	public String toString() {
		return "VerifiedOrder [orderId=" + orderId + ", currency=" + currency + ", amount=" + amount
				+ ", payableAmount=" + payableAmount + ", discountAmount=" + discountAmount + ", discpercent="
				+ discpercent + ", usdAmt=" + usdAmt + ", usdRate=" + usdRate + ", method=" + method + ", spMsg="
				+ spMsg + ", spCode=" + spCode + ", name=" + name + ", email=" + email + ", address=" + address
				+ ", city=" + city + ", value1=" + value1 + ", value2=" + value2 + ", value3=" + value3 + ", value4="
				+ value4 + "]";
	}
	
}
