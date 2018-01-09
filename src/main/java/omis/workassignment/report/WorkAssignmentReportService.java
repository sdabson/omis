package omis.workassignment.report;

import java.util.List;

import omis.offender.domain.Offender;

/**
 * Service for work assignment report.
 * 
 * @author Yidong Li
 * @version 0.1.1 (Sept 1, 2016)
 * @since OMIS 3.0
 */
public interface WorkAssignmentReportService {
	/**
	 * Returns a list of work assignment.
	 * @param offender offender
	 * @return a list of work assignment
	 */
	List<WorkAssignmentSummary> findByOffender(Offender offender);
}