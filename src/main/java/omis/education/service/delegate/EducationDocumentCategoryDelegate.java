package omis.education.service.delegate;

import java.util.List;

import omis.education.dao.EducationDocumentCategoryDao;
import omis.education.domain.EducationDocumentCategory;

/**
 * EducationDocumentCategoryDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 13, 2017)
 *@since OMIS 3.0
 *
 */
public class EducationDocumentCategoryDelegate {
	
	public final EducationDocumentCategoryDao educationDocumentCategoryDao;

	/**
	 * Contructor for EducationDocumentCategoryDelegate
	 * @param educationDocumentCategoryDao
	 */
	public EducationDocumentCategoryDelegate(
			final EducationDocumentCategoryDao educationDocumentCategoryDao) {
		this.educationDocumentCategoryDao = educationDocumentCategoryDao;
	}
	
	/**
	 * Returns a list of all valid EducationDocumentCategories
	 * @return List of all EducationDocumentCategories
	 */
	public List<EducationDocumentCategory> findAll() {
		return this.educationDocumentCategoryDao.findAll();
	}
	
}
