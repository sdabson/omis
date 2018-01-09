package omis.offender.exception;

import omis.exception.BusinessException;

/**
 * Thrown when an offender exists.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class OffenderExistsException
		extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	/** Instantiates exception thrown when offender exists. */
	public OffenderExistsException() {
		// Default instantiation
	}

	/**
	 * Instantiates exception thrown when offender exists.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public OffenderExistsException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates exception thrown when offender exists.
	 * 
	 * @param message message
	 */
	public OffenderExistsException(final String message) {
		super(message);
	}

	/**
	 * Instantiates exception thrown when offender exists.
	 * 
	 * @param cause cause
	 */
	public OffenderExistsException(final Throwable cause) {
		super(cause);
	}
}