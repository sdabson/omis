package omis.program.exception;

import omis.exception.BusinessException;

/**
 * Thrown when program placements exist after a date.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class ProgramPlacementExistsAfterException
		extends BusinessException {

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates exception thrown when program placement exists after a date.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public ProgramPlacementExistsAfterException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates exception thrown when program placement exists after a date.
	 * 
	 * @param message message
	 */
	public ProgramPlacementExistsAfterException(final String message) {
		super(message);
	}

	/**
	 * Instantiates exception thrown when program placement exists after a date.
	 * 
	 * @param cause cause
	 */
	public ProgramPlacementExistsAfterException(final Throwable cause) {
		super(cause);
	}
	
	/**
	 * Instantiates exception thrown when program placement exists after a date.
	 */
	public ProgramPlacementExistsAfterException() {
		// Default instantiation
	}
}