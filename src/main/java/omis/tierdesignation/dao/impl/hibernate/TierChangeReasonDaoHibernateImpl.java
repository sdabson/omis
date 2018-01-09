package omis.tierdesignation.dao.impl.hibernate;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.tierdesignation.dao.TierChangeReasonDao;
import omis.tierdesignation.domain.TierChangeReason;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of an offender tier designation change reason data
 * access object.
 *  
 * @author Jason Nelson
 * @version 0.1.0 (Sept 20, 2012)
 * @since OMIS 3.0
 * @see TierChangeReason
 * @see TierChangeReasonDao
 */
public class TierChangeReasonDaoHibernateImpl 
		extends GenericHibernateDaoImpl<TierChangeReason>
		implements TierChangeReasonDao {

	/* Query names. */
	
	private static final String FIND_QUERY_NAME = "findTierChangeReason";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findTierChangeReasonExcluding";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_CHANGE_REASON_PARAM_NAME 
		= "excludedChangeReason";

	/**
	 * Instantiates a data access object for tier change
	 * reasons with the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public TierChangeReasonDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public TierChangeReason find(final String name) {
		TierChangeReason tierChangeReason = 
				(TierChangeReason) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return tierChangeReason;
	}

	/** {@inheritDoc} */
	@Override
	public TierChangeReason findExcluding(final String name, 
			final TierChangeReason excludedTierChangeReason) {
		TierChangeReason tierChangeReason = 
				(TierChangeReason) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDED_CHANGE_REASON_PARAM_NAME, 
						excludedTierChangeReason)
				.uniqueResult();
		return tierChangeReason;
	}
}