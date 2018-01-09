package omis.health.exception;

/**
 * Thrown during external referral assessments.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 24, 2014)
 * @since OMIS 3.0
 */
public class ExternalReferralAssessmentException
		extends ExternalReferralException {

	private static final long serialVersionUID = 1L;

	/** Default constructor. */
	public ExternalReferralAssessmentException() {
		// Default instantiation
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message business exception message
	 * @param cause business exception cause
	 */
	public ExternalReferralAssessmentException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message business exception message
	 */
	public ExternalReferralAssessmentException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause business exception cause
	 */
	public ExternalReferralAssessmentException(final Throwable cause) {
		super(cause);
	}
}