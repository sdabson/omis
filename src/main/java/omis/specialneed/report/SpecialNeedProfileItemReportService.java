package omis.specialneed.report;

import java.util.Date;

import omis.offender.domain.Offender;

/** Report service for special need profile item.
 * @author Ryan JOhns
 * @version 0.1.0 (Mar 17, 2016)
 * @since OMIS 3.0 */
public interface SpecialNeedProfileItemReportService {
	/** Finds special need count by offender and date.
	 * @param offender - offender.
	 * @param effectiveDate - effective date.
	 * @return count. */
	Integer findSpecialNeedCountByOffenderAndDate(Offender offender, 
			Date effectiveDate);

}
