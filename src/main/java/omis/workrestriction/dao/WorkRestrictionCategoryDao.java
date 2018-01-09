package omis.workrestriction.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.workrestriction.domain.WorkRestrictionCategory;

/**
 * WorkRestrictionCategoryDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 16, 2016)
 *@since OMIS 3.0
 *
 */
public interface WorkRestrictionCategoryDao extends GenericDao<WorkRestrictionCategory> {
	
	/**
	 * Finds and returns a list of all work restriction categories
	 * @return list of all work restriction categories
	 */
	List<WorkRestrictionCategory> findAll();
	
}
