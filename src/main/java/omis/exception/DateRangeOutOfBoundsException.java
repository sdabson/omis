package omis.exception;

/**
 * Thrown to indicate that one range of dates is out of the bounds of one or
 * more range of dates.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Feb 5, 2015)
 * @since OMIS 3.0
 */
public class DateRangeOutOfBoundsException
		extends BusinessException {

	private static final long serialVersionUID = 1L;

	/** Default constructor. */
	public DateRangeOutOfBoundsException() {
		// Do nothing
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message business exception message
	 * @param cause business exception cause
	 */
	public DateRangeOutOfBoundsException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message business exception message
	 */
	public DateRangeOutOfBoundsException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause business exception cause
	 */
	public DateRangeOutOfBoundsException(final Throwable cause) {
		super(cause);
	}
}