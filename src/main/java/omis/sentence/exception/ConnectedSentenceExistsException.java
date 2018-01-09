package omis.sentence.exception;

import omis.exception.BusinessException;

/**
 * Thrown when connections to a sentence exists.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class ConnectedSentenceExistsException
		extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates exception thrown when connections to a sentence exist.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public ConnectedSentenceExistsException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates exception thrown when connections to a sentence exist.
	 * 
	 * @param message message
	 */
	public ConnectedSentenceExistsException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiates exception thrown when connections to a sentence exist. 
	 * 
	 * @param cause cause
	 */
	public ConnectedSentenceExistsException(final Throwable cause) {
		super(cause);
	}
	
	/** Instantiates exception thrown when connections to a sentence exist. */
	public ConnectedSentenceExistsException() {
		// Default instantiation
	}
}