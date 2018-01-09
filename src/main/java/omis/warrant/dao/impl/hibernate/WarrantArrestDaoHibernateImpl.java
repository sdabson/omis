package omis.warrant.dao.impl.hibernate;


import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.warrant.dao.WarrantArrestDao;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantArrest;

/**
 * WarrantArrestDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 8, 2017)
 *@since OMIS 3.0
 *
 */
public class WarrantArrestDaoHibernateImpl
	extends GenericHibernateDaoImpl<WarrantArrest>implements WarrantArrestDao {
	
	private static final String FIND_WARRANT_ARREST_QUERY_NAME = 
			"findWarrantArrest";
	
	private static final String FIND_WARRANT_ARREST_EXCLUDING_QUERY_NAME =
			"findWarrantArrestExcluding";
	
	private static final String WARRANT_PARAM_NAME = "warrant";
	
	private static final String WARRANT_ARREST_PARAM_NAME = "warrantArrest";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected WarrantArrestDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/**{@inheritDoc} */
	@Override
	public WarrantArrest find(final Warrant warrant) {
		WarrantArrest warrantArrest = (WarrantArrest) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WARRANT_ARREST_QUERY_NAME)
				.setParameter(WARRANT_PARAM_NAME, warrant)
				.uniqueResult();
		
		return warrantArrest;
	}

	/**{@inheritDoc} */
	@Override
	public WarrantArrest findExcluding(final Warrant warrant,
			final WarrantArrest warrantArrestExcluded) {
		WarrantArrest warrantArrest = (WarrantArrest) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WARRANT_ARREST_EXCLUDING_QUERY_NAME)
				.setParameter(WARRANT_PARAM_NAME, warrant)
				.setParameter(WARRANT_ARREST_PARAM_NAME, warrantArrestExcluded)
				.uniqueResult();
		
		return warrantArrest;
	}

}
