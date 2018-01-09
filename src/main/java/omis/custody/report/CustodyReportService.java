package omis.custody.report;

import java.util.List;

import omis.offender.domain.Offender;

/**
 * Report service for custody.
 * @author Joel Norris 
 * @version 0.1.0 (Mar, 27 2013)
 * @since OMIS 3.0
 */
public interface CustodyReportService {

	/**
	 * Returns a list of custody summaries.
	 * 
	 * @param offender offender
	 * @return list of custody summaries.
	 */
	List<CustodySummary> findReviewSummaries(
			final Offender offender);
}