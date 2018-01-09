package omis.family.exception;

import omis.exception.BusinessException;

/**
 * Thrown when there is family association with overlapped dateRange existing.
 * 
 * @author Yidong Li
 * @author Sheronda Vaughn
 * @version 0.1.0 (Nov 24, 2017)
 * @since OMIS 3.0
 */
public class FamilyAssociationConflictException
		extends BusinessException {

	private static final long serialVersionUID = -1L;

	/** Default constructor. */
	public FamilyAssociationConflictException() {
		// Default instantiation
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public FamilyAssociationConflictException(final String message, 
			final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates with the specified message.
	 * 
	 * @param message message
	 */
	public FamilyAssociationConflictException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiates with the specified cause.
	 * 
	 * @param cause cause
	 */
	public FamilyAssociationConflictException(final Throwable cause) {
		super(cause);
	}
}