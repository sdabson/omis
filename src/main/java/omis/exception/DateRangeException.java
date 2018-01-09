package omis.exception;

/**
 * Thrown to indicate a problem with a range of dates.
 * 
 * <p>The range of dates may or may not be a {@code DateRange} instance. 
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jan 6, 2015)
 * @since OMIS 3.0
 */
public class DateRangeException extends BusinessException {

	private static final long serialVersionUID = 1L;

	/** Default constructor. */
	public DateRangeException() {
		// Do nothing
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message business exception message
	 * @param cause business exception cause
	 */
	public DateRangeException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message business exception message
	 */
	public DateRangeException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause business exception cause
	 */
	public DateRangeException(final Throwable cause) {
		super(cause);
	}
}