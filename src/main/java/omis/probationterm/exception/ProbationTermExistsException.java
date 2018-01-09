package omis.probationterm.exception;

import omis.exception.BusinessException;

/**
 * Thrown when a probation term exists.
 *
 * @author Josh Divine
 * @version 0.0.1 (May 24, 2017)
 * @since OMIS 3.0
 */
public class ProbationTermExistsException
		extends BusinessException {
	
	private static final long serialVersionUID = 1L;
	
	/** Default constructor. */
	public ProbationTermExistsException() {
		// Do nothing
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message business exception message
	 * @param cause business exception cause
	 */
	public ProbationTermExistsException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message business exception message
	 */
	public ProbationTermExistsException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause business exception cause
	 */
	public ProbationTermExistsException(final Throwable cause) {
		super(cause);
	}
}