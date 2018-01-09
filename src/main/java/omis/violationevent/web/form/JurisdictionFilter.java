package omis.violationevent.web.form;

/**
 * JurisdictionFilter.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 3, 2017)
 *@since OMIS 3.0
 *
 */
public enum JurisdictionFilter {
	
	SECURE_FACILITY,
	
	TREATMENT_CENTER,
	
	PRERELEASE_CENTER,
	
	ASSESSMENT_SANCTION_AND_RECOVATION_CENTER,
	
	COMMUNITY_SUPERVISION_OFFICE;
	
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
