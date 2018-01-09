package omis.person.exception;

import omis.exception.DuplicateEntityFoundException;

/**
 * Thrown if person name exists.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 15, 2017)
 * @since OMIS 3.0
 */
public class PersonNameExistsException
		extends DuplicateEntityFoundException {
	
	private final static long serialVersionUID = 1L;

	/** Instantiates exception thrown when person name exists. */
	public PersonNameExistsException() {
		// Default instantiation
	}
	
	/**
	 * Instantiates exception thrown when person name exists with message
	 * and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public PersonNameExistsException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates exception thrown when person name exists with message.
	 * 
	 * @param message message
	 */
	public PersonNameExistsException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiates exception thrown when person name exists with cause.
	 * 
	 * @param cause cause
	 */
	public PersonNameExistsException(final Throwable cause) {
		super(cause);
	}
}