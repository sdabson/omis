package omis.supervisionfee.web.form;

/**
 * Supervision fee requirement item authority.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Nov 24, 2014)
 * @since OMIS 3.0
 */
public enum SupervisionFeeRequirementItemAuthority {
	
	/** Court. */
	COURT,
	
	/** Officer */
	OFFICER;
	
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
