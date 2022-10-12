package com.shurjopay.plugin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

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
	
	@NonNull @Getter
	private String endpointValue;
}
