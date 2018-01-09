package omis.offenderphoto.report;

import omis.offender.domain.Offender;

/** Report service for offender photo summaries.
 * @author Ryan Johns
 * @version 0.1.0 (Jul 28, 2015)
 * @since OMIS 3.0 */
public interface OffenderPhotoAssociationSummaryReportService {
	/** finds offender photo summary by offender.
	 * @param offender - offender.
	 * @return offender photo summary. */
	OffenderPhotoAssociationSummary findByOffender(Offender offender);
}
