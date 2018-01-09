package omis.health.web.form;

/**
 * Referral type.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 14, 2014)
 * @since OMIS 3.0
 */
public enum ReferralType {

	/** Internal medical. */
	INTERNAL_MEDICAL,
	
	/** External medical. */
	EXTERNAL_MEDICAL,
	
	/** Lab work. */
	LAB,
	
	/** Internal dental. */
	INTERNAL_DENTAL,

	/** External dental. */
	EXTERNAL_DENTAL,
	
	/** Internal mental. */
	INTERNAL_MENTAL,
	
	/** Internal optical. */
	INTERNAL_OPTICAL,

	/** External optical. */
	EXTERNAL_OPTICAL,
	
	/** All. */
	ALL;
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name();
	}
	
	/**
	 * Returns values of referral types for follow ups.
	 * 
	 * @return values of referral types for follow ups
	 */
	public static ReferralType[] supportedValues() {
		return new ReferralType[] { INTERNAL_MEDICAL, EXTERNAL_MEDICAL, LAB };
	}
}