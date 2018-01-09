package omis.health.exception;

/**
 * Thrown during internal referral assessments.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 24, 2014)
 * @since OMIS 3.0
 */
public class InternalReferralAssessmentException
		extends InternalReferralException {
	
	private static final long serialVersionUID = 1L;

	/** Default constructor. */
	public InternalReferralAssessmentException() {
		// Default instantiation
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message business exception message
	 * @param cause business exception cause
	 */
	public InternalReferralAssessmentException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message business exception message
	 */
	public InternalReferralAssessmentException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause business exception cause
	 */
	public InternalReferralAssessmentException(final Throwable cause) {
		super(cause);
	}
}