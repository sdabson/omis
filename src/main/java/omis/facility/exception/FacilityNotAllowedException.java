package omis.facility.exception;

import omis.exception.BusinessException;

/**
 * Thrown if a facility is not allowed.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Feb 17, 2015)
 * @since OMIS 3.0
 */
public class FacilityNotAllowedException
		extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	/** Instantiates default exception. */
	public FacilityNotAllowedException() {
		// Default constructor
	}

	/**
	 * Instantiates with message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public FacilityNotAllowedException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates with message.
	 * 
	 * @param message message
	 */
	public FacilityNotAllowedException(final String message) {
		super(message);
	}

	/**
	 * Instantiates with cause.
	 * 
	 * @param cause cause
	 */
	public FacilityNotAllowedException(final Throwable cause) {
		super(cause);
	}
}