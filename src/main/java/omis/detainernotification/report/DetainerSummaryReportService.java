package omis.detainernotification.report;

import java.util.List;

import omis.detainernotification.domain.DetainerAgency;
import omis.offender.domain.Offender;

/**
 * Summaries the detainer.
 *
 * @author Sheronda Vaughn
 * @author Annie Jacques
 * @version 0.1.1 (Oct 17, 2017)
 * @since OMIS 3.0
 */
public interface DetainerSummaryReportService {
	
	/**
	 * Finds summary of detainers by offender.
	 * 
	 * @param offender offender
	 * @return list of detainers
	 */
	List<DetainerSummary> findByOffender(Offender offender);
	
	/**
	 * Returns a summary of the specified Detainer Agency
	 * 
	 * @param detainerAgency - detainer agency to summary
	 * @return DetainerAgencySummary - summary of specified Detainer Agency
	 */
	DetainerAgencySummary summarizeDetainerAgency(DetainerAgency detainerAgency);
}