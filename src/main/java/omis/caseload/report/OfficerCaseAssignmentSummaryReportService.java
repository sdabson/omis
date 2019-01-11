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
package omis.caseload.report;

import java.util.Date;
import java.util.List;

import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;

/**
 * Officer case assignment summary report service.
 * 
 * @author Josh Divine
 * @author Annie Wahl
 * @version 0.1.3 (Jan 4, 2019)
 * @since OMIS 3.0
 */
public interface OfficerCaseAssignmentSummaryReportService {

	/**
	 * Returns a list of officer case assignment summaries for the specified 
	 * offender.
	 * 
	 * @param offender offender
	 * @return list of officer case assignment summaries
	 */
	List<OfficerCaseAssignmentSummary> findByOffender(Offender offender);
	
	/**
	 * Returns a list of officer case assignment summaries for the specified 
	 * user account and effective date.
	 * 
	 * @param userAccount user account
	 * @param effectiveDate effective date
	 * @return list of officer case assignment summaries
	 */
	List<OfficerCaseAssignmentSummary> findByUser(UserAccount userAccount, 
			Date effectiveDate);
	
	/**
	 * Returns a list of officer case assignment summaries for the specified 
	 * user account and date range.
	 * 
	 * @param userAccount user account
	 * @param startDate start date
	 * @param endDate end date
	 * @return list of officer case assignment summaries
	 */
	List<OfficerCaseAssignmentSummary> findByUserOnDateRange(
			UserAccount userAccount, Date startDate, Date endDate);

	/**
	 * Returns a list of officer case assignment summaried for the specified
	 * offender within the given date range.
	 * 
	 * @param offender offender
	 * @param startDate start date
	 * @param endDate end date
	 * @return list of officer case assignment summaried for the specified
	 * offender within the given date range.
	 */
	List<OfficerCaseAssignmentSummary> findByOffenderOnDateRange(
			Offender offender, Date startDate, Date endDate);
}