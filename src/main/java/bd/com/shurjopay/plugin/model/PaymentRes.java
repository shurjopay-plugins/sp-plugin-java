package bd.com.shurjopay.plugin.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import bd.com.shurjopay.plugin.constants.ShurjopayStatus;
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
	
	/**
	 * Secret Payment URL by which customer can pay.
	 */
	@JsonProperty("checkout_url")
	private String paymentUrl;
	
	/**
	 * Payment amount in BDT/USD currency.
	 */
	private String amount;
	
	/**
	 * Payment amount in currency. shurjoPay supports only BDT and USD.
	 */
	private String currency;
	
	/**
	 * shurjoPay generates a transaction id against a payment.
	 */
	@JsonProperty("sp_order_id")
	private String spOrderId;
	
	/**
	 * Customer defined order id.
	 */
	@JsonProperty("customer_order_id")
	private String customerOrderId;
	
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
	
	/** 
	 * This field is used for presenting payment category. e.g. sale
	 */
	@JsonProperty("intent")
	private String paymentCategory;
	
	/**
	 *  Transaction status of shurjoPay. e.g. Initiated, Failed, Canceled etc.
	 */
	private String transactionStatus;
	
	/**
	 * shurjoPay's won status code. see details {@link ShurjopayStatus}
	 */
	@JsonProperty("sp_code")
	private int spCode;
	
	/**
	 * shurjoPay's status message. see details {@link ShurjopayStatus}
	 */
	private String message;
}
