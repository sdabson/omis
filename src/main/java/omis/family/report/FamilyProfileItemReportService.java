package omis.family.report;

import java.util.Date;

import omis.offender.domain.Offender;

/** Report service for family profile item.
 * @author Ryan Johns'
 * @author Sheronda Vaughn
 * @version 0.1.0 (Mar 15, 2016)
 * @since OMIS 3.0 */
public interface FamilyProfileItemReportService {
	/** Finds family count by offender and date.
	 * @param offender - offender.
	 * @param effectiveDate - effective date.
	 * @return count. */
	Integer findFamilyCountByOffenderAndDate(Offender offender, 
			Date effectiveDate);
}
