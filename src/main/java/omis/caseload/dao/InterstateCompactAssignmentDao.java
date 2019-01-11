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

import omis.caseload.domain.InterstateCompactAssignment;
import omis.caseload.domain.OfficerCaseAssignment;
import omis.dao.GenericDao;

/**
 * Data access object for interstate compact assignment.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Sep 7, 2018)
 * @since OMIS 3.0
 */
public interface InterstateCompactAssignmentDao 
		extends GenericDao<InterstateCompactAssignment>{

	/**
	 * Returns the interstate compact assignment for the specified officer case 
	 * assignment.
	 * 
	 * @param officerCaseAssignment officer case assignment
	 * @return interstate compact assignment
	 */
	InterstateCompactAssignment find(OfficerCaseAssignment officerCaseAssignment);
	
	/**
	 * Returns the interstate compact assignment for the specified officer case 
	 * assignment excluding the specified interstate compact assignment.
	 * 
	 * @param officerCaseAssignment officer case assignment
	 * @param excludedInterstateCompactAssignment excluded interstate compact 
	 * assignment
	 * @return interstate compact assignment
	 */
	InterstateCompactAssignment findExcluding(
			OfficerCaseAssignment officerCaseAssignment, 
			InterstateCompactAssignment excludedInterstateCompactAssignment);
	
	/**
	 * Returns the interstate compact assignment for the specified officer case 
	 * assignment.
	 * 
	 * @param officerCaseAssignment officer case assignment
	 * @return interstate compact assignment
	 */
	InterstateCompactAssignment findByOfficerCaseAssignment(
			OfficerCaseAssignment officerCaseAssignment);
}