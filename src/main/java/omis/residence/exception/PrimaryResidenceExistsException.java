package omis.residence.exception;

import omis.exception.BusinessException;

/**
 * Thrown when primary residence already exists.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Feb 23, 2015)
 * @since  OMIS 3.0
 */
public class PrimaryResidenceExistsException extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	/** Instantiates default primary residence exists exception. */
	public PrimaryResidenceExistsException() {
		//Default constructor
	}

	/**
	 * Constructor.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public PrimaryResidenceExistsException(final String message,
			final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param message message
	 */
	public PrimaryResidenceExistsException(final String message) {
		super(message);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param cause cause
	 */
	public PrimaryResidenceExistsException(final Throwable cause) {
		super(cause);
	}
}
