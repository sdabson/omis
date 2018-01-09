package omis.health.exception;

import omis.exception.BusinessException;

/**
 * Thrown when a lab work related business rule is violated.
 * 
 * @author Yiodng
 * @version 0.1.0 (Nov. 17, 2014)
 * @since OMIS 3.0
 */
public class LabWorkException extends BusinessException {
	private static final long serialVersionUID = 1L;
	
	/** Default constructor. */
	public LabWorkException() {
		// Default instantiation
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message business exception message
	 * @param cause business exception cause
	 */
	public LabWorkException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message business exception message
	 */
	public LabWorkException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause business exception cause
	 */
	public LabWorkException(final Throwable cause) {
		super(cause);
	}
}