package omis.condition.domain;

/**
 * Condition Category.
 * 
 *@author Annie Wahl 
 *@version 0.1.1 (Nov 27, 2017)
 *@since OMIS 3.0
 *
 */
public enum ConditionCategory {
	
	/**
	 * Standard.
	 */
	STANDARD,
	
	/**
	 * Special.
	 */
	SPECIAL,
	
	/**
	 * Prerequisite.
	 */
	PREREQUISITE,
	
	/**
	 * Recommendation.
	 */
	RECOMMENDATION;
	
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
