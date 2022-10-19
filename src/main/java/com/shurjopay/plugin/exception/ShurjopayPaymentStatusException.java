package com.shurjopay.plugin.exception;
/**
 * 
 * @author Al-Amin
 * @since 2022-10-19
 */
public class ShurjopayPaymentStatusException extends RuntimeException{
	private static final long serialVersionUID = 1676894539002889912L;

	public ShurjopayPaymentStatusException(String errMsg, Throwable err) {
		super(errMsg, err);
	}
	
	public ShurjopayPaymentStatusException(String errMsg) {
		super(errMsg);
	}
}
