package bd.com.shurjopay.plugin.constants;

/**
 * All shurjopay's reserved codes and messages.
 * @author Al-Amin
 * @since 2022-10-19
 */
public enum ShurjopayStatus {

	/**
	 *  Success status for every scenario of shurjopay.
	 */
	SHURJOPAY_SUCCESS("1000", "Shurjopay transaction successful."),
	
	/**
	 * Failed payment status.
	 */
	PAYMENT_FAILED("1001", "Shurjopay transaction failed."),
	
	/**
	 * Canceled payment status.
	 */
	PAYMENT_CANCEL("1002", "Shurjopay transaction canceled."),
	
	/**
	 * Failed bank transaction status.
	 */
	BANK_TXN_FAILED("1005", "Bank transaction failed."),
	
	/**
	 * OTP is not matched.
	 */
	OTP_MISSMATCH("1006", "OTP is not matched during mobile wallet transaction."),
	
	/**
	 * Verification PIN is not matched status.
	 */
	PIN_MISSMATCH("1007", "Verification PIN is not matched during mobile wallet transaction."),
	
	/**
	 * Verification PIN is not matched status.
	 */
	INSUFFICIENT_BALANCE("1008", "Insufficient balance to pay the amount."),
	
	/**
	 * Daily limit exceeded status.
	 */
	DAILY_LIMIT("1009", "Shurjopay daily transaction's limit has been exceeded."),
	
	/**
	 * shurjopay's currency miss match status.
	 */
	CURRENCY_MISSMATCH("1010", "Shurjopay accepts BDT and USD currency."),
	
	/**
	 * Invalid order id status.
	 */
	INVALID_ORDER_ID("1011", "Invalid order id. Provide shurjopay valid order id."),
	
	/**
	 * BKASH duplicate payment status.
	 */
	BKASH_DUPLICATE_PAYMENT("1061", "Bkash does not allow same payment transaction within 10 minutes."),
	
	/**
	 * Merchant's authentication failed status.
	 */
	AUTHENTICATION_FAILED("1064", "Shurjopay merchant's authentication failed. Check username and password!"),
	
	/**
	 * DBBL timeout status.
	 */
	DBBL_TIMEOUT("1065", "Payment is initiated but not completed."),
	
	/**
	 * Shurjopay's payment not initiated status.
	 */
	PAYMENT_NOT_INITIATED("1066", "Shurjopay's payment is not initiated."),
	
	/**
	 * Shurjopay's payment declined status.
	 */
	PAYMENT_DECLINED("1067", "Shurjopay payment is declined."),
	
	/**
	 * Shurjopay's payment initiated status.
	 */
	PAYMENT_INITIATED("1068", "Shurjopay payment is initiated."),
	
	/**
	 * Shurjopay's payment refund requested status.
	 */
	REFUND_REQUEST("2000", "Shurjopay payment refund is requested."),
	
	/**
	 * Shurjopay's payment refund accepted status.
	 */
	REFUND_ACCEPT("2001", "Shurjopay payment refund is accepted."),
	
	/**
	 * Shurjopay's payment refund canceled status.
	 */
	REFUND_CANCEL("2006", "Shurjopay payment refund is canceled."),
	
	/**
	 * Shurjopay's payment refund successful status.
	 */
	REFUND_COMPLETED("2002", "Shurjopay payment refund is successfully done."),
	
	/**
	 * Bank review status.
	 */
	BANK_REVIEW("2005", "Bank holds the transaction for review"),
	
	/**
	 * Bank transaction error status.
	 */
	BANK_ERROR("2006", "Error occured while bank processing the transaction."),
	
	/**
	 * Shurjopay timeout status.
	 */
	SHURJOPAY_TIME_OUT("2007", "Shurjopay request timeout."),
	
	/**
	 * Merchant inactive status.
	 */
	INACTIVE_MERCHANT("2008", "Merchant is inactive. Contact with your respective KAM."),
	
	/**
	 * BKASH debit party status.
	 */
	BKASH_DEBIT_PARTY("2009", "Payment has failed debit party identity tag prohibits execution.");
	
	/**
	 * Shurjopay's status code.
	 */
	private String code;
	
	/**
	 * Shurjopay's status message.
	 */
	private String message;
	
	/**
	 * Instantiation constructor.
	 * @param code Shurjopay HTTP status code.
	 * @param message Shurjopay HTTP status message.
	 */
	private ShurjopayStatus(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	/**
	 * Gets shurjopay reserved status code.
	 * @return Shurjopay HTTP status code.
	 */
	public String code() {
		return this.code;
	}
	
	/**
	 * Gets shurjopay's status message.
	 * @return Shurjopay HTTP status message.
	 */
	public String message() {
		return this.message;
	}
	
	/**
	 * Loads message by code.
	 * @param code Shurjopay HTTP status code.
	 * @return Shurjopay HTTP status message.
	 */
	public static String messageByCode(String code) {
        for (ShurjopayStatus b : ShurjopayStatus.values()) {
            if (b.code.equalsIgnoreCase(code)) {
                return b.message;
            }
        }
        return null;
    }
}
