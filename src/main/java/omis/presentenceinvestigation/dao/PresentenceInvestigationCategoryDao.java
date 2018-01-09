package omis.presentenceinvestigation.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationCategory;

/**
 * PresentenceInvestigationCategoryDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 23, 2017)
 *@since OMIS 3.0
 *
 */
public interface PresentenceInvestigationCategoryDao
		extends GenericDao<PresentenceInvestigationCategory> {
	
	/**
	 * Returns a PresentenceInvestigationCategory with the specified name
	 * @param name - String
	 * @return PresentenceInvestigationCategory with the specified name
	 */
	public PresentenceInvestigationCategory find(String name);
	
	/**
	 * Returns a PresentenceInvestigationCategory with the specified name
	 * excluding specified PresentenceInvestigationCategory
	 * @param name - String
	 * @param presentenceInvestigationCategoryExcluded -
	 * PresentenceInvestigationCategory to exclude from search
	 * @return
	 */
	public PresentenceInvestigationCategory findExcluding(String name,
			PresentenceInvestigationCategory
				presentenceInvestigationCategoryExcluded);
	
	/**
	 * Returns a list of all valid PresentenceInvestigationCategories
	 * @return List of valid PresentenceInvestigationCategories
	 */
	public List<PresentenceInvestigationCategory> findAll();
}
