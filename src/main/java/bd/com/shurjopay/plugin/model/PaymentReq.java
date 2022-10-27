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

	private String prefix;

	@JsonProperty("token")
	private String authToken;

	@JsonProperty("return_url")
	private String returnUrl;

	@JsonProperty("cancel_url")
	private String cancelUrl;

	@JsonProperty("store_id")
	private Integer storeId;

	private Double amount;

	@JsonProperty("order_id")
	private String orderId;

	private String currency;

	@JsonProperty("customer_name")
	private String customerName;

	@JsonProperty("customer_address")
	private String customerAddress;

	@JsonProperty("customer_phone")
	private String customerPhone;

	@JsonProperty("customer_city")
	private String customerCity;

	@JsonProperty("customer_post_code")
	private String customerPostCode;
	
	@JsonProperty("customer_email")
	private String customerEmail;
	
	/** Shipping related fields are used to get information of Ecommerce's transactions */
	@JsonProperty("shipping_address")
	private String shippingAddress;
	
	@JsonProperty("shipping_city")
	private String shippingCity;
	
	@JsonProperty("shipping_country")
	private String shippingCountry;
	
	@JsonProperty("received_person_name")
	private String shippingReceiverName;
	
	@JsonProperty("shipping_phone_number")
	private String shippingPhone;

	@JsonProperty("client_ip")
	private String clientIp;
}
