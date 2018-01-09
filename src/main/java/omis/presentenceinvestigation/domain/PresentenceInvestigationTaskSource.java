package omis.presentenceinvestigation.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.task.domain.TaskTemplate;

/**
 * PresentenceInvestigationTaskSource.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 23, 2017)
 *@since OMIS 3.0
 *
 */
public interface PresentenceInvestigationTaskSource
		extends Creatable, Updatable {
	
	/**
	 * Returns the ID of the PresentenceInvestigationTaskSource
	 * @return ID
	 */
	public Long getId();
	
	/**
	 * Sets the ID of the PresentenceInvestigationTaskSource
	 * @param id - Long
	 */
	public void setId(Long id);
	
	/**
	 * Returns the TaskTemplate for the PresentenceInvestigationTaskSource
	 * @return taskTemplate - TaskTemplate
	 */
	public TaskTemplate getTaskTemplate();
	
	/**
	 * Sets the TaskTemplate for the PresentenceInvestigationTaskSource
	 * @param taskTemplate - TaskTemplate
	 */
	public void setTaskTemplate(TaskTemplate taskTemplate);
	
	/**
	 * Returns the Usage for the PresentenceInvestigationTaskSource
	 * @return usage - PresentenceInvestigationTaskAssociationUsageCategory
	 */
	public PresentenceInvestigationTaskAssociationUsageCategory getUsage();
	
	/**
	 * Sets the Usage for the PresentenceInvestigationTaskSource
	 * @param usage - PresentenceInvestigationTaskAssociationUsageCategory
	 */
	public void setUsage(
			PresentenceInvestigationTaskAssociationUsageCategory usage);
	
	/**
	 * Returns the Category for the PresentenceInvestigationTaskSource
	 * @return category - PresentenceInvestigationTaskAssociationCategory
	 */
	public PresentenceInvestigationTaskAssociationCategory getCategory();
	
	/**
	 * Sets the Category for the PresentenceInvestigationTaskSource
	 * @param category - PresentenceInvestigationTaskAssociationCategory
	 */
	public void setCategory(
			PresentenceInvestigationTaskAssociationCategory category);
	
}
