package omis.region.exception;

import omis.exception.DuplicateEntityFoundException;

/**
 * Thrown when State exists.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 15, 2017)
 * @since OMIS 3.0
 */
public class StateExistsException
		extends DuplicateEntityFoundException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates exception thrown when State exists.
	 */
	public StateExistsException() {
		// Default instantiation
	}
	
	/**
	 * Instantiates exception thrown when State exists with message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public StateExistsException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates exception thrown when State exists with message.
	 * 
	 * @param message message
	 */
	public StateExistsException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiates exception thrown when State exists with cause.
	 * 
	 * @param cause cause
	 */
	public StateExistsException(final Throwable cause) {
		super(cause);
	}
}