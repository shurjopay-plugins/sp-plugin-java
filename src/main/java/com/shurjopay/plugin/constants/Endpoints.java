package com.shurjopay.plugin.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This ENUM contains all of the end-points of shurjoPay
 * @author Al-Amin
 * @since 2022-07-30
 * @modified 2022-10-12
 */
@AllArgsConstructor
public enum Endpoints {
	TOKEN("get_token"),
	MAKE_PMNT("secret-pay"),
	VERIFIED_ORDER("verification"),
	PMNT_STAT("payment-status");
	
	@Getter
	private String endpointValue;
}
