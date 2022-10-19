package com.shurjopay.plugin.exception;
/**
 * 
 * @author Al-Amin
 * @since 2022-10-19
 */
public class ShurjopayVerificationException extends RuntimeException{
	private static final long serialVersionUID = 1676894539002889912L;

	public ShurjopayVerificationException(String errMsg, Throwable err) {
		super(errMsg, err);
	}
	
	public ShurjopayVerificationException(String errMsg) {
		super(errMsg);
	}
}
