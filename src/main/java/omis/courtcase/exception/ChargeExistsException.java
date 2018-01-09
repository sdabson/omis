package omis.courtcase.exception;

import omis.exception.BusinessException;

/**
 * Exception thrown when charge exists.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class ChargeExistsException
		extends BusinessException {
	
	private static final long serialVersionUID = 1L;

	/** Instantiates exception thrown when charge exists. */
	public ChargeExistsException() {
		// Default instantiation
	}

	/**
	 * Instantiates exception thrown when charge exists.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public ChargeExistsException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates exception thrown when charge exists.
	 * 
	 * @param message message
	 */
	public ChargeExistsException(final String message) {
		super(message);
	}

	/**
	 * Instantiates exception thrown when charge exists.
	 * 
	 * @param cause cause
	 */
	public ChargeExistsException(final Throwable cause) {
		super(cause);
	}
}