package omis.presentenceinvestigation.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.OtherPertinentInformationSectionSummaryDao;
import omis.presentenceinvestigation.domain.OtherPertinentInformationSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * OtherPertinentInformationSectionSummaryDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 19, 2017)
 *@since OMIS 3.0
 *
 */
public class OtherPertinentInformationSectionSummaryDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"OtherPertinentInformationSectionSummary already exists for the "
			+ "specified PresentenceInvestigiationRequest.";
	
	private final OtherPertinentInformationSectionSummaryDao
		otherPertinentInformationSectionSummaryDao;
	
	private final InstanceFactory<OtherPertinentInformationSectionSummary> 
		otherPertinentInformationSectionSummaryInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for OtherPertinentInformationSectionSummaryDelegate
	 * @param otherPertinentInformationSectionSummaryDao
	 * @param otherPertinentInformationSectionSummaryInstanceFactory
	 * @param auditComponentRetriever
	 */
	public OtherPertinentInformationSectionSummaryDelegate(
			final OtherPertinentInformationSectionSummaryDao
				otherPertinentInformationSectionSummaryDao,
			final InstanceFactory<OtherPertinentInformationSectionSummary> 
				otherPertinentInformationSectionSummaryInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.otherPertinentInformationSectionSummaryDao =
				otherPertinentInformationSectionSummaryDao;
		this.otherPertinentInformationSectionSummaryInstanceFactory =
				otherPertinentInformationSectionSummaryInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates an OtherPertinentInformationSectionSummary with the specified
	 * properties
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param description - String
	 * @return Newly created OtherPertinentInformationSectionSummary
	 * @throws DuplicateEntityFoundException - When an
	 * OtherPertinentInformationSectionSummary already exists for the
	 * specified PresentenceInvestigiationRequest
	 */
	public OtherPertinentInformationSectionSummary create(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final String description)
					throws DuplicateEntityFoundException{
		if(this.otherPertinentInformationSectionSummaryDao
				.find(presentenceInvestigationRequest) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		OtherPertinentInformationSectionSummary
			otherPertinentInformationSectionSummary = 
				this.otherPertinentInformationSectionSummaryInstanceFactory
				.createInstance();
		
		otherPertinentInformationSectionSummary
			.setPresentenceInvestigationRequest(presentenceInvestigationRequest);
		otherPertinentInformationSectionSummary.setDescription(description);
		otherPertinentInformationSectionSummary.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		otherPertinentInformationSectionSummary.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.otherPertinentInformationSectionSummaryDao.makePersistent(
				otherPertinentInformationSectionSummary);
	}
	
	/**
	 * Updates an OtherPertinentInformationSectionSummary with the specified
	 * properties
	 * @param otherPertinentInformationSectionSummary -
	 * OtherPertinentInformationSectionSummary to update
	 * @param description - String
	 * @return Updated OtherPertinentInformationSectionSummary
	 * @throws DuplicateEntityFoundException - When another
	 * OtherPertinentInformationSectionSummary already exists for the
	 * OtherPertinentInformationSectionSummary's PresentenceInvestigiationRequest
	 */
	public OtherPertinentInformationSectionSummary update(
			final OtherPertinentInformationSectionSummary
				otherPertinentInformationSectionSummary,
			final String description)
					throws DuplicateEntityFoundException{
		if(this.otherPertinentInformationSectionSummaryDao
				.findExcluding(otherPertinentInformationSectionSummary
						.getPresentenceInvestigationRequest(),
				otherPertinentInformationSectionSummary) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		otherPertinentInformationSectionSummary.setDescription(description);
		otherPertinentInformationSectionSummary.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.otherPertinentInformationSectionSummaryDao.makePersistent(
				otherPertinentInformationSectionSummary);
	}
	
	/**
	 * Removes an OtherPertinentInformationSectionSummary
	 * @param otherPertinentInformationSectionSummary -
	 * OtherPertinentInformationSectionSummary to remove
	 */
	public void remove(final OtherPertinentInformationSectionSummary
			otherPertinentInformationSectionSummary){
		this.otherPertinentInformationSectionSummaryDao.makeTransient(
				otherPertinentInformationSectionSummary);
	}
	
	/**
	 * Finds an OtherPertinentInformationSectionSummary with the specified
	 * PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return OtherPertinentInformationSectionSummary with the specified
	 * PresentenceInvestigationRequest
	 */
	public OtherPertinentInformationSectionSummary
		findByPresentenceInvestigationRequest(
				final PresentenceInvestigationRequest
					presentenceInvestigationRequest){
		return this.otherPertinentInformationSectionSummaryDao.find(
				presentenceInvestigationRequest);
	}
}
