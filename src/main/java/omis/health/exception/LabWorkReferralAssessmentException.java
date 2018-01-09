package omis.health.exception;

import omis.exception.BusinessException;

/**
 * Thrown during lab work referral assessments.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 14, 2014)
 * @since OMIS 3.0
 */
public class LabWorkReferralAssessmentException 
	extends BusinessException {

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a default instance of lab work referral assessment 
	 * exception.
	 */
	public LabWorkReferralAssessmentException() {
		//Default constructor.
	}
	
	/**
	 * Instantiates a lab work referral assessment exception with the
	 * specified message and cause.
	 * 
	 * @param message business exception message
	 * @param cause business exception cause
	 */
	public LabWorkReferralAssessmentException(final String message, 
			final Throwable cause) {
		super (message, cause);
	}
	
	/**
	 * Instantiates a lab work referral assessment exception with the
	 * specified message.
	 * 
	 * @param message business exception message
	 */
	public LabWorkReferralAssessmentException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiates a lab work referral assessment exception with the
	 * specified message.
	 * 
	 * @param cause business exception cause
	 */
	public LabWorkReferralAssessmentException(final Throwable cause) {
		super(cause);
	}
}