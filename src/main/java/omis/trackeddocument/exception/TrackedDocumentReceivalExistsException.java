package omis.trackeddocument.exception;

import omis.exception.DuplicateEntityFoundException;

/**
 * Exception thrown when document exists.
 *
 * @author Yidong Li
 * @
 * @version 0.0.1 (Dec 12, 2017)
 * @since OMIS 3.0
 */
public class TrackedDocumentReceivalExistsException
		extends DuplicateEntityFoundException {
	
	private static final long serialVersionUID = 1L;

	/** Instantiates exception thrown when document receival exists. */
	public TrackedDocumentReceivalExistsException() {
		// Default instantiation
	}

	/**
	 * Instantiates exception thrown when document receival exists.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public TrackedDocumentReceivalExistsException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates exception thrown when document receival exists.
	 * 
	 * @param message message
	 */
	public TrackedDocumentReceivalExistsException(final String message) {
		super(message);
	}

	/**
	 * Instantiates exception thrown when document receival exists.
	 * 
	 * @param cause cause
	 */
	public TrackedDocumentReceivalExistsException(final Throwable cause) {
		super(cause);
	}
}