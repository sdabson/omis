package omis.demographics.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.demographics.dao.RaceDao;
import omis.demographics.domain.Race;

import org.hibernate.SessionFactory;

/**
 * Hibernate entity configurable implementation of data access object for
 * races.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Jan 11, 2013)
 * @since OMIS 3.0
 */
public class RaceDaoHibernateImpl
		extends GenericHibernateDaoImpl<Race>
		implements RaceDao {
	
	/* Query names. */
	
	private static final String FIND_ALL_QUERY_NAME = "findRaces";

	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate data access object for races.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public RaceDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<Race> findAll() {
		@SuppressWarnings("unchecked")
		List<Race> races = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return races;
	}
}