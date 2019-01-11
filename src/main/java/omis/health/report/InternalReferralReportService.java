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

import java.util.List;

import omis.facility.domain.Facility;

/** Service to report referrals.
 * @author Ryan Johns
 * @author Yidong Li
 * @version 0.1.1 (Oct 23, 2018)
 * @since OMIS 3.0 */
public interface InternalReferralReportService {

	/** Returns scheduled referrals for the facility.
	 * @param facility facility.
	 * @return scheduled referrals for the facility. */
	List<ScheduledInternalReferralSummary> findScheduledInternalReferrals(
			Facility facility);

	/** Returns resolved referrals for the facility.
	 * @param facility facility.
	 * @return resolved referrals for the facility. */
	List<ResolvedInternalReferralSummary> findResolvedInternalReferrals(Facility facility);
	
	/** Returns the count of scheduled referrals for the facility.
	 * @param facility facility.
	 * @return scheduled referrals for the facility. */
	long countScheduledInternalReferrals(Facility facility);

	/** Returns the count of resolved referrals for the facility.
	 * @param facility facility.
	 * @return resolved referrals for the facility. */
	long countResolvedInternalReferrals(Facility facility);
}
