/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.health.report;

import java.util.Date;
import java.util.List;

import omis.datatype.DateRange;
import omis.facility.domain.Facility;
import omis.health.domain.ProviderAssignment;

/** Report service for provider schedule summary.
 * @author Ryan Johns
 * @author Yidong Li
 * @version 0.1.0 (Oct 22, 2018)
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
