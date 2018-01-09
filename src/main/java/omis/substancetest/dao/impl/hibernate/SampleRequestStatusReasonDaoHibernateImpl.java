package omis.substancetest.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.substancetest.dao.SampleRequestStatusReasonDao;
import omis.substancetest.domain.SampleRequestStatusReason;

/**
 * Samples status reason data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.1 (Jun 12, 2015)
 * @since OMIS 3.0
 */
public class SampleRequestStatusReasonDaoHibernateImpl 
extends GenericHibernateDaoImpl<SampleRequestStatusReason>
implements SampleRequestStatusReasonDao {

	/* Query names. */
	
	private static final String FIND_STATUS_REASONS_BY_TAKEN_QUERY_NAME
		= "findStatusReasonsByTakenValue";
	
	/**
	 * Instantiates a sample status data access object with the specified
	 * session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SampleRequestStatusReasonDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<SampleRequestStatusReason> findStatusReasons() {
		@SuppressWarnings("unchecked")
		List<SampleRequestStatusReason> statusReasons = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_STATUS_REASONS_BY_TAKEN_QUERY_NAME)
				.list();
		return statusReasons;
	}
}