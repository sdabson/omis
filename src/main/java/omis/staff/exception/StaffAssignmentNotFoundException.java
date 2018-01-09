package omis.staff.exception;

import omis.exception.BusinessException;

/**
 * Thrown to indicate that an expected staff member assignment does not exist.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 8, 2014)
 * @since OMIS 3.0
 */
public class StaffAssignmentNotFoundException
		extends BusinessException {

	private static final long serialVersionUID = 1L;

	/** Default constructor. */
	public StaffAssignmentNotFoundException() {
		// Do nothing
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message business exception message
	 * @param cause business exception cause
	 */
	public StaffAssignmentNotFoundException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message business exception message
	 */
	public StaffAssignmentNotFoundException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause business exception cause
	 */
	public StaffAssignmentNotFoundException(final Throwable cause) {
		super(cause);
	}
}