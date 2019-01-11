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
 * Report service for summaries of pending referral authorizations.
 * 
 * @author Stephen Abson
 * @author Yidong Li
 * @version 0.0.1 (Oct 22, 2018)
 * @since OMIS 3.0
 */
public interface PendingReferralAuthorizationSummaryReportService {

	/**
	 * Returns summaries of pending referral authorizations by facility.
	 * 
	 * @param facility facility
	 * @return summaries of pending referral authorizations by facility
	 */
	List<PendingReferralAuthorizationSummary> findByFacility(Facility facility);
	
	/**
	 * Returns summaries of pending referral authorizations by offender.
	 * 
	 * @param offender offender
	 * @return summaries of pending referral authorizations by offender
	 */
	List<PendingReferralAuthorizationSummary> findByOffender(Offender offender);
	
	/**
	 * Returns the count of pending referral authorizations by facility.
	 * 
	 * @param facility facility
	 * @return count of pending referral authorizations by facility
	 */
	long countByFacility(Facility facility);
	
	/**
	 * Returns the count of pending referral authorizations by offender.
	 * 
	 * @param offender offender
	 * @return count of pending referral authorizations by offender
	 */
	long countByOffender(Offender offender);
}