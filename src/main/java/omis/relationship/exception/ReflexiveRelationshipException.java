package omis.relationship.exception;

import omis.exception.BusinessException;

/**
 * Thrown if the persons of a relationship are reflexive
 * ({@code relationship.getFirstPerson().equals(relationship.getSecondPerson())}
 * and, in according with {@link Object#equals(Object)}, the reverse).
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 11, 2015)
 * @since OMIS 3.0
 */
public class ReflexiveRelationshipException
		extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	/** Instantiates a default exception. */
	public ReflexiveRelationshipException() {
		// Default instantiation
	}

	/**
	 * Instantiates with message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public ReflexiveRelationshipException(
			final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates with message.
	 * 
	 * @param message message
	 */
	public ReflexiveRelationshipException(final String message) {
		super(message);
	}

	/**
	 * Instantiates with cause.
	 * 
	 * @param cause cause
	 */
	public ReflexiveRelationshipException(final Throwable cause) {
		super(cause);
	}
}