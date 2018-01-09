package omis.locationterm.exception;

import omis.exception.BusinessException;

/**
 * Thrown if location term is ended.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Mar 17, 2015)
 * @since OMIS 3.0
 */
public class EndedLocationTermException
		extends BusinessException {
	
	private static final long serialVersionUID = 1L;

	/** Instantiates default exception. */
	public EndedLocationTermException() {
		// Default instantiation
	}

	/**
	 * Instantiates with message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public EndedLocationTermException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates with message.
	 * 
	 * @param message message
	 */
	public EndedLocationTermException(final String message) {
		super(message);
	}

	/**
	 * Instantiates with cause.
	 * 
	 * @param cause cause
	 */
	public EndedLocationTermException(Throwable cause) {
		super(cause);
	}
}