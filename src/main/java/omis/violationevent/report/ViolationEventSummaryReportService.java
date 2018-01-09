package omis.violationevent.report;

import java.util.List;

import omis.offender.domain.Offender;

/**
 * ViolationEventSummaryReportService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jan 18, 2017)
 *@since OMIS 3.0
 *
 */
public interface ViolationEventSummaryReportService {
	
	
	
	/**
	 * Returns a list of ViolationEventSummaries by specified offender
	 * @param offender - Offender
	 * @return list of ViolationEventSummaries by specified offender
	 */
	List<ViolationEventSummary> findByOffender(Offender offender);
	
}
