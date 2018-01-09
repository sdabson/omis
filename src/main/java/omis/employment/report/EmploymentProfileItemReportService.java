package omis.employment.report;

import java.util.Date;

import omis.offender.domain.Offender;

/** Service for employment profile related operation.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 15, 2016)
 * @since OMIS 3.0 */
public interface EmploymentProfileItemReportService {
	/** Finds employment by offender and date.
	 * @param offender - offender.
	 * @param effectiveDate - effective date. 
	 * @return count. */
	Integer findEmploymentCountByOffenderAndDate(Offender offender, Date date);
}
