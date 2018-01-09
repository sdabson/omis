package omis.health.report;

/**
 * Referral type.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 9, 2014)
 * @since OMIS 3.0
 */
public enum ReferralType {

	/** Internal medical. */
	INTERNAL_MEDICAL,
	
	/** External medical. */
	EXTERNAL_MEDICAL,
	
	/** Lab work. */
	LAB;
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name();
	}
}