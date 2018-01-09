package omis.location.exception;

import omis.exception.BusinessException;

/**
 * Thrown when a location already exists.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Feb 23, 2015)
 * @since  OMIS 3.0
 */
public class LocationNotAllowedException extends BusinessException {

	private static final long serialVersionUID = 1L;

	/**Instantiates default location not allowed exception. */
	public LocationNotAllowedException() {
		//Default constructor
	}
	
	/**
	 * Constructor.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public LocationNotAllowedException(final String message,
			final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param message message
	 */
	public LocationNotAllowedException(final String message) {
		super(message);
	}
	
	/**
	 * Constructor. 
	 * 
	 * @param cause cause
	 */
	public LocationNotAllowedException(final Throwable cause) {
		super(cause);
	}
}
