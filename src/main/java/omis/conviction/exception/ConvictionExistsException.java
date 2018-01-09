package omis.conviction.exception;

import omis.exception.BusinessException;

/**
 * Thrown when conviction exists.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class ConvictionExistsException
		extends BusinessException {
	
	private static final long serialVersionUID = 1L;

	/** Instantiates exception thrown when conviction exists. */
	public ConvictionExistsException() {
		// Default instantiation
	}

	/**
	 * Instantiates exception thrown when conviction exists.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public ConvictionExistsException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates exception thrown when conviction exists.
	 * 
	 * @param message message
	 */
	public ConvictionExistsException(final String message) {
		super(message);
	}

	/**
	 * Instantiates exception thrown when conviction exists.
	 * 
	 * @param cause cause
	 */
	public ConvictionExistsException(final Throwable cause) {
		super(cause);
	}
}