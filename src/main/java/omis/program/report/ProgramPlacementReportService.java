package omis.program.report;

import java.util.Date;
import java.util.List;

import omis.offender.domain.Offender;

/**
 * Report service for program placements.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface ProgramPlacementReportService {

	/**
	 * Summarize program placements by offender.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return summaries of program placements
	 */
	List<ProgramPlacementSummary>summarizeByOffender(
			Offender offender, Date effectiveDate);
}