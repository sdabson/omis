package omis.offender.report;

/**
 * Activity Category.
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 6, 2017)
 *@since OMIS 3.0
 *
 */
public enum ActivityCategory {
	
	/**
	 * Created.
	 */
	CREATED,
	
	/**
	 * Updated.
	 */
	UPDATED;
	
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
