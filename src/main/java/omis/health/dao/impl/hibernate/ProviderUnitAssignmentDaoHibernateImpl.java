package omis.health.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.facility.domain.Unit;
import omis.health.dao.ProviderUnitAssignmentDao;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderUnitAssignment;

import org.hibernate.SessionFactory;

/**
 * Provider unit assignment data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr 9, 2014)
 * @since OMIS 3.0
 */
public class ProviderUnitAssignmentDaoHibernateImpl 
	extends GenericHibernateDaoImpl<ProviderUnitAssignment>
	implements ProviderUnitAssignmentDao {

	/* Query names. */
	
	private static final String FIND_PROVIDER_UNIT_ASSIGNMENT_QUERY_NAME =
			"findProviderUnitAssignment";
	
	private static final String FIND_UNIT_ASSIGNMENTS_BY_UNIT_QUERY_NAME =
			"findUnitAssignmentsByUnit";
	
	/* Parameter names. */
	
	private static final String PROVIDER_ASSIGNMENT_PARAMETER_NAME =
			"providerAssignment";
	
	private static final String UNIT_PARAMETER_NAME = "unit";
	
	private static final String DATE_PARAMETER_NAME = "date";
	
	/**
	 * Instantiates an instance of provider unit assignment data access
	 * object hibernate implementation with the specified session factory and
	 * entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	protected ProviderUnitAssignmentDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public ProviderUnitAssignment find(
			final ProviderAssignment providerAssignment, final Unit unit) {
		ProviderUnitAssignment unitAssignment = (ProviderUnitAssignment) 
			getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_PROVIDER_UNIT_ASSIGNMENT_QUERY_NAME)
			.setParameter(PROVIDER_ASSIGNMENT_PARAMETER_NAME, 
					providerAssignment)
			.setParameter(UNIT_PARAMETER_NAME, unit)
			.uniqueResult();
		
		return unitAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public List<ProviderUnitAssignment> findByUnit(final Unit unit, 
			final Date date) {
		@SuppressWarnings("unchecked")
		List<ProviderUnitAssignment> unitAssignments = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_UNIT_ASSIGNMENTS_BY_UNIT_QUERY_NAME)
				.setParameter(UNIT_PARAMETER_NAME, unit)
				.setDate(DATE_PARAMETER_NAME, date)
				.list();
		
		return unitAssignments;
	}
}