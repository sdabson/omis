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
import omis.offender.domain.Offender;

/**
 * Report service for authorized referral summaries.
 * 
 * @author Stephen Abson
 * @author Yidong Li
 * @version 0.1.0 (Oct 22, 2018)
 * @since OMIS 3.0
 */
public interface AuthorizedReferralSummaryReportService {

	/**
	 * Returns unscheduled authorized referral summaries by facility.
	 * 
	 * @param facility facility
	 * @return unscheduled authorized referral summaries by facility
	 */
	List<AuthorizedReferralSummary> findUnscheduledByFacility(
			Facility facility);
	
	/**
	 * Returns unscheduled authorized referral summaries by offender.
	 * 
	 * @param offender offender
	 * @return unscheduled authorized referral summaries by offender
	 */
	List<AuthorizedReferralSummary> findUnscheduledByOffender(
			Offender offender);
	
	/**
	 * Returns the count of unscheduled authorized referral summaries
	 * by facility.
	 * 
	 * @param facility facility
	 * @return coutn of unscheduled authorized referral summaries by facility
	 */
	long countUnscheduledByFacility(Facility facility);
	
	/**
	 * Returns the count of unscheduled authorized referral summaries
	 * by offender.
	 * 
	 * @param offender offender
	 * @return count of unscheduled authorized referral summaries by offender
	 */
	long countUnscheduledByOffender(Offender offender);
}