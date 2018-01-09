package omis.hearing.domain;

/**
 * LocationType.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Apr 17, 2017)
 *@since OMIS 3.0
 *
 */
public enum LocationType {
	
	JAILS,
	
	COMMUNITY_SUPERVISION_OFFICES,
	
	PRERELEASES,
	
	TREATMENT_AND_SANCTION_CENTERS,
	
	FACILITIES;
	
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
