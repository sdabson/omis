package omis.health.exception;

/**
 * Thrown when a wrong external referral status reason is encountered.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 24, 2014)
 * @since OMIS 3.0
 */
public class WrongExternalReferralStatusReasonException
		extends ExternalReferralException {

	private static final long serialVersionUID = 1L;

	/** Default constructor. */
	public WrongExternalReferralStatusReasonException() {
		// Default instantiation
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message business exception message
	 * @param cause business exception cause
	 */
	public WrongExternalReferralStatusReasonException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message business exception message
	 */
	public WrongExternalReferralStatusReasonException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause business exception cause
	 */
	public WrongExternalReferralStatusReasonException(final Throwable cause) {
		super(cause);
	}
}