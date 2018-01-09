package omis.health.dao.impl.hibernate;

import java.util.Arrays;
import java.util.Date;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.InternalReferralDao;
import omis.health.domain.InternalReferral;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.health.domain.ProviderAssignment;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for internal referrals.
 *
 * @author Stephen Abson
 * @version 0.1.0 (Apr 14, 2014)
 * @since OMIS 3.0
 */
public class InternalReferralDaoHibernateImpl
		extends GenericHibernateDaoImpl<InternalReferral>
		implements InternalReferralDao {

	// Queries
	private static final String FIND_BY_OFFENDER_APPOINTMENT_QUERY_NAME =
			"findItnernalReferralByOffenderAppointmentAssociation";

	private static final String
	FIND_BY_OFFENDER_APPOINTMENT_EXCLUDING_QUERY_NAME =
		"findInternalReferralByOffenderAppointmentAssocaitonExcluding";

	private static final String
	FIND_BY_OFFENDER_DATE_TIME_AND_PROVIDER =
		"findInternalReferralByOffenderDateTimeProvider";

	private static final String
	FIND_BY_OFFENDER_DATE_TIME_AND_PROVIDER_EXCLUDING =
		"findInternalReferralByOffenderDateTimeProviderExcluding";

	// Parameters
	private static final String OFFENDER_APPOINTMENT_ASSOCIATION_PARAM_NAME =
			"offenderAppointmentAssociation";

	private static final String OFFENDER_PARAM_NAME = "offender";

	private static final String EXCLUDE_PARAM_NAME = "exclude";

	private static final String DATE_PARAM_NAME = "date";

	private static final String START_TIME_PARAM_NAME = "startTime";

	private static final String PROVIDER_ASSIGNMENT_PARAM_NAME =
			"providerAssignment";

	// Constructors

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * internal referrals.
	 *
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public InternalReferralDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	// Other stuff

	/** {@inheritDoc} */
	@Override
	public InternalReferral find(
			final OffenderAppointmentAssociation
			offenderAppointmentAssociation) {
		return (InternalReferral) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_OFFENDER_APPOINTMENT_QUERY_NAME)
				.setParameter(OFFENDER_APPOINTMENT_ASSOCIATION_PARAM_NAME,
						offenderAppointmentAssociation).uniqueResult();
	}

	/** {@inheritDoc} */
	@Override
	public InternalReferral find(final Offender offender, final Date date,
			 final Date startTime, final ProviderAssignment providerAssignment) {
		return (InternalReferral) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_OFFENDER_DATE_TIME_AND_PROVIDER)
				.setParameter(OFFENDER_PARAM_NAME, offender).setParameter(
						DATE_PARAM_NAME, date).setTimestamp(
								START_TIME_PARAM_NAME, startTime).setParameter(
										PROVIDER_ASSIGNMENT_PARAM_NAME,
										providerAssignment).uniqueResult();
	}

	/** {@inheritDoc} */
	@Override
	public InternalReferral findExcluding(
			final OffenderAppointmentAssociation offenderAppointmentAssociation,
			final InternalReferral exclude) {
		return (InternalReferral) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_BY_OFFENDER_APPOINTMENT_EXCLUDING_QUERY_NAME)
						.setParameter(
								OFFENDER_APPOINTMENT_ASSOCIATION_PARAM_NAME,
								offenderAppointmentAssociation)
								.setParameterList(EXCLUDE_PARAM_NAME,
										Arrays.asList(exclude)).uniqueResult();
	}

	/** {@inheritDoc} */
	@Override
	public InternalReferral findExcluding(final Offender offender,
			final Date date, final Date startTime,
			final ProviderAssignment providerAssignment,
			final InternalReferral internalReferral) {
		return (InternalReferral) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_BY_OFFENDER_DATE_TIME_AND_PROVIDER_EXCLUDING)
						.setParameter(OFFENDER_PARAM_NAME, offender)
						.setParameter(DATE_PARAM_NAME, date).setTimestamp(
								START_TIME_PARAM_NAME, startTime).setParameter(
										PROVIDER_ASSIGNMENT_PARAM_NAME,
										providerAssignment).setParameterList(
												EXCLUDE_PARAM_NAME,
												Arrays.asList(internalReferral))
												.uniqueResult();
	}

}