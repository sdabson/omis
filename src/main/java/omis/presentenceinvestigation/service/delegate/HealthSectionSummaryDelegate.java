package omis.presentenceinvestigation.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.HealthSectionSummaryDao;
import omis.presentenceinvestigation.domain.HealthRating;
import omis.presentenceinvestigation.domain.HealthSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * Health section summary delegate.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 9, 2017)
 * @since OMIS 3.0
 */
public class HealthSectionSummaryDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG
		= "Health section summary already exists for specified "
			+ "Presentence Investigation";
	
	private final HealthSectionSummaryDao healthSectionSummaryDao;	
	
	private final InstanceFactory<HealthSectionSummary> 
		healthSectionSummaryInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	
	/** Instantiates an implementation of HealthSectionSummaryDelegate */
	public HealthSectionSummaryDelegate(
			final HealthSectionSummaryDao healthSectionSummaryDao,
			final InstanceFactory<HealthSectionSummary> 
				healthSectionSummaryInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		
		this.healthSectionSummaryDao = healthSectionSummaryDao;
		this.healthSectionSummaryInstanceFactory 
			= healthSectionSummaryInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/**
	 * Create a new health section summary.
	 *
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @return new health section summary
	 */
	public HealthSectionSummary createHealthSectionSummary(
			final PresentenceInvestigationRequest 
				presentenceInvestigationRequest, final HealthRating rating) 
					throws DuplicateEntityFoundException {
		if (this.healthSectionSummaryDao.find(
				presentenceInvestigationRequest, rating) != null) {
			throw new DuplicateEntityFoundException(
					DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		HealthSectionSummary summary 
			= this.healthSectionSummaryInstanceFactory.createInstance();
		summary.setPresentenceInvestigationRequest(
				presentenceInvestigationRequest);
		summary.setRating(rating);
		summary.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		summary.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.healthSectionSummaryDao.makePersistent(summary);
	}

	/**
	 * Updates an existing health section summary.
	 *
	 * @param healthSectionSummary health section summary
	 * @param rating gatomg
	 * @return new health section summary
	 * @throws DuplicateEntityFoundException 
	 */
	public HealthSectionSummary updateHealthSectionSummary(
			final HealthSectionSummary healthSectionSummary,
			final HealthRating rating) throws DuplicateEntityFoundException {
		if (this.healthSectionSummaryDao.findExcluding(
				healthSectionSummary, rating) != null) {
			throw new DuplicateEntityFoundException(
					DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		healthSectionSummary.setRating(rating);		
		healthSectionSummary.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.healthSectionSummaryDao.makePersistent(healthSectionSummary);
	}
	
	/**
	 * Find health section summary by presentence investigation request.
	 *
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @return health section summary
	 */
	public HealthSectionSummary 
		findHealthSectionSummaryByPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest 
				presentenceInvestigationRequest) {
		return this.healthSectionSummaryDao
				.findHealthSectionSummaryByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
	}	
}