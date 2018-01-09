package omis.stg.report;

import java.util.List;

import omis.offender.domain.Offender;
import omis.stg.domain.SecurityThreatGroupActivity;

/**
 * Report service for security threat group activities.
 *
 * @author Trevor Isles
 * @version 0.0.1 (Dec 5, 2016)
 * @since OMIS 3.0
 */
public interface SecurityThreatGroupActivityReportService {

	/**
	 * Returns summaries of security threat group activities by offender.
	 * 
	 * @param offender offender
	 * 
	 * @return summaries of security threat group activities by offender
	 */
	List<SecurityThreatGroupActivitySummary> summarizeByOffender(
			Offender offender);
	
	/**
	 * Returns summaries of security threat group involvements by activity.
	 * 
	 * @param offender offender
	 * 
	 * @return summaries of security threat group involvements by activity
	 */
	List<SecurityThreatGroupActivityInvolvementSummary> summarizeInvolvement(
			SecurityThreatGroupActivity activity);
	
}
