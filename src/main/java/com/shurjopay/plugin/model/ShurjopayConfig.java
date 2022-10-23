package com.shurjopay.plugin.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * This model represents the merchant's credentials and shurjoPay URLs
 * 
 * @author Al-Amin
 * @since 2022-10-12
 */
@Data @Accessors(chain = true)
public class ShurjopayConfig {

	private String username;
	private String password;
	private String callbackUrl;
	private String apiBaseUrl;
}
