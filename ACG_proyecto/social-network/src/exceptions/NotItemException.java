package exceptions;

/**
 * <i>NotItemException</i> is raised when some item can not be found in the
 * <i>ISocialNetwork</i>
 * 
 */
public class NotItemException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotItemException(String message) {
		super(message);
	}
}
