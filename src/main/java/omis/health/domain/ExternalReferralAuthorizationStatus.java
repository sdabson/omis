package omis.health.domain;

/**
 * External Referral Authorization Status.
 * 
 * @author Joel Norris
 * @author Stephen Abson
 * @version 0.1.0 (Mar 31, 2014)
 * @since OMIS 3.0
 */
public enum ExternalReferralAuthorizationStatus {

	/** Approved. */
	APPROVED,
	
	/** Panel Review Required. */
	PANEL_REVIEW_REQUIRED,
	
	/** Internal alternative treatment. */
	INTERNAL_ALTERNATIVE_TREATMENT,
	
	/** External alternative treatment. */
	EXTERNAL_ALTERNATIVE_TREATMENT;
		
	/* Instantiates an external referral authorization status. */
	private ExternalReferralAuthorizationStatus() {
		// Default instantiation
	}
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name();
	}
}