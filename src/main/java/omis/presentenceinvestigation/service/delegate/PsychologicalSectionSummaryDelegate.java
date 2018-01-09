package omis.presentenceinvestigation.service.delegate;


import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.PsychologicalSectionSummaryDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummary;

/**
 * PsychologicalSectionSummaryDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 2, 2017)
 *@since OMIS 3.0
 *
 */
public class PsychologicalSectionSummaryDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Psychological Section Summary already exists for specified "
			+ "Presentence Investigation Request";
	
	private final PsychologicalSectionSummaryDao psychologicalSectionSummaryDao;
	
	private final InstanceFactory<PsychologicalSectionSummary> 
		psychologicalSectionSummaryInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for PsychologicalSectionSummaryDelegate
	 * @param psychologicalSectionSummaryDao
	 * @param psychologicalSectionSummaryInstanceFactory
	 * @param auditComponentRetriever
	 */
	public PsychologicalSectionSummaryDelegate(
			final PsychologicalSectionSummaryDao psychologicalSectionSummaryDao,
			final InstanceFactory<PsychologicalSectionSummary> 
				psychologicalSectionSummaryInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.psychologicalSectionSummaryDao = psychologicalSectionSummaryDao;
		this.psychologicalSectionSummaryInstanceFactory =
				psychologicalSectionSummaryInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a PsychologicalSectionSummary with the specified parameters
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return Newly created PsychologicalSectionSummary
	 * @throws DuplicateEntityFoundException - when a PsychologicalSectionSummary
	 * already exists for the specified PresentenceInvestigationRequest
	 */
	public PsychologicalSectionSummary create(
			final PresentenceInvestigationRequest presentenceInvestigationRequest)
			throws DuplicateEntityFoundException{
		if(this.psychologicalSectionSummaryDao
				.find(presentenceInvestigationRequest) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		PsychologicalSectionSummary psychologicalSectionSummary = 
				this.psychologicalSectionSummaryInstanceFactory.createInstance();
		
		psychologicalSectionSummary.setPresentenceInvestigationRequest(
				presentenceInvestigationRequest);
		psychologicalSectionSummary.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		psychologicalSectionSummary.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.psychologicalSectionSummaryDao
				.makePersistent(psychologicalSectionSummary);
	}
	
	/**
	 * Updates a specified PsychologicalSectionSummary with the given parameters
	 * @param psychologicalSectionSummary - PsychologicalSectionSummary to update
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return Updated PsychologicalSectionSummary
	 * @throws DuplicateEntityFoundException - when another 
	 * PsychologicalSectionSummary already exists for the specified 
	 * PresentenceInvestigationRequest
	 */
	public PsychologicalSectionSummary update(
			final PsychologicalSectionSummary psychologicalSectionSummary,
			final PresentenceInvestigationRequest presentenceInvestigationRequest)
			throws DuplicateEntityFoundException{
		if(this.psychologicalSectionSummaryDao
				.findExcluding(presentenceInvestigationRequest,
						psychologicalSectionSummary) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		psychologicalSectionSummary.setPresentenceInvestigationRequest(
				presentenceInvestigationRequest);
		psychologicalSectionSummary.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.psychologicalSectionSummaryDao
				.makePersistent(psychologicalSectionSummary);
	}
	
	/**
	 * Removes specified PsychologicalSectionSummary
	 * @param psychologicalSectionSummary - PsychologicalSectionSummary to remove
	 */
	public void remove(
			final PsychologicalSectionSummary psychologicalSectionSummary){
		this.psychologicalSectionSummaryDao
				.makeTransient(psychologicalSectionSummary);
	}
	
	
	/**
	 * Returns a PsychologicalSectionSummary found by specified
	 * PresentenceInvestigationRequest 
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return PsychologicalSectionSummary
	 */
	public PsychologicalSectionSummary findByPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest presentenceInvestigationRequest){
		return this.psychologicalSectionSummaryDao
				.find(presentenceInvestigationRequest);
	}
	
	
}
