package omis.health.exception;

/**
 * Lab work referral cancellation exception.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 20, 2014)
 * @since OMIS 3.0
 *
 */
public class LabWorkReferralCancellationException 
	extends LabWorkReferralException {

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a default instance of lab work referral cancellation 
	 * exception.
	 */
	public LabWorkReferralCancellationException() {
		//Default constructor.
	}
	
	/**
	 * Instantiates a lab work referral cancellation exception with the 
	 * specified message and throwable cause.
	 * 
	 * @param message business exception message
	 * @param cause throwable business exception cause
	 */
	public LabWorkReferralCancellationException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates a lab work referral cancellation exception with the 
	 * specified message.
	 * 
	 * @param message business exception message
	 */
	public LabWorkReferralCancellationException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiates a lab work referral cancellation exception with the 
	 * specified throwable cause.
	 * 
	 * @param cause throwable business exception cause
	 */
	public LabWorkReferralCancellationException(final Throwable cause) {
		super(cause);
	}
}