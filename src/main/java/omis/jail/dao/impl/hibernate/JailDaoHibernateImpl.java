package omis.jail.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.jail.dao.JailDao;
import omis.jail.domain.Jail;
import omis.organization.domain.Organization;

/**
 * Hibernate implementation of data access object for jails.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Apr 18, 2016)
 * @since OMIS 3.0
 */
public class JailDaoHibernateImpl
		extends GenericHibernateDaoImpl<Jail>
		implements JailDao {
	
	/* Query names. */
	
	private static final String FIND_ALL_QUERY_NAME = "findJails";
	
	private static final String FIND_BY_ORGANIZATION_QUERY_NAME
		= "findJailsByOrganization";
	
	/* Parameter names. */
	
	private static final String ORGANIZATION_PARAM_NAME = "organization";
	
	/* Constructors. */

	/**
	 * Instantiates Hibernate implementation of data access object for jails.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public JailDaoHibernateImpl(SessionFactory sessionFactory,
			String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<Jail> findAll() {
		@SuppressWarnings("unchecked")
		List<Jail> jails = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return jails;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Jail> findByOrganization(
			final Organization organization) {
		@SuppressWarnings("unchecked")
		List<Jail> jails = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_ORGANIZATION_QUERY_NAME)
				.setParameter(ORGANIZATION_PARAM_NAME, organization)
				.list();
		return jails;
	}
}