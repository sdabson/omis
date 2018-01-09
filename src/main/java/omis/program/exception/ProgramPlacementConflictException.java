package omis.program.exception;

import omis.exception.BusinessException;

/**
 * Thrown when program placements conflict.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class ProgramPlacementConflictException
		extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates exception thrown when program placements conflict.
	 * 
	 * @param cause cause
	 * @param message message
	 */
	public ProgramPlacementConflictException(
			final Throwable cause, final String message) {
		super(message, cause);
	}
	
	/**
	 * Instantiates exception thrown when program placements conflict.
	 * 
	 * @param cause cause
	 */
	public ProgramPlacementConflictException(final Throwable cause) {
		super(cause);
	}
	
	/**
	 * Instantiates exception thrown when program placements conflict.
	 * 
	 * @param message message
	 */
	public ProgramPlacementConflictException(final String message) {
		super(message);
	}
	
	/** Instantiates exception thrown when program placements conflict. */
	public ProgramPlacementConflictException() {
		// Default instantiation
	}
}