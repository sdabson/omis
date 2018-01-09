package omis.health.exception;

import omis.exception.BusinessException;

/**
 * Thrown to indicate a problem with a follow up.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jun 17, 2014)
 * @since OMIS 3.0
 */
public class FollowUpException
		extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	/** Default constructor. */
	public FollowUpException() {
		// Default instantiation
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message business exception message
	 * @param cause business exception cause
	 */
	public FollowUpException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message business exception message
	 */
	public FollowUpException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause business exception cause
	 */
	public FollowUpException(final Throwable cause) {
		super(cause);
	}
}