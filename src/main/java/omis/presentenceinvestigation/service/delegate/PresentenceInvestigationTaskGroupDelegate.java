package omis.presentenceinvestigation.service.delegate;

import java.util.List;

import omis.presentenceinvestigation.dao.PresentenceInvestigationTaskGroupDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationCategory;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskGroup;
import omis.task.domain.TaskTemplateGroup;

/**
 * PresentenceInvestigationTaskGroupDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 11, 2017)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationTaskGroupDelegate {
	
	public final PresentenceInvestigationTaskGroupDao
		presentenceInvestigationTaskGroupDao;

	/**
	 * Contructor for PresentenceInvestigationTaskGroupDelegate
	 * @param presentenceInvestigationTaskGroupDao
	 */
	public PresentenceInvestigationTaskGroupDelegate(
			final PresentenceInvestigationTaskGroupDao
				presentenceInvestigationTaskGroupDao) {
		this.presentenceInvestigationTaskGroupDao =
				presentenceInvestigationTaskGroupDao;
	}
	
	/**
	 * Returns a list of PresentenceInvestigation TaskTemplateGroups
	 * @return List of PresentenceInvestigation TaskTemplateGroups
	 */
	public List<TaskTemplateGroup> findPresentenceInvestigationTaskGroups() {
		return this.presentenceInvestigationTaskGroupDao
				.findPresentenceInvestigationTaskGroups();
	}
	
	/**
	 * Returns a PresentenceInvestigationTaskGroup with the specified
	 * PresentenceInvestigationCategory
	 * @param presentenceInvestigationCategory - PresentenceInvestigationCategory
	 * @return PresentenceInvestigationTaskGroup with the specified
	 * PresentenceInvestigationCategory
	 */
	public PresentenceInvestigationTaskGroup
		findByPresentenceInvestigationCategory(
			final PresentenceInvestigationCategory
					presentenceInvestigationCategory) {
		return this.presentenceInvestigationTaskGroupDao
				.findByPresentenceInvestigationCategory(
						presentenceInvestigationCategory);
	}
	
}
