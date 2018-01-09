package omis.offenderphoto.web.summary;

import java.util.Date;
import java.util.Map;

import omis.offender.domain.Offender;
import omis.offender.web.summary.AbstractSummaryItem;
import omis.offender.web.summary.SummaryItem;
import omis.offender.web.summary.SummaryItemRegistry;
import omis.offenderphoto.report.OffenderPhotoAssociationSummaryReportService;

/** Summary item for offender photo association.
 * @author Ryan Johns
 * @author Stephen Abson
 * @version 0.1.0 (Jul 29, 2015)
 * @since OMIS 3.0 */
public class OffenderPhotoAssociationSummaryItem 
	extends AbstractSummaryItem
		implements SummaryItem {
	private static final long serialVersionUID = 1L;
	private static final String OFFENDER_PHOTO_ASSOCIATION_SUMMARY_MODEL_KEY = 
		"offenderPhotoAssociationSummary";
	private final OffenderPhotoAssociationSummaryReportService
		offenderPhotoAssociationSummaryReportService;
	
	/** Constructor.
	 * @param offenderPhotoAssociationSummaryReportService - offender photo
	 * association summary report service.
	 * @param summaryItemRegistry - summary item registry.
	 * @param includedPageName - included page name.
	 * @param order - order
	 * @param enabled - enabled */
	public OffenderPhotoAssociationSummaryItem(
			final OffenderPhotoAssociationSummaryReportService
			offenderPhotoAssociationSummaryReportService,
			final SummaryItemRegistry summaryItemRegistry, 
			final String includedPageName, final int order,
			final boolean enabled) {
		super(includedPageName, summaryItemRegistry, order, enabled);
		this.offenderPhotoAssociationSummaryReportService = 
				offenderPhotoAssociationSummaryReportService;
	}
	
	/** {@inheritDoc} */
	@Override
	public void build(final Map<String, Object> map, final Offender offender,
			final Date date) {
		map.put(OFFENDER_PHOTO_ASSOCIATION_SUMMARY_MODEL_KEY,
				this.offenderPhotoAssociationSummaryReportService
				.findByOffender(offender));
	}
}
