package omis.health.exception;

import omis.exception.BusinessException;

/**
 * Thrown to indicate an issue relating to providers.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 30, 2014)
 * @since OMIS 3.0
 */
public class ProviderException extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	/** Default constructor. */
	public ProviderException() {
		// Default instantiation
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message business exception message
	 * @param cause business exception cause
	 */
	public ProviderException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message business exception message
	 */
	public ProviderException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause business exception cause
	 */
	public ProviderException(final Throwable cause) {
		super(cause);
	}
}