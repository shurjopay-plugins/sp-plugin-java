package com.shurjopay.plugin.exception;
/**
 * 
 * @author Al-Amin
 * @since 2022-10-19
 */
public class ShurjopayAuthenticationException extends RuntimeException{
	private static final long serialVersionUID = 213095527584273895L;

	public ShurjopayAuthenticationException(String errMsg, Throwable err) {
		super(errMsg, err);
	}
	
	public ShurjopayAuthenticationException(String errMsg) {
		super(errMsg);
	}
}
