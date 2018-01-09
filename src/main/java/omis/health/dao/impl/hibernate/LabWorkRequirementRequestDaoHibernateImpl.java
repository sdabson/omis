package omis.health.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.LabWorkRequirementRequestDao;
import omis.health.domain.HealthRequest;
import omis.health.domain.LabWorkRequirementRequest;

/**
 * Hibernate implementation of data access object for lab work requirement
 * requests.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jun 3, 2014)
 * @since OMIS 3.0
 */
public class LabWorkRequirementRequestDaoHibernateImpl
		extends GenericHibernateDaoImpl<LabWorkRequirementRequest>
		implements LabWorkRequirementRequestDao {

	/* Query names. */
	
	private static final String FIND_BY_HEALTH_REQUEST_QUERY_NAME
		= "findLabWorkRequirementRequestsByHealthRequest";
	
	/* Parameter names. */
	
	private static final String HEALTH_REQUEST_PARAM_NAME
		= "healthRequest";

	/* Constructor. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for lab
	 * work requirement requests.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public LabWorkRequirementRequestDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<LabWorkRequirementRequest> findByHealthRequest(
			final HealthRequest healthRequest) {
		@SuppressWarnings("unchecked")
		List<LabWorkRequirementRequest> requests = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_BY_HEALTH_REQUEST_QUERY_NAME)
				.setParameter(HEALTH_REQUEST_PARAM_NAME, healthRequest).list();
		return requests;
	}
}