package omis.relationship.exception;

import omis.exception.DuplicateEntityFoundException;

/**
 * Thrown when relationship note exists.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 7, 2017)
 * @since OMIS 3.0
 */
public class RelationshipNoteExistsException
		extends DuplicateEntityFoundException {

	private static final long serialVersionUID = 1L;

	/** Instantiates exception thrown when relationship note exists. */
	public RelationshipNoteExistsException() {
		// Default instantiation
	}
	
	/**
	 * Instantiates exception thrown when relationship note exists with message
	 * and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public RelationshipNoteExistsException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates exception thrown when relationship note exists with message.
	 * 
	 * @param message message
	 */
	public RelationshipNoteExistsException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiates exception thrown when relationship note exists with cause.
	 * 
	 * @param cause cause
	 */
	public RelationshipNoteExistsException(final Throwable cause) {
		super(cause);
	}
}