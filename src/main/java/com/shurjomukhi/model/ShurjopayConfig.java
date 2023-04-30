package com.shurjomukhi.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Merchant's credentials provided by shurjopay and shurjoPay configurations.
 * 
 * @author Al - Amin
 * @since 2022-10-12
 */
@Data @Accessors(chain = true)
public class ShurjopayConfig {

	/** Merchant's username provided by shurjoPay author. */
	private String username;
	
	/** Merchant's password provided by shurjoPay author. */
	private String password;
	
	/** Merchant's callback URL provided by shurjoPay author. */
	private String callbackUrl;
	
	/** ShurjoPay base URL for performing a payment cycle. */
	private String apiBaseUrl;
}
