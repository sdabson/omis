package omis.bedplacement.web.summary;

import java.util.Date;
import java.util.Map;

import omis.bedplacement.report.BedPlacementSummaryReportService;
import omis.offender.domain.Offender;
import omis.offender.web.summary.AbstractSummaryItem;
import omis.offender.web.summary.SummaryItem;
import omis.offender.web.summary.SummaryItemRegistry;

/** Bed placement summary item.
 * @author Ryan Johns
 * @author Stephen Abson
 * @version 0.1.0 (Jul 27, 2015)
 * @since OMIs 3.0 */
public class BedPlacementSummaryItem 
				extends AbstractSummaryItem 
				implements SummaryItem {
	private static final long serialVersionUID = 1L;
	private static final String BED_PLACEMENT_SUMMARY_MODEL_KEY = 
					"bedPlacementSummary";
	private BedPlacementSummaryReportService bedPlacementSummaryReportService;
	
	/** Constructor.
	 * @param bedPlacementSummaryReportService - bed placement summary report 
	 * service.
	 * @param summaryItemRegistry - summary item registry.
	 * @param includedPageName - included page name. 
	 * @param order - order.
	 * @param enabled - whether enabled. */
	public BedPlacementSummaryItem(
		final BedPlacementSummaryReportService 
		bedPlacementSummaryReportService,
		final SummaryItemRegistry summaryItemRegistry,
		final String includedPageName, final int order, final boolean enabled) {
		super(includedPageName, summaryItemRegistry, order, enabled);
		this.bedPlacementSummaryReportService = 
				bedPlacementSummaryReportService;
	}

	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, final Offender offender, 
					final Date date) {
		map.put(BED_PLACEMENT_SUMMARY_MODEL_KEY, 
						this.bedPlacementSummaryReportService
						.findBedPlacementSummaryByOffenderAndDate(offender, 
								date));
		
	}
}
