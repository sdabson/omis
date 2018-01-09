package omis.custody.dao.impl.hibernate;

import omis.custody.dao.CustodyOverrideDao;
import omis.custody.domain.CustodyLevel;
import omis.custody.domain.CustodyOverride;
import omis.custody.domain.CustodyReview;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

import org.hibernate.SessionFactory;

/**Implementation of
 * Database access objects for custody override.
 * @author Joel Norris 
 * @version 0.1.0 (Mar, 18 2013)
 * @since OMIS 3.0
 */
public class CustodyOverrideDaoHibernateImpl 
		extends GenericHibernateDaoImpl<CustodyOverride>
		implements CustodyOverrideDao {

	/* Query names. */
	private static final String FIND_CUSTODY_OVERRIDE_QUERY_NAME
		= "findCustodyOverride";
	
	private static final String FIND_CUSTODY_OVERRIDE_EXCLUDING_QUERY_NAME
		= "findCustodyOverrideExcluding";
	
	/* Parameter names. */
	
	private static final String CUSTODY_REVIEW_PARAM_NAME = "custodyReview";
	
	private static final String CUSTODY_LEVEL_PARAM_NAME = "custodyLevel";
	
	private static final String EXCLUDED_CUSTODY_OVERRIDE_PARAM_NAME
		= "excludedCustodyOverride";
	
	/**
	 * Instantiates a data access object for custody override with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public CustodyOverrideDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public CustodyOverride findByReview(final CustodyReview custodyReview) {
		CustodyOverride custodyOverride = (CustodyOverride) getSessionFactory()
				.getCurrentSession()
				.getNamedQuery("findCustodyOverrideByCustodyReview")
				.setParameter("custodyReview", custodyReview)
				.uniqueResult();
		
		return custodyOverride;
	}

	@Override
	public CustodyOverride find(CustodyReview custodyReview, 
			CustodyLevel custodyLevel) {
		CustodyOverride custodyOverride = (CustodyOverride) this
				.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_CUSTODY_OVERRIDE_QUERY_NAME)
				.setParameter(CUSTODY_REVIEW_PARAM_NAME, custodyReview)
				.setParameter(CUSTODY_LEVEL_PARAM_NAME, custodyLevel)
				.uniqueResult();
		return custodyOverride;
	}

	@Override
	public CustodyOverride findExcluding(CustodyReview custodyReview, 
			CustodyLevel custodyLevel, 
			CustodyOverride excludedCustodyOverride) {
		CustodyOverride custodyOverride = (CustodyOverride) this
				.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_CUSTODY_OVERRIDE_EXCLUDING_QUERY_NAME)
				.setParameter(CUSTODY_REVIEW_PARAM_NAME, custodyReview)
				.setParameter(CUSTODY_LEVEL_PARAM_NAME, custodyLevel)
				.setParameter(EXCLUDED_CUSTODY_OVERRIDE_PARAM_NAME, 
						excludedCustodyOverride)
				.uniqueResult();
		return custodyOverride;
	}
}