package omis.health.exception;

/**
 * Thrown when an internal referral status reason is of the wrong status type.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 24, 2014)
 * @since OMIS 3.0
 */
public class WrongInternalReferralStatusReasonException
		extends InternalReferralException {

	private static final long serialVersionUID = 1L;
	
	/** Default constructor. */
	public WrongInternalReferralStatusReasonException() {
		// Default instantiation
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message business exception message
	 * @param cause business exception cause
	 */
	public WrongInternalReferralStatusReasonException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message business exception message
	 */
	public WrongInternalReferralStatusReasonException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause business exception cause
	 */
	public WrongInternalReferralStatusReasonException(final Throwable cause) {
		super(cause);
	}
}