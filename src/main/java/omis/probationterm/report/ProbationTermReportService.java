package omis.probationterm.report;

import java.util.Date;
import java.util.List;

import omis.courtcase.domain.CourtCase;
import omis.offender.domain.Offender;

/**
 * Report service for probation term.
 * 
 * @author Josh Divine
 * @version 0.1.0 (May 24, 2017)
 * @since OMIS 3.0
 */
public interface ProbationTermReportService {

	/**
	 * Returns list of probation term summaries for the specified offender and 
	 * date.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return probation term summaries
	 */
	List<ProbationTermSummary> summarizeByOffender(Offender offender, 
			Date effectiveDate);
	
	/**
	 * Returns list of probation term summaries for the specified court case.
	 * 
	 * @param courtCase court case
	 * @return probation term summaries
	 */
	List<ProbationTermSummary> summarizeByCourtCase(CourtCase courtCase);
	
}
