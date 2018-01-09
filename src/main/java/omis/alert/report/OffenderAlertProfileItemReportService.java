package omis.alert.report;

import java.util.Date;

import omis.offender.domain.Offender;

/** Report service for offender alert profile item.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 28, 2016)
 * @since OMIS 3.0 */
public interface OffenderAlertProfileItemReportService {
	/** Find offender alert profile item summary by offender and date.
	 * @param offender - offender.
	 * @param effectiveDate - effective date. 
	 * @return count. */
	OffenderAlertProfileItemSummary 
		findOffenderAlertProfileItemSummaryByOffenderAndDate(
				Offender offender, Date effectiveDate);
}
