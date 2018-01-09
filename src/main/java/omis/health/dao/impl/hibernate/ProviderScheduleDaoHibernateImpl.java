package omis.health.dao.impl.hibernate;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.ProviderScheduleDao;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderSchedule;

import org.hibernate.SessionFactory;

/**
 * Provider schedule data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr 10, 2014)
 * @since OMIS 3.0
 */
public class ProviderScheduleDaoHibernateImpl 
	extends GenericHibernateDaoImpl<ProviderSchedule>
	implements ProviderScheduleDao {

	/* Query names. */
	
	private static final String FIND_PROVIDER_SCHEDULE_QUERY_NAME =
			"findProviderSchedule";
	
	/* Parameter names. */
	
	private static final String PROVIDER_ASSIGNMENT_PARAMETER_NAME = 
			"providerAssignment";
	
	/**
	 * Instantiates an instance of provider schedule data access object 
	 * hibernate implementation.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ProviderScheduleDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public ProviderSchedule findByAssignment(
			final ProviderAssignment providerAssignment) {
		ProviderSchedule schedule = (ProviderSchedule) getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_PROVIDER_SCHEDULE_QUERY_NAME)
				.setParameter(PROVIDER_ASSIGNMENT_PARAMETER_NAME, 
						providerAssignment)
				.uniqueResult();
		return schedule;
	}
}