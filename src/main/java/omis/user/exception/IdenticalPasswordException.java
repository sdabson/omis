package omis.user.exception;

import omis.exception.BusinessException;

/**
 * Thrown to indicate that two passwords are identical.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 4, 2014)
 * @since OMIS 3.0
 */
public class IdenticalPasswordException
		extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	/** Default constructor. */
	public IdenticalPasswordException() {
		// Do nothing
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message business exception message
	 * @param cause business exception cause
	 */
	public IdenticalPasswordException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message business exception message
	 */
	public IdenticalPasswordException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause business exception cause
	 */
	public IdenticalPasswordException(final Throwable cause) {
		super(cause);
	}
}