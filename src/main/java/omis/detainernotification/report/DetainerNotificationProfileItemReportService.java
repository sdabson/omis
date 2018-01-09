package omis.detainernotification.report;

import omis.offender.domain.Offender;

/**
 * DetainerNotificationProfileItemReportService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Dec 9, 2016)
 *@since OMIS 3.0
 *
 */
public interface DetainerNotificationProfileItemReportService {
	
	/**
	 * Finds detainer notifications count by offender
	 * @param offender - Offender
	 * @return Integer - count of detainer notifcations by offender
	 */
	Integer findDetainerNotificationCountByOffender(Offender offender);
	
	
}
