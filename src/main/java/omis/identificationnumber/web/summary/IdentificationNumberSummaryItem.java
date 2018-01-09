package omis.identificationnumber.web.summary;

import java.util.Date;
import java.util.Map;

import omis.identificationnumber.report.IdentificationNumberSummaryItemReportService;
import omis.offender.domain.Offender;
import omis.offender.web.summary.AbstractSummaryItem;
import omis.offender.web.summary.SummaryItem;
import omis.offender.web.summary.SummaryItemRegistry;

/**
 * Identification Number Summary Item.
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 2, 2017)
 *@since OMIS 3.0
 *
 */
public class IdentificationNumberSummaryItem extends AbstractSummaryItem
		implements SummaryItem {
	
	private static final long serialVersionUID = 1L;
	
	private static final String IDENTIFICATION_NUMBER_SUMMARIES_MODEL_KEY =
			"identificationNumberSummaryItems";
	
	private IdentificationNumberSummaryItemReportService
				identificationNumberSummaryItemReportService;
	
	/**
	 * @param includedPageName - String
	 * @param summaryItemRegistry - Summary Item Registry
	 * @param order - int
	 * @param enabled - Boolean
	 * @param identificationNumberSummaryItemReportService - Identification
	 * Number Summary Item Report Service
	 */
	public IdentificationNumberSummaryItem(final String includedPageName,
			final SummaryItemRegistry summaryItemRegistry, final int order,
			final boolean enabled,
			final IdentificationNumberSummaryItemReportService
				identificationNumberSummaryItemReportService) {
		super(includedPageName, summaryItemRegistry, order, enabled);
		this.identificationNumberSummaryItemReportService =
				identificationNumberSummaryItemReportService;
				
	}

	/**{@inheritDoc} */
	@Override
	public void build(final Map<String, Object> modelMap,
			final Offender offender, final Date date) {
		modelMap.put(IDENTIFICATION_NUMBER_SUMMARIES_MODEL_KEY,
				this.identificationNumberSummaryItemReportService
				.findSummariesByOffender(offender));
	}

}
