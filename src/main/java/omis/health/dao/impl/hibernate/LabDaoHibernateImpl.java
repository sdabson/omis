package omis.health.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.LabDao;
import omis.health.domain.Lab;
import omis.location.domain.Location;

import org.hibernate.SessionFactory;

/**
 * Lab data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.0 (May 7, 2014)
 * @since OMIS 3.0
 */
public class LabDaoHibernateImpl 
	extends GenericHibernateDaoImpl<Lab>
	implements LabDao {

	/* Query names. */
	
	private static final String FIND_LABS_QUERY_NAME = "findLabs";
	
	private static final String FIND_LABS_AT_LOCATION_QUERY_NAME 
		="findLabsAtLocation";
	
	private static final String FIND_RESULT_LABS_QUERY_NAME
		="findResultLabs";
	
	/* Parameter names. */
	
	private static final String LOCATION_PARAMETER_NAME = "location";
	
	/**
	 * Instantiates a lab data access object with the specified session
	 * factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public LabDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<Lab> findLabs() {
		@SuppressWarnings("unchecked")
		List<Lab> labs = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_LABS_QUERY_NAME)
				.list();
		return labs;
	}

	/** {@inheritDoc} */
	@Override
	public List<Lab> findLabsAtLocation(final Location location) {
		@SuppressWarnings("unchecked")
		List<Lab> labs = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_LABS_AT_LOCATION_QUERY_NAME)
				.setParameter(LOCATION_PARAMETER_NAME, location)
				.list();
		return labs;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Lab> findResultLabs() {
		@SuppressWarnings("unchecked")
		List<Lab> labs = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_RESULT_LABS_QUERY_NAME)
				.list();
		return labs;
	}
}