package bd.com.shurjopay.plugin;
/**
 * This is the default shurjopay's exception.
 * This class handles all exception of shurjopay's plugin
 * @author Al-Amin
 * @since 2022-10-19
 */
public class ShurjopayException extends Exception{
	private static final long serialVersionUID = 213095527584273895L;

	/**
	 * {@link ShurjopayException} constructor with error message and error.
	 * @param exMsg
	 * @param ex
	 */
	public ShurjopayException(String exMsg, Throwable ex) {
		super(exMsg, ex);
	}
	
	/**
	 * {@link ShurjopayException} constructor with error message only.
	 * @param exMsg
	 */
	public ShurjopayException(String exMsg) {
		super(exMsg);
	}
}
