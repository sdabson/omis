package omis.person.exception;

import omis.exception.DuplicateEntityFoundException;

/**
 * Thrown when person identity exists.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 15, 2017)
 * @since OMIS 3.0
 */
public class PersonIdentityExistsException
		extends DuplicateEntityFoundException {

	private static final long serialVersionUID = 1L;
	
	/** Instantiates exception thrown when person identity exists. */
	public PersonIdentityExistsException() {
		// Default instantiation
	}
	
	/**
	 * Instantiates exception thrown when person identity exists.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public PersonIdentityExistsException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates exception thrown when person identity exists.
	 * 
	 * @param message message
	 */
	public PersonIdentityExistsException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiates exception thrown when person identity exists.
	 * 
	 * @param cause cause
	 */
	public PersonIdentityExistsException(final Throwable cause) {
		super(cause);
	}
}