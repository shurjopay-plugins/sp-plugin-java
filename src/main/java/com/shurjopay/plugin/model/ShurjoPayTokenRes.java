package com.shurjopay.plugin.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This model is used internally to authenticate merchant for accessing shurjoPay's APIs.
 * 
 * @author Al - Amin
 * @since 2022-06-13
 */
public class ShurjoPayTokenRes implements Serializable {
	private static final long serialVersionUID = 1960827183789531739L;

	private String token;

	/** e.g. https://engine.shurjopayment.com/api/secret-pay */
	@JsonProperty("execute_url")
	private String spPaymentApi;

	@JsonProperty("token_type")
	private String tokenType;

	@JsonProperty("sp_code")
	private String spStatusCode;

	/** shurjoPay gives time in BST time zone */
	@JsonProperty("token_create_time")
	private String tokenCreatedTime;

	@JsonProperty("expires_in")
	private Integer expiredTimeInSecond;

	@JsonProperty("store_id")
	private Integer storeId;

	private String message;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSpPaymentApi() {
		return spPaymentApi;
	}

	public void setSpPaymentApi(String spPaymentApi) {
		this.spPaymentApi = spPaymentApi;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getSpStatusCode() {
		return spStatusCode;
	}

	public void setSpStatusCode(String spStatusCode) {
		this.spStatusCode = spStatusCode;
	}

	public String getTokenCreatedTime() {
		return tokenCreatedTime;
	}

	public void setTokenCreatedTime(String tokenCreatedTime) {
		this.tokenCreatedTime = tokenCreatedTime;
	}

	public Integer getExpiredTimeInSecond() {
		return expiredTimeInSecond;
	}

	public void setExpiredTimeInSecond(Integer expiredTimeInSecond) {
		this.expiredTimeInSecond = expiredTimeInSecond;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ShurjoPayToken [token=" + token + ", spPaymentApi=" + spPaymentApi + ", tokenType=" + tokenType
				+ ", spStatusCode=" + spStatusCode + ", tokenCreatedTime=" + tokenCreatedTime + ", expiredTimeInSecond="
				+ expiredTimeInSecond + ", storeId=" + storeId + ", message=" + message + "]";
	}
}
