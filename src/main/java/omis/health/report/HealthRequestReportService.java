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
import omis.health.domain.HealthRequestCategory;
import omis.offender.domain.Offender;

/**
 * Service to report health requests.
 *
 * @author Stephen Abson
 * @author Yidong Li
 * @version 0.1.0 (Oct 22, 2018)
 * @since OMIS 3.0
 */
public interface HealthRequestReportService {

	/**
	 * Returns open health request summaries by category for the facility.
	 *
	 * @param facility facility
	 * @param effectiveDate effective date
	 * @param categories categories
	 * @return open requests by category for facility
	 */
	List<HealthRequestSummary> findOpenByCategory(Facility facility,
			Date effectiveDate,	HealthRequestCategory... categories);

	/**
	 * Returns open health request summaries for the facility.
	 *
	 * @param facility facility
	 * @param effectiveDate effective date
	 * @return open requests for facility
	 */
	List<HealthRequestSummary> findOpen(Facility facility,
			Date effectiveDate);
	
	/**
	 * Returns resolved health request summaries for the facility by category.
	 * 
	 * @param facility facility
	 * @param effectiveDate effective date
	 * @param categories categories
	 * @return resolved health request summaries for facility by category
	 */
	List<HealthRequestSummary> findResolvedByCategory(Facility facility,
			Date effectiveDate, HealthRequestCategory... categories);
	
	/**
	 * Returns resolved health request summaries for facility.
	 * 
	 * @param facility facility
	 * @param effectiveDate effective date
	 * @return resolved health request summaries for facility
	 */
	List<HealthRequestSummary> findResolved(Facility facility,
			Date effectiveDate);
	
	/**
	 * Returns health request summaries by category for facility.
	 * 
	 * @param facility facility
	 * @param effectiveDate effective date
	 * @param categories categories
	 * @return health request summaries by category
	 */
	List<HealthRequestSummary> findByCategory(
			Facility facility, Date effectiveDate,
			HealthRequestCategory... categories);
	
	/**
	 * Returns health request summaries for facility.
	 * 
	 * @param facility facility
	 * @param effectiveDate effective date
	 * @return health request summaries for facility
	 */
	List<HealthRequestSummary> find(Facility facility, Date effectiveDate);

	/**
	 * Returns open health request summaries for offender.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return open health request summaries for offender
	 */
	List<HealthRequestSummary> findOpenByOffender(Offender offender,
			Date effectiveDate);
	
	/**
	 * Returns the count of open health request summaries by category
	 * for the facility.
	 *
	 * @param facility facility
	 * @param categories categories
	 * @return the count of open requests by category for facility
	 */
	long countOpenByCategory(Facility facility,
		HealthRequestCategory... categories);

	/**
	 * Returns the count of open health request summaries for the
	 * facility.
	 *
	 * @param facility facility
	 * @param effectiveDate effective date
	 * @return the count of open requests for facility
	 */
	long countOpen(Facility facility,
		Date effectiveDate);
	
	/**
	 * Returns the count of resolved health request summaries for the
	 * facility by category.
	 * 
	 * @param facility facility
	 * @param effectiveDate effective date
	 * @param categories categories
	 * @return the count of resolved health request summaries for
	 * facility by category
	 */
	long countResolvedByCategory(Facility facility,
		Date effectiveDate, HealthRequestCategory... categories);
	
	/**
	 * Returns the count of resolved health request summaries for
	 * facility.
	 * 
	 * @param facility facility
	 * @param effectiveDate effective date
	 * @return the count of resolved health request summaries for facility
	 */
	long countResolved(Facility facility,
			Date effectiveDate);
	
	/**
	 * Returns the count of health request summaries by category for
	 * facility.
	 * 
	 * @param facility facility
	 * @param effectiveDate effective date
	 * @param categories categories
	 * @return the count of health request summaries by category
	 */
	long countByCategory(
		Facility facility, Date effectiveDate,
		HealthRequestCategory... categories);
	
	/**
	 * Returns the count of health request summaries for facility.
	 * 
	 * @param facility facility
	 * @param effectiveDate effective date
	 * @return the count of health request summaries for facility
	 */
	long count(Facility facility, Date effectiveDate);

	/**
	 * Returns the count of open health request summaries for offender.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return the count of open health request summaries for offender
	 */
	long countOpenByOffender(Offender offender,
			Date effectiveDate);
}