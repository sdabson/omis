package omis.custody.web.summary;

import java.util.Date;
import java.util.Map;

import omis.custody.report.CustodyReviewSummaryItemService;
import omis.offender.domain.Offender;
import omis.offender.web.summary.AbstractSummaryItem;
import omis.offender.web.summary.SummaryItem;
import omis.offender.web.summary.SummaryItemRegistry;

/**
 * Custody review summary item.
 *
 * @author Sheronda Vaughn
 * @author Stephen Abson
 * @version 0.1.0 (Jun 8, 2017)
 * @since OMIS 3.0
 */
public class CustodyReviewSummaryItem extends AbstractSummaryItem 
							implements SummaryItem {

	private static final long serialVersionUID = 1L;
	
	private static final String CUSTODY_REVIEW_SUMMARY_MODEL_KEY
		= "custodyReviewSummary";
	
	private CustodyReviewSummaryItemService custodyReviewSummaryItemService;
	
	/** Instantiates an implementation of CustodyReviewSummaryItem */
	public CustodyReviewSummaryItem(
			final CustodyReviewSummaryItemService 
			custodyReviewSummaryItemService,
			final SummaryItemRegistry summaryItemRegistry, 
			final String includedPageName, final int order,
			final boolean enabled) {
		super(includedPageName, summaryItemRegistry, order, enabled);
		this.custodyReviewSummaryItemService = custodyReviewSummaryItemService;
	}

	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> modelMap, 
			final Offender offender, final Date date) {
		modelMap.put(CUSTODY_REVIEW_SUMMARY_MODEL_KEY, 
				this.custodyReviewSummaryItemService
				.findCustodyReviewSummaryByOffenderAndDate(offender, date));
	}
}