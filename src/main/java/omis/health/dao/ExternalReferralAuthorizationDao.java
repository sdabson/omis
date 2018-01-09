package omis.health.dao;

import omis.dao.GenericDao;
import omis.health.domain.ExternalReferralAuthorization;
import omis.health.domain.ExternalReferralAuthorizationRequest;

/**
 * Data access object for external referral authorizations.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 7, 2014)
 * @since OMIS 3.0
 */
public interface ExternalReferralAuthorizationDao
		extends GenericDao<ExternalReferralAuthorization> {

	/**
	 * Returns the authorization for the request.
	 * 
	 * <p>Returns {@code null} if no authorization exists for the request.
	 * 
	 * @param request request
	 * @return authorization
	 */
	ExternalReferralAuthorization findByRequest(
			ExternalReferralAuthorizationRequest request);
}