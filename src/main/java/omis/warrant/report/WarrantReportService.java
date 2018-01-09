package omis.warrant.report;

import java.util.List;

import omis.offender.domain.Offender;

/**
 * WarrantReportService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 9, 2017)
 *@since OMIS 3.0
 *
 */
public interface WarrantReportService {
	
	/**
	 * Returns a list a WarrantSummaries by specified Offender
	 * @param offender - Offender
	 * @return List a WarrantSummaries by specified Offender
	 */
	public List<WarrantSummary> findByOffender(Offender offender);
	
}
