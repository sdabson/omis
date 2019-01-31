package omis.prisonterm.report;

import java.util.Date;
import java.util.List;

import omis.offender.domain.Offender;

/**
 * Report service for prison term.
 * 
 * @author Trevor Isles
 * @author Annie Wahl
 * @version 0.1.1 (Jan 30, 2019)
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
	
	/**
	 * Returns summaries of both active and verified prison terms by Offender.
	 * 
	 * @param offender offender
	 * @return Summaries of both active and verified prison terms by Offender.
	 */
	List<PrisonTermSummary> findActiveVerifiedTermsByOffender(
			Offender offender);
}

