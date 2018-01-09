package omis.health.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.LabWorkReferralStatusReasonDao;
import omis.health.domain.HealthAppointmentStatus;
import omis.health.domain.LabWorkReferralStatusReason;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of lab work referral status reason data access 
 * object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 12, 2014)
 * @since OMIS 3.0
 */
public class LabWorkReferralStatusReasonDaoHibernateImpl 
extends GenericHibernateDaoImpl<LabWorkReferralStatusReason> 
implements LabWorkReferralStatusReasonDao {

	/* Query names. */
	
	private static final String FIND_REASONS_BY_APPOINTMENT_STATUS_QUERY_NAME
		= "findLabWorkReferralStatusReasonsByAppointmentStatus";
	
	/* Parameter names. */
	
	private static final String APPOINTMENT_STATUS_PARAM_NAME
		= "appointmentStatus";
	
	/**
	 * Instantiates an instance of lab work referral status reason data
	 * access object with the specified session factory and entity name.
	 */
	public LabWorkReferralStatusReasonDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<LabWorkReferralStatusReason> findByAppointmentStatus(
			HealthAppointmentStatus appointmentStatus) {
		@SuppressWarnings("unchecked")
		List<LabWorkReferralStatusReason> statusReasons = 
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_REASONS_BY_APPOINTMENT_STATUS_QUERY_NAME)
				.setParameter(APPOINTMENT_STATUS_PARAM_NAME, appointmentStatus)
				.list();
		return statusReasons;
	}

	/** {@inheritDoc} */
	@Override
	public LabWorkReferralStatusReason findKeptStatusReason() {
		LabWorkReferralStatusReason statusReason = (LabWorkReferralStatusReason) 
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_REASONS_BY_APPOINTMENT_STATUS_QUERY_NAME)
				.setParameter(APPOINTMENT_STATUS_PARAM_NAME, 
						HealthAppointmentStatus.KEPT)
				.uniqueResult();
		return statusReason;
	}
}