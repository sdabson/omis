package omis.workrestriction.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.workrestriction.dao.WorkRestrictionCategoryDao;
import omis.workrestriction.domain.WorkRestrictionCategory;

/**
 * WorkRestrictionCategoryDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 16, 2016)
 *@since OMIS 3.0
 *
 */
public class WorkRestrictionCategoryDaoHibernateImpl extends GenericHibernateDaoImpl<WorkRestrictionCategory> 
		implements WorkRestrictionCategoryDao {
	
	private static final String FIND_WORK_RESTRICTION_CATEGORIES_QUERY_NAME 
		= "findWorkRestrictionCategories";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected WorkRestrictionCategoryDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}
	
	/**{@inheritDoc} */
	@Override
	public List<WorkRestrictionCategory> findAll() {
		@SuppressWarnings("unchecked")
		List<WorkRestrictionCategory> categories = this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_WORK_RESTRICTION_CATEGORIES_QUERY_NAME)
			.list();
		
		return categories;
	}



}