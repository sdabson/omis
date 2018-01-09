package omis.health.dao.impl.hibernate;

import java.util.Arrays;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.OffenderAppointmentAssociationDao;
import omis.health.domain.HealthAppointment;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Offender appointment association data access object hibernate
 * implementation.
 *
 * @author Joel Norris
 * @author Ryan Johns
 * @version 0.1.0 (Apr 15, 2014)
 * @since OMIS 3.0
 */
public class OffenderAppointmentAssociationDaoHibernateImpl
	extends GenericHibernateDaoImpl<OffenderAppointmentAssociation>
	implements OffenderAppointmentAssociationDao {

	// Queries

	private static final String FIND_BY_OFFENDER_AND_APPOINTMENT =
		"findOffenderAppointmentAssociationByOffenderAndAppointment";

	private static final String FIND_BY_OFFENDER_AND_APPOINTMENT_EXCLUDING =
		"findOffenderAppointmentAssociationByOffenderAndAppointmentExcluding";

	// Parameters

	private static final String OFFENDER_PARAMETER_NAME = "offender";

	private static final String HEALTH_APPOINTMENT_PARAMETER_NAME =
			"appointment";

	private static final String EXCLUDE_PARAMETER_NAME = "exclude";

	// Constructors
	/**
	 * Instantiates an offender appointment association data access object
	 * with the specified session factory and entity name.
	 *
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public OffenderAppointmentAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public OffenderAppointmentAssociation find(final Offender offender,
			final HealthAppointment healthAppointment) {
		return (OffenderAppointmentAssociation) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_BY_OFFENDER_AND_APPOINTMENT).setParameter(
								OFFENDER_PARAMETER_NAME, offender).setParameter(
										HEALTH_APPOINTMENT_PARAMETER_NAME,
										healthAppointment).uniqueResult();
	}

	/** {@inheritDoc} */
	@Override
	public OffenderAppointmentAssociation findExcluding(final Offender offender,
			final HealthAppointment healthAppointment,
			final OffenderAppointmentAssociation exclude) {
		return (OffenderAppointmentAssociation) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_BY_OFFENDER_AND_APPOINTMENT_EXCLUDING)
						.setParameter(OFFENDER_PARAMETER_NAME, offender)
						.setParameter(HEALTH_APPOINTMENT_PARAMETER_NAME,
								healthAppointment).setParameterList(
										EXCLUDE_PARAMETER_NAME,
										Arrays.asList(exclude)).uniqueResult();
	}

}