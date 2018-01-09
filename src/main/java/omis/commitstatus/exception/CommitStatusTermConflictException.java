package omis.commitstatus.exception;

import omis.exception.DuplicateEntityFoundException;

/**
 * Exceptions thrown when commit status term conflict exists.
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class CommitStatusTermConflictException
		extends DuplicateEntityFoundException {
	
	private static final long serialVersionUID = 1L;

	/** Instantiates exception thrown when commit status term conflict exists. */
	public CommitStatusTermConflictException() {
		// Default instantiation
	}

	/**
	 * Instantiates exception with the specified message and cause thrown when 
	 * commit status term conflict exists.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public CommitStatusTermConflictException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates exception with specified message thrown when commit status 
	 * term conflict exists.
	 * 
	 * @param message message
	 */
	public CommitStatusTermConflictException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiates exception with specified cause thrown when commit status term 
	 * exists.
	 * 
	 * @param cause cause
	 */
	public CommitStatusTermConflictException(final Throwable cause) {
		super(cause);
	}
}