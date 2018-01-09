package omis.offender.exception;

import omis.exception.BusinessException;

/**
 * Thrown when two or more offenders are not the same.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jan 28, 2015)
 * @since OMIS 3.0
 */
public class OffenderMismatchException
		extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	/** Instantiates. */
	public OffenderMismatchException() {
		// Default instantiation
	}

	/**
	 * Instantiates with message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public OffenderMismatchException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates with message.
	 * 
	 * @param message message
	 */
	public OffenderMismatchException(final String message) {
		super(message);
	}

	/**
	 * Instantiates with cause.
	 * 
	 * @param cause cause
	 */
	public OffenderMismatchException(final Throwable cause) {
		super(cause);
	}
}