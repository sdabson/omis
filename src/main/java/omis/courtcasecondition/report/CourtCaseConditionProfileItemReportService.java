package omis.courtcasecondition.report;

import java.util.Date;

import omis.offender.domain.Offender;

/** Report service for court case condition profile item.
 * @author Trevor Isles
 * @version 0.1.0 (August 3, 2017)
 * @since OMIS 3.0
 */
public interface CourtCaseConditionProfileItemReportService {
	
	/** Finds court case condition profile item summary by offender.
	 * @param offender - offender
	 * @return court case condition profile item summary.
	 */
	Integer findCourtCaseConditionsByOffenderAndEffectiveDate(
			Offender offender, Date date);

}
