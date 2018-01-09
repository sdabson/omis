package omis.bedplacement.exception;

/**
 * Thrown to indicate that a bed placement's date is in conflict.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Feb 18, 2015)
 * @since OMIS 3.0
 */
public class BedPlacementDateConflictException 
	extends BedPlacementConflictException {
	
	private static final long serialVersionUID = 1L;

	/** Instantiates a default exception. */
	public BedPlacementDateConflictException() {
		// Default instantiation
	}

	/**
	 * Instantiates with message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public BedPlacementDateConflictException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates with message.
	 * 
	 * @param message message
	 */
	public BedPlacementDateConflictException(final String message) {
		super(message);
	}

	/**
	 * Instantiates with cause.
	 * 
	 * @param cause cause
	 */
	public BedPlacementDateConflictException(final Throwable cause) {
		super(cause);
	}
}