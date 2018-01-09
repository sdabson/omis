package omis.health.dao.impl.hibernate;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.LabWorkReferralDao;
import omis.health.domain.LabWorkReferral;
import omis.health.domain.OffenderAppointmentAssociation;

import org.hibernate.SessionFactory;

/**
 * Lab work referral data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 7, 2014)
 * @since OMIS 3.0
 */
public class LabWorkReferralDaoHibernateImpl 
	extends GenericHibernateDaoImpl<LabWorkReferral> 
	implements LabWorkReferralDao {

	/* Query names. */
	
	private static final String FIND_LAB_WORK_REFERRAL_QUERY_NAME
		= "findLabWorkReferral";
	
	private static final String FIND_LAB_WORK_REFERRAL_EXCLUDING_QUERY_NAME
		= "findLabWorkReferralExcluding";
	
	/* Parameter names. */
	
	private static final String OFFENDER_APPOINTMENT_ASSOCIATION_PARAMETER_NAME
		= "offenderAppointmentAssociation";
	
	private static final String LAB_WORK_REFERRAL_PARAMETER_NAME
		= "labWorkReferral";
	
	/**
	 * Instantiates a lab work referral data access object with the specified
	 * session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public LabWorkReferralDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public LabWorkReferral find(
			final OffenderAppointmentAssociation 
			offenderAppointmentAssociation) {
		LabWorkReferral referral = (LabWorkReferral) getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_LAB_WORK_REFERRAL_QUERY_NAME)
				.setParameter(OFFENDER_APPOINTMENT_ASSOCIATION_PARAMETER_NAME, 
						offenderAppointmentAssociation)
				.uniqueResult();
		return referral;
	}

	/** {@inheritDoc} */
	@Override
	public LabWorkReferral findExcluding(final LabWorkReferral labWorkReferral,
			final OffenderAppointmentAssociation 
			offenderAppointmentAssociation) {
		LabWorkReferral referral = (LabWorkReferral) getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_LAB_WORK_REFERRAL_EXCLUDING_QUERY_NAME)
				.setParameter(LAB_WORK_REFERRAL_PARAMETER_NAME, labWorkReferral)
				.setParameter(OFFENDER_APPOINTMENT_ASSOCIATION_PARAMETER_NAME, 
						offenderAppointmentAssociation)
				.uniqueResult();
		return referral;
	}
}