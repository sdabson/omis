package omis.hearing.domain;

/**
 * HearingStatusCategory.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Apr 17, 2017)
 *@since OMIS 3.0
 *
 */
public enum HearingStatusCategory {
	
	PENDING(false),
	
	HELD(true),
	
	DELAYED(false),
	
	DISMISSED(true),
	
	UPHELD(true),
	
	MODIFIED(true);
	
	
	private final Boolean adjudicated;
	
	/**
	 * 
	 */
	private HearingStatusCategory(Boolean adjudicated) {
		this.adjudicated = adjudicated;
	}
	
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

	/**
	 * Returns the adjudicated
	 * @return adjudicated - Boolean
	 */
	public Boolean getAdjudicated() {
		return adjudicated;
	}
	
	
}
