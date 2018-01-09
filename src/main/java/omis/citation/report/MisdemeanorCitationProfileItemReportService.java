package omis.citation.report;

import omis.offender.domain.Offender;

/**
 * Profile item report service for misdemeanor citation.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Aug 17, 2016)
 * @since OMIS 3.0
 */

public interface MisdemeanorCitationProfileItemReportService {

	/** Finds misdemeanor citation summary by offender.
	 * @param offender - offender.
	 * @return count.
	 */
	Integer findMisdemeanorCitationCountByCitations(Offender offender);
}
