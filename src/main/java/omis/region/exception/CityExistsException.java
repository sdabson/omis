package omis.region.exception;

import omis.exception.DuplicateEntityFoundException;

/**
 * Thrown if city exists.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 15, 2017)
 * @since OMIS 3.0
 */
public class CityExistsException
		extends DuplicateEntityFoundException {
	
	private static final long serialVersionUID = 1L;

	/** Instantiates exception thrown if city exists. */
	public CityExistsException() {
		// Default instantiation
	}
	
	/**
	 * Instantiates exception thrown if city exists with message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public CityExistsException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates exception thrown if city exists with message.
	 * 
	 * @param message message
	 */
	public CityExistsException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiates exception thrown if city exists with cause.
	 * 
	 * @param cause cause
	 */
	public CityExistsException(final Throwable cause) {
		super(cause);
	}
}