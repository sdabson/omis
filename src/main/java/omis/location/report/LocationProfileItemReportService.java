package omis.location.report;

import java.util.Date;

import omis.offender.domain.Offender;

/** Report service for location profile item.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 16, 2016)
 * @since OMIS 3.0 */
public interface LocationProfileItemReportService {
	/** finds existence of location term by offender and date.
	 * @param offender - offender.
	 * @param effectiveDate - effective date.
	 * @return true if a location term exists for offender and date. */
	boolean findLocationTermExistenceByOffenderAndDate(Offender offender, 
			Date effectiveDate);

}
