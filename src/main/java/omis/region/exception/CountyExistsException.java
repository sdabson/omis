package omis.region.exception;

import omis.exception.DuplicateEntityFoundException;

/**
 * Thrown when county exists.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 15, 2017)
 * @since OMIS 3.0
 */
public class CountyExistsException
		extends DuplicateEntityFoundException {

	private final static long serialVersionUID = 1L;
	
	/** Instantiates exception thrown when county exists. */
	public CountyExistsException() {
		// Default instantiation
	}
	
	/**
	 * Instantiates exception thrown when county exists with message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public CountyExistsException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates exception thrown when county exists with message.
	 * 
	 * @param message message
	 */
	public CountyExistsException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiates exception thrown when county exists with cause.
	 * 
	 * @param cause cause
	 */
	public CountyExistsException(final Throwable cause) {
		super(cause);
	}
}