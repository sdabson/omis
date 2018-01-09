package omis.prisonterm.web.summary;

import java.util.Date;
import java.util.Map;

import omis.offender.domain.Offender;
import omis.offender.web.summary.AbstractSummaryItem;
import omis.offender.web.summary.SummaryItem;
import omis.offender.web.summary.SummaryItemRegistry;
import omis.prisonterm.report.PrisonTermSummaryItemReportService;

/**
 * PrisonTermSummaryItem.java
 * 
 *@author Annie Jacques
 *@author Stephen Abson
 *@version 0.1.0 (Jun 6, 2017)
 *@since OMIS 3.0
 *
 */
public class PrisonTermSummaryItem extends AbstractSummaryItem
		implements SummaryItem {
	
	private static final long serialVersionUID = 1L;
	
	private static final String PRISON_TERM_SUMMARY_MODEL_KEY =
			"prisonTermSummary";
	
	private PrisonTermSummaryItemReportService prisonTermSummaryItemReportService;
	
	/**
	 * @param includedPageName
	 * @param summaryItemRegistry
	 * @param order
	 * @param enabled
	 */
	public PrisonTermSummaryItem(
			final PrisonTermSummaryItemReportService prisonTermSummaryItemReportService,
			final String includedPageName,
			final SummaryItemRegistry summaryItemRegistry, final int order,
			final boolean enabled) {
		super(includedPageName, summaryItemRegistry, order, enabled);
		this.prisonTermSummaryItemReportService = prisonTermSummaryItemReportService;
	}
	
	/**{@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map,
			final Offender offender, final Date date) {
		map.put(PRISON_TERM_SUMMARY_MODEL_KEY,
				this.prisonTermSummaryItemReportService
				.findPrisonTermSummaryByOffender(offender));
	}

}
