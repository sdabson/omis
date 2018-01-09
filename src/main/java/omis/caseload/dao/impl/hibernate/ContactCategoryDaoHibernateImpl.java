package omis.caseload.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.caseload.dao.ContactCategoryDao;
import omis.caseload.domain.ContactCategory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

/**
 * Hibernate implementation of the contact category data access object.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 2, 2016)
 * @since OMIS 3.0
 */
public class ContactCategoryDaoHibernateImpl 
	extends GenericHibernateDaoImpl<ContactCategory>
	implements ContactCategoryDao {
	
	/* Queries. */
	private static final String FIND_CONTACT_CATEGORIES_QUERY_NAME 
		= "findAllContactCategories";

	/**
	 * Instantiates a hibernate implementation of the data access object for
	 * contact category.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ContactCategoryDaoHibernateImpl(SessionFactory sessionFactory,
			String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<ContactCategory> findAllContactCategories() {
		@SuppressWarnings("unchecked")
		List<ContactCategory> categories = getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_CONTACT_CATEGORIES_QUERY_NAME).list();
		return categories;
	}

}
