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
import omis.health.domain.HealthAppointmentStatus;
import omis.health.domain.HealthRequest;
import omis.health.exception.HealthRequestFollowsUpMultipleReferralsException;
import omis.offender.domain.Offender;

/**
 * Report service for referral summaries.
 * 
 * @author Stephen Abson
 * @author Yidong Li
 * @version 0.1.0 (Oct 22, 2018)
 * @since OMIS 3.0
 */
public interface ReferralSummaryReportService {

	/**
	 * Returns the internal referral that resulted in the action request.
	 * 
	 * <p>If no such referral exists, return {@code null}.
	 * 
	 * @param actionRequest action request
	 * @param effectiveDate effective date
	 * @return referral that resulted in action request
	 */
	ReferralSummary findInternalForActionRequest(HealthRequest actionRequest,
			Date effectiveDate);
	
	/**
	 * Returns the external referral that resulted in the action request.
	 * 
	 * <p>If no such referral exists, return {@code null}.
	 * 
	 * @param actionRequest action request
	 * @param effectiveDate effective date
	 * @return referral that resulted in action request
	 */
	ReferralSummary findExternalForActionRequest(HealthRequest actionRequest,
			Date effectiveDate);
	
	/**
	 * Returns the referral that resulted in the action request.
	 * 
	 * <p>If no such referral exists, return {@code null}.
	 * 
	 * @param actionRequest action request
	 * @param effectiveDate effective date
	 * @return referral that resulted in action request
	 * @throws HealthRequestFollowsUpMultipleReferralsException if more than
	 * one referral resulted in the action request
	 */
	ReferralSummary findForActionRequest(HealthRequest actionRequest,
			Date effectiveDate)
					throws HealthRequestFollowsUpMultipleReferralsException;
	/**
	 * Returns referral summaries by facility.
	 * 
	 * @param facility facility
	 * @param startDate start date
	 * @param endDate end date
	 * @param types referral types
	 * @param statuses appointment statuses
	 * @param effectiveDate effective date
	 * @return referral summaries by facility
	 */
	List<ReferralSummary> findByFacility(Facility facility,
			Date startDate, Date endDate,
			ReferralType[] types, HealthAppointmentStatus[] statuses,
			Date effectiveDate);
	
	/**
	 * Returns referral summaries by offender.
	 * 
	 * @param offender offender
	 * @param startDate start date
	 * @param endDate end date
	 * @param types referral types
	 * @param statuses appointment statuses
	 * @param effectiveDate effective date
	 * @return referral summaries by offender
	 */
	List<ReferralSummary> findByOffender(Offender offender,
			Date startDate, Date endDate,
			ReferralType[] types, HealthAppointmentStatus[] statuses,
			Date effectiveDate);
	
	/**
	 * Returns the count of referral summaries by facility.
	 * 
	 * @param facility facility
	 * @param startDate start date
	 * @param endDate end date
	 * @param types referral types
	 * @param statuses appointment statuses
	 * @return the count of referral summaries by facility
	 */
	long countByFacility(Facility facility,
		Date startDate, Date endDate,
		ReferralType[] types, HealthAppointmentStatus[] statuses);
	
	/**
	 * Returns the count of referral summaries by offender.
	 * 
	 * @param offender offender
	 * @param startDate start date
	 * @param endDate end date
	 * @param types referral types
	 * @param statuses appointment statuses
	 * @return the count of referral summaries by offender
	 */
	long countByOffender(Offender offender,
		Date startDate, Date endDate,
		ReferralType[] types, HealthAppointmentStatus[] statuses);
}