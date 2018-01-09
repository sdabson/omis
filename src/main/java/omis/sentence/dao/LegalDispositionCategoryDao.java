package omis.sentence.dao;

import omis.dao.GenericDao;
import omis.sentence.domain.LegalDispositionCategory;

/**
 * Data access object for legal disposition categories.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Apr 3, 2017)
 * @since OMIS 3.0
 */
public interface LegalDispositionCategoryDao extends GenericDao<LegalDispositionCategory> {

	/**
	 * Finds the specified legal disposition category by the specified name.
	 * 
	 * @param name name
	 * @return legal disposition category
	 */
	LegalDispositionCategory find(String name);
	
	/**
	 * Finds the specified legal disposition category by the specified name 
	 * excluding the specified category.
	 * 
	 * @param name name
	 * @param excludedCategory excluded legal disposition category
	 * @return legal disposition category
	 */
	LegalDispositionCategory findExcluding(String name, 
			LegalDispositionCategory excludedCategory);
}
