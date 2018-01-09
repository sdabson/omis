package omis.paroleboarditinerary.web.form;

/**
 * Parole board meeting site location.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Dec 4, 2017)
 * @since OMIS 3.0
 */
public enum BoardMeetingSiteLocation {

	/** Secure facility. */
	SECURE_FACILITY,
	/** Jail. */
	JAIL,
	/** Prerelease. */
	PRERELEASE,
	/** Community supervision office. */
	COMMUNITY_SUPERVISION_OFFICE,
	/** Treatment and sanction center. */
	TREATMENT_AND_SANCTION_CENTER;
	
	/**
	 * Returns the name of the {@code this}.
	 * 
	 * @return name of {@code this}
	 */
	public String getName() {
		return this.name();
	}
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	@Override
	public String toString() {
		return this.name();
	}
}