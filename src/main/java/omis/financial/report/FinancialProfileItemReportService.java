package omis.financial.report;

import omis.offender.domain.Offender;

/** Report service for finance profile item.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 18, 2017)
 * @since OMIS 3.0 */
public interface FinancialProfileItemReportService {
	/** Finds financial profile item summary by offender.
	 * @param offender - offender.
	 * @return financial profile item summary. */
	FinancialProfileItemSummary findProfileItemSummaryByOffender(
			Offender offender);
}
