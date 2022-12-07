package bd.com.shurjopay.plugin;
/**
 * An exception that provides an easy and safe way to track occurrence.
 * <p>
 * ShurjopayException provides the exception message or message with cause of exception.
 * </p>
 * 
 * @author Al - Amin
 * @since 2022-10-19
 */
public class ShurjopayException extends Exception{
	private static final long serialVersionUID = 213095527584273895L;

	/**
     * Instantiates ShurjopayException with message and exception.
     * @param message the shurjopay's exception message.
     * @param cause the underlying cause of the ShurjopayException.
     */
	public ShurjopayException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
     * Instantiates ShurjopayException with message only.
     * @param message the shurjopay's exception message.
     */
	public ShurjopayException(String message) {
		super(message);
	}
}
