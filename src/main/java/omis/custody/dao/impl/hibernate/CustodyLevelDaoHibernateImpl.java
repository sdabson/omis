package omis.custody.dao.impl.hibernate;

import java.util.Date;

import omis.custody.dao.CustodyLevelDao;
import omis.custody.domain.CustodyLevel;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Implementation of data access object for custody level.
 * 
 * @author Joel Norris 
 * @version 0.1.1 (February, 05 2015)
 * @since OMIS 3.0
 */
public class CustodyLevelDaoHibernateImpl 
		extends GenericHibernateDaoImpl<CustodyLevel>
		implements CustodyLevelDao {

	/* Query names. */
	
	private static final String 
		FIND_CUSTODY_LEVEL_BY_OFFENDER_AND_DATE_QUERY_NAME
		= "findCustodyLevelByoffenderAndDate";
	
	private static final String FIND_CUSTODY_LEVEL_QUERY_NAME
		= "findCustodyLevel";
	
	private static final String FIND_CUSTODY_LEVEL_EXCLUDING_QUERY_NAME
		= "findCustodyLevelExcluding";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_CUSTODY_LEVEL_PARAM_NAME
		= "excludedCustodyLevel";
	
	/**
	 * Instantiates a data access object for custody level with the specified
	 * session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public CustodyLevelDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public CustodyLevel findCustodyLevel(final Offender offender, 
			final Date date) {
		CustodyLevel custodyLevel = (CustodyLevel) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_CUSTODY_LEVEL_BY_OFFENDER_AND_DATE_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setDate(DATE_PARAM_NAME, date)
				.uniqueResult();
		return custodyLevel;
	}

	/** {@inheritDoc} */
	@Override
	public CustodyLevel find(String name) {
		CustodyLevel custodyLevel = (CustodyLevel) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_CUSTODY_LEVEL_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return custodyLevel;
	}

	/** {@inheritDoc} */
	@Override
	public CustodyLevel findExcluding(String name, CustodyLevel excludedCustodyLevel) {
		CustodyLevel custodyLevel = (CustodyLevel) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_CUSTODY_LEVEL_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDED_CUSTODY_LEVEL_PARAM_NAME, 
						excludedCustodyLevel)
				.uniqueResult();
		return custodyLevel;
	}
}