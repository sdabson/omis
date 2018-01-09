package omis.paroleeligibility.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.paroleeligibility.dao.AppearanceCategoryDao;
import omis.paroleeligibility.domain.AppearanceCategory;

/**
 * Hibernate implementation of data access object for appearance category.
 *
 * @author Trevor Isles
 * @version 0.1.0 (Nov 8, 2017)
 * @since OMIS 3.0
 */
public class AppearanceCategoryDaoHibernateImpl 
	extends GenericHibernateDaoImpl<AppearanceCategory>
	implements AppearanceCategoryDao {

	/* Query names */
	
	private static final String FIND_APPEARANCE_CATEGORIES_QUERY_NAME 
		= "findAppearanceCategories";
	
	private static final String FIND_APPEARANCE_CATEGORY_QUERY_NAME
		= "findAppearanceCategory";
	
	private static final String FIND_APPEARANCE_CATEGORY_EXCLUDING_QUERY_NAME
		= "findAppearanceCategoryExcluding";
	
	/* Parameter names */
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_CATEGORY_PARAM_NAME 
		= "excludedCategory";
	
	/* Constructor */
	
	/**
	 * Instantiates a hibernate implementation of data access object for
	 * appearance category.
	 * 
	 * @param sessionFactory
	 * @param entityName
	 */
	public AppearanceCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory, 
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations */
	
	/** {@inheritDoc} */
	@Override
	public List<AppearanceCategory> findAppearanceCategories() {
		@SuppressWarnings("unchecked")
		List<AppearanceCategory> categories = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_APPEARANCE_CATEGORIES_QUERY_NAME)
				.list();
		return categories;
	}

	/** {@inheritDoc} */
	@Override
	public AppearanceCategory find(
			final String name) {
		AppearanceCategory category = (AppearanceCategory) 
			this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_APPEARANCE_CATEGORY_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return category;
	}

	/** {@inheritDoc} */
	@Override
	public AppearanceCategory findExcluding(
			final AppearanceCategory excludedCategory, 
			final String name) {
		AppearanceCategory category = (AppearanceCategory)
			this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_APPEARANCE_CATEGORY_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDED_CATEGORY_PARAM_NAME, excludedCategory)
				.uniqueResult();
		return category;
	}

}
