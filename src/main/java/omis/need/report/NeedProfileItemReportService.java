package omis.need.report;

import omis.offender.domain.Offender;

/** Report service for need profile item.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 16, 2016)
 * @since OMIS 3.0 */
public interface NeedProfileItemReportService {
	/** find case plan objective count by offender.
	 * @param offender - offender.
	 * @return count. */
	Integer findCasePlanObjectiveCountByOffender(Offender offender);
}
