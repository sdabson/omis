package omis.health.exception;

import omis.exception.BusinessException;

/**
 * Thrown to indicate lab work referral business rule violation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 20, 2014)
 * @since OMIS 3.0
 *
 */
public class LabWorkReferralException extends BusinessException {

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a default instance of lab work referral exception.
	 */
	public LabWorkReferralException() {
		//Default constructor.
	}
	
	/**
	 * Instantiate a lab work referral exception with the specified message
	 * and throwable cause.
	 * 
	 * @param message business exception message
	 * @param cause throwable business exception cause
	 */
	public LabWorkReferralException(final String message, 
			final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates a lab work referral exception with the specified message.
	 * 
	 * @param message business exception message
	 */
	public LabWorkReferralException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiates a lab work referral exception with the specified throwable
	 * cause.
	 * 
	 * @param cause throwable business exception cause
	 */
	public LabWorkReferralException(final Throwable cause) {
		super(cause);
	}
}