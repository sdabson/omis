package omis.health.dao;

import omis.dao.GenericDao;
import omis.health.domain.ExternalReferralAuthorizationRequest;
import omis.health.domain.ExternalReferralRequestAuthorizationRequirement;
import omis.health.domain.HealthRequest;

/**
 * Data access object for external referral request authorization requirements.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 15, 2014)
 * @since OMIS 3.0
 */
public interface ExternalReferralRequestAuthorizationRequirementDao
		extends GenericDao<ExternalReferralRequestAuthorizationRequirement> {

	/**
	 * Returns requirement for request.
	 * 
	 * @param healthRequest health request
	 * @return requirement for request
	 */
	ExternalReferralRequestAuthorizationRequirement find(
			HealthRequest healthRequest);
	
	/**
	 * Returns requirement for authorization request.
	 * 
	 * @param authorizationRequest authorization request
	 * @return requirement for authorization request
	 */
	ExternalReferralRequestAuthorizationRequirement findByAuthorizationRequest(
			ExternalReferralAuthorizationRequest authorizationRequest);
}