package omis.presentenceinvestigation.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.EducationSectionSummaryDao;
import omis.presentenceinvestigation.domain.EducationSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * EducationSectionSummaryDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 16, 2017)
 *@since OMIS 3.0
 *
 */
public class EducationSectionSummaryDelegate {
	
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Education Section Summary already exists with specified "
			+ "Presentence Investigation Request";
	
	private final EducationSectionSummaryDao educationSectionSummaryDao;
	
	private final InstanceFactory<EducationSectionSummary> 
		educationSectionSummaryInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for EducationSectionSummaryDelegate
	 * @param educationSectionSummaryDao
	 * @param educationSectionSummaryInstanceFactory
	 * @param auditComponentRetriever
	 */
	public EducationSectionSummaryDelegate(
			final EducationSectionSummaryDao educationSectionSummaryDao,
			final InstanceFactory<EducationSectionSummary> 
				educationSectionSummaryInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.educationSectionSummaryDao = educationSectionSummaryDao;
		this.educationSectionSummaryInstanceFactory =
				educationSectionSummaryInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates an EducationSectionSummary with specified parameters
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param text - String
	 * @return Newly created EducationSectionSummary
	 * @throws DuplicateEntityFoundException - when an EducationSectionSummary
	 * already exists for specified PresentenceInvestigationRequest
	 */
	public EducationSectionSummary create(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final String text)
					throws DuplicateEntityFoundException{
		if(this.educationSectionSummaryDao.find(presentenceInvestigationRequest)
				!= null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		EducationSectionSummary educationSectionSummary = 
				this.educationSectionSummaryInstanceFactory.createInstance();
		
		educationSectionSummary.setPresentenceInvestigationRequest(
				presentenceInvestigationRequest);
		educationSectionSummary.setText(text);
		educationSectionSummary.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		educationSectionSummary.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.educationSectionSummaryDao.makePersistent(
				educationSectionSummary);
	}
	
	/**
	 * Updates an EducationSectionSummary with specified parameters
	 * @param educationSectionSummary - EducationSectionSummary to update
	 * @param text - String
	 * @return Updated EducationSectionSummary
	 * @throws DuplicateEntityFoundException - when an EducationSectionSummary
	 * already exists for specified PresentenceInvestigationRequest
	 */
	public EducationSectionSummary update(
			final EducationSectionSummary educationSectionSummary,
			final String text)
					throws DuplicateEntityFoundException{
		if(this.educationSectionSummaryDao.findExcluding(
				educationSectionSummary.getPresentenceInvestigationRequest(),
				educationSectionSummary) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		educationSectionSummary.setText(text);
		educationSectionSummary.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.educationSectionSummaryDao.makePersistent(
				educationSectionSummary);
	}
	
	/**
	 * Removes an EducationSectionSummary
	 * @param educationSectionSummary - EducationSectionSummary to remove
	 */
	public void remove(final EducationSectionSummary educationSectionSummary){
		this.educationSectionSummaryDao.makeTransient(educationSectionSummary);
	}
	
	/**
	 * Returns an EducationSectionSummary found by specified
	 * PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return EducationSectionSummary
	 */
	public EducationSectionSummary findByPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest presentenceInvestigationRequest){
		return this.educationSectionSummaryDao.find(
				presentenceInvestigationRequest);
	}
	
	
	
}
