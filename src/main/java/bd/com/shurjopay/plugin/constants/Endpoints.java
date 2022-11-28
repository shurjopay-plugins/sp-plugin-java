package bd.com.shurjopay.plugin.constants;

/**
 * This ENUM contains all of the end-points of shurjoPay.
 * <ul>
 * <li>Getting shurjoPay token: <b>get_token</b></li>
 * <li>Making shurjoPay payment: <b>secret-pay</b></li>
 * <li>Verifying shurjoPay payment: <b>verification</b></li>
 * <li>Checking shurjoPay payment status: <b>payment-status</b></li>
 * </ul>
 * @author Al-Amin
 * @since 2022-07-30
 */
public enum Endpoints {
	/**
	 * JWT token end-point.
	 */
	TOKEN("get_token"),
	
	/**
	 * Making secret payment end-point.
	 */
	MAKE_PMNT("secret-pay"),
	
	/**
	 * Payment verification end-point.
	 */
	VERIFIED_ORDER("verification"),
	
	/**
	 * Checking payment status end-point
	 */
	PMNT_STAT("payment-status");
	
	/**
	 * Representing endpoint's title
	 */
	private String title;
	
	/**
	 * 
	 * @param title
	 */
	private Endpoints(String title) {
		this.title = title;
	}
	
	/**
	 * This method is used to get endpoint's title
	 * @return endpoint's title.
	 */
	public String title() {
		return this.title;
	}
	
	
}
