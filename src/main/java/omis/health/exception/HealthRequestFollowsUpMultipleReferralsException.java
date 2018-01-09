package omis.health.exception;

/**
 * Thrown when a health request follows up more than one referral
 * (and should not).
 * 
 * <p>Perhaps controversially, this exception is treated as a business rule
 * violation and therefore checked exception. This would better be dealt
 * with as data integrity violation resulting in an unchecked exception.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Aug 14, 2014)
 * @since OMIS 3.0
 */
public class HealthRequestFollowsUpMultipleReferralsException
		extends HealthRequestException {

	private static final long serialVersionUID = 1L;
	
	/** Default constructor. */
	public HealthRequestFollowsUpMultipleReferralsException() {
		// Default instantiation
	}
	
	/**
	 * Instantiate with the specified message and cause.
	 * 
	 * @param message business exception message
	 * @param cause business exception cause
	 */
	public HealthRequestFollowsUpMultipleReferralsException(
			final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiate with the specified message.
	 * 
	 * @param message business exception message
	 */
	public HealthRequestFollowsUpMultipleReferralsException(
			final String message) {
		super(message);
	}
	
	/**
	 * Instantiate with the specified cause.
	 * 
	 * @param cause business exception cause
	 */
	public HealthRequestFollowsUpMultipleReferralsException(
			final Throwable cause) {
		super(cause);
	}
}