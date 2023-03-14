package com.shurjomukhi;

/**
 * All shurjopay's reserved codes and messages.
 * @author Al-Amin
 * @since 2022-10-19
 */
public enum ShurjopayStatus {

	/** Failed payment status */
	PAYMENT_FAILED("1001", "shurjoPay transaction failed"),

	/** Canceled payment status */
	PAYMENT_CANCEL("1002", "shurjoPay transaction cancelled"),

	/** OTP is not matched */
	OTP_MISMATCH("1006", "OTP is not matched during mobile wallet transaction"),

	/** Verification PIN is not matched status */
	PIN_MISMATCH("1007", "Verification PIN is not matched during mobile wallet transaction"),

	/** Verification PIN is not matched status */
	INSUFFICIENT_BALANCE("1008", "Insufficient balance to pay the amount"),

	/** Daily limit exceeded status. */
	DAILY_LIMIT("1009", "shurjoPay daily transaction's limit has been exceeded"),

	/** shurjopay's currency miss match status */
	CURRENCY_MISMATCH("1010", "shurjoPay accepts BDT and USD currency"),

	/** Invalid order id status */
	INVALID_ORDER_ID("1011", "Invalid order id. Provide shurjoPay valid order id"),

	/** BKASH duplicate payment status */
	BKASH_DUPLICATE_PAYMENT("1061", "Bkash does not allow same payment transaction within 10 minutes"),

	/** Merchant's authentication failed status */
	AUTHENTICATION_FAILED("1064", "shurjoPay merchant's authentication failed. Check username and password!"),

	/** DBBL timeout status */
	DBBL_TIMEOUT("1065", "Payment is initiated but not completed"),

	/** Shurjopay's payment declined status */
	PAYMENT_DECLINED("1067", "shurjoPay payment is declined"),

	/** Shurjopay's payment initiated status */
	PAYMENT_INITIATED("1068", "shurjoPay payment is initiated"),

	/** Shurjopay timeout status */
	SHURJOPAY_TIME_OUT("2007", "shurjoPay request timeout"),

	/** Success status for every scenario of shurjopay */
	SHURJOPAY_SUCCESS("1000", "shurjoPay transaction successful"),

	/** Shurjopay's payment refund requested status */
	REFUND_REQUEST("2000", "shurjoPay payment refund is requested"),

	/** Shurjopay's payment refund accepted status */
	REFUND_ACCEPT("2001", "shurjoPay payment refund is accepted"),

	/** Shurjopay's payment refund successful status */
	REFUND_COMPLETED("2002", "shurjoPay payment refund is successfully done"),

	/** Merchant inactive status */
	INACTIVE_MERCHANT("2008", "Merchant is inactive. Contact with your respective KAM"),

	/* Sp code missing */
	SP_HTTP_OK( "200", "HTTP Status Ok"),

	/** HTTP not found status */
	SP_HTTP_NOT_FOUND( "404", "HTTP Status Not Found"),

	/** Credentials mismatch status */
	SP_CRED_MISMATCH( "1003", "Credentials Mismatch"),

	/** Session expired status */
	SP_SESSION_EXPIRED( "1004", "Session Expired"),

	/** EMI not found status */
	SP_TX_EMI_NA( "1012", "EMI Not Available"),

	/** Param missing status */
	SP_PARAM_MISSING( "1062", "Param Missing"),

	/** Method not found status */
	SP_METHOD_NOT_FOUND( "1063", "Method Not Found"),

	/** Refund cancelled status */
	SP_REFUND_SCRAP( "2006", "Refund Cancelled or Scrapped"),

	/** Consumer wallet locked status */
	CONSUMER_WALLET_LOCKED( "2009", "Consumer Wallet Locked"),

	/** Transaction reversed status */
	SP_TX_REVERSED( "3000", "Transaction Reversed"),

	/** Failed bank transaction status */
	SP_CHANNEL_UNAVAILABLE("1005", "Channel Unavailable"),

	/** Shurjopay's payment not initiated status */
	PAYMENT_NOT_INITIATED("1066", "Shurjopay's payment is not initiated"),

	/** Bank review status */
	BANK_REVIEW("2005", "Bank holds the transaction for review");


	/** Shurjopay's status code */
	private final String code;

	/** Shurjopay's status message */
	private final String message;

	/**
	 * Instantiates ShurjopayStatus
	 *
	 * @param code Shurjopay's status code.
	 * @param message Shurjopay's status message.
	 */
	ShurjopayStatus(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	/**
	 * Gets shurjopay reserved status code
	 *
	 * @return Shurjopay status code.
	 */
	public String code() {
		return this.code;
	}
	
	/**
	 * Gets shurjopay's status message.
	 *
	 * @return Shurjopay status message.
	 */
	public String message() {
		return this.message;
	}
	
	/**
	 * Loads message by code.
	 *
	 * @param code Shurjopay status code.
	 * @return Shurjopay status message.
	 */
	public static String messageByCode(String code) {
		ShurjopayStatus[] values = ShurjopayStatus.values();
		for (ShurjopayStatus b : values) {
            if (b.code.equalsIgnoreCase(code)) {
                return b.message;
            }
        }
        return null;
    }
}
