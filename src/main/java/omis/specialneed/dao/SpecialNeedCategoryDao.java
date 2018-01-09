/**
 * 
 */
package omis.specialneed.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.specialneed.domain.SpecialNeedCategory;
import omis.specialneed.domain.SpecialNeedClassification;

/**
 * Data access object for special need category.
 * 
 * @author Joel Norris 
 * @author Sheronda Vaughn
 * @author Josh Divine
 * @version 0.1.3 (Jul 19, 2017)
 * @since OMIS 3.0
 */
public interface SpecialNeedCategoryDao 
	extends GenericDao<SpecialNeedCategory> {

	
	List<SpecialNeedCategory> findCategories(
			SpecialNeedClassification classification);
	
	/**
	 * Returns special need category matching the specified name and 
	 * classification.
	 * 
	 * @param name name
	 * @param classifiction special need classification
	 * @return special need category
	 */
	SpecialNeedCategory find(String name, 
			SpecialNeedClassification classification);
	
	/**
	 * Returns special need category matching the specified name and 
	 * classification, excluding the specified category.
	 * 
	 * @param name name
	 * @param classifiction special need classification
	 * @param excludedCategory excluded special need category
	 * @return special need category
	 */
	SpecialNeedCategory findExcluding(String name, 
			SpecialNeedClassification classification, 
			SpecialNeedCategory excludedCategory);
}