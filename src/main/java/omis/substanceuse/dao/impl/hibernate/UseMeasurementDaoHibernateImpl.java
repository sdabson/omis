package omis.substanceuse.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.substanceuse.dao.UseMeasurementDao;
import omis.substanceuse.domain.UseMeasurement;

/**
 * Use measurement data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 18, 2016)
 * @since OMIS 3.0 
 */
public class UseMeasurementDaoHibernateImpl 
	extends GenericHibernateDaoImpl<UseMeasurement> 
	implements UseMeasurementDao {

	/* Query names. */
	
	private static final String FIND_VALID_MEASUREMENTS_QUERY_NAME
		= "findValidUseMeasurements";
	
	/**
	 * Instantiates an instance of use measurement data access object with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public UseMeasurementDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<UseMeasurement> findAll() {
		@SuppressWarnings("unchecked")
		List<UseMeasurement> measurements = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_VALID_MEASUREMENTS_QUERY_NAME)
				.list();
		return measurements;
	}
}