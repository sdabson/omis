package omis.relationship.exception;

import omis.exception.DuplicateEntityFoundException;

/**
 * Thrown when a relationship note category exists.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 7, 2017)
 * @since OMIS 3.0
 */
public class RelationshipNoteCategoryExistsException
		extends DuplicateEntityFoundException {

	private static final long serialVersionUID = 1L;

	/** Instantiates exception thrown when relationship note category exists. */
	public RelationshipNoteCategoryExistsException() {
		// Default instantiation
	}
	
	/**
	 * Instantiates exception thrown when relationship note category exists with
	 * message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public RelationshipNoteCategoryExistsException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates exception thrown when relationship note category exists with
	 * message.
	 * 
	 * @param message message
	 */
	public RelationshipNoteCategoryExistsException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiates exception thrown when relationship note category exists with
	 * cause.
	 * 
	 * @param cause cause
	 */
	public RelationshipNoteCategoryExistsException(final Throwable cause) {
		super(cause);
	}
}