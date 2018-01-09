package omis.tierdesignation.report;

import java.util.Date;

import omis.offender.domain.Offender;

/** Report service for tier designation profile item.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 18, 2016)
 * @since OMIS 3.0 */
public interface TierDesignationProfileItemReportService {
	/** Finds tier designation count by offender and date.
	 * @param offender - offender.
	 * @param effectiveDate - effective date.
	 * @return count. */
	Integer findTierDesignationCountByOffenderAndDate(Offender offender, 
			Date effectiveDate);

}
