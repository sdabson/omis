package omis.incident.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.incident.dao.JurisdictionDao;
import omis.incident.domain.Jurisdiction;

/**
 * Jurisdiction data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 6, 2015)
 * @since OMIS 3.0
 */
public class JurisdictionDaoHibernateImpl
extends GenericHibernateDaoImpl<Jurisdiction>
implements JurisdictionDao {

	/* Query names. */
	
	private static final String FIND_ALL_JURISDICTIONS_QUERY_NAME
		= "findAllJurisdictions";
	
	/**
	 * Instantiates a jurisdiction data access object with the specified
	 * session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public JurisdictionDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Jurisdiction> findAll() {
		@SuppressWarnings("unchecked")
		List<Jurisdiction> jurisdictions = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALL_JURISDICTIONS_QUERY_NAME)
				.list();
		return jurisdictions;
	}
}