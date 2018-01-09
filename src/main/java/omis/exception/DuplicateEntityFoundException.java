package omis.exception;

/**
 * Thrown when the same entity exists more than once.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 29, 2014)
 * @since OMIS 3.0
 */
public class DuplicateEntityFoundException
		extends BusinessException {

	private static final long serialVersionUID = -1L;

	/** Instantiates a default duplicate entity found exception. */
	public DuplicateEntityFoundException() {
		// Default instantiation
	}
	
	/**
	 * Instantiates with the specified message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public DuplicateEntityFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates with the specified message.
	 * 
	 * @param message message
	 */
	public DuplicateEntityFoundException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiates with the specified cause.
	 * 
	 * @param cause cause
	 */
	public DuplicateEntityFoundException(final Throwable cause) {
		super(cause);
	}
}