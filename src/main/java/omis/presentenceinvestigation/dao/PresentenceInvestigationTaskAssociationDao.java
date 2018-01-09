package omis.presentenceinvestigation.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskAssociation;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskSource;
import omis.task.domain.Task;
import omis.task.domain.TaskTemplate;

/**
 * PresentenceInvestigationTaskAssociation.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 11, 2017)
 *@since OMIS 3.0
 *
 */
public interface PresentenceInvestigationTaskAssociationDao
		extends GenericDao<PresentenceInvestigationTaskAssociation> {
	
	/**
	 * Returns a PresentenceInvestigationTaskAssociation found with the
	 * specified properties
	 * @param task - Task
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param taskSource - PresentenceInvestigationTaskSource
	 * @return PresentenceInvestigationTaskAssociation found with the
	 * specified properties
	 */
	public PresentenceInvestigationTaskAssociation find(Task task,
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			PresentenceInvestigationTaskSource taskSource);
	
	/**
	 * Returns a PresentenceInvestigationTaskAssociation found with the
	 * specified properties excluding specified
	 * PresentenceInvestigationTaskAssociation
	 * @param task - Task
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param taskSource - PresentenceInvestigationTaskSource
	 * @param presentenceInvestigationTaskAssociationExcluded -
	 * PresentenceInvestigationTaskAssociation to exclude from search
	 * @return PresentenceInvestigationTaskAssociation found with the
	 * specified properties excluding specified
	 * PresentenceInvestigationTaskAssociation
	 */
	public PresentenceInvestigationTaskAssociation findExcluding(Task task,
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			PresentenceInvestigationTaskSource taskSource,
			PresentenceInvestigationTaskAssociation
				presentenceInvestigationTaskAssociationExcluded);
	
	/**
	 * Returns a list of Presentence Investigation Tasks found by specified
	 * PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return List of Presentence Investigation Tasks found by specified
	 * PresentenceInvestigationRequest
	 */
	public List<PresentenceInvestigationTaskAssociation>
		findTasksByPresentenceInvestigationRequest(
			PresentenceInvestigationRequest presentenceInvestigationRequest);
	
	/**
	 * Returns a Task found with specified TaskTemplate and
	 * PresentenceInvestigationRequest
	 * @param taskTemplate - TaskTemplate
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return Task found with specified TaskTemplate and
	 * PresentenceInvestigationRequest
	 */
	public Task findTaskByTaskTemplateAndPresentenceInvestigationRequest(
			TaskTemplate taskTemplate,
			PresentenceInvestigationRequest presentenceInvestigationRequest);
}
