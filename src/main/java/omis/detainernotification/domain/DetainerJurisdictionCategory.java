/**
 * DetainerJurisdictionCategory
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 8, 2016)
 *@since OMIS 3.0
 *
 */
package omis.detainernotification.domain;


public enum DetainerJurisdictionCategory {
	
	/** Federal. */
	FEDERAL,
	
	/** In state. */
	IN_STATE,
	
	/** Out of state. */
	OUT_OF_STATE,
	
	/** US immigration. */
	US_IMMIGRATION;
	
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
