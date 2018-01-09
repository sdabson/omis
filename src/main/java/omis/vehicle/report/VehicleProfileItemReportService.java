package omis.vehicle.report;

import java.util.Date;

import omis.offender.domain.Offender;

/** Report service for vehicle profile item.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 18, 2016)
 * @since OMIS 3.0 */
public interface VehicleProfileItemReportService {
	/** Find vehicle count by offender and date.
	 * @param offender - offender.
	 * @param effectiveDate - effective date.
	 * @return count. */
	Integer findVehicleCountByOffenderAndDate(Offender offender, 
			Date effectiveDate);
}
