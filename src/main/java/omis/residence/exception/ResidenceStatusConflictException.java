package omis.residence.exception;

import omis.exception.BusinessException;

/**
 * Thrown when residence term already exists.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Feb 23, 2015)
 * @since  OMIS 3.0
 */
public class ResidenceStatusConflictException extends BusinessException {

	private static final long serialVersionUID = 1L;

	/** Instantiates default residence term exists exception. */
	public ResidenceStatusConflictException() {
		//Default constructor.
	}
	
	/**
	 * Constructor. 
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public ResidenceStatusConflictException(final String message, 
			final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param message message
	 */
	public ResidenceStatusConflictException(final String message) {
		super(message);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param cause cause
	 */
	public ResidenceStatusConflictException(final Throwable cause) {
		super(cause);
	}
}
