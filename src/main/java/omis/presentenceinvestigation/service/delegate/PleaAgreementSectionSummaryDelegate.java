package omis.presentenceinvestigation.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.PleaAgreementSectionSummaryDao;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * PleaAgreementSectionSummaryDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 13, 2017)
 *@since OMIS 3.0
 *
 */
public class PleaAgreementSectionSummaryDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"PleaAgreementSectionSummary already exists with provided "
			+ "PresentenceInvestigationRequest";
	
	private final PleaAgreementSectionSummaryDao pleaAgreementSectionSummaryDao;
	
	private final InstanceFactory<PleaAgreementSectionSummary> 
		pleaAgreementSectionSummaryInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for PleaAgreementSectionSummaryDelegate
	 * @param pleaAgreementSectionSummaryDao
	 * @param pleaAgreementSectionSummaryInstanceFactory
	 * @param auditComponentRetriever
	 */
	public PleaAgreementSectionSummaryDelegate(
			final PleaAgreementSectionSummaryDao pleaAgreementSectionSummaryDao,
			final InstanceFactory<PleaAgreementSectionSummary> 
				pleaAgreementSectionSummaryInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.pleaAgreementSectionSummaryDao = pleaAgreementSectionSummaryDao;
		this.pleaAgreementSectionSummaryInstanceFactory =
				pleaAgreementSectionSummaryInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a PleaAgreementSectionSummary with the specified properties
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param summary - String
	 * @return Newly created PleaAgreementSectionSummary
	 * @throws DuplicateEntityFoundException - when a PleaAgreementSectionSummary
	 * already exists with given PresentenceInvestigationRequest
	 */
	public PleaAgreementSectionSummary create(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final String summary)
					throws DuplicateEntityFoundException{
		if(this.pleaAgreementSectionSummaryDao
				.find(presentenceInvestigationRequest) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		PleaAgreementSectionSummary pleaAgreementSectionSummary = 
				this.pleaAgreementSectionSummaryInstanceFactory.createInstance();
		
		pleaAgreementSectionSummary.setPresentenceInvestigationRequest(
				presentenceInvestigationRequest);
		pleaAgreementSectionSummary.setSummary(summary);
		pleaAgreementSectionSummary.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		pleaAgreementSectionSummary.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.pleaAgreementSectionSummaryDao.makePersistent(pleaAgreementSectionSummary);
	}
	
	/**
	 * Updates an existing PleaAgreementSectionSummary with the specified
	 * properties
	 * @param pleaAgreementSectionSummary - PleaAgreementSectionSummary to update
	 * @param summary - String
	 * @return Updated PleaAgreementSectionSummary
	 * @throws DuplicateEntityFoundException - When another
	 * PleaAgreementSectionSummary
	 * already exists with specified PleaAgreementSectionSummary
	 */
	public PleaAgreementSectionSummary update(
			final PleaAgreementSectionSummary pleaAgreementSectionSummary,
			final String summary)
					throws DuplicateEntityFoundException{
		if(this.pleaAgreementSectionSummaryDao.findExcluding(
				pleaAgreementSectionSummary.getPresentenceInvestigationRequest(),
				pleaAgreementSectionSummary) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		pleaAgreementSectionSummary.setSummary(summary);
		pleaAgreementSectionSummary.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.pleaAgreementSectionSummaryDao.makePersistent(
				pleaAgreementSectionSummary);
	}
	
	/**
	 * Removes a PleaAgreementSectionSummary
	 * @param pleaAgreementSectionSummary - PleaAgreementSectionSummary to remove
	 */
	public void remove(
			final PleaAgreementSectionSummary pleaAgreementSectionSummary){
		this.pleaAgreementSectionSummaryDao.makeTransient(
				pleaAgreementSectionSummary);
	}
	
	/**
	 * Finds and returns a PleaAgreementSectionSummary found with specified
	 * PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return Found PleaAgreementSectionSummary found with specified
	 * PresentenceInvestigationRequest
	 */
	public PleaAgreementSectionSummary findByPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest presentenceInvestigationRequest){
		return this.pleaAgreementSectionSummaryDao.find(
				presentenceInvestigationRequest);
	}
	
	
}
