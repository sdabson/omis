package omis.health.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.ProviderLevelDao;
import omis.health.domain.ProviderLevel;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for provider levels.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 13, 2014)
 * @since OMIS 3.0
 */
public class ProviderLevelDaoHibernateImpl
		extends GenericHibernateDaoImpl<ProviderLevel>
		implements ProviderLevelDao {

	/* Queries. */
	
	private static final String FIND_ALL_QUERY_NAME = "findAllProviderLevels";
	
	/* Constructors. */

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * provider levels.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ProviderLevelDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<ProviderLevel> findAll() {
		@SuppressWarnings("unchecked")
		List<ProviderLevel> levels = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return levels;
	}
}