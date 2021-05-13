package exceptions;

/**
 * <i>NotMemberException</i> is raised when some member can not be found in the
 * <i>ISocialNetwork</i>
 * 
 */
public class NotMemberException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotMemberException(String message) {
		super(message);
	}

}
