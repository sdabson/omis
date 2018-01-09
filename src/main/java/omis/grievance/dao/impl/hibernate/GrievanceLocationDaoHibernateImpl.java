package omis.grievance.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.grievance.dao.GrievanceLocationDao;
import omis.grievance.domain.GrievanceLocation;

import org.hibernate.SessionFactory;

/**
 * Grievance location data access object hibernate implementation.
 * 
 * @author Yidong Li
 * @author Stephen Abson
 * @version 0.1.0 (May 19, 2015)
 * @since OMIS 3.0
 */
public class GrievanceLocationDaoHibernateImpl
extends GenericHibernateDaoImpl<GrievanceLocation>
implements GrievanceLocationDao {

	/* Query names. */
	
	private static final String FIND_LOCATIONS_QUERY_NAME 
		= "findGrievanceLocations";
	
	private static final String FIND_LOCATION_QUERY_NAME
		= "findGrievanceLocation";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	/* Constructors. */
	
	/**
	 * Instantiates a grievance location data access object
	 * with the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public GrievanceLocationDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<GrievanceLocation> findAll() {
		@SuppressWarnings("unchecked")
		List<GrievanceLocation> locations = 
			this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_LOCATIONS_QUERY_NAME)
			.list();
		return locations;
	}
	
	/** {@inheritDoc} */
	@Override
	public GrievanceLocation find(final String name) {
		GrievanceLocation location = (GrievanceLocation) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_LOCATION_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return location;
	}
}