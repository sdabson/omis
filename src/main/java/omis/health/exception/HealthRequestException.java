package omis.health.exception;

import omis.exception.BusinessException;

/**
 * Thrown to indicate a health request business rule violation.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 16, 2014)
 * @since OMIS 3.0
 */
public class HealthRequestException
		extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	/** Default constructor. */
	public HealthRequestException() {
		// Default instantiation
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message business exception message
	 * @param cause business exception cause
	 */
	public HealthRequestException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message business exception message
	 */
	public HealthRequestException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause business exception cause
	 */
	public HealthRequestException(final Throwable cause) {
		super(cause);
	}
}
