package omis.health.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.ExternalReferralStatusReasonDao;
import omis.health.domain.ExternalReferralStatusReason;
import omis.health.domain.HealthAppointmentStatus;

/**
 * Hibernate implementation of data access object for external referral status
 * reasons.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 24, 2014)
 * @since OMIS 3.0
 */
public class ExternalReferralStatusReasonDaoHibernateImpl
		extends GenericHibernateDaoImpl<ExternalReferralStatusReason>
		implements ExternalReferralStatusReasonDao {

	/* Query names. */
	
	private static final String FIND_BY_APPOINTMENT_STATUS_QUERY_NAME
		= "findExternalReferralStatusReasonsByAppointmentStatus";
	
	private static final String FIND_KEPT_QUERY_NAME
		= "findExternalReferralStatusReasonByAppointmentStatus";
	
	/* Parameter names. */
	
	private static final String APPOINTMENT_STATUS_PARAM_NAME
		= "appointmentStatus";
	
	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * external referral status reasons.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ExternalReferralStatusReasonDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<ExternalReferralStatusReason> findByAppointmentStatus(
			final HealthAppointmentStatus appointmentStatus) {
		@SuppressWarnings("unchecked")
		List<ExternalReferralStatusReason> statusReasons
			= this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_APPOINTMENT_STATUS_QUERY_NAME)
				.setParameter(APPOINTMENT_STATUS_PARAM_NAME, appointmentStatus)
				.list();
		return statusReasons;
	}

	/** {@inheritDoc} */
	@Override
	public ExternalReferralStatusReason findKeptStatusReason() {
		ExternalReferralStatusReason statusReason =
				(ExternalReferralStatusReason) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_KEPT_QUERY_NAME)
				.setParameter(APPOINTMENT_STATUS_PARAM_NAME,
						HealthAppointmentStatus.KEPT).uniqueResult();
		return statusReason;
	}
}