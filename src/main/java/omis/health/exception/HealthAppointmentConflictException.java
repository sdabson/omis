package omis.health.exception;

import omis.exception.BusinessException;

/**
 * Thrown to indicate a conflict between health appointments.
 * 
 * <p>A conflict can occur if a required appointment before or after another
 * appointment is not scheduled at a time when it can be completed before
 * or after the latter appointment.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 6, 2014)
 * @since OMIS 3.0
 */
public class HealthAppointmentConflictException
		extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	/** Default constructor. */
	public HealthAppointmentConflictException() {
		// Default instantiation
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message business exception message
	 * @param cause business exception cause
	 */
	public HealthAppointmentConflictException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message business exception message
	 */
	public HealthAppointmentConflictException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause business exception cause
	 */
	public HealthAppointmentConflictException(final Throwable cause) {
		super(cause);
	}
}