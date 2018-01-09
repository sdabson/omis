package omis.hearing.report;

import java.util.List;

import omis.hearing.domain.Hearing;
import omis.offender.domain.Offender;

/**
 * HearingSummaryReportService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (April 18, 2017)
 *@since OMIS 3.0
 *
 */
public interface HearingSummaryReportService {
	
	/**
	 * Returns a list of Hearing Summaries found by specified offender
	 * @param offender
	 * @return list of Hearing Summaries found by specified offender
	 */
	List<HearingSummary> findByOffender(Offender offender);
	
	/**
	 * Returns a HearingSummary for the specified Hearing
	 * @param hearing - Hearing to summarize
	 * @return HearingSummary for the specified Hearing
	 */
	HearingSummary summarize(Hearing hearing);
}
