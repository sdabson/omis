package omis.bedplacement.exception;

/**
 * Thrown to indicate a bed is occupied.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Feb 18, 2015)
 * @since OMIS 3.0
 */
public class BedOccupiedException extends BedPlacementConflictException {

	private static final long serialVersionUID = 1L;

	/** Instantiates a default exception. */
	public BedOccupiedException() {
		// Default instantiation
	}

	/**
	 * Instantiates with message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public BedOccupiedException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates with message.
	 * 
	 * @param message message
	 */
	public BedOccupiedException(final String message) {
		super(message);
	}

	/**
	 * Instantiates with cause.
	 * 
	 * @param cause cause
	 */
	public BedOccupiedException(final Throwable cause) {
		super(cause);
	}
}