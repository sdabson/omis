package omis.presentenceinvestigation.report;

import omis.offender.domain.Offender;

/**
 * PresentenceInvestigationRequestProfileItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Dec 12, 2016)
 *@since OMIS 3.0
 *
 */
public interface PresentenceInvestigationRequestProfileItemReportService {
	
	/**
	 * Returns a count of presentence investigation requests by offender
	 * @param offender - Offender
	 * @return Integer - count of presentence investigation requests by offender
	 */
	Integer findPresentenceInvestigationRequestCountByOffender(
			Offender offender);

}
