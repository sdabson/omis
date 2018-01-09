package omis.commitstatus.exception;

import omis.exception.DuplicateEntityFoundException;

/**
 * Exception thrown when commit status term exists after exception.
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class CommitStatusTermExistsAfterException
		extends DuplicateEntityFoundException {
	
	private static final long serialVersionUID = 1L;

	/** Instantiates exception thrown when commit status term exists after. 
	 *  exception
	 */
	public CommitStatusTermExistsAfterException() {
		// Default instantiation
	}

	/**
	 * Instantiates exception with the specified message and cause thrown when 
	 * commit status term exists after exception.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public CommitStatusTermExistsAfterException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates exception with specified message thrown when commit status 
	 * term exists after exception .
	 * 
	 * @param message message
	 */
	public CommitStatusTermExistsAfterException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiates exception with specified cause thrown when commit status term 
	 * exists after exception.
	 * 
	 * @param cause cause
	 */
	public CommitStatusTermExistsAfterException(final Throwable cause) {
		super(cause);
	}
}