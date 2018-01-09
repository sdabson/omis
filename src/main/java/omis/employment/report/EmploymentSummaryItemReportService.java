package omis.employment.report;

import omis.offender.domain.Offender;

/**
 * Employment Summary Item Report Service.
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 26, 2017)
 *@since OMIS 3.0
 *
 */
public interface EmploymentSummaryItemReportService {
	
	/**
	 * Returns the latest employment summary by the specified offender.
	 * 
	 * @param offender - Offender
	 * @return employmentSummary - latest employment summary by the specified
	 * offender
	 */
	EmploymentSummary findLatestEmploymentSummaryByOffender(Offender offender);
	
}
