package omis.health.exception;

import omis.exception.BusinessException;

/**
 * Thrown when an external referral related business rule is violated.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Aug 8, 2014)
 * @since OMIS 3.0
 */
public class ExternalReferralException
		extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	/** Default constructor. */
	public ExternalReferralException() {
		// Default instantiation
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message business exception message
	 * @param cause business exception cause
	 */
	public ExternalReferralException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message business exception message
	 */
	public ExternalReferralException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause business exception cause
	 */
	public ExternalReferralException(final Throwable cause) {
		super(cause);
	}
}