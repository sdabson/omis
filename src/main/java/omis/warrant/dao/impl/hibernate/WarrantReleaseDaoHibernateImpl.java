package omis.warrant.dao.impl.hibernate;


import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.warrant.dao.WarrantReleaseDao;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantRelease;

/**
 * WarrantReleaseDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 8, 2017)
 *@since OMIS 3.0
 *
 */
public class WarrantReleaseDaoHibernateImpl
	extends GenericHibernateDaoImpl<WarrantRelease>
		implements WarrantReleaseDao {
	
	private static final String FIND_WARRANT_RELEASE_QUERY_NAME =
			"findWarrantRelease";
	
	private static final String FIND_WARRANT_RELEASE_EXCLUDING_QUERY_NAME =
			"findWarrantReleaseExcluding";
	
	private static final String WARRANT_PARAM_NAME = "warrant";
	
	private static final String WARRANT_RELEASE_PARAM_NAME = "warrantRelease";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected WarrantReleaseDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public WarrantRelease find(final Warrant warrant) {
		WarrantRelease warrantRelease = (WarrantRelease) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WARRANT_RELEASE_QUERY_NAME)
				.setParameter(WARRANT_PARAM_NAME, warrant)
				.uniqueResult();
		
		return warrantRelease;
	}

	/**{@inheritDoc} */
	@Override
	public WarrantRelease findExcluding(final Warrant warrant,
			final WarrantRelease warrantReleaseExlcluded) {
		WarrantRelease warrantRelease = (WarrantRelease) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WARRANT_RELEASE_EXCLUDING_QUERY_NAME)
				.setParameter(WARRANT_PARAM_NAME, warrant)
				.setParameter(WARRANT_RELEASE_PARAM_NAME, warrantReleaseExlcluded)
				.uniqueResult();
		
		return warrantRelease;
	}

}
