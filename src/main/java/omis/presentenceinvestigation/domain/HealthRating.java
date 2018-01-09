package omis.presentenceinvestigation.domain;

/**
 * Health rating.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 30, 2017)
 * @since OMIS 3.0
 */
public enum HealthRating {
	
	/* Good. */
	GOOD,
	
	/* Medium. */
	MEDIUM,
	
	/* Poor. */
	POOR;
	
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