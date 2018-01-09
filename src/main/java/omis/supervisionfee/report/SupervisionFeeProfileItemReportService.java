package omis.supervisionfee.report;

import java.util.Date;

import omis.offender.domain.Offender;

/** Report service for supervision fee profile item.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 17, 2016)
 * @since OMIS 3.0 */
public interface SupervisionFeeProfileItemReportService {
	/** finds supervision fee count by offender and date.
	 * @param offender - offender.
	 * @param effectiveDate - effective date.
	 * @return count. */
	Integer findSupervisionFeeCountByOffenderAndDate(Offender offender, 
			Date effectiveDate);

}
