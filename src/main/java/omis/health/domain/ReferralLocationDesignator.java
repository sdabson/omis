package omis.health.domain;

/**
 * Referral Location Designator
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 2, 2014)
 * @since OMIS 3.0
 */
public enum ReferralLocationDesignator {
	
	/** Housing Unit. */
	HOUSING_UNIT,
	
	/** Main Infirmary. */
	MAIN_INFIRMARY,
	
	/** Office Visit. */
	OFFICE_VISIT;
	
	/**
	 * 	Returns the name.
	 * 
	 * @return name
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
