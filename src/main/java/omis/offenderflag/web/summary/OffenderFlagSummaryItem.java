package omis.offenderflag.web.summary;

import java.util.Date;
import java.util.Map;

import omis.offender.domain.Offender;
import omis.offender.web.summary.AbstractSummaryItem;
import omis.offender.web.summary.SummaryItem;
import omis.offender.web.summary.SummaryItemRegistry;
import omis.offenderflag.report.OffenderFlagReportService;

/**
 * OffenderFlagSummaryItem.java
 * 
 *@author Annie Jacques
 *@author Stephen Abson
 *@version 0.1.0 (Dec 15, 2016)
 *@since OMIS 3.0
 *
 */
public class OffenderFlagSummaryItem extends AbstractSummaryItem
	implements SummaryItem {
	
	private static final long serialVersionUID = 1L;
	
	private static final String OFFENDER_FLAG_SUMMARIES_MODEL_KEY =
			"offenderFlagSummaries";
	
	private final OffenderFlagReportService offenderFlagReportService;
	
	
	/**
	 * @param includedPageName
	 * @param summaryItemRegistry
	 * @param order
	 * @param enabled
	 */
	public OffenderFlagSummaryItem(
			final OffenderFlagReportService offenderFlagReportService,
			final String includedPageName,
			final SummaryItemRegistry summaryItemRegistry,
			final int order,
			final boolean enabled) {
		super(includedPageName, summaryItemRegistry, order, enabled);
		this.offenderFlagReportService = offenderFlagReportService;
	}

	/**{@inheritDoc} */
	@Override
	public void build(final Map<String, Object> modelMap,
			final Offender offender, final Date date) {
		modelMap.put(OFFENDER_FLAG_SUMMARIES_MODEL_KEY,
				this.offenderFlagReportService
					.findOffenderFlagSummariesByOffender(offender));
	}

}
