package omis.prisonterm.report;


import omis.offender.domain.Offender;

/**
 * PrisonTermSummaryItemReportService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 6, 2017)
 *@since OMIS 3.0
 *
 */
public interface PrisonTermSummaryItemReportService {
	
	/**
	 * Returns a PrisonTermSummary for the specified Offender
	 * @param offender - Offender
	 * @return PrisonTermSummary for the specified Offender
	 */
	PrisonTermSummary findPrisonTermSummaryByOffender(
			final Offender offender);
	
}
