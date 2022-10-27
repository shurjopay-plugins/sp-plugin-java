package bd.com.shurjopay.plugin;
/**
 * 
 * @author Al-Amin
 * @since 2022-10-19
 */
public class ShurjopayException extends Exception{
	private static final long serialVersionUID = 213095527584273895L;

	public ShurjopayException(String errMsg, Throwable err) {
		super(errMsg, err);
	}
	
	public ShurjopayException(String errMsg) {
		super(errMsg);
	}
}
