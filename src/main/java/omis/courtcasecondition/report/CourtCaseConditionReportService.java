package omis.courtcasecondition.report;

import java.util.Date;
import java.util.List;

import omis.condition.report.ConditionSummary;
import omis.offender.domain.Offender;
import omis.offender.report.OffenderSummary;

/**
 * Report service for court case conditions.
 *
 * @author Trevor Isles
 * @version 0.1.0 (August 3, 2017)
 * @since OMIS 3.0
 */
public interface CourtCaseConditionReportService {
	
	/**
	 * Returns summaries of court case conditions by offender.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return summaries of court case conditions by offender
	 */
	List<ConditionSummary> summarizeByOffender(
			Offender offender, Date effectiveDate);
	
	/**
	 * Returns summaries of offenders based on query.
	 * 
	 * @param query query
	 * @return summaries of offenders based on query
	 */
	List<OffenderSummary> searchOffenders(String query);

}
