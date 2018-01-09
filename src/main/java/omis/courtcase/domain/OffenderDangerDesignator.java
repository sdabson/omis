package omis.courtcase.domain;

/**
 * Offender danger designator.
 * 
 * @author Josh Divine
 * @version 0.1.1 (May 9, 2017)
 * @since OMIS 3.0
 */
public enum OffenderDangerDesignator {
	
	/* Offender danger designator is dangerous */
	DANGEROUS,
	/* Offender danger designator is non dangerous */
	NON_DANGEROUS,
	/* Offender danger designator is pre 1977 */
	PRE_1977;
	
	/**
	 * Returns <code>this.name()</code>.
	 * 
	 * @return <code>this.name()</code>
	 */
	public String getName() {
		return this.name();
	}
}
