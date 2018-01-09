package omis.locationterm.exception;

import omis.exception.BusinessException;

/**
 * Thrown when a location terms end date is null.
 *
 * @author Josh Divine
 * @version 0.0.1 (Mar 2, 2017)
 * @since OMIS 3.0
 */
public class LocationTermExistsAfterException
		extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	/** Default constructor. */
	public LocationTermExistsAfterException() {
		// Default instantiation
	}

	/**
	 * Instantiates with message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public LocationTermExistsAfterException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates with message.
	 * 
	 * @param message message
	 */
	public LocationTermExistsAfterException(final String message) {
		super(message);
	}

	/**
	 * Instantiates with cause.
	 * 
	 * @param cause cause
	 */
	public LocationTermExistsAfterException(final Throwable cause) {
		super(cause);
	}
}