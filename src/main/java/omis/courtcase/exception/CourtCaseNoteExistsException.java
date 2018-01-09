package omis.courtcase.exception;

import omis.exception.BusinessException;

/**
 * Thrown when court case note exists.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class CourtCaseNoteExistsException
		extends BusinessException {
	
	private static final long serialVersionUID = 1L;

	/** Instantiates exception thrown when court case note exists. */
	public CourtCaseNoteExistsException() {
		// Default instantiation
	}

	/**
	 * Instantiates exception thrown when court case note exists.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public CourtCaseNoteExistsException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates exception thrown when court case note exists.
	 * 
	 * @param message message
	 */
	public CourtCaseNoteExistsException(final String message) {
		super(message);
	}

	/**
	 * Instantiates exception thrown when court case note exists.
	 * 
	 * @param cause cause
	 */
	public CourtCaseNoteExistsException(final Throwable cause) {
		super(cause);
	}
}