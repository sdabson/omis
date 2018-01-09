package omis.sentence.dao;

import omis.dao.GenericDao;
import omis.sentence.domain.SentenceCategory;

/**
 * Data access object for sentence categories.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 27, 2017)
 * @since OMIS 3.0
 */
public interface SentenceCategoryDao extends GenericDao<SentenceCategory> {

	/**
	 * Finds the specified sentence category by the specified name.
	 * 
	 * @param name name
	 * @return sentence category
	 */
	SentenceCategory find(String name);
	
	/**
	 * Finds the specified sentence category by the specified name excluding 
	 * the specified category.
	 * 
	 * @param name name
	 * @param excludedCategory excluded sentence category
	 * @return sentence category
	 */
	SentenceCategory findExcluding(String name, 
			SentenceCategory excludedCategory);
}
