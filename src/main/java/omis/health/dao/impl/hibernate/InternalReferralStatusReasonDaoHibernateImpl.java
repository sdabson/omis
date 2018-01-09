package omis.health.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.InternalReferralStatusReasonDao;
import omis.health.domain.HealthAppointmentStatus;
import omis.health.domain.InternalReferralStatusReason;

/**
 * Hibernate implementation of data access object for internal referral status
 * reasons.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 20, 2014)
 * @since OMIS 3.0
 */
public class InternalReferralStatusReasonDaoHibernateImpl
		extends GenericHibernateDaoImpl<InternalReferralStatusReason>
		implements InternalReferralStatusReasonDao {

	/* Query names. */
	
	private static final String FIND_BY_APPOINTMENT_STATUS_QUERY_NAME
		= "findInternalReferralStatusReasonsByAppointmentStatus";
	
	private static final String FIND_KEPT_QUERY_NAME
		= "findInternalReferralStatusReasonByAppointmentStatus";
	
	/* Parameter names. */
	
	private static final String APPOINTMENT_STATUS_PARAM_NAME
		= "appointmentStatus";
	
	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * internal referral status reasons.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public InternalReferralStatusReasonDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<InternalReferralStatusReason> findByAppointmentStatus(
			final HealthAppointmentStatus appointmentStatus) {
		@SuppressWarnings("unchecked")
		List<InternalReferralStatusReason> statusReasons
			= this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_APPOINTMENT_STATUS_QUERY_NAME)
				.setParameter(APPOINTMENT_STATUS_PARAM_NAME, appointmentStatus)
				.list();
		return statusReasons;
	}

	/** {@inheritDoc} */
	@Override
	public InternalReferralStatusReason findKeptStatusReason() {
		InternalReferralStatusReason statusReason =
			(InternalReferralStatusReason) this.getSessionFactory()
			.getCurrentSession().getNamedQuery(FIND_KEPT_QUERY_NAME)
			.setParameter(APPOINTMENT_STATUS_PARAM_NAME,
					HealthAppointmentStatus.KEPT).uniqueResult();
		return statusReason;
	}
}