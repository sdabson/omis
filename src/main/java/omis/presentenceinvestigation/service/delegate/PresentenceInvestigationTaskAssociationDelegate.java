
package omis.presentenceinvestigation.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.PresentenceInvestigationTaskAssociationDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskAssociation;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskSource;
import omis.task.domain.Task;
import omis.task.domain.TaskTemplate;

/**
 * PresentenceInvestigationTaskAssociationDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 11, 2017)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationTaskAssociationDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"resentenceInvestigationTaskAssociation already exists with the " +
			"specified properties";
	
	private final PresentenceInvestigationTaskAssociationDao 
		presentenceInvestigationTaskAssociationDao;
	
	private final InstanceFactory<PresentenceInvestigationTaskAssociation> 
		presentenceInvestigationTaskAssociationInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for PresentenceInvestigationTaskAssociationDelegate
	 * @param presentenceInvestigationTaskAssociationDao
	 * @param presentenceInvestigationTaskAssociationInstanceFactory
	 * @param auditComponentRetriever
	 */
	public PresentenceInvestigationTaskAssociationDelegate(
			final PresentenceInvestigationTaskAssociationDao 
				presentenceInvestigationTaskAssociationDao,
			final InstanceFactory<PresentenceInvestigationTaskAssociation> 
				presentenceInvestigationTaskAssociationInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.presentenceInvestigationTaskAssociationDao =
				presentenceInvestigationTaskAssociationDao;
		this.presentenceInvestigationTaskAssociationInstanceFactory =
				presentenceInvestigationTaskAssociationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a PresentenceInvestigationTaskAssociation with the specified
	 * properties
	 * @param task - Task
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param taskSource - PresentenceInvestigationTaskSource
	 * @return Newly created PresentenceInvestigationTaskAssociation
	 * @throws DuplicateEntityFoundException - When a
	 * PresentenceInvestigationTaskAssociation already exists with the specified
	 * properties
	 */
	public PresentenceInvestigationTaskAssociation create(final Task task,
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final PresentenceInvestigationTaskSource taskSource)
					throws DuplicateEntityFoundException{
		if(this.presentenceInvestigationTaskAssociationDao.find(
				task, presentenceInvestigationRequest, taskSource) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		PresentenceInvestigationTaskAssociation
			presentenceInvestigationTaskAssociation = 
				this.presentenceInvestigationTaskAssociationInstanceFactory
				.createInstance();
		
		presentenceInvestigationTaskAssociation.setTask(task);
		presentenceInvestigationTaskAssociation.setTaskSource(taskSource);
		presentenceInvestigationTaskAssociation
			.setPresentenceInvestigationRequest(presentenceInvestigationRequest);
		presentenceInvestigationTaskAssociation.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		presentenceInvestigationTaskAssociation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.presentenceInvestigationTaskAssociationDao.makePersistent(
				presentenceInvestigationTaskAssociation);
	}
	
	/**
	 * Updates a PresentenceInvestigationTaskAssociation with the specified
	 * properties
	 * @param presentenceInvestigationTaskAssociation -
	 * PresentenceInvestigationTaskAssociation to update
	 * @param task - Task
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param taskSource - PresentenceInvestigationTaskSource
	 * @return Updated PresentenceInvestigationTaskAssociation
	 * @throws DuplicateEntityFoundException - When another
	 * PresentenceInvestigationTaskAssociation already exists with the specified
	 * properties
	 */
	public PresentenceInvestigationTaskAssociation update(
			final PresentenceInvestigationTaskAssociation
				presentenceInvestigationTaskAssociation, final Task task,
				final PresentenceInvestigationRequest presentenceInvestigationRequest,
				final PresentenceInvestigationTaskSource taskSource)
					throws DuplicateEntityFoundException{
		if(this.presentenceInvestigationTaskAssociationDao.findExcluding(
				task, presentenceInvestigationRequest, taskSource,
				presentenceInvestigationTaskAssociation) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		presentenceInvestigationTaskAssociation.setTask(task);
		presentenceInvestigationTaskAssociation.setTaskSource(taskSource);
		presentenceInvestigationTaskAssociation
			.setPresentenceInvestigationRequest(presentenceInvestigationRequest);
		presentenceInvestigationTaskAssociation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.presentenceInvestigationTaskAssociationDao.makePersistent(
				presentenceInvestigationTaskAssociation);
	}
	
	/**
	 * Removes the specified PresentenceInvestigationTaskAssociation
	 * @param presentenceInvestigationTaskAssociation -
	 * PresentenceInvestigationTaskAssociation to remove
	 */
	public void remove(final PresentenceInvestigationTaskAssociation
			presentenceInvestigationTaskAssociation){
		this.presentenceInvestigationTaskAssociationDao.makeTransient(
				presentenceInvestigationTaskAssociation);
	}
	
	/**
	 * Returns a list of Presentence Investigation Tasks found by specified
	 * PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return List of Presentence Investigation Tasks found by specified
	 * PresentenceInvestigationRequest
	 */
	public List<PresentenceInvestigationTaskAssociation>
		findTasksByPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest presentenceInvestigationRequest){
		return this.presentenceInvestigationTaskAssociationDao
				.findTasksByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
	}
	
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
			PresentenceInvestigationRequest presentenceInvestigationRequest) {
		return this.presentenceInvestigationTaskAssociationDao
				.findTaskByTaskTemplateAndPresentenceInvestigationRequest(
						taskTemplate, presentenceInvestigationRequest);
	}
}
