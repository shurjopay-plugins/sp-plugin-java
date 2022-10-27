package bd.com.shurjopay.plugin.constants;

/**
 * This ENUM contains all of the end-points of shurjoPay
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
