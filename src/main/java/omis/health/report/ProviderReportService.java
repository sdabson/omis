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

import omis.facility.domain.Facility;

/** Report service for health providers.
 * @author Ryan Johns
 * @author Yidong Li
 * @version 0.1.0 (Oct 23, 2018)
 * @since OMIS 3.0 */
public interface ProviderReportService {
	/** list health providers summary.
	 * @param facility facility.
	 * @param startDate start date.
	 * @param endDate end date.
	 * @return provider summary. */
	List<ProviderSummary> findHealthProviders(Facility facility,
			Date startDate, Date endDate);

	/** list health provider summaries.
	 * @param facility facility.
	 * @param date date.
	 * @return provider summaries. */
	List<ProviderSummary> findHealthProviders(Facility facility,
			Date date);
}
