package omis.exception;

/**
 * Thrown to indicate a conflict between dates, date representations and
 * date ranges.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 17, 2013)
 * @since OMIS 3.0
 */
public class DateConflictException extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	/** Instantiates a default date conflict exception. */
	public DateConflictException() {
		// Default instantiation
	}
	
	/**
	 * Instantiates with the specified message and cause.
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public DateConflictException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates with the specified message.
	 * 
	 * @param message message
	 */
	public DateConflictException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiates with the specified cause.
	 * 
	 * @param cause cause
	 */
	public DateConflictException(final Throwable cause) {
		super(cause);
	}
}