package omis.presentenceinvestigation.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.PresentenceInvestigationTaskSourceDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskAssociationCategory;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskAssociationUsageCategory;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskSource;
import omis.task.domain.TaskTemplate;
import omis.task.domain.TaskTemplateGroup;

/**
 * PresentenceInvestigationTaskSourceDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 23, 2017)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationTaskSourceDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"PresentenceInvestigationTaskSource already exists with given " +
			"TaskTemplate, PresentenceInvestigationTaskAssociationUsageCategory," +
			" and PresentenceInvestigationTaskAssociationCategory.";
	
	private final PresentenceInvestigationTaskSourceDao
		presentenceInvestigationTaskSourceDao;
	
	private final InstanceFactory<PresentenceInvestigationTaskSource> 
		presentenceInvestigationTaskSourceInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for PresentenceInvestigationTaskSourceDelegate
	 * @param presentenceInvestigationTaskSourceDao
	 * @param presentenceInvestigationTaskSourceInstanceFactory
	 * @param auditComponentRetriever
	 */
	public PresentenceInvestigationTaskSourceDelegate(
			final PresentenceInvestigationTaskSourceDao
				presentenceInvestigationTaskSourceDao,
			final InstanceFactory<PresentenceInvestigationTaskSource> 
				presentenceInvestigationTaskSourceInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.presentenceInvestigationTaskSourceDao =
				presentenceInvestigationTaskSourceDao;
		this.presentenceInvestigationTaskSourceInstanceFactory =
				presentenceInvestigationTaskSourceInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a PresentenceInvestigationTaskSource with the specified properties
	 * @param taskTemplate - TaskTemplate
	 * @param usage - PresentenceInvestigationTaskAssociationUsageCategory
	 * @param category - PresentenceInvestigationTaskAssociationCategory
	 * @return Newly created PresentenceInvestigationTaskSource
	 * @throws DuplicateEntityFoundException - When a
	 * PresentenceInvestigationTaskSource already exists with given
	 * TaskTemplate, PresentenceInvestigationTaskAssociationUsageCategory,
	 * and PresentenceInvestigationTaskAssociationCategory
	 */
	public PresentenceInvestigationTaskSource create(
			final TaskTemplate taskTemplate,
			final PresentenceInvestigationTaskAssociationUsageCategory usage,
			final PresentenceInvestigationTaskAssociationCategory category)
					throws DuplicateEntityFoundException{
		if(this.presentenceInvestigationTaskSourceDao.find(
				taskTemplate, usage, category) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		PresentenceInvestigationTaskSource presentenceInvestigationTaskSource = 
				this.presentenceInvestigationTaskSourceInstanceFactory
					.createInstance();
		
		presentenceInvestigationTaskSource.setTaskTemplate(taskTemplate);
		presentenceInvestigationTaskSource.setUsage(usage);
		presentenceInvestigationTaskSource.setCategory(category);
		presentenceInvestigationTaskSource.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		presentenceInvestigationTaskSource.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.presentenceInvestigationTaskSourceDao.makePersistent(
				presentenceInvestigationTaskSource);
	}
	
	/**
	 * Updates a PresentenceInvestigationTaskSource with the specified
	 * properties
	 * @param presentenceInvestigationTaskSource -
	 * PresentenceInvestigationTaskSource to update
	 * @param taskTemplate - TaskTemplate
	 * @param usage - PresentenceInvestigationTaskAssociationUsageCategory
	 * @param category - PresentenceInvestigationTaskAssociationCategory
	 * @return Updated PresentenceInvestigationTaskSource
	 * @throws DuplicateEntityFoundException - When a
	 * PresentenceInvestigationTaskSource already exists with given
	 * TaskTemplate, PresentenceInvestigationTaskAssociationUsageCategory,
	 * and PresentenceInvestigationTaskAssociationCategory
	 */
	public PresentenceInvestigationTaskSource update(
			final PresentenceInvestigationTaskSource
				presentenceInvestigationTaskSource,
			final TaskTemplate taskTemplate,
			final PresentenceInvestigationTaskAssociationUsageCategory usage,
			final PresentenceInvestigationTaskAssociationCategory category)
					throws DuplicateEntityFoundException{
		if(this.presentenceInvestigationTaskSourceDao.findExcluding(
				taskTemplate, usage, category,
				presentenceInvestigationTaskSource) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		presentenceInvestigationTaskSource.setTaskTemplate(taskTemplate);
		presentenceInvestigationTaskSource.setUsage(usage);
		presentenceInvestigationTaskSource.setCategory(category);
		presentenceInvestigationTaskSource.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.presentenceInvestigationTaskSourceDao.makePersistent(
				presentenceInvestigationTaskSource);
	}
	
	/**
	 * Removes specified PresentenceInvestigationTaskSource
	 * @param presentenceInvestigationTaskSource -
	 * PresentenceInvestigationTaskSource to remove
	 */
	public void remove(final PresentenceInvestigationTaskSource
			presentenceInvestigationTaskSource){
		this.presentenceInvestigationTaskSourceDao.makeTransient(
				presentenceInvestigationTaskSource);
	}
	
	/**
	 * Returns a list of PresentenceInvestigationTaskSources found by specified
	 * TaskTemplateGroup
	 * @param group - TaskTemplateGroup
	 * @return List of PresentenceInvestigationTaskSources found by specified
	 * TaskTemplateGroup
	 */
	public List<PresentenceInvestigationTaskSource> findByTaskTemplateGroup(
			TaskTemplateGroup group){
		return this.presentenceInvestigationTaskSourceDao
				.findByTaskTemplateGroup(group);
	}
}
