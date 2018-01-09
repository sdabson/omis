package omis.workassignment.report;

import java.util.Date;

import omis.offender.domain.Offender;

/**
 * Report service for work assignmeent profile items.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface WorkAssignmentProfileItemReportService {

	/**
	 * Returns count of work assignments for offender on date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return count of work assignments for offender on date
	 */
	Integer findWorkAssignmentCountForOffenderOnDate(
			Offender offender, Date date);
}