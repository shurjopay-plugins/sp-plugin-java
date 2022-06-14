package com.shurjopay.plugin.enums;

public enum EndPoints {

	GET_TOKEN("get_token"),
	SECRET_PAY("secret-pay"),
	VERIFICATION("verification"),
	PMNT_STAT("payment-status");
	
	private String value;

	private EndPoints(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
