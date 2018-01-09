package omis.separationneed.report;

import java.util.Date;

import omis.offender.domain.Offender;

/** Report service for separation need profile item.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 17, 2016)
 * @since OMIS 3.0 */
public interface SeparationNeedProfileItemReportService {
	/** Finds separation need profile item summary by offender.
	 * @param offender - offender.
	 * @param effectiveDate - effective date.
	 * @return separation need profile item summary. */
	SeparationNeedProfileItemSummary findSeparationNeedProfileItemSummaryByOffenderAndDate(Offender offender, 
			Date effectiveDate);

}
