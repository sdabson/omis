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
package omis.program.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.program.domain.Program;
import omis.program.domain.ProgramPlacement;

/**
 * Data access object for program placements.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Dec 9, 2015)
 * @since OMIS 3.0
 */
public interface ProgramPlacementDao
		extends GenericDao<ProgramPlacement> {

	/**
	 * Returns program placement.
	 * 
	 * @param offender offender
	 * @param dateRange date range
	 * @param program program
	 * @return program placement
	 */
	ProgramPlacement find(Offender offender, DateRange dateRange,
			Program program);
	
	/**
	 * Returns program placement other than those excluded.
	 * 
	 * @param offender offender
	 * @param dateRange date range
	 * @param program program
	 * @param excludedProgramPlacements program placements to exclude
	 * @return program placement other than those excluded
	 */
	ProgramPlacement findExcluding(Offender offender, DateRange dateRange,
			Program program, ProgramPlacement... excludedProgramPlacements);
	
	/**
	 * Returns program placements for offender.
	 * 
	 * @param offender offender
	 * @return program placements for offender
	 */
	List<ProgramPlacement> findByOffender(Offender offender);
	
	/**
	 * Returns program placement for offender on date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return program placement for offender on date
	 */
	ProgramPlacement findByOffenderOnDate(Offender offender, Date date);
	
	/**
	 * Returns number of program placements for offender between dates that are 
	 * not excluded.
	 * 
	 * @param offender offender
	 * @param startDate start date
	 * @param endDate end date
	 * @param excludedProgramPlacements program placements to exclude
	 * @return number of program placements for offender between dates
	 */
	long countExcluding(Offender offender, Date startDate, Date endDate,
			ProgramPlacement... excludedProgramPlacements);
	
	/**
	 * Returns number of program placements for an offender after the specified 
	 * date that are not excluded.
	 * 
	 * @param offender offender
	 * @param startDate start date
	 * @param excludedProgramPlacement excluded program placement
	 * @return number of program placements for an offender after the specified 
	 * date
	 */
	long countAfterDateExcluding(Offender offender, Date startDate, 
			ProgramPlacement excludedProgramPlacement);
}