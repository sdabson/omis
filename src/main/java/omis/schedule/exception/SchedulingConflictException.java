package omis.schedule.exception;

import omis.exception.BusinessException;


/**
 * Used to indicate an attempt to schedule a proposed event that has conflicts
 * with one or more conflict events.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Feb 25, 2013)
 * @since OMIS 3.0
 */
public class SchedulingConflictException extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	/** Instantiates a default scheduling conflict exception. */
	public SchedulingConflictException() {
		// Do nothing
	}
	
	/**
	 * Instantiates a scheduling conflict exception with the specified message
	 * and cause.
	 * 
	 * @param message scheduling conflict exception message
	 * @param cause scheduling conflict exception cause
	 */
	public SchedulingConflictException(final String message,
			final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates a scheduling conflict exception with the specified message.
	 * 
	 * @param message business exception message
	 */
	public SchedulingConflictException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiates a scheduling conflict exception with the specified cause.
	 * 
	 * @param cause business exception cause
	 */
	public SchedulingConflictException(final Throwable cause) {
		super(cause);
	}
}