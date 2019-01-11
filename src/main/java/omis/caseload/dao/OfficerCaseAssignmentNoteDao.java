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
package omis.caseload.dao;

import java.util.Date;
import java.util.List;

import omis.caseload.domain.OfficerCaseAssignment;
import omis.caseload.domain.OfficerCaseAssignmentNote;
import omis.dao.GenericDao;

/**
 * Data access object for officer case assignment note.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Sep 7, 2018)
 * @since OMIS 3.0
 */
public interface OfficerCaseAssignmentNoteDao 
		extends GenericDao<OfficerCaseAssignmentNote> {

	/**
	 * Returns the officer case assignment note for the specified officer case 
	 * assignment, description and date.
	 * 
	 * @param officerCaseAssignment officer case assignment
	 * @param description description
	 * @param date date
	 * @return officer case assignment note
	 */
	OfficerCaseAssignmentNote find(OfficerCaseAssignment officerCaseAssignment, 
			String description, Date date);
	
	/**
	 * Returns the officer case assignment note for the specified officer case 
	 * assignment, description and date excluding the specified officer case 
	 * assignment note.
	 * 
	 * @param officerCaseAssignment officer case assignment
	 * @param description description
	 * @param date date
	 * @param excludedNote excluded officer case assignment note
	 * @return officer case assignment note
	 */
	OfficerCaseAssignmentNote findExcluding(
			OfficerCaseAssignment officerCaseAssignment, String description, 
			Date date, OfficerCaseAssignmentNote excludedNote);
	
	/**
	 * Returns a list of officer case assignment notes for the specified officer 
	 * case assignment.
	 * @param officerCaseAssignment officer case assignment
	 * @return list of officer case assignment notes
	 */
	List<OfficerCaseAssignmentNote> findByOfficerCaseAssignment(
			OfficerCaseAssignment officerCaseAssignment);
}