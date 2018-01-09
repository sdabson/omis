package omis.health.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.facility.domain.Facility;
import omis.health.dao.ExternalReferralAuthorizationRequestDao;
import omis.health.domain.ExternalReferralAuthorizationRequest;

/**
 * Hibernate implementation of data access object for external referral
 * authorization requests.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 7, 2014)
 * @since OMIS 3.0
 */
public class ExternalReferralAuthorizationRequestDaoHibernateImpl
		extends GenericHibernateDaoImpl<ExternalReferralAuthorizationRequest>
		implements ExternalReferralAuthorizationRequestDao {

	/* Query names. */
	
	private static final String FIND_BY_FACILITY_QUERY_NAME
		= "findExternalReferralAuthorizationRequestByFacility";
	
	/* Parameters. */
	
	private static final String FACILITY_PARAM_NAME = "facility";
	
	/* Constructors. */

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * external referral authorization requests.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ExternalReferralAuthorizationRequestDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<ExternalReferralAuthorizationRequest> findByFacility(
			final Facility facility) {
		@SuppressWarnings("unchecked")
		List<ExternalReferralAuthorizationRequest> requests
			= this.getSessionFactory().getCurrentSession().getNamedQuery(
					FIND_BY_FACILITY_QUERY_NAME)
				.setParameter(FACILITY_PARAM_NAME, facility).list();
		return requests;
	}
}