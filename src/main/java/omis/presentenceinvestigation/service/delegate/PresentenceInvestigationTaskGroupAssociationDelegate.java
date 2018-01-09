
package omis.presentenceinvestigation.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.PresentenceInvestigationTaskGroupAssociationDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskGroup;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskGroupAssociation;

/**
 * PresentenceInvestigationTaskGroupAssociationDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 11, 2017)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationTaskGroupAssociationDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Presentence Investigation Task Group Association already exists " +
			"with given PresentenceInvestigationTaskGroup and " +
			"PresentenceInvestigationRequest";
	
	private final PresentenceInvestigationTaskGroupAssociationDao
		presentenceInvestigationTaskGroupAssociationDao;
	
	private final InstanceFactory<PresentenceInvestigationTaskGroupAssociation> 
		presentenceInvestigationTaskGroupAssociationInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for PresentenceInvestigationTaskGroupAssociationDelegate
	 * @param presentenceInvestigationTaskGroupAssociationDao
	 * @param presentenceInvestigationTaskGroupAssociationInstanceFactory
	 * @param auditComponentRetriever
	 */
	public PresentenceInvestigationTaskGroupAssociationDelegate(
			final PresentenceInvestigationTaskGroupAssociationDao
			presentenceInvestigationTaskGroupAssociationDao,
			final InstanceFactory<PresentenceInvestigationTaskGroupAssociation> 
				presentenceInvestigationTaskGroupAssociationInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.presentenceInvestigationTaskGroupAssociationDao =
				presentenceInvestigationTaskGroupAssociationDao;
		this.presentenceInvestigationTaskGroupAssociationInstanceFactory =
				presentenceInvestigationTaskGroupAssociationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a PresentenceInvestigationTaskGroupAssociation with the specified
	 * properties
	 * @param group - PresentenceInvestigationTaskGroup
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return Newly created PresentenceInvestigationTaskGroupAssociation
	 * @throws DuplicateEntityFoundException - When a
	 * PresentenceInvestigationTaskGroupAssociation already exists with the
	 * specified properties
	 */
	public PresentenceInvestigationTaskGroupAssociation create(
			final PresentenceInvestigationTaskGroup group,
			final PresentenceInvestigationRequest presentenceInvestigationRequest)
					throws DuplicateEntityFoundException{
		if(this.presentenceInvestigationTaskGroupAssociationDao.find(
				group, presentenceInvestigationRequest) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		PresentenceInvestigationTaskGroupAssociation
			presentenceInvestigationTaskGroupAssociation = 
				this.presentenceInvestigationTaskGroupAssociationInstanceFactory
				.createInstance();
		
		presentenceInvestigationTaskGroupAssociation.setGroup(group);
		presentenceInvestigationTaskGroupAssociation
			.setPresentenceInvestigationRequest(presentenceInvestigationRequest);
		presentenceInvestigationTaskGroupAssociation.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		presentenceInvestigationTaskGroupAssociation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.presentenceInvestigationTaskGroupAssociationDao
				.makePersistent(presentenceInvestigationTaskGroupAssociation);
	}
	
	/**
	 * Updates a PresentenceInvestigationTaskGroupAssociation with the specified
	 * properties
	 * @param presentenceInvestigationTaskGroupAssociation -
	 * PresentenceInvestigationTaskGroupAssociation to update
	 * @param group - PresentenceInvestigationTaskGroup
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return Updated PresentenceInvestigationTaskGroupAssociated
	 * @throws DuplicateEntityFoundException - When a
	 * PresentenceInvestigationTaskGroupAssociation already exists with the
	 * specified properties
	 */
	public PresentenceInvestigationTaskGroupAssociation update(
			final PresentenceInvestigationTaskGroupAssociation
				presentenceInvestigationTaskGroupAssociation,
			final PresentenceInvestigationTaskGroup group,
			final PresentenceInvestigationRequest
				presentenceInvestigationRequest)
					throws DuplicateEntityFoundException{
		if(this.presentenceInvestigationTaskGroupAssociationDao.findExcluding(
				group, presentenceInvestigationRequest,
				presentenceInvestigationTaskGroupAssociation) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		presentenceInvestigationTaskGroupAssociation.setGroup(group);
		presentenceInvestigationTaskGroupAssociation
			.setPresentenceInvestigationRequest(presentenceInvestigationRequest);
		presentenceInvestigationTaskGroupAssociation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.presentenceInvestigationTaskGroupAssociationDao
				.makePersistent(presentenceInvestigationTaskGroupAssociation);
	}
	
	/**
	 * Removes a PresentenceInvestigationTaskGroupAssociation
	 * @param presentenceInvestigationTaskGroupAssociation -
	 * PresentenceInvestigationTaskGroupAssociation to remove
	 */
	public void remove(final PresentenceInvestigationTaskGroupAssociation
			presentenceInvestigationTaskGroupAssociation){
		this.presentenceInvestigationTaskGroupAssociationDao.makeTransient(
				presentenceInvestigationTaskGroupAssociation);
	}
	
	/**
	 * Returns a PresentenceInvestigationTaskGroupAssociation with the specified
	 * PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return PresentenceInvestigationTaskGroupAssociation with the specified
	 * PresentenceInvestigationRequest
	 */
	public PresentenceInvestigationTaskGroupAssociation
		findByPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest presentenceInvestigationRequest) {
		return this.presentenceInvestigationTaskGroupAssociationDao
				.findByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
	}
}
