package omis.warrant.dao.impl.hibernate;


import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.warrant.dao.WarrantCancellationDao;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantCancellation;

/**
 * WarrantCancellationDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 8, 2017)
 *@since OMIS 3.0
 *
 */
public class WarrantCancellationDaoHibernateImpl
	extends GenericHibernateDaoImpl<WarrantCancellation>
		implements WarrantCancellationDao {
	
	private static final String FIND_WARRANT_CANCELLATION_QUERY_NAME =
			"findWarrantCancellation";
	
	private static final String FIND_WARRANT_CANCELLATION_EXCLUDING_QUERY_NAME =
			"findWarrantCancellationExcluding";
	
	private static final String WARRANT_PARAM_NAME = "warrant";
	
	private static final String WARRANT_CANCELLATION_PARAM_NAME =
			"warrantCancellation";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected WarrantCancellationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/**{@inheritDoc} */
	@Override
	public WarrantCancellation find(final Warrant warrant) {
		WarrantCancellation warrantCancellation = (WarrantCancellation)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WARRANT_CANCELLATION_QUERY_NAME)
				.setParameter(WARRANT_PARAM_NAME, warrant)
				.uniqueResult();
		
		return warrantCancellation;
	}

	/**{@inheritDoc} */
	@Override
	public WarrantCancellation findExcluding(final Warrant warrant,
			final WarrantCancellation warrantCancellationExcluding) {
		WarrantCancellation warrantCancellation = (WarrantCancellation)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WARRANT_CANCELLATION_EXCLUDING_QUERY_NAME)
				.setParameter(WARRANT_PARAM_NAME, warrant)
				.setParameter(WARRANT_CANCELLATION_PARAM_NAME,
						warrantCancellationExcluding)
				.uniqueResult();
		
		return warrantCancellation;
	}

}
