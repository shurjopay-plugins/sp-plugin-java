package com.shurjopay.plugin.model;

import lombok.Data;

/**
 * This model represents the merchant's credentials and shurjoPay URLs
 * @author Al-Amin
 * @since 2022-10-12
 */
@Data
public class ShurjoPayConfig {

	private String username;
	private String password;
	private String callbackApi;
	private String apiBaseUrl;
}
