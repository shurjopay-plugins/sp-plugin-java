package com.shurjomukhi.constants;

/**
 * All of the end-points of shurjopay.
 * <ul>
 * <li><b>TOKEN:</b> Getting shurjoPay token.</li>
 * <li><b>MAKE_PMNT:</b> Initiating a shurjoPay payment.</li>
 * <li><b>VERIFIED_PMNT:</b> Verifying shurjoPay payment.</li>
 * <li><b>PMNT_STAT:</b> Checking a successful shurjoPay payment status.</li>
 * </ul>
 * @author Al - Amin
 * @since 2022-07-30
 */
public enum Endpoints {
	/** JWT token end-point. */
	TOKEN("get_token"),
	
	/** Initiating secret payment end-point.*/
	MAKE_PMNT("secret-pay"),
	
	/** Verifying initiated payment end-point.*/
	VERIFIED_PMNT("verification"),
	
	/** Checking payment status end-point.*/
	PMNT_STAT("payment-status");
	
	/** Representing endpoint's title.*/
	private String title;
	
	/**
	 * Instantiation constructor.
	 * @param title ENUM title of end-points
	 */
	private Endpoints(String title) {
		this.title = title;
	}
	
	/**
	 * Gets endpoint's ENUM title.
	 * @return endpoint's title.
	 */
	public String title() {
		return this.title;
	}
	
	
}
