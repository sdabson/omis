package omis.employment.web.summary;

import java.util.Date;
import java.util.Map;

import omis.employment.report.EmploymentSummaryItemReportService;
import omis.offender.domain.Offender;
import omis.offender.web.summary.AbstractSummaryItem;
import omis.offender.web.summary.SummaryItem;
import omis.offender.web.summary.SummaryItemRegistry;

/**
 * Employment Summary Item.
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 26, 2017)
 *@since OMIS 3.0
 *
 */
public class EmploymentSummaryItem extends AbstractSummaryItem
		implements SummaryItem {
	
	private static final long serialVersionUID = 1L;
	
	private static final String EMPLOYMENY_SUMMARY_MODEL_KEY =
			"employmentSummary";
	
	private EmploymentSummaryItemReportService
		employmentSummaryItemReportService;
	
	/**
	 * @param employmentSummaryItemReportService - Employment Summary Item
	 * Report Service
	 * @param includedPageName - String
	 * @param summaryItemRegistry - Summary Item Registry
	 * @param order - int
	 * @param enabled - Boolean
	 */
	public EmploymentSummaryItem(
			final EmploymentSummaryItemReportService
				employmentSummaryItemReportService,
			final String includedPageName,
			final SummaryItemRegistry summaryItemRegistry, final int order,
			final boolean enabled) {
		super(includedPageName, summaryItemRegistry, order, enabled);
		this.employmentSummaryItemReportService =
				employmentSummaryItemReportService;
	}

	/**{@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map,
			final Offender offender, final Date date) {
		map.put(EMPLOYMENY_SUMMARY_MODEL_KEY,
				this.employmentSummaryItemReportService
				.findLatestEmploymentSummaryByOffender(offender));
	}

}
