package omis.offenderphoto.report;

import omis.offender.domain.Offender;

/** Photo association profile item report service.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 12, 2016)
 * @since OMIS 3.0 */
public interface OffenderPhotoAssociationProfileItemReportService {
	/** Finds photo association count by offender.
	 * @param offender - offender.
	 * @return count. */
	Integer findOffenderPhotoAssociationCountByOffender(Offender offender);

}
