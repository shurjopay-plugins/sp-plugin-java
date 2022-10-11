package com.shurjopay.plugin;

/**
 * 
 * @author Al-Amin
 *
 */
public enum Endpoints {
	TOKEN("get_token"),
	MAKE_PMNT("secret-pay"),
	VERIFIED_ORDER("verification"),
	PMNT_STAT("payment-status");
	
	private String endpointValue;

	private Endpoints(String endpointValue) {
		this.endpointValue = endpointValue;
	}

	public String getValue() {
		return endpointValue;
	}
	
}
