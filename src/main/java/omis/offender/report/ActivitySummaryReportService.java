package omis.offender.report;

import java.util.List;

import omis.offender.domain.Offender;

/**
 * Activity Summary Report Service Interface.
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 6, 2017)
 *@since OMIS 3.0
 *
 */
public interface ActivitySummaryReportService {
	
	/**
	 * Returns a list of Activity Summaries found by the specified Offender.
	 * 
	 * @param offender - Offender
	 * @return List of Activity Summaries found by the specified Offender
	 */
	List<ActivitySummary> findByOffender(Offender offender);

	/**
	 * Returns a list of Basic Information Activity Summaries found by the
	 * specified Offender.
	 * 
	 * @param offender - Offender
	 * @return List of Basic Information Activity Summaries found by the
	 * specified Offender
	 */
	List<ActivitySummary>
		findBasicInformationActivitySummariesByOffender(Offender offender);

	/**
	 * Returns a list of Placement Activity Summaries found by the
	 * specified Offender.
	 * 
	 * @param offender - Offender
	 * @return List of Placement Activity Summaries found by the
	 * specified Offender
	 */
	List<ActivitySummary>
		findPlacementActivitySummariesByOffender(Offender offender);

	/**
	 * Returns a list of Legal Activity Summaries found by the
	 * specified Offender.
	 * 
	 * @param offender - Offender
	 * @return List of Legal Activity Summaries found by the
	 * specified Offender
	 */
	List<ActivitySummary>
		findLegalActivitySummariesByOffender(Offender offender);

	/**
	 * Returns a list of Case Management Activity Summaries found by the
	 * specified Offender.
	 * 
	 * @param offender - Offender
	 * @return List of Case Management Activity Summaries found by the
	 * specified Offender
	 */
	List<ActivitySummary>
		findCaseManagementActivitySummariesByOffender(Offender offender);

	/**
	 * Returns a list of Safety Activity Summaries found by the
	 * specified Offender.
	 * 
	 * @param offender - Offender
	 * @return List of Safety Activity Summaries found by the
	 * specified Offender
	 */
	List<ActivitySummary>
		findSafetyActivitySummariesByOffender(Offender offender);

	/**
	 * Returns a list of Compliance Activity Summaries found by the
	 * specified Offender.
	 * 
	 * @param offender - Offender
	 * @return List of Compliance Activity Summaries found by the
	 * specified Offender
	 */
	List<ActivitySummary>
		findComplianceActivitySummariesByOffender(Offender offender);

	/**
	 * Returns a list of Health Activity Summaries found by the
	 * specified Offender.
	 * 
	 * @param offender - Offender
	 * @return List of Health Activity Summaries found by the
	 * specified Offender
	 */
	List<ActivitySummary>
		findHealthActivitySummariesByOffender(Offender offender);

	/**
	 * Returns a list of Relationships Activity Summaries found by the
	 * specified Offender.
	 * 
	 * @param offender - Offender
	 * @return List of Relationships Activity Summaries found by the
	 * specified Offender
	 */
	List<ActivitySummary>
		findRelationshipsActivitySummariesByOffender(Offender offender);
	
}
