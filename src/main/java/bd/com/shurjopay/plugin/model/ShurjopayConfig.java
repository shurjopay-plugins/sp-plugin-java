package bd.com.shurjopay.plugin.model;

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

	/**
	 * Merchant's user name provided by shurjoPay author.
	 */
	private String username;
	
	/**
	 * Merchant's password provided by shurjoPay author.
	 */
	private String password;
	
	/**
	 * Merchant's callback URL provided by shurjoPay author.
	 */
	private String callbackUrl;
	
	/**
	 * shurjoPay's endpoint's base URL.
	 */
	private String apiBaseUrl;
}
