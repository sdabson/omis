package omis.region.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.region.dao.CountyDao;
import omis.region.domain.County;
import omis.region.domain.State;

/**
 * Hibernate entity configurable implementation of data access object for
 * counties.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 6, 2013)
 * @since OMIS 3.0
 */
public class CountyDaoHibernateImpl
		extends GenericHibernateDaoImpl<County>
		implements CountyDao {

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * counties with the specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public CountyDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<County> findAll() {
		@SuppressWarnings("unchecked")
		List<County> counties = getSessionFactory().getCurrentSession()
				.getNamedQuery("findCounties").list();
		return counties;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<County> findByState(final State state) {
		@SuppressWarnings("unchecked")
		List<County> counties = getSessionFactory().getCurrentSession()
				.getNamedQuery("findAllCountiesInState")//this was pointed to "findCountiesByState" which didn't exist.
				.setParameter("state", state)
				.list();
		return counties;
	}
}