package omis.health.exception;

/**
 * Thrown during internal referral cancellation.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 24, 2014)
 * @since OMIS 3.0
 */
public class InternalReferralCancellationException 
		extends InternalReferralException {
	
	private static final long serialVersionUID = 1L;
	
	/** Default constructor. */
	public InternalReferralCancellationException() {
		// Default instantiation
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message business exception message
	 * @param cause business exception cause
	 */
	public InternalReferralCancellationException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message business exception message
	 */
	public InternalReferralCancellationException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause business exception cause
	 */
	public InternalReferralCancellationException(final Throwable cause) {
		super(cause);
	}
}
