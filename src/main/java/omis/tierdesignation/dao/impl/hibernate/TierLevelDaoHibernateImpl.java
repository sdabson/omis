package omis.tierdesignation.dao.impl.hibernate;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.tierdesignation.dao.TierLevelDao;
import omis.tierdesignation.domain.TierLevel;

import org.hibernate.SessionFactory;
/**
 * Hibernate implementation of an offender tier designation level data access
 * object.
 *  
 * @author Jason Nelson
 * @version 0.1.0 (Sept 20, 2012)
 * @since OMIS 3.0
 * @see TierLevel
 * @see TierLevelDao
 */
public class TierLevelDaoHibernateImpl
		extends GenericHibernateDaoImpl<TierLevel>
		implements TierLevelDao {

	/* Query names. */
	
	private static final String FIND_QUERY_NAME = "findTierLevel";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findTierLevelExcluding";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_LEVEL_PARAM_NAME 
		= "excludedLevel";

	/**
	 * Instantiates a data access object for tier designations
	 *  with the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public TierLevelDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public TierLevel find(final String name) {
		TierLevel tierLevel = (TierLevel) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return tierLevel;
	}

	/** {@inheritDoc} */
	@Override
	public TierLevel findExcluding(final String name, 
			final TierLevel excludedTierLevel) {
		TierLevel tierLevel = (TierLevel) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDED_LEVEL_PARAM_NAME, excludedTierLevel)
				.uniqueResult();
		return tierLevel;
	}	
}