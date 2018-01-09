package omis.presentenceinvestigation.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationCategory;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskGroup;
import omis.task.domain.TaskTemplateGroup;

/**
 * PresentenceInvestigationTaskGroupDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 11, 2017)
 *@since OMIS 3.0
 *
 */
public interface PresentenceInvestigationTaskGroupDao
		extends GenericDao<PresentenceInvestigationTaskGroup> {
	
	/**
	 * Returns a list of PresentenceInvestigation TaskTemplateGroups
	 * @return List of PresentenceInvestigation TaskTemplateGroups
	 */
	public List<TaskTemplateGroup> findPresentenceInvestigationTaskGroups();
	
	/**
	 * Returns a PresentenceInvestigationTaskGroup with the specified
	 * PresentenceInvestigationCategory
	 * @param presentenceInvestigationCategory - PresentenceInvestigationCategory
	 * @return PresentenceInvestigationTaskGroup with the specified
	 * PresentenceInvestigationCategory
	 */
	public PresentenceInvestigationTaskGroup
		findByPresentenceInvestigationCategory(
			PresentenceInvestigationCategory presentenceInvestigationCategory);
}
