package omis.education.dao.impl.hibernate;



import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.education.dao.InstituteCategoryDao;
import omis.education.domain.InstituteCategory;

/**
 * InstituteCategoryDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 26, 2016)
 *@since OMIS 3.0
 *
 */
public class InstituteCategoryDaoHibernateImpl 
	extends GenericHibernateDaoImpl<InstituteCategory> 
	implements InstituteCategoryDao {

	/* Query Names */
	
	private static final String FIND_INSTITUTE_CATEGORY_QUERY_NAME 
		= "findInstituteCategory";
	
	private static final String 
		FIND_INSTITUTE_CATEGORY_EXCLUDING_QUERY_NAME 
			= "findInstituteCategoryExcluding";
	
	private static final String FIND_ALL_INSTITUTE_CATEGORIES_QUERY_NAME 
		= "findAllInstituteCategories";
	
	/* Parameter names */
	
	private static final String NAME_PARAM_NAME = "name";
	
	
	private static final String INSTITUTE_CATEGORY_PARAM_NAME 
		= "instituteCategory";

	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected InstituteCategoryDaoHibernateImpl(SessionFactory sessionFactory, 
			String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public InstituteCategory find(final String name) {
		InstituteCategory instituteCategory 
		= (InstituteCategory) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_INSTITUTE_CATEGORY_QUERY_NAME)
			.setParameter(NAME_PARAM_NAME, name)
			.uniqueResult();
		return instituteCategory;
	}

	/**{@inheritDoc} */
	@Override
	public InstituteCategory findExcluding(final String name, 
			final InstituteCategory instituteCategory) {
		InstituteCategory foundInstituteCategory 
		= (InstituteCategory) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(
					FIND_INSTITUTE_CATEGORY_EXCLUDING_QUERY_NAME)
			.setParameter(NAME_PARAM_NAME, name)
			.setParameter(INSTITUTE_CATEGORY_PARAM_NAME, instituteCategory)
			.uniqueResult();
		return foundInstituteCategory;
	}
	
	/**{@inheritDoc} */
	@Override
	public List<InstituteCategory> findAll() {
		@SuppressWarnings("unchecked")
		List<InstituteCategory> instituteCategories =
			this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_ALL_INSTITUTE_CATEGORIES_QUERY_NAME)
			.list();
		return instituteCategories;
	}

}
