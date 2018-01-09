package omis.residence.report;

import omis.offender.domain.Offender;

/**
 * Residence Summary Item Report Service.
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 26, 2017)
 *@since OMIS 3.0
 *
 */
public interface ResidenceSummaryItemReportService {
	
	/**
	 * Returns a Residence Summary by the specified offender.
	 * 
	 * @param offender - Offender
	 * @return residenceSummary - Residence Summary found by the specified
	 * offender
	 */
	ResidenceSummary findResidenceSummaryByOffender(Offender offender);
	
}
