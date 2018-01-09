package omis.hearing.web.form;

/**
 * GoToOption.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 26, 2017)
 *@since OMIS 3.0
 *
 */
public enum GoToOption {
	
	RESOLUTION,
	
	SCHEDULE,
	
	VIOLATIONS_LIST,
	
	HEARINGS_LIST,
	
	ADJUDICATE;
	
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
