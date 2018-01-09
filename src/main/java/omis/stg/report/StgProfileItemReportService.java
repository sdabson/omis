package omis.stg.report;

import java.util.Date;

import omis.offender.domain.Offender;

/** Report service for stg profile item.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 17, 2016)
 * @since OMIS 3.0 */
public interface StgProfileItemReportService {
	/** finds security threat group affiliation by offender and date.
	 * @param offender - offender.
	 * @param effectiveDate - effective date. 
	 * @return count. */
	Integer findStgAffiliationCountByOffenderAndDate(final Offender offender, 
			final Date effectiveDate);

}
