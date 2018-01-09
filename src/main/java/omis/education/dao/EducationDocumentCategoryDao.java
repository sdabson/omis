package omis.education.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.education.domain.EducationDocumentCategory;

/**
 * EducationalDocumentCategoryDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 13, 2017)
 *@since OMIS 3.0
 *
 */
public interface EducationDocumentCategoryDao
	extends GenericDao<EducationDocumentCategory> {
	
	
	/**
	 * Finds and returns a list of all valid EducationDocumentCategories
	 * @return List of EducationDocumentCategories
	 * */
	List<EducationDocumentCategory> findAll();
	
}
