package omis.adaaccommodation.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.adaaccommodation.dao.AuthorizationSourceCategoryDao;
import omis.adaaccommodation.domain.AuthorizationSourceCategory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

/**
 * Hibernate implementation of the authorization source category 
 * data access object.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 6, 2015)
 * @since OMIS 3.0
 */
public class AuthorizationSourceCategoryDaoHibernateImpl 
	extends GenericHibernateDaoImpl<AuthorizationSourceCategory> 
	implements AuthorizationSourceCategoryDao {

	/* Queries. */
	private static final String FIND_AUTHORIZATION_SOURCE_CATEGORIES_QUERY_NAME
		= "findAllAuthorizationSourceCategories";
	
	/**
	 * Instantiates a hibernate implementation of the data access object for 
	 * authorization source category.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public AuthorizationSourceCategoryDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<AuthorizationSourceCategory> findCategories() {
		@SuppressWarnings("unchecked")
		List<AuthorizationSourceCategory> categories = getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_AUTHORIZATION_SOURCE_CATEGORIES_QUERY_NAME).list();
		return categories;
	}
}