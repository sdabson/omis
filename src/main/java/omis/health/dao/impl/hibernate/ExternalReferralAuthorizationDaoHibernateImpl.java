package omis.health.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.ExternalReferralAuthorizationDao;
import omis.health.domain.ExternalReferralAuthorization;
import omis.health.domain.ExternalReferralAuthorizationRequest;

/**
 * Hibernate implementation of data access object for external referral
 * authorizations.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 7, 2014)
 * @since OMIS 3.0
 */
public class ExternalReferralAuthorizationDaoHibernateImpl
		extends GenericHibernateDaoImpl<ExternalReferralAuthorization>
		implements ExternalReferralAuthorizationDao {

	/* Query names. */
	
	private static final String FIND_BY_REQUEST_QUERY_NAME
		= "findExternalReferralAuthorizationByRequest";
	
	/* Parameter names. */
	
	/* Constructors. */
	
	private static final String REQUEST_PARAM_NAME = "request";

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * external referral authorizations.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ExternalReferralAuthorizationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	@Override
	public ExternalReferralAuthorization findByRequest(
			final ExternalReferralAuthorizationRequest request) {
		ExternalReferralAuthorization authorization
			= (ExternalReferralAuthorization) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_BY_REQUEST_QUERY_NAME)
				.setParameter(REQUEST_PARAM_NAME, request).uniqueResult();
		return authorization;
	}
}