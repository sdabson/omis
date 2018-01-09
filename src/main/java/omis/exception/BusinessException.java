package omis.exception;

/**
 * Thrown to indicate a business rule violation.
 * 
 * @author Stephen Abson
 * @version 0.1.0, (Dec 31, 2011)
 * @since OMIS 3.0
 */
public class BusinessException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/** Default constructor. */
	public BusinessException() {
		// Do nothing
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message business exception message
	 * @param cause business exception cause
	 */
	public BusinessException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message business exception message
	 */
	public BusinessException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause business exception cause
	 */
	public BusinessException(final Throwable cause) {
		super(cause);
	}
}