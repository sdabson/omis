package omis.tierdesignation.dao.impl.hibernate;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.tierdesignation.dao.TierSourceDao;
import omis.tierdesignation.domain.TierSource;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of an offender tier designation source data access
 * object.
 *  
 * @author Jason Nelson
 * @version 0.1.0 (Sept 20, 2012)
 * @since OMIS 3.0
 * @see TierSource
 * @see TierSourceDao
 */
public class TierSourceDaoHibernateImpl
		extends GenericHibernateDaoImpl<TierSource>
		implements TierSourceDao {

	/* Query names. */
	
	private static final String FIND_QUERY_NAME = "findTierSource";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findTierSourceExcluding";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_SOURCE_PARAM_NAME 
		= "excludedSource";

	/**
	 * Instantiates a data access object for tier sources
	 *  with the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public TierSourceDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public TierSource find(final String name) {
		TierSource tierSource = (TierSource) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return tierSource;
	}

	/** {@inheritDoc} */
	@Override
	public TierSource findExcluding(final String name, 
			final TierSource excludedTierSource) {
		TierSource tierSource = (TierSource) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDED_SOURCE_PARAM_NAME, excludedTierSource)
				.uniqueResult();
		return tierSource;
	}	
}