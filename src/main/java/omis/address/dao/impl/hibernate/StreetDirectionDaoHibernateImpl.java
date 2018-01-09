package omis.address.dao.impl.hibernate;

import java.util.List;

import omis.address.dao.StreetDirectionDao;
import omis.address.domain.StreetDirection;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for street directions.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 6, 2013)
 * @since OMIS 3.0
 */
public class StreetDirectionDaoHibernateImpl
		extends GenericHibernateDaoImpl<StreetDirection>
		implements StreetDirectionDao {
	
	/* Query names. */
	
	private static final String FIND_ALL_QUERY_NAME
		= "findAllStreetDirections";

	private static final String FIND_ALL_PRE_DIRECTIONS_QUERY_NAME
		= "findAllStreetPreDirections";
	
	private static final String FIND_ALL_POST_DIRECTIONS_QUERY_NAME
		= "findAllStreetPostDirections";
	
	/* Constructor. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * street directions with specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public StreetDirectionDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}	
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<StreetDirection> findAll() {
		@SuppressWarnings("unchecked")
		List<StreetDirection> directions = getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return directions;
	}

	/** {@inheritDoc} */
	@Override
	public List<StreetDirection> findAllPreDirections() {
		@SuppressWarnings("unchecked")
		List<StreetDirection> directions = getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_ALL_PRE_DIRECTIONS_QUERY_NAME).list();
		return directions;
	}

	/** {@inheritDoc} */
	@Override
	public List<StreetDirection> findAllPostDirections() {
		@SuppressWarnings("unchecked")
		List<StreetDirection> directions = getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_ALL_POST_DIRECTIONS_QUERY_NAME).list();
		return directions;
	}
}