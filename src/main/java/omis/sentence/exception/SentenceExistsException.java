package omis.sentence.exception;

import omis.exception.BusinessException;

/**
 * Exception thrown when sentence exists.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class SentenceExistsException
		extends BusinessException {

	private static final long serialVersionUID = 1L;

	/** Instantiates exception thrown when sentence exists. */
	public SentenceExistsException() {
		// Default instantiation
	}

	/**
	 * Instantiates exception thrown when sentence exists.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public SentenceExistsException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates exception thrown when sentence exists.
	 * 
	 * @param message message
	 */
	public SentenceExistsException(final String message) {
		super(message);
	}

	/**
	 * Instantiates exception thrown when sentence exists.
	 * 
	 * @param cause cause
	 */
	public SentenceExistsException(final Throwable cause) {
		super(cause);
	}
}