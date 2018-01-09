package omis.grievance.report;

import java.util.Date;

import omis.offender.domain.Offender;

/** Report service for grievance profile item.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 16, 2016)
 * @since OMIS 3.0 */
public interface GrievanceProfileItemReportService {
	/** finds grievance count by offender and date.
	 * @param offender - offender.
	 * @param effectiveDate - effective date. 
	 * @return count. */
	Integer findGrievanceCountByOffenderAndDate(Offender offender, 
			Date effectiveDate);
}
