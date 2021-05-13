package exceptions;

/**
 * <i>BadEntryException</i> is raised when some <i>ISocialNetwork</i> method is called
 * with a non-correct parameter value.
 * 
 */

public class BadEntryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadEntryException(String message) {
		super(message);
	}
}
