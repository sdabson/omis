package omis.specialneed.dao;

import omis.dao.GenericDao;
import omis.specialneed.domain.SpecialNeedClassification;

/**
 * Data access object for special need classification.
 *
 * @author Sheronda Vaughn
 * @author Josh Divine
 * @version 0.1.1 (Jul 19, 2017)
 * @since OMIS 3.0
 */
public interface SpecialNeedClassificationDao 
	extends GenericDao<SpecialNeedClassification> {

	/**
	 * Returns special need classification matching the specified name.
	 * 
	 * @param name name
	 * @return special need classification
	 */
	SpecialNeedClassification find(String name);
	
	/**
	 * Returns special need classification matching the specified name, 
	 * excluding the specified classification.
	 * 
	 * @param name name
	 * @param excludedClassification excluded special need classification
	 * @return special need classification
	 */
	SpecialNeedClassification findExcluding(String name, 
			SpecialNeedClassification excludedClassification);
}