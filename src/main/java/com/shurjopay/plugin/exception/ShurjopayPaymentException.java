package com.shurjopay.plugin.exception;
/**
 * 
 * @author Al-Amin
 * @since 2022-10-19
 */
public class ShurjopayPaymentException extends RuntimeException{
	private static final long serialVersionUID = -6027957253727079082L;

	public ShurjopayPaymentException(String errMsg, Throwable err) {
		super(errMsg, err);
	}
	
	public ShurjopayPaymentException(String errMsg) {
		super(errMsg);
	}
}
