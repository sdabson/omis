package omis.exception;


/**
 * Thrown to indicate a violation of permissions and security rules.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Dec 31, 2011)
 * @since OMIS 3.0
 */
public class PermissionsException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/** Default constructor. */
	public PermissionsException() {
		// Do nothing
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message permissions exception message
	 * @param cause permissions exception cause
	 */
	public PermissionsException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message permissions exception message
	 */
	public PermissionsException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause permissions exception cause
	 */
	public PermissionsException(final Throwable cause) {
		super(cause);
	}
}