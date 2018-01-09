package omis.residence.web.summary;

import java.util.Date;
import java.util.Map;

import omis.offender.domain.Offender;
import omis.offender.web.summary.AbstractSummaryItem;
import omis.offender.web.summary.SummaryItem;
import omis.offender.web.summary.SummaryItemRegistry;
import omis.residence.report.ResidenceSummaryItemReportService;

/**
 * Residence Summary Item.
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 25, 2017)
 *@since OMIS 3.0
 *
 */
public class ResidenceSummaryItem extends AbstractSummaryItem
		implements SummaryItem {
	
	private static final long serialVersionUID = 1L;
	
	private static final String RESIDENCE_SUMMARY_MODEL_KEY =
			"residenceSummary";
	
	private ResidenceSummaryItemReportService residenceSummaryItemReportService;
	
	/**
	 * @param residenceSummaryItemReportService - Residence Summary Item Report
	 * Service
	 * @param includedPageName - String
	 * @param summaryItemRegistry - Summary Item Registry
	 * @param order - int
	 * @param enabled - Boolean
	 */
	public ResidenceSummaryItem(final ResidenceSummaryItemReportService
				residenceSummaryItemReportService,
			final String includedPageName,
			final SummaryItemRegistry summaryItemRegistry, final int order,
			final boolean enabled) {
		super(includedPageName, summaryItemRegistry, order, enabled);
		this.residenceSummaryItemReportService =
				residenceSummaryItemReportService;
	}

	/**{@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map,
			final Offender offender, final Date date) {
		map.put(RESIDENCE_SUMMARY_MODEL_KEY,
				this.residenceSummaryItemReportService
				.findResidenceSummaryByOffender(offender));
	}

}
