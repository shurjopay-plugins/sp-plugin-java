package com.shurjopay.plugin.constants;

/**
 * This ENUM represents all shurjoPay's codes and their definition
 * @author Al-Amin
 * @since 2022-10-19
 */
public enum ShurjopayStatus {

	SHURJOPAY_SUCCESS("1000", "Shurjopay transaction successful."),
	PAYMENT_FAILED("1001", "Shurjopay transaction failed."),
	PAYMENT_CANCEL("1002", "Shurjopay transaction canceled."),
	BANK_RESPONSE_FAILED("1005", "Bank transaction failed."),
	OTP_MISSMATCH("1006", "OTP is not matched during mobile wallet transaction."),
	PIN_MISSMATCH("1007", "Verification PIN is not matched during mobile wallet transaction."),
	INSUFFICIENT_BALANCE("1008", "Insufficient balance to pay the amount."),
	DAILY_LIMIT("1009", "Shurjopay daily transaction's limit has been exceeded."),
	CURRENCY_MISSMATCH("1010", "Shurjopay accepts BDT and USD currency."),
	INVALID_ORDER_ID("1011", "Invalid order id. Provide shurjopay valid order id."),
	BKASH_DUPLICATE_PAYMENT("1061", "Bkash does not allow same payment transaction within 10 minutes."),
	AUTHENTICATION_FAILED("1064", "Shurjopay merchant's authentication failed. Check username and password!"),
	DBBL_TIMEOUT("1065", "Payment is initiated but not completed."),
	PAYMENT_NOT_INITIATED("1066", "Shurjopay payment is not initiated."),
	PAYMENT_DECLINED("1067", "Shurjopay payment is declined."),
	PAYMENT_INITIATED("1068", "Shurjopay payment is initiated."),
	REFUND_REQUEST("2000", "Shurjopay payment refund is requested."),
	REFUND_ACCEPT("2001", "Shurjopay payment refund is accepted."),
	REFUND_CANCEL("2006", "Shurjopay payment refund is canceled."),
	REFUND_COMPLETED("2002", "Shurjopay payment refund is successfully done."),
	BANK_REVIEW("2005", "Bank holds the transaction for review"),	
	BANK_ERROR("2006", "Error occured while bank processing the transaction."),
	SHURJOPAY_TIME_OUT("2007", "Shurjopay request timeout."),
	INACTIVE_MERCHANT("2008", "Merchant is inactive. Contact with your respective KAM."),
	BKASH_DEBIT_PARTY("2009", "Payment has failed debit party identity tag prohibits execution.");
	
	private String code;
	private String status;
	
	private ShurjopayStatus(String code, String status) {
		this.code = code;
		this.status = status;
	}
	
	public String code() {
		return this.code;
	}
	
	public String status() {
		return this.status;
	}
	
	public static String statusByCode(String code) {
        for (ShurjopayStatus b : ShurjopayStatus.values()) {
            if (b.code.equalsIgnoreCase(code)) {
                return b.status;
            }
        }
        return null;
    }
}
