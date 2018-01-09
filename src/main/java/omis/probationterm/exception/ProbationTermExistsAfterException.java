package omis.probationterm.exception;

import omis.exception.BusinessException;

/**
 * Thrown when a probation terms end date is null and other terms have a start 
 * date after the null end date term.
 *
 * @author Josh Divine
 * @version 0.0.1 (May 24, 2017)
 * @since OMIS 3.0
 */
public class ProbationTermExistsAfterException
		extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	/** Default constructor. */
	public ProbationTermExistsAfterException() {
		// Default instantiation
	}

	/**
	 * Instantiates with message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public ProbationTermExistsAfterException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates with message.
	 * 
	 * @param message message
	 */
	public ProbationTermExistsAfterException(final String message) {
		super(message);
	}

	/**
	 * Instantiates with cause.
	 * 
	 * @param cause cause
	 */
	public ProbationTermExistsAfterException(final Throwable cause) {
		super(cause);
	}
}