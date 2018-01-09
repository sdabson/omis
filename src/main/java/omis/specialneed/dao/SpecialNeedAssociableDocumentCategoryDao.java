/**
 * 
 */
package omis.specialneed.dao;

import omis.dao.GenericDao;
import omis.specialneed.domain.SpecialNeedAssociableDocumentCategory;

/**
 * Data access object for special need associable document category.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Nov 1, 2017)
 * @since OMIS 3.0
 */
public interface SpecialNeedAssociableDocumentCategoryDao 
	extends GenericDao<SpecialNeedAssociableDocumentCategory> {
	
	/**
	 * Returns special need associable document category matching the specified 
	 * name.
	 * 
	 * @param name name
	 * @return special need associable document category
	 */
	SpecialNeedAssociableDocumentCategory find(String name);
	
	/**
	 * Returns special need associable document category matching the specified 
	 * name excluding the specified category.
	 * 
	 * @param name name
	 * @param excludedCategory excluded special need associable document 
	 * category
	 * @return special need associable document category
	 */
	SpecialNeedAssociableDocumentCategory findExcluding(String name, 
			SpecialNeedAssociableDocumentCategory excludedCategory);
}