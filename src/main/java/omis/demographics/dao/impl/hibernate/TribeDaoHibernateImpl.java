package omis.demographics.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.demographics.dao.TribeDao;
import omis.demographics.domain.Tribe;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for tribes.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Jan 11, 2013)
 * @since OMIS 3.0
 */
public class TribeDaoHibernateImpl
		extends GenericHibernateDaoImpl<Tribe>
		implements TribeDao {
	
	/* Query names. */
	
	private static final String FIND_ALL_QUERY_NAME = "findTribes";

	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * tribes.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public TribeDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<Tribe> findAll() {
		@SuppressWarnings("unchecked")
		List<Tribe> tribes = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return tribes;
	}
}