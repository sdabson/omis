package omis.offender.report;

import java.util.Date;

import omis.offender.domain.Offender;

/** Report service for alternative offender Identity profile item.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 12, 2016)
 * @since OMIS 3.0 */
public interface AlternativeOffenderIdentityProfileItemReportService {
	/** Finds alternative identity count by offender and date.
	 * @param offender - offender.
	 * @param effectiveDate - effective date.
	 * @return count. */
	Integer findAlternativeOffenderIdentityCountByOffenderAndDate(
			Offender offender, Date effectiveDate);
}
