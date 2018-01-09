package omis.locationterm.report;

import java.util.Date;
import java.util.List;

import omis.offender.domain.Offender;

/**
 * Report service for location terms.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface LocationTermReportService {

	/**
	 * Summarize location terms by offender on date.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return location terms by offender on date
	 */
	List<LocationTermSummary> summarizeByOffenderOnDate(
			Offender offender, Date effectiveDate);
}