package omis.courtcase.domain;

/**
 * Jurisdiction authority.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Feb 9, 2017)
 * @since OMIS 3.0
 */
public enum JurisdictionAuthority {

	/* Jurisdiction authority is state */
	STATE,
	/* Jurisdiction authority is federal */
	FEDERAL;
	
	/**
	 * Returns <code>this.name()</code>.
	 * 
	 * @return <code>this.name()</code>
	 */
	public String getName() {
		return this.name();
	}
}
