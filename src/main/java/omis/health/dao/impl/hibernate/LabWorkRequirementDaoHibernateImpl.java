package omis.health.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.LabWorkRequirementDao;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkRequirement;
import omis.health.domain.OffenderAppointmentAssociation;

import org.hibernate.SessionFactory;

/**
 * Lab work requirement data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 5, 2014)
 * @since OMIS 3.0
 */
public class LabWorkRequirementDaoHibernateImpl 
	extends GenericHibernateDaoImpl<LabWorkRequirement> 
	implements LabWorkRequirementDao {

	/* Query names */
	
	private static final String FIND_REQUIREMENT_QUERY_NAME 
		= "findLabWorkRequirement";
	
	private static final String 
	FIND_REQUIREMENTS_BY_APPOINTMENT_ASSOCIATION_QUERY_NAME 
		= "findLabWorkRequirementByAppointmentAssociation";
	
	private static final String FIND_REQUIREMENTS_BY_LAB_WORK_QUERY_NAME
		= "findLabWorkRequirementByLabWork";
	
	private static final String 
	FIND_REQUIREMENTS_BY_LAB_WORK_EXCLUDING_QUERY_NAME
	= "findLabWorkRequirementByLabWorkExcluding";
	
	/* Parameter names */
	
	private static final String APPOINTMENT_ASSOCIATION_PARAM =
			"offenderAppointmentAssociation";
	
	private static final String LAB_WORK_PARAM = "labWork";
	
	/**
	 * Instantiates an instance of lab work requirement data access object.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public LabWorkRequirementDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public LabWorkRequirement find(
			final OffenderAppointmentAssociation appointmentAssociation,
			final LabWork labWork) {
		LabWorkRequirement requirement = (LabWorkRequirement)
				getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_REQUIREMENT_QUERY_NAME)
				.setParameter(APPOINTMENT_ASSOCIATION_PARAM, 
						appointmentAssociation)
				.setParameter(LAB_WORK_PARAM, labWork)
				.uniqueResult();
		return requirement;
	}

	/** {@inheritDoc} */
	@Override
	public List<LabWorkRequirement> findByOffenderAppointmentAssociaiton(
			final OffenderAppointmentAssociation 
			offenderAppointmentAssociation) {
		@SuppressWarnings("unchecked")
		List<LabWorkRequirement> labWorkRequirements = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_REQUIREMENTS_BY_APPOINTMENT_ASSOCIATION_QUERY_NAME)
				.setParameter(APPOINTMENT_ASSOCIATION_PARAM, 
						offenderAppointmentAssociation)
				.list();
		return labWorkRequirements;
	}

	/** {@inheritDoc} */
	@Override
	public List<LabWorkRequirement> findByLabWork(final LabWork labWork) {
		@SuppressWarnings("unchecked")
		List<LabWorkRequirement> labWorkRequirements = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_REQUIREMENTS_BY_LAB_WORK_QUERY_NAME)
				.setParameter(LAB_WORK_PARAM, labWork)
				.list();
		return labWorkRequirements;
	}

	/** {@inheritDoc} */
	@Override
	public List<LabWorkRequirement> findByLabWorkExcluding(final LabWork labWork,
			final OffenderAppointmentAssociation 
			offenderAppointmentAssociation) {
		@SuppressWarnings("unchecked")
		List<LabWorkRequirement> labWorkRequirements = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_REQUIREMENTS_BY_LAB_WORK_EXCLUDING_QUERY_NAME)
				.setParameter(LAB_WORK_PARAM, labWork)
				.setParameter(APPOINTMENT_ASSOCIATION_PARAM, 
						offenderAppointmentAssociation)
				.list();
		return labWorkRequirements;
	}
}