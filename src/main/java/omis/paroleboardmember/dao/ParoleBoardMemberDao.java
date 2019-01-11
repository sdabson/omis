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
package omis.paroleboardmember.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.paroleboardmember.domain.ParoleBoardMember;
import omis.staff.domain.StaffAssignment;

/**
 * Data access object for parole board member.
 * 
 * @author Josh Divine
 * @version 0.1.2 (Nov 28, 2018)
 * @since OMIS 3.0
 */
public interface ParoleBoardMemberDao extends GenericDao<ParoleBoardMember> {

	/**
	 * Returns the parole board member matching the specified staff assignment 
	 * and start date.
	 * 
	 * @param staffAssignment staff assignment
	 * @param startDate start date
	 * @return parole board member
	 */
	ParoleBoardMember find(StaffAssignment staffAssignment, Date startDate);
	
	/**
	 * Returns the parole board member matching the specified staff assignment 
	 * and start date, excluding the specified parole board member.
	 * 
	 * @param staffAssignment staff assignment
	 * @param startDate start date
	 * @param excludedParoleBoardMember parole board member
	 * @return parole board member
	 */
	ParoleBoardMember findExcluding(StaffAssignment staffAssignment, 
			Date startDate, ParoleBoardMember excludedParoleBoardMember);
	
	/**
	 * Returns a list of parole board members on the effective date.
	 * 
	 * @param effectiveDate effective date
	 * @return list of parole board members
	 */
	List<ParoleBoardMember> findBoardMembersByDate(Date effectiveDate);

	/**
	 * Returns the parole board member for the specified staff assignment during
	 *  the specified range.
	 * 
	 * @param staffAssignment staff assignment
	 * @param startDate start date
	 * @param endDate end date
	 * @return parole board member
	 */
	List<ParoleBoardMember> findExistingWithinDateRange(
			StaffAssignment staffAssignment, Date startDate, Date endDate);
	
	/**
	 * Returns the parole board member for the specified staff assignment during
	 *  the specified range excluding the specified parole board member.
	 * 
	 * @param staffAssignment staff assignment
	 * @param startDate start date
	 * @param endDate end date
	 * @param excludedParoleBoardMember parole board member
	 * @return parole board member
	 */
	List<ParoleBoardMember> findExistingWithinDateRangeExcluding(
			StaffAssignment staffAssignment, Date startDate, Date endDate, 
			ParoleBoardMember excludedParoleBoardMember);
}