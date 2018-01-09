package omis.health.web.controller;

/**
 * Schedule lab work referral operation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 16, 2014)
 * @since OMIS 3.0
 */
public enum ScheduleLabWorkReferralOperation {
	
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