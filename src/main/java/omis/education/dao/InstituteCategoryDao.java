package omis.education.dao;

import omis.dao.GenericDao;
import omis.education.domain.InstituteCategory;

/**
 * InstituteCategoryDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 26, 2016)
 *@since OMIS 3.0
 *
 */
public interface InstituteCategoryDao extends GenericDao<InstituteCategory> {
	
	/**
	 * Finds and returns an institute category by specified name
	 * @param name - name
	 * @return institute category with specified name
	 */
	InstituteCategory find(String name);
	
	/**
	 *  Finds and returns an institute category by specified name 
	 *  excluding specified institute category (Is this possible to need?)
	 * @param name - name
	 * @param instituteCategory - institute category to exclude
	 * @return institute category with specified name excluding specified 
	 * institute category
	 */
	InstituteCategory findExcluding(String name, InstituteCategory instituteCategory);
	
	
	
	
}
