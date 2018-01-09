package omis.citation.report;

import java.util.List;

import omis.offender.domain.Offender;

/**
 * Report service for misdemeanor citation.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Aug 17, 2016)
 * @since OMIS 3.0
 */

public interface MisdemeanorCitationReportService {
	
	/**
	 * Returns summaries of misdemeanor citations by offender.
	 * 
	 * @param offender offender
	 * @return summaries of misdemeanor citations by offender
	 */
	List<MisdemeanorCitationSummary> summarizeByOffender(Offender offender);
}
