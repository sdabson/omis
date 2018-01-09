package omis.person.exception;

import omis.exception.DuplicateEntityFoundException;

/**
 * Thrown when person exists.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 15, 2017)
 * @since OMIS 3.0
 */
public class PersonExistsException
		extends DuplicateEntityFoundException {

	private static final long serialVersionUID = 1L;
	
	/** Instantiates exception thrown when person exists. */
	public PersonExistsException() {
		// Default instantiation
	}
	
	/**
	 * Instantiates exception thrown when person exists with message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public PersonExistsException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates exception thrown when person exists with message.
	 * 
	 * @param message message
	 */
	public PersonExistsException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiates exception thrown when person exists with cause.
	 * 
	 * @param cause cause
	 */
	public PersonExistsException(final Throwable cause) {
		super(cause);
	}
}