package omis.locationterm.exception;

import omis.exception.BusinessException;

/**
 * Thrown if a location term is ended.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Mar 20, 2015)
 * @since OMIS 3.0
 */
public class EndedLocationReasonTermException
		extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	/** Instantiates default exception. */
	public EndedLocationReasonTermException() {
		// Default instantiation
	}

	/**
	 * Instantiates with message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public EndedLocationReasonTermException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates with message.
	 * 
	 * @param message message
	 */
	public EndedLocationReasonTermException(final String message) {
		super(message);
	}

	/**
	 * Instantiates with cause.
	 * 
	 * @param cause cause
	 */
	public EndedLocationReasonTermException(final Throwable cause) {
		super(cause);
	}
}