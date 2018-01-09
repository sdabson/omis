package omis.health.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.ExternalReferralRequestAuthorizationRequirementDao;
import omis.health.domain.ExternalReferralAuthorizationRequest;
import omis.health.domain.ExternalReferralRequestAuthorizationRequirement;
import omis.health.domain.HealthRequest;

/**
 * Hibernate implementation of data access object for external referral request
 * authorization requirements.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 15, 2014)
 * @since OMIS 3.0
 */
public class ExternalReferralRequestAuthorizationRequirementDaoHibernateImpl
		extends GenericHibernateDaoImpl
			<ExternalReferralRequestAuthorizationRequirement>
		implements ExternalReferralRequestAuthorizationRequirementDao {
	
	/* Queries. */
	
	private static final String FIND_QUERY_NAME
		= "findExternalReferralRequestAuthroizationRequirement";

	private static final String FIND_BY_AUTHORIZATION_REQUEST_QUERY_NAME
		= "findExternalReferralRequestAuthroizationRequirement"
				+ "ByAuthorizationRequest";
	
	/* Parameter names. */
	
	private static final String HEALTH_REQUEST_PARAM_NAME = "healthRequest";

	private static final String AUTHORIZATION_REQUEST_PARAM_NAME
		= "authorizationRequest";
	
	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * external referral request authorization requirements.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ExternalReferralRequestAuthorizationRequirementDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public ExternalReferralRequestAuthorizationRequirement find(
			final HealthRequest healthRequest) {
		ExternalReferralRequestAuthorizationRequirement requirement
			= (ExternalReferralRequestAuthorizationRequirement)
				this.getSessionFactory().getCurrentSession().getNamedQuery(
						FIND_QUERY_NAME)
					.setParameter(HEALTH_REQUEST_PARAM_NAME, healthRequest)
					.uniqueResult();
		return requirement;
	}

	/** {@inheritDoc} */
	@Override
	public ExternalReferralRequestAuthorizationRequirement
	findByAuthorizationRequest(
			final ExternalReferralAuthorizationRequest authorizationRequest) {
		ExternalReferralRequestAuthorizationRequirement requirement
			= (ExternalReferralRequestAuthorizationRequirement)
				this.getSessionFactory().getCurrentSession().getNamedQuery(
						FIND_BY_AUTHORIZATION_REQUEST_QUERY_NAME)
					.setParameter(AUTHORIZATION_REQUEST_PARAM_NAME,
							authorizationRequest)
					.uniqueResult();
		return requirement;
	}
}