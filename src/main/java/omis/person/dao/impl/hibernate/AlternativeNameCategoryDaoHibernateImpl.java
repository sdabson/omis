package omis.person.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.person.dao.AlternativeNameCategoryDao;
import omis.person.domain.AlternativeNameCategory;

/**
 * Hibernate implementation of data access object for alternative name
 * categories.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Nov 22, 2013)
 * @since OMIS 3.0
 */
public class AlternativeNameCategoryDaoHibernateImpl
		extends GenericHibernateDaoImpl<AlternativeNameCategory>
		implements AlternativeNameCategoryDao {
	
	private static final String FIND_ALL_QUERY_NAME
			= "findAlternativeNameCategories";

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * alternative name categories.
	 * 
	 * @param sessionFactory
	 * @param entityName
	 */
	public AlternativeNameCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<AlternativeNameCategory> findAll() {
		@SuppressWarnings("unchecked")
		List<AlternativeNameCategory> categories
				= this.getSessionFactory().getCurrentSession()
					.getNamedQuery(FIND_ALL_QUERY_NAME)
					.list();
		return categories;
	}
}