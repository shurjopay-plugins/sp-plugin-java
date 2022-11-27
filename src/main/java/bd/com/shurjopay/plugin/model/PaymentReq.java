package bd.com.shurjopay.plugin.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * This model is used for making payment.
 * Model is not required to set returnUrl and cancelUrl.
 * For this purpose, set callback-URL in shurjopay.properties. It will be used for concerned fields.
 * 
 * @author Al - Amin
 * @since 2022-06-16
 */
@Data
@Accessors(chain = true)
public class PaymentReq implements Serializable {
	private static final long serialVersionUID = 4191752321718444127L;

	/**
	 *  Prefix of a merchant.
	 */
	private String prefix;

	/**
	 *  JWT token to authenticate merchant.
	 */
	@JsonProperty("token")
	private String authToken;

	/**
	 * Callback URL to return verify payment id.
	 */
	@JsonProperty("return_url")
	private String returnUrl;

	/**
	 * Callback URL to cancel a shurjoPay payment
	 */
	@JsonProperty("cancel_url")
	private String cancelUrl;

	/**
	 * Merchant's registered store id.
	 */
	@JsonProperty("store_id")
	private Integer storeId;

	/**
	 * Payment amount in BDT/USD currency.
	 */
	private Double amount;

	/**
	 * Customer order id.
	 */
	@JsonProperty("order_id")
	private String orderId;

	/**
	 * Payment amount in currency. shurjoPay supports only BDT and USD.
	 */
	private String currency;

	/**
	 * Customer's full name who want to pay by shurjoPay.
	 */
	@JsonProperty("customer_name")
	private String customerName;

	/**
	 * Customer's address.
	 */
	@JsonProperty("customer_address")
	private String customerAddress;

	/**
	 * Customer's contact number.
	 */
	@JsonProperty("customer_phone")
	private String customerPhone;

	/**
	 * Customer's city where he/she lives in.
	 */
	@JsonProperty("customer_city")
	private String customerCity;

	/**
	 * Customer's valid postal code.
	 */
	@JsonProperty("customer_post_code")
	private String customerPostCode;
	
	/**
	 * Customer's valid email address.
	 */
	@JsonProperty("customer_email")
	private String customerEmail;
		
	/**
	 * Shipping address for E-commerce shipping transaction.
	 */
	@JsonProperty("shipping_address")
	private String shippingAddress;
	
	/**
	 * Shipping city for E-commerce shipping transaction.
	 */
	@JsonProperty("shipping_city")
	private String shippingCity;
	
	/**
	 * Shipping country for E-commerce shipping transaction.
	 */
	@JsonProperty("shipping_country")
	private String shippingCountry;
	
	/**
	 * Name of the person who will be received E-commerce shipping product.
	 */
	@JsonProperty("received_person_name")
	private String shippingReceiverName;
	
	/**
	 * Contact number of the person who will be received E-commerce shipping product.
	 */
	@JsonProperty("shipping_phone_number")
	private String shippingPhone;

	/**
	 * Client IP from where shurjoPay payment is initiated
	 */
	@JsonProperty("client_ip")
	private String clientIp;
}
