package omis.adaaccommodation.report;

import java.util.Date;

import omis.offender.domain.Offender;

/** Report service for ada profile.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 28, 2016)
 * @since OMIS 3.0 */
public interface AdaProfileItemReportService {
	/** Find count by offender and date.
	 * @param offender - offender.
	 * @param effectiveDate - effective date.
	 * @return count. */
	Integer findCountByOffenderAndDate(Offender offender, Date effectiveDate);	
}
