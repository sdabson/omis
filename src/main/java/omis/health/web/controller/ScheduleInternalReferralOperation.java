package omis.health.web.controller;

import java.io.Serializable;

/**
 * Operation to perform to schedule a referral.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 14, 2014)
 * @since OMIS 3.0
 */
public enum ScheduleInternalReferralOperation
		implements Serializable {

	/** Schedule a new internal referral. */
	SCHEDULE,
	
	/** Reschedules an existing internal referral. */
	RESCHEDULE,
	
	/** Edits an existing internal referral. */
	EDIT;
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name();
	}
}