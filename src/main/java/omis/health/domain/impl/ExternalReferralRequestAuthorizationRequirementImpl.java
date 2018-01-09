package omis.health.domain.impl;

import omis.health.domain.ExternalReferralAuthorizationRequest;
import omis.health.domain.ExternalReferralRequestAuthorizationRequirement;
import omis.health.domain.HealthRequest;

/**
 * Implementation of external referral request authorization requirement.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 11, 2014)
 * @since OMIS 3.0
 */
public class ExternalReferralRequestAuthorizationRequirementImpl
		implements ExternalReferralRequestAuthorizationRequirement {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private HealthRequest referralRequest;
	
	private ExternalReferralAuthorizationRequest authorizationRequest;
	
	/**
	 * Instantiates an implementation of external referral request authorization
	 * requirement. 
	 */
	public ExternalReferralRequestAuthorizationRequirementImpl() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setReferralRequest(final HealthRequest referralRequest) {
		this.referralRequest = referralRequest;
	}

	/** {@inheritDoc} */
	@Override
	public HealthRequest getReferralRequest() {
		return this.referralRequest;
	}

	/** {@inheritDoc} */
	@Override
	public void setAuthorizationRequest(
			final ExternalReferralAuthorizationRequest authorizationRequest) {
		this.authorizationRequest = authorizationRequest;
	}

	/** {@inheritDoc} */
	@Override
	public ExternalReferralAuthorizationRequest getAuthorizationRequest() {
		return this.authorizationRequest;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof ExternalReferralRequestAuthorizationRequirement)) {
			return false;
		}
		ExternalReferralRequestAuthorizationRequirement that
			= (ExternalReferralRequestAuthorizationRequirement) obj;
		if (this.getReferralRequest() == null) {
			throw new IllegalStateException(
					"External referral request required");
		}
		if (!this.getReferralRequest()
				.equals(that.getReferralRequest())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getReferralRequest() == null) {
			throw new IllegalStateException(
					"External referral request required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getReferralRequest().hashCode(); 
		return hashCode;
	}
}