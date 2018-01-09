package omis.court.dao.impl.hibernate;

import java.util.List;

import omis.court.dao.CourtDao;
import omis.court.domain.Court;
import omis.court.domain.CourtCategory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.location.domain.Location;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for courts.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 28, 2013)
 * @since OMIS 3.0
 */
public class CourtDaoHibernateImpl
		extends GenericHibernateDaoImpl<Court>
		implements CourtDao {

	/* Queries. */
	
	private static final String FIND_ALL_QUERY_NAME = "findAllCourts";
	
	private static final String FIND_QUERY_NAME = "findCourt";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = "findCourtExcluding";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String CATEGORY_PARAM_NAME = "category";
	
	private static final String LOCATION_PARAM_NAME = "location";
	
	private static final String EXCLUDED_COURT_PARAM_NAME = "excludedCourt";


	/* Constructor. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for courts
	 * with the specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public CourtDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<Court> findAll() {
		@SuppressWarnings("unchecked")
		List<Court> courts = super.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return courts;
	}

	/** {@inheritDoc} */
	@Override
	public Court find(String name, CourtCategory category, Location location) {
		Court court = (Court) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.setParameter(LOCATION_PARAM_NAME, location)
				.uniqueResult();
		return court;
	}

	/** {@inheritDoc} */
	@Override
	public Court findExcluding(String name, CourtCategory category, 
			Location location, Court excludedCourt) {
		Court court = (Court) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.setParameter(LOCATION_PARAM_NAME, location)
				.setParameter(EXCLUDED_COURT_PARAM_NAME, excludedCourt)
				.uniqueResult();
		return court;
	}
}