/**
 * 
 */
package omis.separationneed.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.separationneed.dao.SeparationNeedReasonDao;
import omis.separationneed.domain.SeparationNeedReason;

import org.hibernate.SessionFactory;

/**
 * Hibernate entity configurable implementation of data access object for
 * separation need reason..
 * 
 * @author Joel Norris 
 * @version 0.1.0 (Feb, 21 2013)
 * @since OMIS 3.0
 */
public class SeparationNeedReasonDaoHibernateImpl 
	extends GenericHibernateDaoImpl<SeparationNeedReason> 
	implements SeparationNeedReasonDao {
	
	/* Query names */
	
	private static final String FIND_ALL_REASONS_QUERY_NAME
		= "findAllValidSeparationNeedReasons";
	
	private static final String FIND_QUERY_NAME
		= "findSeparationNeedReason";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findSeparationNeedReasonExcluding";
	
	/* Param names. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_REASON_PARAM_NAME = "excludedReason";
	
	/* Constructor */
	
	/**
	 * Instantiates a data access object for separation need reason with
	 * the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SeparationNeedReasonDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations */
	
	/** {@inheritDoc} */
	@Override	
	public List<SeparationNeedReason> findAll() {
		@SuppressWarnings("unchecked")
		List<SeparationNeedReason> reasons = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALL_REASONS_QUERY_NAME)
				.list();
		return reasons;
	}

	/** {@inheritDoc} */
	@Override
	public SeparationNeedReason find(final String name) {
		SeparationNeedReason reason = (SeparationNeedReason) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return reason;
	}

	/** {@inheritDoc} */
	@Override
	public SeparationNeedReason findExcluding(final String name, 
			final SeparationNeedReason excludedReason) {
		SeparationNeedReason reason = (SeparationNeedReason) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDED_REASON_PARAM_NAME, excludedReason)
				.uniqueResult();
		return reason;
	}
}