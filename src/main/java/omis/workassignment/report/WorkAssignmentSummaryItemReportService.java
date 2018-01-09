package omis.workassignment.report;

import omis.offender.domain.Offender;

/**
 * Work Assignment Summary Report Service.
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 25, 2017)
 *@since OMIS 3.0
 *
 */
public interface WorkAssignmentSummaryItemReportService {
	
	/**
	 * Returns a Work Assignment Summary by the specified Offender.
	 * 
	 * @param offender - offender
	 * @return workAssignmentSummary - Work Assignment Summary by the
	 * specified Offender
	 */
	WorkAssignmentSummary findWorkAssignmentSummaryByOffender(
			Offender offender);
	
}
