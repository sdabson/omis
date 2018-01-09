package omis.prisonterm.report;

import java.util.List;

import omis.offender.domain.Offender;

/**
 * Report service for prison term.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (March 21, 2017)
 * @since OMIS 3.0
 */

public interface PrisonTermReportService {

	/**
	 * Returns summaries of prison terms by offender.
	 * 
	 * @param offender offender
	 * @return summaries of prison terms by offender
	 */
	List<PrisonTermSummary> summarizeByOffender(Offender offender);
}

