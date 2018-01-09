package omis.health.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.ProviderExternalReferralAssociationDao;
import omis.health.domain.ExternalReferral;
import omis.health.domain.ProviderExternalReferralAssociation;

/**
 * Hibernate implementation of data access object for provider external
 * referral associations.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 7, 2014)
 * @since OMIS 3.0
 */
public class ProviderExternalReferralAssociationDaoHibernateImpl
		extends GenericHibernateDaoImpl<ProviderExternalReferralAssociation>
		implements ProviderExternalReferralAssociationDao {
	
	/* Query names. */
	
	private static final String FIND_PRIMARY_QUERY_NAME
		= "findPrimaryProviderExternalReferralAssociation";
	
	/* Parameter names. */
	
	private static final String EXTERNAL_REFERRAL_PARAM_NAME
		= "externalReferral";

	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * provider external referral association.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ProviderExternalReferralAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public ProviderExternalReferralAssociation findPrimaryForReferral(
			final ExternalReferral referral) {
		ProviderExternalReferralAssociation association
			= (ProviderExternalReferralAssociation)
				this.getSessionFactory().getCurrentSession().getNamedQuery(
					FIND_PRIMARY_QUERY_NAME)
				.setParameter(EXTERNAL_REFERRAL_PARAM_NAME, referral)
				.uniqueResult();
		return association;
	}
}