package omis.education.dao;

import omis.dao.GenericDao;
import omis.education.domain.EducationalAchievementCategory;

/**
 * EducationalAchievementCategoryDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 26, 2016)
 *@since OMIS 3.0
 *
 */
public interface EducationalAchievementCategoryDao 
	extends GenericDao<EducationalAchievementCategory> {
	
	/**
	 * Finds and returns an educational achievement category by specified name
	 * @param name - name
	 * @return educational achievement category with specified name
	 */
	EducationalAchievementCategory find(String name);
	
	/**
	 *  Finds and returns an educational achievement category by specified name 
	 *  excluding specified educational achievement category
	 * @param name - name
	 * @param educationalAchievementCategory - educational achievement category
	 * to exclude
	 * @return Educational Achievement Category category with specified name 
	 * excluding specified educational achievement category
	 */
	EducationalAchievementCategory findExcluding(String name, 
			EducationalAchievementCategory educationalAchievementCategory);
	
	
}
