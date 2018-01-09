package omis.demographics.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.demographics.dao.BuildDao;
import omis.demographics.domain.Build;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for builds.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Sep 6, 2013)
 * @since OMIS 3.0
 */
public class BuildDaoHibernateImpl
		extends GenericHibernateDaoImpl<Build>
		implements BuildDao {
	
	/* Query names. */
	
	private static final String FIND_ALL_QUERY_NAME = "findBuilds";

	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * builds.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public BuildDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<Build> findAll() {
		@SuppressWarnings("unchecked")
		List<Build> builds = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return builds;
	}
}