package omis.workassignment.web.summary;

import java.util.Date;
import java.util.Map;

import omis.offender.domain.Offender;
import omis.offender.web.summary.AbstractSummaryItem;
import omis.offender.web.summary.SummaryItem;
import omis.offender.web.summary.SummaryItemRegistry;
import omis.workassignment.report.WorkAssignmentSummaryItemReportService;

/**
 * Work Assignment Summary Item.
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 25, 2017)
 *@since OMIS 3.0
 *
 */
public class WorkAssignmentSummaryItem extends AbstractSummaryItem
		implements SummaryItem {
	
	private static final long serialVersionUID = 1L;
	
	private static final String WORK_ASSIGNMENT_SUMMARY_MODEL_KEY =
			"workAssignmentSummary";
	
	private WorkAssignmentSummaryItemReportService
		workAssignmentSummaryItemReportService;
	
	/**
	 * @param workAssignmentSummaryItemReportService - Work Assignment Summary
	 * Item Report Service
	 * @param includedPageName - String
	 * @param summaryItemRegistry - Summary Item Registry
	 * @param order - Int
	 * @param enabled - Boolean
	 */
	public WorkAssignmentSummaryItem(
			final WorkAssignmentSummaryItemReportService
				workAssignmentSummaryItemReportService,
			final String includedPageName,
			final SummaryItemRegistry summaryItemRegistry, final int order,
			final boolean enabled) {
		super(includedPageName, summaryItemRegistry, order, enabled);
		this.workAssignmentSummaryItemReportService =
				workAssignmentSummaryItemReportService;
	}

	/**{@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map,
			final Offender offender, final Date date) {
		map.put(WORK_ASSIGNMENT_SUMMARY_MODEL_KEY,
				this.workAssignmentSummaryItemReportService
				.findWorkAssignmentSummaryByOffender(offender));
	}

}
