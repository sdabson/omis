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
package omis.earlyreleasetracking.report;

import java.util.Date;
import java.util.List;

import omis.earlyreleasetracking.domain.EarlyReleaseStatusCategory;
import omis.offender.domain.Offender;

/**
 * Early Release Request Summary Report Service.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 11, 2019)
 *@since OMIS 3.0
 *
 */
public interface EarlyReleaseRequestSummaryReportService {
	
	/**
	 * Returns a list of Early Release Request Summaries by the specified
	 * Offender.
	 * 
	 * @param offender Offender
	 * @return List of Early Release Request Summaries by the specified
	 * Offender.
	 */
	List<EarlyReleaseRequestSummary> findByOffender(Offender offender);
	
	/**
	 * Returns a list of Early Release Request Summaries within the specified
	 * request dates and for offenders with Parole Eligibilities within the
	 * specified eligibility dates and with the specified Early Release Status
	 * Category.
	 * 
	 * @param requestStartDate Request Start Date
	 * @param requestEndDate Request End Date
	 * @param requestDate Request Date
	 * @param eligibilityStartDate Eligibility Start Date
	 * @param eligibilityEndDate Eligibility End Date
	 * @param eligibilityDate Eligibility Date
	 * @param earlyReleaseStatusCategory Early Release Status Category
	 * @return List of Early Release Request Summaries.
	 */
	List<EarlyReleaseRequestSummary> findByDatesWithStatus(
			Date requestStartDate, Date requestEndDate,
			Date requestDate, Date eligibilityStartDate,
			Date eligibilityEndDate, Date eligibilityDate,
			EarlyReleaseStatusCategory earlyReleaseStatusCategory);
}
