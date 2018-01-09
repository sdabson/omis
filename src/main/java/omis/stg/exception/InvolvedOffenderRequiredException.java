package omis.stg.exception;

import omis.exception.BusinessException;

/**
 * Thrown when an involved offender does not exist.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Nov 29, 2016)
 * @since OMIS 3.0
 */
public class InvolvedOffenderRequiredException
		extends BusinessException {

	private static final long serialVersionUID = -1L;
	
	/** Instantiates a default involved offender required exception. */
	public InvolvedOffenderRequiredException() {
		// Default instantiation
	}
	
	/**
	 * Instantiates with the specified message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public InvolvedOffenderRequiredException(
			final String message, 
			final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates with the specified message.
	 * 
	 * @param message message
	 */
	public InvolvedOffenderRequiredException(
			final String message) {
		super(message);
	}
	
	/**
	 * Instantiates with the specified cause.
	 * 
	 * @param cause cause
	 */
	public InvolvedOffenderRequiredException(
			final Throwable cause) {
		super(cause);
	}
	
}
