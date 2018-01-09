package omis.custody.dao.impl.hibernate;

import omis.custody.dao.CustodyChangeReasonDao;
import omis.custody.domain.CustodyChangeReason;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

import org.hibernate.SessionFactory;

/**
 * Implementation of
 * database access objects for custody change reason.
 * @author Joel Norris 
 * @version 0.1.0 (Mar, 07 2013)
 * @since OMIS 3.0
 */
public class CustodyChangeReasonDaoHibernateImpl 
		extends GenericHibernateDaoImpl<CustodyChangeReason> 
		implements CustodyChangeReasonDao {

	/* Query names */
	private static final String FIND_CUSTODY_CHANGE_REASON_QUERY_NAME
		= "findCustodyChangeReason";
	private static final String FIND_CUSTODY_CHANGE_REASON_EXCLUDING_QUERY_NAME
		= "findCustodyChangeReasonExcluding";
	
	/* Parameter names */
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_CUSTODY_CHANGE_REASON_PARAM_NAME
		= "excludedCustodyChangeReason";
	
	/**
	 * Instantiates a data access object for custody change reason with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public CustodyChangeReasonDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public CustodyChangeReason find(final String name) {
		CustodyChangeReason reason = (CustodyChangeReason) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_CUSTODY_CHANGE_REASON_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return reason;
	}

	/** {@inheritDoc} */
	@Override
	public CustodyChangeReason findExcluding(final String name, 
			final CustodyChangeReason excludedCustodyChangeReason) {
		CustodyChangeReason reason = (CustodyChangeReason) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_CUSTODY_CHANGE_REASON_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDED_CUSTODY_CHANGE_REASON_PARAM_NAME, 
						excludedCustodyChangeReason)
				.uniqueResult();
		return reason;
	}
}