package omis.probationterm.exception;

import omis.exception.BusinessException;

/**
 * Thrown when two or more probation terms conflict.
 *
 * @author Josh Divine
 * @version 0.0.1 (May 24, 2017)
 * @since OMIS 3.0
 */
public class ProbationTermConflictException
		extends BusinessException {
	
	private static final long serialVersionUID = 1L;

	/** Default constructor. */
	public ProbationTermConflictException() {
		// Default instantiation
	}

	/**
	 * Instantiates with message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public ProbationTermConflictException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates with message.
	 * 
	 * @param message message
	 */
	public ProbationTermConflictException(final String message) {
		super(message);
	}

	/**
	 * Instantiates with cause.
	 * 
	 * @param cause cause
	 */
	public ProbationTermConflictException(final Throwable cause) {
		super(cause);
	}
}