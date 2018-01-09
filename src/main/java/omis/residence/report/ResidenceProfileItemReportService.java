package omis.residence.report;

import java.util.Date;

import omis.offender.domain.Offender;

/** Report service for residence profile item.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 17, 2016)
 * @since OMIS 3.0 */
public interface ResidenceProfileItemReportService {
	/** Finds residence count by offender and date.
	 * @param offender - offender.
	 * @param effectivedate - effective date. 
	 * @return count. */
	public Integer findResidenceCountByOffenderAndDate(Offender offender, 
			Date effectiveDate);

}
