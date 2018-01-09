package omis.user.exception;

import omis.exception.BusinessException;

/**
 * Thrown to indicate that a password has already been used for a user account.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 2, 2014)
 * @since OMIS 3.0
 */
public class PasswordUsedException
		extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	/** Default constructor. */
	public PasswordUsedException() {
		// Do nothing
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message business exception message
	 * @param cause business exception cause
	 */
	public PasswordUsedException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message business exception message
	 */
	public PasswordUsedException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause business exception cause
	 */
	public PasswordUsedException(final Throwable cause) {
		super(cause);
	}
}