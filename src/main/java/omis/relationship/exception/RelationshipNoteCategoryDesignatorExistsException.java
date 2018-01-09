package omis.relationship.exception;

import omis.exception.DuplicateEntityFoundException;

/**
 * Thrown when relationship note category designator exists.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 8, 2017)
 * @since OMIS 3.0
 */
public class RelationshipNoteCategoryDesignatorExistsException
		extends DuplicateEntityFoundException {

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates exception thrown when relationship note category designator
	 * exists.
	 */
	public RelationshipNoteCategoryDesignatorExistsException() {
		// Default instantiation
	}
	
	/**
	 * Instantiates exception thrown when relationship note category designator
	 * exists with message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public RelationshipNoteCategoryDesignatorExistsException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates exception thrown when relationship note category designator
	 * exists with message
	 * 
	 * @param message message
	 */
	public RelationshipNoteCategoryDesignatorExistsException(
			final String message) {
		super(message);
	}
	
	/**
	 * Instantiates exception thrown when relationship note category designator
	 * exists with cause.
	 * 
	 * @param cause cause
	 */
	public RelationshipNoteCategoryDesignatorExistsException(
			final Throwable cause) {
		super(cause);
	}
}