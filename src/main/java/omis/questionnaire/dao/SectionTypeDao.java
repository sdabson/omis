package omis.questionnaire.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.questionnaire.domain.SectionType;

/**
 * SectionTypeDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 8, 2016)
 *@since OMIS 3.0
 *
 */
public interface SectionTypeDao extends GenericDao<SectionType> {
	
	/**
	 * Returns a SectionType with specified description
	 * @param description - String
	 * @return SectionType
	 */
	SectionType find(String description);
	
	/**
	 * Returns a SectionType with specified description, excluding specified
	 * SectionType
	 * @param description - String
	 * @param excludedSectionType - SectionType to exclude
	 * @return SectionType
	 */
	SectionType findExcluding(String description, 
			SectionType excludedSectionType);
	
	/** 
	 * Returns a list of all valid SectionTypes
	 * @return List of all valid SectionTypes
	 * */
	List<SectionType> findAll();
	
}
