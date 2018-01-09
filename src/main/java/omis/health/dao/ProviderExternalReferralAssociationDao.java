package omis.health.dao;

import omis.dao.GenericDao;
import omis.health.domain.ExternalReferral;
import omis.health.domain.ProviderExternalReferralAssociation;

/**
 * Data access object for provider external referral associations.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 7, 2014)
 * @since OMIS 3.0
 */
public interface ProviderExternalReferralAssociationDao
		extends GenericDao<ProviderExternalReferralAssociation> {

	/**
	 * Returns primary provider association for external referral.
	 * 
	 * @param referral referral
	 * @return primary provider association for referral
	 */
	ProviderExternalReferralAssociation findPrimaryForReferral(
			ExternalReferral referral);
}