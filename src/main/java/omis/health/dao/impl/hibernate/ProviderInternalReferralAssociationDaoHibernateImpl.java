package omis.health.dao.impl.hibernate;

import java.util.Arrays;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.ProviderInternalReferralAssociationDao;
import omis.health.domain.InternalReferral;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderInternalReferralAssociation;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for provider internal
 * referral associations.
 *
 * @author Stephen Abson
 * @author Ryan Johns
 * @version 0.1.0 (Apr 28, 2014)
 * @since OMIS 3.0
 */
public class ProviderInternalReferralAssociationDaoHibernateImpl
		extends GenericHibernateDaoImpl<ProviderInternalReferralAssociation>
		implements ProviderInternalReferralAssociationDao {

	/* Queries. */

	private static final String FIND_BY_REFERRAL_QUERY_NAME
		= "findPrimaryProviderInternalReferralAssociationByReferral";

	private static final String FIND_BY_REFERRAL_AND_PROVIDER_QUERY_NAME =
			"findProviderInternalReferralAssociationByReferralAndProvider";

	private static final String
	FIND_BY_REFERRAL_AND_PROVIDER_EXCLUDING_QUERY_NAME =
		"findProviderInternalReferralAssocitionByReferralAndProviderExcluding";

	/* Parameters. */

	private static final String INTERNAL_REFERRAL_PARAM_NAME
		= "internalReferral";

	private static final String PROVIDER_ASSIGNMENT_PARAM_NAME =
			"providerAssignment";

	private static final String EXCLUDES_PARAM_NAME = "excluding";

	/* Constructors. */

	/**
	 * Hibernate implementation of data access object for provider internal
	 * referrals associations.
	 *
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ProviderInternalReferralAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */

	/** {@inheritDoc} */
	@Override
	public ProviderInternalReferralAssociation findPrimaryByReferral(
			final InternalReferral internalReferral) {
		final ProviderInternalReferralAssociation association =
				(ProviderInternalReferralAssociation) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_BY_REFERRAL_QUERY_NAME)
				.setParameter(INTERNAL_REFERRAL_PARAM_NAME, internalReferral)
				.uniqueResult();
		return association;
	}

	/** {@inheritDoc} */
	@Override
	public ProviderInternalReferralAssociation find(
			final ProviderAssignment providerAssignment,
			final InternalReferral internalReferral) {

		return (ProviderInternalReferralAssociation) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_BY_REFERRAL_AND_PROVIDER_QUERY_NAME)
						.setParameter(INTERNAL_REFERRAL_PARAM_NAME,
								internalReferral).setParameter(
										PROVIDER_ASSIGNMENT_PARAM_NAME,
										providerAssignment).uniqueResult();
	}

	/** {@inheritDoc} */
	@Override
	public ProviderInternalReferralAssociation findExcluding(
			final ProviderAssignment providerAssignment,
			final InternalReferral internalReferral,
			final ProviderInternalReferralAssociation exclude) {
		return (ProviderInternalReferralAssociation) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_BY_REFERRAL_AND_PROVIDER_EXCLUDING_QUERY_NAME)
						.setParameter(INTERNAL_REFERRAL_PARAM_NAME,
								internalReferral).setParameter(
										PROVIDER_ASSIGNMENT_PARAM_NAME,
										providerAssignment).setParameterList(
												EXCLUDES_PARAM_NAME,
												Arrays.asList(exclude))
												.uniqueResult();
	}

	/** {@inheritDoc} */
	//@Override
	//public ProviderInternalReferralAssociation findExcluding();
}