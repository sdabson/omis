package omis.identificationnumber.report;

import java.util.List;

import omis.offender.domain.Offender;

/**
 * Identification Number Summary Item Report Service Interface.
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 2, 2017)
 *@since OMIS 3.0
 *
 */
public interface IdentificationNumberSummaryItemReportService {
	
	/**
	 * Returns a list of Identification Number Summaries
	 * by the specified offender.
	 * 
	 * @param offender - Offender
	 * @return List of Identification Number Summaries
	 * by the specified offender
	 */
	List<IdentificationNumberSummary> findSummariesByOffender(
			Offender offender);
	
}
