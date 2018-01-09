package omis.probationterm.report;

import java.util.Date;

import omis.offender.domain.Offender;

/**
 * ProbationTermProfileItemService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 2, 2017)
 *@since OMIS 3.0
 *
 */
public interface ProbationTermSummaryItemReportService {
	
	
	/**
	 * Returns the ProbationTermSummary for the specified Offender
	 * @param offender - Offender
	 * @param effectiveDate - Date
	 * @return ProbationTermSummary for the specified Offender
	 */
	public ProbationTermSummary findMaxProbationTermExpirationDate(
			Offender offender, Date effectiveDate);
	
}
