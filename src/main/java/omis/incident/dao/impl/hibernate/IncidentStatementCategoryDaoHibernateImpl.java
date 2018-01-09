package omis.incident.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.incident.dao.IncidentStatementCategoryDao;
import omis.incident.domain.IncidentStatementCategory;

/**
 * Incident statement category data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Sept 12, 2016)
 * @since OMIS 3.0
 */
public class IncidentStatementCategoryDaoHibernateImpl
	extends GenericHibernateDaoImpl<IncidentStatementCategory>
	implements IncidentStatementCategoryDao {

	/* Query names. */
	
	private static final String FIND_ALL_INCIDENT_STATEMENT_CATEGORIES
		= "findIncidentStatementCategories";
	
	/* Constructor. */
	/**
	 * Instantiates a default instance of incident statement data access object.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public IncidentStatementCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	@Override
	public List<IncidentStatementCategory> findAll() {
		@SuppressWarnings("unchecked")
		List<IncidentStatementCategory> categories = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALL_INCIDENT_STATEMENT_CATEGORIES)
				.list();
		return categories;
	}
}