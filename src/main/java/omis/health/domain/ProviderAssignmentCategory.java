package omis.health.domain;

/**
 * Provider assignment category.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Mar 31, 2014)
 * @since OMIS 3.0
 */
public enum ProviderAssignmentCategory {

	/** Nurse. */
	NURSE,
	
	/** MD. */
	MD,
	
	/** Lab Specialist. */
	LAB_SPECIALIST,
	
	/** Psychiatrist. */
	PSYCHIATRIST,
	
	/** Optometrist. */
	OPTOMETRIST;
	
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
