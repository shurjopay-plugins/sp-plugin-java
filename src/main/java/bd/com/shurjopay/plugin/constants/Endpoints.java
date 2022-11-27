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
 * @modified 2022-10-12
 */
public enum Endpoints {
	TOKEN("get_token"),
	MAKE_PMNT("secret-pay"),
	VERIFIED_ORDER("verification"),
	PMNT_STAT("payment-status");
	
	private String title;
	
	private Endpoints(String title) {
		this.title = title;
	}
	
	public String title() {
		return this.title;
	}
	
	
}
