package omis.locationterm.exception;

import omis.exception.BusinessException;

/**
 * Thrown when an attempt is made to updated a locked location term.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class LocationTermLockedException
		extends BusinessException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates exception thrown when an attempt is made to update a locked
	 * location term.
	 */
	public LocationTermLockedException() {
		// Default instantiation
	}

	/**
	 * Instantiates exception thrown when an attempt is made to update a locked
	 * location term.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public LocationTermLockedException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates exception thrown when an attempt is made to update a locked
	 * location term.
	 * 
	 * @param message message
	 */
	public LocationTermLockedException(final String message) {
		super(message);
	}

	/**
	 * Instantiates exception thrown when an attempt is made to update a locked
	 * location term.
	 * 
	 * @param cause cause
	 */
	public LocationTermLockedException(final Throwable cause) {
		super(cause);
	}
}