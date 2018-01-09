package omis.presentenceinvestigation.report;

import java.util.List;

import omis.offender.domain.Offender;

/** Report service for Biographic And Contact Section Summary.
 * @author Jonny Santy
 * @version 0.1.0 (Jun 28, 2016)
 * @since OMIS 3.0 */
public interface BiographicAndContactSectionSummaryReportService {
	
	/** Finds Biographic And Contact Section summaries by offender.
	 * @param offender - offender to search by.
	 * @return list of presentence investigation summaries. */
	List<BiographicAndContactSummary> 
		findBiographicAndContactSectionSummaryByOffender(
				Offender offender);

}
