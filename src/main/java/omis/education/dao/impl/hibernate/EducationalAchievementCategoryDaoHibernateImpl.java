package omis.education.dao.impl.hibernate;


import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.education.dao.EducationalAchievementCategoryDao;
import omis.education.domain.EducationalAchievementCategory;

/**
 * EducationalAchievementCategoryDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 26, 2016)
 *@since OMIS 3.0
 *
 */
public class EducationalAchievementCategoryDaoHibernateImpl
		extends GenericHibernateDaoImpl<EducationalAchievementCategory> 
		implements EducationalAchievementCategoryDao {

	/* Query Names */
	
	private static final String FIND_EDUCATIONAL_ACHIEVEMENT_CATEGORY_QUERY_NAME 
		= "findEducationalAchievementCategory";
	
	private static final String 
		FIND_EDUCATIONAL_ACHIEVEMENT_CATEGORY_EXCLUDING_QUERY_NAME 
			= "findEducationalAchievementCategoryExcluding";
	
	private static final String 
		FIND_ALL_EDUCATIONAL_ACHIEVEMENT_CATEGORIES_QUERY_NAME 
		= "findAllEducationalAchievementCategories";
	
	/* Parameter names */
	
	private static final String NAME_PARAM_NAME = "name";
	
	
	private static final String EDUCATIONAL_ACHIEVEMENT_CATEGORY_PARAM_NAME 
		= "educationalAchievementCategory";

	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected EducationalAchievementCategoryDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public EducationalAchievementCategory find(final String name) {
		EducationalAchievementCategory educationalAchievementCategory 
		= (EducationalAchievementCategory) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_EDUCATIONAL_ACHIEVEMENT_CATEGORY_QUERY_NAME)
			.setParameter(NAME_PARAM_NAME, name)
			.uniqueResult();
		return educationalAchievementCategory;
	}

	/**{@inheritDoc} */
	@Override
	public EducationalAchievementCategory findExcluding(final String name,
			final EducationalAchievementCategory educationalAchievementCategory) {
		EducationalAchievementCategory foundEducationalAchievementCategory 
		= (EducationalAchievementCategory) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(
					FIND_EDUCATIONAL_ACHIEVEMENT_CATEGORY_EXCLUDING_QUERY_NAME)
			.setParameter(NAME_PARAM_NAME, name)
			.setParameter(EDUCATIONAL_ACHIEVEMENT_CATEGORY_PARAM_NAME, 
					educationalAchievementCategory)
			.uniqueResult();
		return foundEducationalAchievementCategory;
	}
	
	/**{@inheritDoc} */
	@Override
	public List<EducationalAchievementCategory> findAll() {
		@SuppressWarnings("unchecked")
		List<EducationalAchievementCategory> educationalAchievementCategories =
			this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_ALL_EDUCATIONAL_ACHIEVEMENT_CATEGORIES_QUERY_NAME)
			.list();
		return educationalAchievementCategories;
	}

}
