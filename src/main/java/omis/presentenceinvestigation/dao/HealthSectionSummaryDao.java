package omis.presentenceinvestigation.dao;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.HealthRating;
import omis.presentenceinvestigation.domain.HealthSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * Health section summary data access object.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 4, 2017)
 * @since OMIS 3.0
 */
public interface HealthSectionSummaryDao 
	extends GenericDao<HealthSectionSummary> {
	
	/**
	 * Find a health section summary by presentence investigation request.
	 *
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @return health section summary
	 */
	HealthSectionSummary find(
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			HealthRating rating);

	/**
	 * Find a health section summary by presentence investigation request 
	 * excluding the one in view.
	 *
	 *
	 * @param healthSectionSummary health section summary
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @return health section summary
	 */
	HealthSectionSummary findExcluding(
			HealthSectionSummary healthSectionSummary, 
			HealthRating rating);
	
	/**
	 * Find a health section summary by presentence investigation request.
	 *
	 *
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @return health section summary
	 */
	HealthSectionSummary 
		findHealthSectionSummaryByPresentenceInvestigationRequest(
				PresentenceInvestigationRequest 
				presentenceInvestigationRequest);
}