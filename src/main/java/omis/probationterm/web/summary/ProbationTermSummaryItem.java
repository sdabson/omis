package omis.probationterm.web.summary;

import java.util.Date;
import java.util.Map;

import omis.offender.domain.Offender;
import omis.offender.web.summary.AbstractSummaryItem;
import omis.offender.web.summary.SummaryItem;
import omis.offender.web.summary.SummaryItemRegistry;
import omis.probationterm.report.ProbationTermSummaryItemReportService;

/**
 * ProbationTermSummaryItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 2, 2017)
 *@since OMIS 3.0
 *
 */
public class ProbationTermSummaryItem extends AbstractSummaryItem
		implements SummaryItem {
	
	private static final long serialVersionUID = 1L;
	
	private static final String PROBATION_TERM_SUMMARY_MODEL_KEY =
			"probationTermSummary";
	
	private ProbationTermSummaryItemReportService
				probationTermSummaryItemReportService;
	
	/**
	 * @param includedPageName
	 * @param summaryItemRegistry
	 * @param order
	 * @param enabled
	 */
	public ProbationTermSummaryItem(
			final ProbationTermSummaryItemReportService
				probationTermSummaryItemReportService,
			final String includedPageName,
			final SummaryItemRegistry summaryItemRegistry, final int order,
			final boolean enabled) {
		super(includedPageName, summaryItemRegistry, order, enabled);
		this.probationTermSummaryItemReportService =
				probationTermSummaryItemReportService;
	}

	/**{@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map,
			final Offender offender, final Date date) {
		map.put(PROBATION_TERM_SUMMARY_MODEL_KEY,
				this.probationTermSummaryItemReportService
				.findMaxProbationTermExpirationDate(offender, date));
	}

}
