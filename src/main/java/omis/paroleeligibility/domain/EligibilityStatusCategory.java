package omis.paroleeligibility.domain;

/**
 * Eligibility status category
 *
 * @author Trevor Isles
 * @version 0.1.0 (Nov 1, 2017)
 * @since OMIS 3.0
 */

public enum EligibilityStatusCategory {

	/** Eligibility category is Appearing. */
	APPEARING,
	
	/** Eligibility category is Waived. */
	WAIVED,
	
	/** Eligibility category is Ineligible. */
	INELIGIBLE;
	
	/**
	 * Returns <code>this.name()</code>.
	 * 
	 * @return <code>this.name()</code>
	 */
	public String getName() {
		return this.name();
	}
	
}
