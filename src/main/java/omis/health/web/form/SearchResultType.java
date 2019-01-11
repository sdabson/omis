package omis.health.web.form;

/**
 * Search result type.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Dec 5, 2018)
 * @since OMIS 3.0
 */
public enum SearchResultType {

	/** Authorized referral request. */
	AUTHORIZED_REFERRAL_REQUEST,
	
	/** External referral request. */
	EXTERNAL_REFERRAL_REQUEST,
	
	/** Health Request. */
	HEALTH_REQUEST,
	
	/** Internal referral request. */
	INTERNAL_REFERRAL_REQUEST,
	
	/** Pending referral authorization request. */
	PENDING_REFERRAL_AUTHORIZATION_REQUEST,
	
	/** Referral request. */
	REFERRAL_REQUEST;
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name();
	}
}