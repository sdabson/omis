package omis.presentenceinvestigation.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.VictimSectionSummaryDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.VictimSectionSummary;

/**
 * VictimSectionSummaryDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 20, 2017)
 *@since OMIS 3.0
 *
 */
public class VictimSectionSummaryDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"VictimSectionSummary already exists with the specified "
			+ "PresentenceInvestigationRequest.";
	
	private final VictimSectionSummaryDao victimSectionSummaryDao;
	
	private final InstanceFactory<VictimSectionSummary> 
		victimSectionSummaryInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for VictimSectionSummaryDelegate
	 * @param victimSectionSummaryDao
	 * @param victimSectionSummaryInstanceFactory
	 * @param auditComponentRetriever
	 */
	public VictimSectionSummaryDelegate(
			final VictimSectionSummaryDao victimSectionSummaryDao,
			final InstanceFactory<VictimSectionSummary> 
				victimSectionSummaryInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.victimSectionSummaryDao = victimSectionSummaryDao;
		this.victimSectionSummaryInstanceFactory =
				victimSectionSummaryInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a VictimSectionSummary with the specified properties
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param text - String
	 * @return Newly created VictimSectionSummary
	 * @throws DuplicateEntityFoundException - When a VictimSectionSummary
	 * already exists with the specified PresentenceInvestigationRequest
	 */
	public VictimSectionSummary create(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final String text)
					throws DuplicateEntityFoundException{
		if(this.victimSectionSummaryDao.find(
				presentenceInvestigationRequest) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		VictimSectionSummary victimSectionSummary = 
				this.victimSectionSummaryInstanceFactory.createInstance();
		
		victimSectionSummary.setPresentenceInvestigationRequest(
				presentenceInvestigationRequest);
		victimSectionSummary.setText(text);
		victimSectionSummary.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		victimSectionSummary.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.victimSectionSummaryDao.makePersistent(victimSectionSummary);
	}
	
	/**
	 * Updates a VictimSectionSummary with the specified properties
	 * @param victimSectionSummary - VictimSectionSummary to update
	 * @param text - String
	 * @return Updated VictimSectionSummary
	 * @throws DuplicateEntityFoundException - When another VictimSectionSummary
	 * already exists with given VictimSectionSummary's
	 * PresentenceInvestigationRequest
	 */
	public VictimSectionSummary update(
			final VictimSectionSummary victimSectionSummary,
			final String text)
					throws DuplicateEntityFoundException{
		if(this.victimSectionSummaryDao.findExcluding(
				victimSectionSummary.getPresentenceInvestigationRequest(),
				victimSectionSummary) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}

		victimSectionSummary.setText(text);
		victimSectionSummary.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.victimSectionSummaryDao.makePersistent(victimSectionSummary);
	}
	
	/**
	 * Removes a VictimSectionSummary
	 * @param victimSectionSummary - VictimSectionSummary to remove
	 */
	public void remove(final VictimSectionSummary victimSectionSummary){
		this.victimSectionSummaryDao.makeTransient(victimSectionSummary);
	}
	
	/**
	 * Returns a VictimSectionSummary with the specified
	 * PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return VictimSectionSummary with the specified
	 * PresentenceInvestigationRequest
	 */
	public VictimSectionSummary findByPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest presentenceInvestigationRequest){
		return this.victimSectionSummaryDao.find(presentenceInvestigationRequest);
	}
	
	
}
