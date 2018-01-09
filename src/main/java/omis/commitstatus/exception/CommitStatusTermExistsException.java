package omis.commitstatus.exception;

import omis.exception.DuplicateEntityFoundException;

/**
 * Exception thrown when commit status term exists.
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class CommitStatusTermExistsException
		extends DuplicateEntityFoundException {
	
	private static final long serialVersionUID = 1L;

	/** Instantiates exception thrown when commit status term exists. */
	public CommitStatusTermExistsException() {
		// Default instantiation
	}

	/**
	 * Instantiates exception with the specified message and cause thrown when 
	 * commit status term exists.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public CommitStatusTermExistsException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates exception with specified message thrown when commit status 
	 * term exists.
	 * 
	 * @param message message
	 */
	public CommitStatusTermExistsException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiates exception with specified cause thrown when commit status 
	 * term exists.
	 * 
	 * @param cause cause
	 */
	public CommitStatusTermExistsException(final Throwable cause) {
		super(cause);
	}
}