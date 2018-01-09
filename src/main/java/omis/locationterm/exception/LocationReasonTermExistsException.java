package omis.locationterm.exception;

import omis.exception.BusinessException;

/**
 * Thrown when a location reason term exists.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Feb 5, 2015)
 * @since OMIS 3.0
 */
public class LocationReasonTermExistsException
		extends BusinessException {
	
	private static final long serialVersionUID = 1L;
	
	/** Default constructor. */
	public LocationReasonTermExistsException() {
		// Do nothing
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message business exception message
	 * @param cause business exception cause
	 */
	public LocationReasonTermExistsException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message business exception message
	 */
	public LocationReasonTermExistsException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause business exception cause
	 */
	public LocationReasonTermExistsException(final Throwable cause) {
		super(cause);
	}
}