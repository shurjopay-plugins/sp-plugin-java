package com.shurjopay.plugin.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Al - Amin
 * @since 2022-06-13
 */
public class ShurjoPayToken implements Serializable {
	private static final long serialVersionUID = 1960827183789531739L;

	private String token;

	@JsonProperty("execute_url")
	private String executeUrl;

	@JsonProperty("token_type")
	private String tokenType;

	@JsonProperty("sp_code")
	private String spCode;

	@JsonProperty("token_create_time")
	private String tokenCreateTime;

	@JsonProperty("expires_in")
	private Integer expiresIn;

	@JsonProperty("store_id")
	private Integer storeId;

	private String message;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getExecuteUrl() {
		return executeUrl;
	}

	public void setExecuteUrl(String executeUrl) {
		this.executeUrl = executeUrl;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getSpCode() {
		return spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public String getTokenCreateTime() {
		return tokenCreateTime;
	}

	public void setTokenCreateTime(String tokenCreateTime) {
		this.tokenCreateTime = tokenCreateTime;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
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
		return "TokenRes [token=" + token + ", executeUrl=" + executeUrl + ", tokenType=" + tokenType + ", spCode="
				+ spCode + ", tokenCreateTime=" + tokenCreateTime + ", expiresIn=" + expiresIn + ", storeId=" + storeId
				+ ", message=" + message + "]";
	}
}
