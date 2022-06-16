package com.shurjopay.plugin;

public enum EndPoints {

	TOKEN("get_token"),
	MAKE_PMNT("secret-pay"),
	VERIFIED_ORDER("verification"),
	PMNT_STAT("payment-status");
	
	private String value;

	private EndPoints(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
