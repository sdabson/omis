package omis.commitstatus.report;

import java.util.List;

import omis.offender.domain.Offender;

/**
 * Report service for commit status.
 * 
 * @author Yidong Li
 * @version 0.1.0 (June 5, 2017)
 * @since OMIS 3.0
 */
public interface CommitStatusReportService {
	/**
	 * Returns a list of employer.
	 * 
	 * @param name name
	 * @return list of employer
	 */
	List<CommitStatusTermSummary> summarizeByOffender(Offender offender); 
}