package omis.vehicle.report;

import java.util.List;

import omis.offender.domain.Offender;

/**
 * Report service for offender vehicle summary.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Aug 7, 2014)
 * @since OMIS 3.0
 */
public interface OffenderVehicleSummaryReportService {

	/**
	 * Returns the list of offender vehicle summary.
	 * 
	 * @param offender offender
	 * @return list of offender vehicle summary
	 */

	List<OffenderVehicleSummary> findByOffender(Offender offender); 

}