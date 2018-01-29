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
package omis.paroleeligibility.report;

import omis.offender.domain.Offender;

/**
 * Profile item report service for parole eligibility.
 *
 * @author Trevor Isles
 * @version 0.1.0 (Jan 9, 2018)
 * @since OMIS 3.0
 */
public interface ParoleEligibilityProfileItemReportService {
	
	
	/** Finds parole eligibility count by offender.
	 * @param offender - offender
	 * @return count
	 */
	Long findParoleEligibilityCountByEligibilities(Offender offender);

}
