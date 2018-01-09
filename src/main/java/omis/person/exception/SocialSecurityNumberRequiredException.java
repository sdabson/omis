package omis.person.exception;

import omis.exception.BusinessException;

/**
 * Thrown if a person does not have a social security when required.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Apr 8, 2015)
 * @since OMIS 3.0
 */
public class SocialSecurityNumberRequiredException
		extends BusinessException {
	
	private static final long serialVersionUID = 1L;

	/** Instantiates default exception. */
	public SocialSecurityNumberRequiredException() {
		// Default instantiation
	}

	/**
	 * Instantiates with message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public SocialSecurityNumberRequiredException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates with message.
	 * 
	 * @param message message
	 */
	public SocialSecurityNumberRequiredException(final String message) {
		super(message);
	}

	/**
	 * Instantiates with cause.
	 * 
	 * @param cause cause
	 */
	public SocialSecurityNumberRequiredException(final Throwable cause) {
		super(cause);
	}
}