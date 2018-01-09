package omis.health.dao.impl.hibernate;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.HealthAppointmentDao;
import omis.health.domain.HealthAppointment;
import omis.health.domain.InternalReferral;

import org.hibernate.SessionFactory;

/**
 * Health Appointment Data Access Object Hibernate implementation.
 *
 * @author Joel Norris
 * @author Ryan Johns
 * @version 0.1.1 (May 8, 2014)
 * @since OMIS 3.0
 */
public class HealthAppointmentDaoHibernateImpl
	extends GenericHibernateDaoImpl<HealthAppointment>
	implements HealthAppointmentDao {
	private static final String FIND_HEALTH_APPOINTMENT_BY_INTERNAL_REFERRAL =
			"findHealthAppointmentByInternalReferral";

	/**
	 * Instantiates an instance of health appointment data access object
	 * hibernate implementation.
	 *
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public HealthAppointmentDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public HealthAppointment findByInternalReferral(
			final InternalReferral intennalReferral) {
		return (HealthAppointment) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_HEALTH_APPOINTMENT_BY_INTERNAL_REFERRAL)
				.setParameter("internalReferral", intennalReferral)
				.uniqueResult();
	}
}