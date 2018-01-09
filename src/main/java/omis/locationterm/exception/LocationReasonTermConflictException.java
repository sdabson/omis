package omis.locationterm.exception;

import omis.exception.BusinessException;

/**
 * Thrown when two or ore location reason terms conflict.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Feb 5, 2015)
 * @since OMIS 3.0
 */
public class LocationReasonTermConflictException
		extends BusinessException {
	
	private static final long serialVersionUID = 1L;

	/** Default constructor. */
	public LocationReasonTermConflictException() {
		// Default instantiation
	}

	/**
	 * Instantiates with message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public LocationReasonTermConflictException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates with message.
	 * 
	 * @param message message
	 */
	public LocationReasonTermConflictException(final String message) {
		super(message);
	}

	/**
	 * Instantiates with cause.
	 * 
	 * @param cause cause
	 */
	public LocationReasonTermConflictException(final Throwable cause) {
		super(cause);
	}
}