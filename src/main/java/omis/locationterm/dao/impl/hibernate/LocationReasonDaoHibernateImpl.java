package omis.locationterm.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.locationterm.dao.LocationReasonDao;
import omis.locationterm.domain.LocationReason;

/**
 * Hibernate implementation of data access object for location reasons.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Nov 8, 2013)
 * @since OMIS 3.0
 */
public class LocationReasonDaoHibernateImpl
		extends GenericHibernateDaoImpl<LocationReason>
		implements LocationReasonDao {
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * location reasons.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public LocationReasonDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
}