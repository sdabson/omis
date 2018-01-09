package omis.placement.report;

import java.util.Date;

import omis.offender.domain.Offender;

/** Report service for placement profile.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 16, 2016)
 * @since OMIS 3.0 */
public interface PlacementProfileItemReportService {
	/** finds placement existence by offender and date.
	 * @param offender - offender.
	 * @param effectiveDate - effective date.
	 * @return true if placement exists for offender on date. */
	boolean findPlacementExistenceByOffenderAndDate(Offender offender, 
			Date effectiveDate);

}
