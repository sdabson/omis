package omis.presentenceinvestigation.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskAssociationCategory;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskAssociationUsageCategory;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskSource;
import omis.task.domain.TaskTemplate;
import omis.task.domain.TaskTemplateGroup;

/**
 * PresentenceInvestigationTaskSourceDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 23, 2017)
 *@since OMIS 3.0
 *
 */
public interface PresentenceInvestigationTaskSourceDao
		extends GenericDao<PresentenceInvestigationTaskSource> {
	
	/**
	 * Returns a PresentenceInvestigationTaskSource with the specified properties
	 * @param taskTemplate - TaskTemplate
	 * @param usage - PresentenceInvestigationTaskAssociationUsageCategory
	 * @param category - PresentenceInvestigationTaskAssociationCategory
	 * @return PresentenceInvestigationTaskSource with the specified properties
	 */
	public PresentenceInvestigationTaskSource find(TaskTemplate taskTemplate,
			PresentenceInvestigationTaskAssociationUsageCategory usage,
			PresentenceInvestigationTaskAssociationCategory category);
	
	/**
	 * Returns a PresentenceInvestigationTaskSource with the specified properties
	 * excluding specified PresentenceInvestigationTaskSource
	 * @param taskTemplate - TaskTemplate
	 * @param usage - PresentenceInvestigationTaskAssociationUsageCategory
	 * @param category - PresentenceInvestigationTaskAssociationCategory
	 * @param presentenceInvestigationTaskSourceExcluding -
	 * 
	 * PresentenceInvestigationTaskSource to exclude
	 * @return PresentenceInvestigationTaskSource with the specified properties
	 * excluding specified PresentenceInvestigationTaskSource
	 */
	public PresentenceInvestigationTaskSource findExcluding(
			TaskTemplate taskTemplate,
			PresentenceInvestigationTaskAssociationUsageCategory usage,
			PresentenceInvestigationTaskAssociationCategory category,
			PresentenceInvestigationTaskSource
				presentenceInvestigationTaskSourceExcluding);
	
	/**
	 * Returns a list of PresentenceInvestigationTaskSources found by specified
	 * TaskTemplateGroup
	 * @param group - TaskTemplateGroup
	 * @return List of PresentenceInvestigationTaskSources found by specified
	 * TaskTemplateGroup
	 */
	public List<PresentenceInvestigationTaskSource> findByTaskTemplateGroup(
			TaskTemplateGroup group);
}
