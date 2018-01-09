package omis.offenderflag.report;

import java.util.List;

import omis.offender.domain.Offender;

/**
 * OffenderFlagReportService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Dec 15, 2016)
 *@since OMIS 3.0
 *
 */
public interface OffenderFlagReportService {
	
	/**
	 * Returns a list of OffenderFlagSummaries by Offender
	 * @param offender - Offender
	 * @return list of OffenderFlagSummaries by Offender
	 */
	List<OffenderFlagSummary> findOffenderFlagSummariesByOffender(
			Offender offender);
	
}
