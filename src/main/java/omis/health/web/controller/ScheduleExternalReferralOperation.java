package omis.health.web.controller;

/**
 * Operation to perform to schedule an external referral.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Aug 8, 2014)
 * @since OMIS 3.0
 */
public enum ScheduleExternalReferralOperation {

	/** Schedule a new external referral. */
	SCHEDULE,
	
	/** Reschedules an existing external referral. */
	RESCHEDULE,
	
	/** Edits an existing external referral. */
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