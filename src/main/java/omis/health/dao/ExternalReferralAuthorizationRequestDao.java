package omis.health.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.facility.domain.Facility;
import omis.health.domain.ExternalReferralAuthorizationRequest;

/**
 * Data access object for external referral authorization requests.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 7, 2014)
 * @since OMIS 3.0
 */
public interface ExternalReferralAuthorizationRequestDao
		extends GenericDao<ExternalReferralAuthorizationRequest> {

	/**
	 * Returns requests by facility.
	 * 
	 * @param facility facility
	 * @return requests by facility
	 */
	List<ExternalReferralAuthorizationRequest> findByFacility(
			Facility facility);
}