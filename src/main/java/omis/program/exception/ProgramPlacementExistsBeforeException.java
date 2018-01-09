package omis.program.exception;

import omis.exception.BusinessException;

/**
 * Thrown when program placements exist before a date.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class ProgramPlacementExistsBeforeException
		extends BusinessException {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates exception thrown when program placements exist before
	 * a date.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public ProgramPlacementExistsBeforeException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates exception thrown when program placements exist before
	 * a date.
	 * 
	 * @param message message
	 */
	public ProgramPlacementExistsBeforeException(final String message) {
		super(message);
	}

	/**
	 * Instantiates exception thrown when program placements exist before
	 * a date.
	 * 
	 * @param cause cause
	 */
	public ProgramPlacementExistsBeforeException(final Throwable cause) {
		super(cause);
	}
	
	/**
	 * Instantiates exception thrown when program placements exist before
	 * a date.
	 */
	public ProgramPlacementExistsBeforeException() {
		// Default instantiation
	}
}