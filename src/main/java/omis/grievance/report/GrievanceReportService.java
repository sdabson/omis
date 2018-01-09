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
package omis.grievance.report;

import java.util.Date;
import java.util.List;

import omis.grievance.domain.GrievanceLocation;
import omis.grievance.domain.GrievanceSubject;
import omis.offender.domain.Offender;
import omis.offender.report.OffenderSummary;

/**
 * Report service for grievances.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 22, 2015)
 * @since OMIS 3.0
 */
public interface GrievanceReportService {

	/**
	 * Returns summaries of grievances by offender.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return summaries of grievances by offender
	 */
	List<GrievanceSummary> summarizeByOffender(
			Offender offender, Date effectiveDate);
	
	/**
	 * Returns summaries of grievances by location.
	 * 
	 * @param location location
	 * @param effectiveDate effective date
	 * @return summaries of grievances by location
	 */
	List<GrievanceSummary> summarizeByLocation(
			GrievanceLocation location, Date effectiveDate);
	
	/**
	 * Returns summaries of offenders based on query.
	 * 
	 * @param query query
	 * @return summaries of offenders based on query
	 */
	List<OffenderSummary> searchOffenders(String query);
	
	/**
	 * Returns grievance locations
	 * 
	 * @return grievance locations
	 */
	List<GrievanceLocation> findGrievanceLocations();
	
	/**
	 * Returns grievance subjects.
	 * 
	 * @return grievance subjects
	 */
	List<GrievanceSubject> findGrievanceSubjects();
}