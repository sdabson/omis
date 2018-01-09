package omis.health.dao;

import omis.dao.GenericDao;
import omis.health.domain.InternalReferral;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderInternalReferralAssociation;

/**
 * Data access object for provider inside referral associations.
 *
 * @author Stephen Abson
 * @author Ryan Johns
 * @version 0.1.0 (Apr 28, 2014)
 * @since OMIS 3.0
 */
public interface ProviderInternalReferralAssociationDao
		extends GenericDao<ProviderInternalReferralAssociation> {

	/**
	 * Returns the association of primary provider to internal referral.
	 *
	 * @param internalReferral internal referral
	 * @return association of primary provider to internal referral
	 */
	ProviderInternalReferralAssociation findPrimaryByReferral(
			InternalReferral internalReferral);

	/** Returns provider internal referral association by business key.
	 * @param providerAssignment provider assignment.
	 * @param internalReferral internal referral.
	 * @return provider internal referral association. */
	ProviderInternalReferralAssociation find(
			ProviderAssignment providerAssignment,
			InternalReferral internalReferral);

	/** Returns provider internal referral association by business key.
	 * @param providerAssignment provider assignment.
	 * @param internalReferral internal referral.
	 * @param exclude provider internal referral association to exclude.
	 * @return provider internal referral association. */
	ProviderInternalReferralAssociation findExcluding(
			ProviderAssignment providerAssignment,
			InternalReferral internalReferral,
			ProviderInternalReferralAssociation exclude);
}