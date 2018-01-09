package omis.presentenceinvestigation.domain;

/**
 * PresentenceInvestigationTaskAssociationCategory.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 11, 2017)
 *@since OMIS 3.0
 *
 */
public enum PresentenceInvestigationTaskAssociationCategory {
	
	BASIC_INFORMATION,
	
	LEGAL,
	
	COMPLIANCE,
	
	CASE_MANAGEMENT,
	
	RELATIONSHIPS;
	
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
