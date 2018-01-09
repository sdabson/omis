package omis.placementscreening.exception;

import omis.exception.BusinessException;

/** Thrown to indicate conflicting referral exists over category and date.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 19, 2015)
 * @since OMIS 3.0 */ 
public class ReferralDateCategoryConflictException extends BusinessException {
	private static final long serialVersionUID = 1L;
	
	/** Constructor. */
	public ReferralDateCategoryConflictException() { /* Do nothing. */ }
	
	/** Constructor.
	 * @param msg message. */
	public ReferralDateCategoryConflictException(final String msg) { 
		super(msg);
	}
	
	/** Constructor.
	 * @param msg message
	 * @param cause cause. */
	public ReferralDateCategoryConflictException(final String msg, 
			final Throwable cause) { 
		super(msg, cause); 
	}
	
	/** Constructor.
	 * @param cause cause. */
	public ReferralDateCategoryConflictException(final Throwable cause) {
		super(cause);
	}
}
