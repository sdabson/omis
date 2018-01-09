package omis.presentenceinvestigation.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.SupervisionHistorySectionSummaryDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.SupervisionHistorySectionSummary;

/**
 * Supervision history summary delegate.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 9, 2017)
 * @since OMIS 3.0
 */
public class SupervisionHistorySummaryDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG
	= "Supervision history section summary already exists for specified "
		+ "Presentence Investigation";
	
	private final SupervisionHistorySectionSummaryDao 
		supervisionHistorySectionSummaryDao;
	
	private final InstanceFactory<SupervisionHistorySectionSummary> 
		supervisionHistorySectionSummaryInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/** Instantiates an implementation of SupervisionHistorySummaryDelegate */
	public SupervisionHistorySummaryDelegate(
			final SupervisionHistorySectionSummaryDao 
			supervisionHistorySectionSummaryDao,
			final InstanceFactory<SupervisionHistorySectionSummary> 
			supervisionHistorySectionSummaryInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		
		this.supervisionHistorySectionSummaryDao 
			= supervisionHistorySectionSummaryDao;
		this.supervisionHistorySectionSummaryInstanceFactory 
			= supervisionHistorySectionSummaryInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/**
	 * Create a supervision history section summary on this PSI.
	 *
	 *
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @return new supervision history section summary
	 * @throws DuplicateEntityFoundException
	 */
	public SupervisionHistorySectionSummary 
		createSupervisionHistorySectionSummary(
			final String text,
			final PresentenceInvestigationRequest 
				presentenceInvestigationRequest) 
					throws DuplicateEntityFoundException {
		if (this.supervisionHistorySectionSummaryDao
				.findSupervisionHistorySectionSummaryByPSI(
				presentenceInvestigationRequest) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}

		SupervisionHistorySectionSummary summary 
		= this.supervisionHistorySectionSummaryInstanceFactory.createInstance();
		summary.setPresentenceInvestigationRequest(
				presentenceInvestigationRequest);
		summary.setText(text);
		summary.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		summary.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.supervisionHistorySectionSummaryDao.makePersistent(summary);
	}
	
	/**
	 * Updates this supervision history section summary.
	 *
	 *
	 * @param priorSupervisionSectionSummary prior supervision section summary
	 * @param text text
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @return update supervision history section summary
	 * @throws DuplicateEntityFoundException
	 */
	public SupervisionHistorySectionSummary updateSupervisionHistorySection(
			final SupervisionHistorySectionSummary priorSupervisionSectionSummary, 
			final String text, final PresentenceInvestigationRequest 
			presentenceInvestigationRequest) 
					throws DuplicateEntityFoundException {
		if (this.supervisionHistorySectionSummaryDao.findExcluding(
				priorSupervisionSectionSummary, 
				presentenceInvestigationRequest) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		priorSupervisionSectionSummary.setPresentenceInvestigationRequest(
				presentenceInvestigationRequest);
		priorSupervisionSectionSummary.setText(text);
		priorSupervisionSectionSummary.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.supervisionHistorySectionSummaryDao.makePersistent(
				priorSupervisionSectionSummary);
	}
	
	/**
	 * Removes current supervision history section summary.
	 *
	 *
	 * @param supervisionHistorySectionSummary supervision history 
	 * section summary
	 */
	public void removeSupervisionHistorySectionSummary(
			SupervisionHistorySectionSummary supervisionHistorySectionSummary) {
		this.supervisionHistorySectionSummaryDao
				.makeTransient(supervisionHistorySectionSummary);
	}
		
	/**
	 * Finds a specific supervision history section summary by PSI.
	 *
	 *
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @return specific supervision history section summary
	 */
	public SupervisionHistorySectionSummary 
	findSupervisionHistorySectionSummaryByPresentenceInvestigationRequest(
			PresentenceInvestigationRequest presentenceInvestigationRequest) {
		return this.supervisionHistorySectionSummaryDao
				.findSupervisionHistorySectionSummaryByPSI(
				presentenceInvestigationRequest);
	}	
}