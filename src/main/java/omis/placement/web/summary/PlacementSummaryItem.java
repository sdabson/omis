package omis.placement.web.summary;

import java.util.Date;
import java.util.Map;

import omis.offender.domain.Offender;
import omis.offender.web.summary.AbstractSummaryItem;
import omis.offender.web.summary.SummaryItem;
import omis.offender.web.summary.SummaryItemRegistry;
import omis.placement.report.PlacementReportService;

/** Summary item for placement.
 * @author Ryan Johns
 * @author Stephen Abson
 * @version 0.1.0 (Jul 28 2016)
 * @since OMIS 3.0 */
public class PlacementSummaryItem 
				extends AbstractSummaryItem 
				implements SummaryItem {
	private static final long serialVersionUID = 1L;
	private static final String OFFENDER_PLACEMENT_SUMMARY_MODEL_KEY 
					= "offenderPlacementSummary";
	private final PlacementReportService placementReportService;
	
	/** Constructor.
	 * @param placementReportService - placement summary report service.
	 * @param summaryItemRegistry - summary item registry.
	 * @param includedPageName - include page name.
	 * @param order - order.
	 * @param enabled - enabled */
	public PlacementSummaryItem(
					final PlacementReportService placementReportService,
					final SummaryItemRegistry summaryItemRegistry,
					final String includedPageName, final int order,
					final boolean enabled) {
		super(includedPageName, summaryItemRegistry, order, enabled);
		this.placementReportService = placementReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, final Offender offender, 
					final Date date) {
		map.put(OFFENDER_PLACEMENT_SUMMARY_MODEL_KEY, 
						this.placementReportService
							.findOffenderPlacementSummaryByOffender(offender, 
									date));
	}
}
