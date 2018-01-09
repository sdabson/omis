package omis.presentenceinvestigation.dao;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.SupervisionHistorySectionSummary;

/**
 * Supervision history section summary data access object.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 10, 2017)
 * @since OMIS 3.0
 */
public interface SupervisionHistorySectionSummaryDao 
	extends GenericDao<SupervisionHistorySectionSummary> {

	/**
	 * Find supervision history section summary by PSI.
	 *
	 *
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @return supervision history section summary
	 */
	/*SupervisionHistorySectionSummary find(
			PresentenceInvestigationRequest presentenceInvestigationRequest);*/

	/**
	 * Find supervision history section summary by PSI excluding 
	 * the one in view.
	 *
	 *
	 * @param supervisionHistorySectionSummary supervision history 
	 * section summary
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @return supervision history sectin summary
	 */
	SupervisionHistorySectionSummary findExcluding(
			SupervisionHistorySectionSummary supervisionHistorySectionSummary,
			PresentenceInvestigationRequest presentenceInvestigationRequest);

	/**
	 * Find supervision history section summary by PSI.
	 *
	 *
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @return supervision history section summary
	 */
	SupervisionHistorySectionSummary findSupervisionHistorySectionSummaryByPSI(
			PresentenceInvestigationRequest presentenceInvestigationRequest);
}