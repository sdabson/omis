package omis.identificationnumber.report;

import java.util.List;

/**
 * IdentificationNumberCategorySummaryReportService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 27, 2017)
 *@since OMIS 3.0
 *
 */
public interface IdentificationNumberCategorySummaryReportService {
	
	/**
	 * Returns a list of summaries for all IdentificationNumberCategories
	 * @return List of IdentificationNumberCategorySummaries
	 */
	List<IdentificationNumberCategorySummary>
			findAllIdentificationNumberCategorySummaries();
	
}
