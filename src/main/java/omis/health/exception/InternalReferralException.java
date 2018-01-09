package omis.health.exception;

import omis.exception.BusinessException;

/**
 * Thrown to indicate an internal referral business rule violation.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 20, 2014)
 * @since OMIS 3.0
 */
public class InternalReferralException
		extends BusinessException {

	private static final long serialVersionUID = 1L;
	
	/** Default constructor. */
	public InternalReferralException() {
		// Default instantiation
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message business exception message
	 * @param cause business exception cause
	 */
	public InternalReferralException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message business exception message
	 */
	public InternalReferralException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause business exception cause
	 */
	public InternalReferralException(final Throwable cause) {
		super(cause);
	}
}