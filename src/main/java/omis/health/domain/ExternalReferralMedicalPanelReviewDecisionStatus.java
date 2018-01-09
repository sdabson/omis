package omis.health.domain;

/**
 * Status of an external referral medical panel review decision.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Aug 22, 2014)
 * @since OMIS 3.0
 */
public enum ExternalReferralMedicalPanelReviewDecisionStatus {

	/** Approved. */
	APPROVED,
	
	/** Internal alternative treatment. */
	INTERNAL_ALTERNATIVE_TREATMENT,
	
	/** External alternative treatment. */
	EXTERNAL_ALTERNATIVE_TREATMENT;
	
	/**
	 * Returns the instance name of {@code this}.
	 * 
	 * @return instance name of {@code this}
	 */
	public String getName() {
		return this.name();
	}
}