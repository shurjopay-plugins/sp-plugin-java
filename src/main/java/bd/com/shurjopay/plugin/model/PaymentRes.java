package bd.com.shurjopay.plugin.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * After making a payment with shurjoPay this model will be the response object
 * @author Al - Amin
 * @since 2022-06-16
 */
@Data
@Accessors(chain = true)
public class PaymentRes implements Serializable{
	private static final long serialVersionUID = -4989310354723281491L;
	
	@JsonProperty("checkout_url")
	private String paymentUrl;
	
	private String amount;
	private String currency;
	
	@JsonProperty("sp_order_id")
	private String spOrderId;
	
	@JsonProperty("customer_order_id")
	private String customerOrderId;
	
	@JsonProperty("customer_name")
	private String customerName;
	
	@JsonProperty("customer_address")
	private String customerAddress;
	
	@JsonProperty("customer_phone")
	private String customerPhone;
	
	@JsonProperty("customer_city")
	private String customerCity;
	
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
	
	/** This field is used for presenting payment category. e.g. sale */
	@JsonProperty("intent")
	private String paymentCategory;
	
	/** Transaction status of shurjoPay. e.g. Initiated, Failed, Canceled */
	private String transactionStatus;
	
	@JsonProperty("sp_code")
	private int spCode;
	
	private String message;
}
