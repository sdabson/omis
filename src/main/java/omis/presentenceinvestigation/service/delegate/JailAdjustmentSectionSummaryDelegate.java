package omis.presentenceinvestigation.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.JailAdjustmentSectionSummaryDao;
import omis.presentenceinvestigation.domain.JailAdjustmentSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * JailAdjustmentSectionSummaryDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Feb 24, 2017)
 *@since OMIS 3.0
 *
 */
public class JailAdjustmentSectionSummaryDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Jail Adjustment Section Summary Already Exists For Given "
			+ "Presentence Investigation Request";
	
	private final JailAdjustmentSectionSummaryDao jailAdjustmentSectionSummaryDao;
	
	private final InstanceFactory<JailAdjustmentSectionSummary> 
		jailAdjustmentSectionSummaryInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for JailAdjustmentSectionSummaryDelegate
	 * @param jailAdjustmentSectionSummaryDao
	 * @param jailAdjustmentSectionSummaryInstanceFactory
	 * @param auditComponentRetriever
	 */
	public JailAdjustmentSectionSummaryDelegate(
			final JailAdjustmentSectionSummaryDao jailAdjustmentSectionSummaryDao,
			final InstanceFactory<JailAdjustmentSectionSummary> 
				jailAdjustmentSectionSummaryInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.jailAdjustmentSectionSummaryDao = jailAdjustmentSectionSummaryDao;
		this.jailAdjustmentSectionSummaryInstanceFactory =
				jailAdjustmentSectionSummaryInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a JailAdjustmentSectionSummary with the specified properties
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return Newly Created JailAdjustmentSectionSummary
	 * @throws DuplicateEntityFoundException - when a JailAdjustmentSectionSummary
	 * already exists with specified PresentenceInvestigationRequest
	 */
	public JailAdjustmentSectionSummary create(
			final PresentenceInvestigationRequest presentenceInvestigationRequest)
					throws DuplicateEntityFoundException{
		if(this.jailAdjustmentSectionSummaryDao
				.find(presentenceInvestigationRequest) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		JailAdjustmentSectionSummary jailAdjustmentSectionSummary = 
				this.jailAdjustmentSectionSummaryInstanceFactory.createInstance();
		
		jailAdjustmentSectionSummary.setPresentenceInvestigationRequest(
				presentenceInvestigationRequest);
		jailAdjustmentSectionSummary.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		jailAdjustmentSectionSummary.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.jailAdjustmentSectionSummaryDao.makePersistent(
				jailAdjustmentSectionSummary);
	}
	
	/**
	 * Updates a JailAdjustmentSectionSummary with the specified properties
	 * @param jailAdjustmentSectionSummary - JailAdjustmentSectionSummary to update
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return Updated JailAdjustmentSectionSummary
	 * @throws DuplicateEntityFoundException - when a JailAdjustmentSectionSummary
	 * already exists with specified PresentenceInvestigationRequest
	 */
	public JailAdjustmentSectionSummary update(
			final JailAdjustmentSectionSummary jailAdjustmentSectionSummary,
			final PresentenceInvestigationRequest presentenceInvestigationRequest)
			throws DuplicateEntityFoundException{
		if(this.jailAdjustmentSectionSummaryDao.findExcluding(
				presentenceInvestigationRequest, jailAdjustmentSectionSummary)
				!= null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		jailAdjustmentSectionSummary.setPresentenceInvestigationRequest(
				presentenceInvestigationRequest);
		jailAdjustmentSectionSummary.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.jailAdjustmentSectionSummaryDao.makePersistent(
				jailAdjustmentSectionSummary);
	}
	
	/**
	 * Removes a JailAdjustmentSectionSummary
	 * @param jailAdjustmentSectionSummary - JailAdjustmentSectionSummary to
	 * remove
	 */
	public void remove(final JailAdjustmentSectionSummary 
			jailAdjustmentSectionSummary){
		this.jailAdjustmentSectionSummaryDao.makeTransient(
				jailAdjustmentSectionSummary);
	}
	
	/**
	 * Finds and returns a JailAdjustmentSectionSummary by specified
	 * PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return JailAdjustmentSectionSummary
	 */
	public JailAdjustmentSectionSummary findByPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest presentenceInvestigationRequest){
		return this.jailAdjustmentSectionSummaryDao.find(
				presentenceInvestigationRequest);
	}
	
}
