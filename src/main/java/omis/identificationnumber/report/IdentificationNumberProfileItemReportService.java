package omis.identificationnumber.report;

import java.util.Date;

import omis.offender.domain.Offender;

/**
 * Report service for profile item for identification numbers.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface IdentificationNumberProfileItemReportService {

	/**
	 * Returns count of identification numbers for offender on date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return count of identification numbers for offender on date
	 */
	Integer countForOffenderOnDate(Offender offender, Date date);
}