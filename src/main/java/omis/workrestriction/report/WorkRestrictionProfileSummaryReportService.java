package omis.workrestriction.report;

import omis.offender.domain.Offender;

/** Report service for work restrictions profile item.
 * @author Ryan Johns
 * @version 0.1.0 (Sep 2, 2016)
 * @since OMIS 3.0 */
public interface WorkRestrictionProfileSummaryReportService {
	
	/** Find work restriction count by offender and date.
	 * @param offender - offender.
	 * @return work restrictions count. */
	Integer findCountByOffender(final Offender offender);

}
