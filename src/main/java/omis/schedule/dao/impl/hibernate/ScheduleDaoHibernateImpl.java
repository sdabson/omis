package omis.schedule.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.schedule.dao.ScheduleDao;
import omis.schedule.domain.Schedule;

/**
 * Entity configurable Hibernate implementation of data access object for
 * schedules.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Feb 5, 2013)
 * @since OMIS 3.0
 * @see ScheduleDao
 */
public class ScheduleDaoHibernateImpl
		extends GenericHibernateDaoImpl<Schedule>
		implements ScheduleDao {

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * schedules with specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ScheduleDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
}