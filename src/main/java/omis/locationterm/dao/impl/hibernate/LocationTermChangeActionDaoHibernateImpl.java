package omis.locationterm.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.locationterm.dao.LocationTermChangeActionDao;
import omis.locationterm.domain.LocationTermChangeAction;

/**
 * Hibernate implementation of data access object for location term change
 * action.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Mar 6, 2015)
 * @since OMIS 3.0
 */
public class LocationTermChangeActionDaoHibernateImpl
		extends GenericHibernateDaoImpl<LocationTermChangeAction>
		implements LocationTermChangeActionDao {

	/* Query names. */
	
	private static final String FIND_QUERY_NAME
		= "findLocationTermChangeAction";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";

	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of data access object for location
	 * term change action.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public LocationTermChangeActionDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public LocationTermChangeAction find(final String name) {
		LocationTermChangeAction changeAction
				= (LocationTermChangeAction) this.getSessionFactory()
					.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
						.setParameter(NAME_PARAM_NAME, name)
						.uniqueResult();
		return changeAction;
	}
}