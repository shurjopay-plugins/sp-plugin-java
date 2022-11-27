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
	 * {@link ShurjopayException} with error message and error.
	 * @param {@link String} errMsg
	 * @param {@link Throwable} err
	 */
	public ShurjopayException(String errMsg, Throwable err) {
		super(errMsg, err);
	}
	
	/**
	 * {@link ShurjopayException} with error message only.
	 * @param  errMsg
	 */
	public ShurjopayException(String errMsg) {
		super(errMsg);
	}
}
