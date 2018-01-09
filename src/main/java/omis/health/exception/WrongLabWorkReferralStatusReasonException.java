package omis.health.exception;

/**
 * Wrong lab work referral status reason exception.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 20, 2014)
 * @since OMIS 3.0
 *
 */
public class WrongLabWorkReferralStatusReasonException 
	extends LabWorkReferralException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a default instance of wrong lab work referral status
	 * reason exception. 
	 */
	public WrongLabWorkReferralStatusReasonException() {
		//Default constructor.
	}
	
	/**
	 * Instantiates a default instance of wrong lab work referral status
	 * reason exception with the specified message and throwable cause.
	 * 
	 * @param message business exception message
	 * @param cause throwable business exception cause
	 */
	public WrongLabWorkReferralStatusReasonException(final String message,
			final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates a default instance of wrong lab work referral status
	 * reason exception with the specified message.
	 * 
	 * @param message business exception message
	 */
	public WrongLabWorkReferralStatusReasonException(final String message) {
		super(message);
	}
	
	/**
	 * Instantiates a default instance of wrong lab work referral status
	 * reason exception with the specified throwable cause.
	 * 
	 * @param cause throwable business exception cause
	 */
	public WrongLabWorkReferralStatusReasonException(final Throwable cause) {
		super(cause);
	}
}