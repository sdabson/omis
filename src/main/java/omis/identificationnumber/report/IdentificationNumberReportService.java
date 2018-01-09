package omis.identificationnumber.report;

import java.util.Date;
import java.util.List;

import omis.offender.domain.Offender;

/**
 * Report service for identification numbers.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface IdentificationNumberReportService {

	/**
	 * Returns summaries of identification numbers for offender on date.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return summaries of identification numbers for offender on date
	 */
	List<IdentificationNumberSummary> summarizeByOffenderOnDate(
			Offender offender, Date effectiveDate);
}