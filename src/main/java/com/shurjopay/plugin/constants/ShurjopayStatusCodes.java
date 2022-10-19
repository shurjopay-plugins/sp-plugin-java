package com.shurjopay.plugin.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This ENUM represents all shurjoPay's codes and their definition
 * @author Al-Amin
 * @since 2022-10-19
 */
@AllArgsConstructor
public enum ShurjopayStatusCodes {

	PAYMENT_SUCCESS("1000", "Shurjopay transaction successful."),
	PAYMENT_FAILED("1001", "Shurjopay transaction failed."),
	PAYMENT_CANCEL("1002", "Shurjopay transaction canceled."),
	BANK_RESPONSE_FAILED("1005", "Bank transaction failed."),
	OTP_MISSMATCH("1006", "OTP is not matched during mobile wallet transaction."),
	PIN_MISSMATCH("1007", "OTP is not matched during mobile wallet transaction."),
	INSUFFICIENT_BALANCE("1008", "Insufficient balance to pay the amount."),
	DAILY_LIMIT("1009", "Shurjopay daily transaction's limit has been exceeded."),
	CURRENCY_MISSMATCH("1010", "Shurjopay accepts BDT and USD currency."),
	INVALID_ORDER_ID("1011", "Invalid order id. Provide shurjopay valid order id."),
	BKASH_DUPLICATE_PAYMENT("1061", "Bkash does not allow same payment transaction within 10 minutes."),
	AUTHENTICATION_FAILED("1064", "Shurjopay merchant's authentication failed."),
	DBBL_TIMEOUT("1065", "Payment is initiated but not completed."),
	PAYMENT_NOT_INITIATED("1066", "Shurjopay payment is not initiated."),
	PAYMENT_DECLINED("1067", "Shurjopay payment is declined."),
	PAYMENT_INITIATED("1068", "Shurjopay payment is initiated."),
	REFUND_REQUEST("2000", "Shurjopay payment refund is requested."),
	REFUND_ACCEPT("2001", "Shurjopay payment refund is accepted."),
	REFUND_CANCEL("2006", "Shurjopay payment refund is canceled."),
	REFUND_COMPLETED("2002", "Shurjopay payment refund is successfully done."),
	
	//TODO What is this status? @fahim explanation
	BANK_REVIEW("2005", "Bank holds the transaction for review"),
	
	//TODO What is this status? @fahim explanation
	BANK_ERROR("2006", "Bank error"),
	TIME_OUT("2007", "Shurjopay timeout occured."),
	INACTIVE_MERCHANT("2008", "Merchant is inactive. Contact with your respective KAM."),
	
	//TODO What is this status? @fahim explanation
	BKASH_DEBIT_PARTY("2009", "Payment has failed Debit party identity tag prohibits execution");
	
	@Getter
	private String code;
	@Getter
	private String title;
}
