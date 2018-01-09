package omis.victim.report;

import omis.offender.domain.Offender;

/** Report service for victim profile.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 21, 2016)
 * @since OMIS 3.0 */
public interface VictimProfileItemReportService {
	/** Finds victim count by offender.
	 * @param offender - offender.
	 * @return count. */
	Integer findVictimCountByOffender(final Offender offender);
}
