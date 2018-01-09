package omis.prisonterm.report;

import omis.offender.domain.Offender;

/**
 * Profile item report service for prison term.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (March 17, 2017)
 * @since OMIS 3.0
 */

public interface PrisonTermProfileItemReportService {
	
	/** Finds prison term summary by offender.
	 * @param offender - offender.
	 * @return count.
	 */
	Integer findPrisonTermCountByPrisonTerms(Offender offender);

}
