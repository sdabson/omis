package omis.offenderflag.report;

import omis.offender.domain.Offender;

/** Report service for offender flag profile.
 * @author Ryan Johns
 * @version 0.1.0 (Mar15, 2016)
 * @since OMIS 3.0 */
public interface OffenderFlagProfileItemReportService {
	/** Finds offender flag count by offender and date.
	 * @param offender - offender.
	 * @return count. */
	public Integer findOffenderFlagCountByOffender(Offender offender);

}
