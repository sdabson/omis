package omis.bedplacement.exception;

import omis.exception.BusinessException;

/**
 * Thrown to indicate a bed placement is in conflict with another.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Feb 18, 2015)
 * @since OMIS 3.0
 */
public class BedPlacementConflictException extends BusinessException {

	private static final long serialVersionUID = 1L;

	/** Instantiates a default exception. */
	public BedPlacementConflictException() {
		// Default instantiation
	}

	/**
	 * Instantiates with message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public BedPlacementConflictException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates with message.
	 * 
	 * @param message message
	 */
	public BedPlacementConflictException(final String message) {
		super(message);
	}

	/**
	 * Instantiates with cause.
	 * 
	 * @param cause cause
	 */
	public BedPlacementConflictException(final Throwable cause) {
		super(cause);
	}
}