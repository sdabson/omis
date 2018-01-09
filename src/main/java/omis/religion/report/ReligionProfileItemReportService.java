package omis.religion.report;

import java.util.Date;

import omis.offender.domain.Offender;

/** Report service for religion profile item.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 16, 2016)
 * @since OMIS 3.0 */
public interface ReligionProfileItemReportService {
	/** find religious preference count by offender and date.
	 * @param offender - offender.
	 * @param date - date.
	 * @return count. */
	Integer findReligiousPreferenceCountByOffenderAndDate(Offender offender, 
			Date effectiveDate);
}
