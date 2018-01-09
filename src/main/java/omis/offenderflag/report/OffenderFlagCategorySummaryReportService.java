package omis.offenderflag.report;

import java.util.List;

/**
 * Offender flag category summary report service.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 31, 2017)
 * @since OMIS 3.0
 */
public interface OffenderFlagCategorySummaryReportService {
	
	/**
	 * Summarized list of all offender flag categories.
	 *
	 *
	 * @return list of offender flag categories
	 */
	List<OffenderFlagCategorySummary> findAllOffenderFlagCategorySummaries();
}