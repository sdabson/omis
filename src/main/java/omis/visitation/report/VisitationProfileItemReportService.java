package omis.visitation.report;

import java.util.Date;

import omis.offender.domain.Offender;

/** Profile Item report service for visitation.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 21, 2016)
 * @since OMIS 3.0 */
public interface VisitationProfileItemReportService {
	/** Finds visitor count by offender and date.
	 * @param offender - offender.
	 * @param effectiveDate - effective date.
	 * @return count. */
	Integer findVisitorCountByOffenderAndDate(Offender offender, 
			Date effectiveDate);
	
	/** Finds visit count by offender.
	 * @param offender - offender.
	 * @return count. */
	Integer findVisitCountByOffender(Offender offender);
}
