package omis.caseload.web.form;

/**
 * OfficeFilter.java
 * 
 *@author Trevor Isles
 *@version 0.1.0 (Oct 24, 2018)
 *@since OMIS 3.0
 *
 */
public enum OfficeFilter {
	
	COMMUNITY_SUPERVISION_OFFICE,
	
	INSTITUTIONAL_PROBATION_AND_PAROLE_OFFICE;
	
	/**
	 * Returns the name.
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
