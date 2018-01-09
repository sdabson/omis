package omis.health.report;

import java.util.Date;
import java.util.List;

import omis.datatype.DateRange;
import omis.facility.domain.Facility;
import omis.health.domain.ProviderAssignment;

/** Report service for provider schedule summary.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 22, 2014)
 * @since OMIS 3.0 */
public interface ProviderScheduleReportService {

	/** Find provider schedule on date.
	 * @param providerAssignment provider assignment.
	 * @param day date.
	 * @return daily provider schedule summary. */
	DailyProviderScheduleSummary findOnDate(
			ProviderAssignment providerAssignment,
			Date day);

	/** Find provider schedules on date at facility.
	 * @param facility facility.
	 * @param day day.
	 * @return daily provider schedules. */
	List<DailyProviderScheduleSummary> findByFacilityOnDate(Facility facility,
			Date day);

	/** Find provider schedule on date range.
	 * @param providerAssignment provider assignment.
	 * @param dateRange date range.
	 * @return daily provider schedules. */
	List<DailyProviderScheduleSummary> findDuringDateRange(
			ProviderAssignment providerAssignment, DateRange dateRange);

	/** Find provider schedules on date range by facility.
	 * @param facility facility.
	 * @param dateRange date range.
	 * @return daily provider schedules. */
	List<DailyProviderScheduleSummary> findByFacilityDuringDateRange(
			Facility facility, DateRange dateRange);

}
