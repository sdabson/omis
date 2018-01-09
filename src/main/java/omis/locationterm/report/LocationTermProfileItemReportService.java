package omis.locationterm.report;

import java.util.Date;

import omis.offender.domain.Offender;

/**
 * Report service for location term profile item.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface LocationTermProfileItemReportService {

	/**
	 * Returns whether offender has active location term on date.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return whether offender has active location term on date 
	 */
	boolean findLocationExistenceByOffenderAndDate(
			Offender offender, Date effectiveDate);
}