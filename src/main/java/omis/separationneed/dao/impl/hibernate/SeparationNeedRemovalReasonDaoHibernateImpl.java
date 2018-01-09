package omis.separationneed.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.separationneed.dao.SeparationNeedRemovalReasonDao;
import omis.separationneed.domain.SeparationNeedRemovalReason;

/**
 * Separation need removal reason data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Sep 9, 2015)
 * @since OMIS 3.0
 */
public class SeparationNeedRemovalReasonDaoHibernateImpl
	extends GenericHibernateDaoImpl<SeparationNeedRemovalReason>
	implements SeparationNeedRemovalReasonDao {
	
	/* Query names. */
	
	private static final String FIND_ALL_QUERY_NAME
		= "findAllSeparationNeedRemovalReasons";
	
	private static final String FIND_QUERY_NAME
		= "findSeparationNeedRemovalReason";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findSeparationNeedRemovalReasonExcluding";
	
	/* Param names. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_REASON_PARAM_NAME = "excludedReason";
	
	/**
	 * Instantiates an instance of separation need removal reason data
	 * access object with the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SeparationNeedRemovalReasonDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<SeparationNeedRemovalReason> findAll() {
		@SuppressWarnings("unchecked")
		List<SeparationNeedRemovalReason> removalReasons =
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALL_QUERY_NAME)
				.list();
		return removalReasons;
	}

	/** {@inheritDoc} */
	@Override
	public SeparationNeedRemovalReason find(final String name) {
		SeparationNeedRemovalReason reason = (SeparationNeedRemovalReason) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return reason;
	}

	/** {@inheritDoc} */
	@Override
	public SeparationNeedRemovalReason findExcluding(final String name, 
			final SeparationNeedRemovalReason excludedReason) {
		SeparationNeedRemovalReason reason = (SeparationNeedRemovalReason) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDED_REASON_PARAM_NAME, excludedReason)
				.uniqueResult();
		return reason;
	}
}