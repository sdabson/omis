package omis.presentenceinvestigation.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.FinancialSectionSummaryDao;
import omis.presentenceinvestigation.domain.FinancialSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * FinancialSectionSummaryDelegate
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (July 18, 2017)
 *@since OMIS 3.0
 *
 */
public class FinancialSectionSummaryDelegate {

	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"A Financial Section Summary already exists with specified "
			+ "Presentence Investigation Request";
	
	private final FinancialSectionSummaryDao financialSectionSummaryDao;
	
	private final InstanceFactory<FinancialSectionSummary> 
		financialSectionSummaryInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Constructor for FinancialSectionSummaryDelegate
	 * @param financialSectionSummaryDao
	 * @param financialSectionSummaryInstanceFactory
	 * @param auditComponentRetriever
	 */
	public FinancialSectionSummaryDelegate(
			final FinancialSectionSummaryDao financialSectionSummaryDao,
			final InstanceFactory<FinancialSectionSummary> 
				financialSectionSummaryInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.financialSectionSummaryDao = financialSectionSummaryDao;
		this.financialSectionSummaryInstanceFactory =
				financialSectionSummaryInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a FinancialSectionSummary with the specified properties
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param text - String
	 * @return Newly created FinancialSectionSummary
	 * @throws DuplicateEntityFoundException - when a FinancialSectionSummary
	 * already exists with the specified PresentenceInvestigationRequest
	 */
	public FinancialSectionSummary create(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final String text)
					throws DuplicateEntityFoundException {
		if(this.financialSectionSummaryDao.find(presentenceInvestigationRequest) 
				!= null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		FinancialSectionSummary financialSectionSummary 
			= this.financialSectionSummaryInstanceFactory.createInstance();
		
		financialSectionSummary.setPresentenceInvestigationRequest(
				presentenceInvestigationRequest);
		financialSectionSummary.setText(text);
		financialSectionSummary.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		financialSectionSummary.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		
		
		return this.financialSectionSummaryDao.makePersistent(
				financialSectionSummary);
		
	}
	
	/**
	 * Updates a FinancialSectionSummary with the specified properties
	 * @param financialSectionSummary - FinancialSectionSummary to update
	 * @param text - String
	 * @return Updated FinancialSectionSummary
	 * @throws DuplicateEntityFoundException - When a FinancialSectionSummary
	 * already exists with its PresentenceInvestigationRequest (should never
	 * happen on update) 
	 */
	public FinancialSectionSummary update(
			final FinancialSectionSummary financialSectionSummary,
			final String text)
					throws DuplicateEntityFoundException {
		if(this.financialSectionSummaryDao.findExcluding(
				financialSectionSummary.getPresentenceInvestigationRequest(), 
				financialSectionSummary) !=null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		financialSectionSummary.setText(text);
		financialSectionSummary.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		
		return this.financialSectionSummaryDao.makePersistent(
				financialSectionSummary);
		
	}
	
	/**
	 * Removes a FinancialSectionSummary
	 * @param financialSectionSummary - FinancialSectionSummary to remove
	 */
	public void remove(final FinancialSectionSummary financialSectionSummary) {
		this.financialSectionSummaryDao.makeTransient(financialSectionSummary);
	}
	
	/**
	 * Finds and returns a FinancialSectionSummary by specified 
	 * PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return FinancialSectionSummary found by specified 
	 * PresentenceInvestigationRequest
	 */
	public FinancialSectionSummary findByPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest 
				presentenceInvestigationRequest) {
		return this.financialSectionSummaryDao.find(
				presentenceInvestigationRequest);
	}
	
}
