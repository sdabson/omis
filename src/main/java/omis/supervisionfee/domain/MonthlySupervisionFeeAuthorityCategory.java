package omis.supervisionfee.domain;

/**
 * Monthly supervision fee authority category.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 27, 2014)
 * @since OMIS 3.0
 */
public enum MonthlySupervisionFeeAuthorityCategory {

	/** Standard Offenders. */
	STANDARD,
	
	/** Felony Drug Offense Conviction. */
	FELONY_DRUG_OFFENSE_CONVICTION,
	
	/** ISP-Intensive Supervision Program Offenders. */
	ISP_INTENSIVE_SUPERVISION_PROGRAM,
	
	/** ISP Sanction Offenders. */
	ISP_SANCTION,
	
	/** Satellite-Based Monitoring. */
	SATELLITE_BASED_MONITORING;
	
	/**
	 * Returns the instance name.
	 * 
	 * @return instance name
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
