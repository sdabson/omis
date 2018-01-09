package omis.docket.exception;

import omis.exception.BusinessException;

/**
 * Thrown when docket exists.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class DocketExistsException
		extends BusinessException {
	
	private static final long serialVersionUID = 1L;

	/** Instantiates exception thrown when docket exists. */
	public DocketExistsException() {
		// Default instantiation
	}

	/**
	 * Instantiates exception thrown when docket exists.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public DocketExistsException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates exception thrown when docket exists.
	 * 
	 * @param message message
	 */
	public DocketExistsException(final String message) {
		super(message);
	}

	/**
	 * Instantiates exception thrown when docket exists.
	 * 
	 * @param cause cause
	 */
	public DocketExistsException(final Throwable cause) {
		super(cause);
	}
}