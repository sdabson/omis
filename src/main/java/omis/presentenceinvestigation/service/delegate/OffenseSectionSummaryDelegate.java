package omis.presentenceinvestigation.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.OffenseSectionSummaryDao;
import omis.presentenceinvestigation.domain.OffenseSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * OffenseSectionSummaryDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 31, 2017)
 *@since OMIS 3.0
 *
 */
public class OffenseSectionSummaryDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Offense Section Summary already exists with given "
			+ "Presentence Investigation Request.";
	
	private final OffenseSectionSummaryDao offenseSectionSummaryDao;
	
	private final InstanceFactory<OffenseSectionSummary> 
		offenseSectionSummaryInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for OffenseSectionSummaryDelegate
	 * @param offenseSectionSummaryDao
	 * @param offenseSectionSummaryInstanceFactory
	 * @param auditComponentRetriever
	 */
	public OffenseSectionSummaryDelegate(
			final OffenseSectionSummaryDao offenseSectionSummaryDao,
			final InstanceFactory<OffenseSectionSummary> 
				offenseSectionSummaryInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.offenseSectionSummaryDao = offenseSectionSummaryDao;
		this.offenseSectionSummaryInstanceFactory =
				offenseSectionSummaryInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates an OffenseSectionSummary with specified parameters
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param text - String
	 * @return Newly Created OffenseSectionSummary
	 * @throws DuplicateEntityFoundException - When a OffenseSectionSummary
	 * already exists with given PresentenceInvestigationRequest
	 */
	public OffenseSectionSummary create(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final String text)
					throws DuplicateEntityFoundException{
		if(this.offenseSectionSummaryDao.find(presentenceInvestigationRequest)
				!= null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		OffenseSectionSummary offenseSectionSummary = 
				this.offenseSectionSummaryInstanceFactory.createInstance();
		
		offenseSectionSummary.setText(text);
		offenseSectionSummary.setPresentenceInvestigationRequest(
				presentenceInvestigationRequest);
		offenseSectionSummary.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		offenseSectionSummary.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.offenseSectionSummaryDao.makePersistent(offenseSectionSummary);
	}
	
	/**
	 * Updates an OffenseSectionSummary with specified properties
	 * @param offenseSectionSummary - OffenseSectionSummary to update
	 * @param text - String
	 * @return Updated OffenseSectionSummary
	 * @throws DuplicateEntityFoundException - When a OffenseSectionSummary
	 * already exists with given PresentenceInvestigationRequest
	 */
	public OffenseSectionSummary update(
			final OffenseSectionSummary offenseSectionSummary,
			final String text)
					throws DuplicateEntityFoundException{
		if(this.offenseSectionSummaryDao.findExcluding(
				offenseSectionSummary.getPresentenceInvestigationRequest(),
				offenseSectionSummary) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		offenseSectionSummary.setText(text);
		offenseSectionSummary.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.offenseSectionSummaryDao.makePersistent(offenseSectionSummary);
	}
	
	/**
	 * Removes specified OffenseSectionSummary
	 * @param offenseSectionSummary - OffenseSectionSummary to remove
	 */
	public void remove(final OffenseSectionSummary offenseSectionSummary){
		this.offenseSectionSummaryDao.makeTransient(offenseSectionSummary);
	}
	
	/**
	 * Returns the OffenseSectionSummary for specified
	 * PresentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return OffenseSectionSummary for specified
	 * PresentenceInvestigationRequest
	 */
	public OffenseSectionSummary findByPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest presentenceInvestigationRequest){
		return this.offenseSectionSummaryDao.find(presentenceInvestigationRequest);
	}
	
	
}
