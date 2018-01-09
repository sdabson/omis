package omis.facility.exception;

import omis.exception.BusinessException;

/**
 * Thrown to indicate that a facility and an element in its layout structure
 * (complex, unit, etc) are mismatched.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 8, 2014)
 * @since OMIS 3.0
 */
public class FacilityLayoutException
		extends BusinessException {

	private static final long serialVersionUID = 1L;

	/** Default constructor. */
	public FacilityLayoutException() {
		// Do nothing
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message business exception message
	 * @param cause business exception cause
	 */
	public FacilityLayoutException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message business exception message
	 */
	public FacilityLayoutException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause business exception cause
	 */
	public FacilityLayoutException(final Throwable cause) {
		super(cause);
	}
}