package omis.courtcase.exception;

import omis.exception.BusinessException;

/**
 * Thrown when court case exists.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class CourtCaseExistsException
		extends BusinessException {
	
	private static final long serialVersionUID = 1L;

	/** Instantiates exception thrown when court case exists. */
	public CourtCaseExistsException() {
		// Default instantiation
	}

	/**
	 * Instantiates exception thrown when court case exists.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public CourtCaseExistsException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates exception thrown when court case exists.
	 * 
	 * @param message message
	 */
	public CourtCaseExistsException(final String message) {
		super(message);
	}

	/**
	 * Instantiates exception thrown when court case exists.
	 * 
	 * @param cause cause
	 */
	public CourtCaseExistsException(final Throwable cause) {
		super(cause);
	}
}