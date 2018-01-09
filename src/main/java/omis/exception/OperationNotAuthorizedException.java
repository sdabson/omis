package omis.exception;

/**
 * Thrown to indicate that an operation what attempted without authorization.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 29, 2014)
 * @since OMIS 3.0
 */
public class OperationNotAuthorizedException
		extends BusinessException {
	
	private static final long serialVersionUID = 1L;

	/** Default constructor. */
	public OperationNotAuthorizedException() {
		// Do nothing
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message permissions exception message
	 * @param cause permissions exception cause
	 */
	public OperationNotAuthorizedException(final String message,
			final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message permissions exception message
	 */
	public OperationNotAuthorizedException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause permissions exception cause
	 */
	public OperationNotAuthorizedException(final Throwable cause) {
		super(cause);
	}
}