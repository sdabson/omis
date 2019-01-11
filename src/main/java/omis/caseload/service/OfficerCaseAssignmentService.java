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
package omis.caseload.service;

import java.util.Date;
import java.util.List;

import omis.caseload.domain.InterstateCompactAssignment;
import omis.caseload.domain.InterstateCompactCorrectionalStatus;
import omis.caseload.domain.InterstateCompactEndReasonCategory;
import omis.caseload.domain.InterstateCompactTypeCategory;
import omis.caseload.domain.OfficerCaseAssignment;
import omis.caseload.domain.OfficerCaseAssignmentNote;
import omis.caseload.domain.SupervisionLevelCategory;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.location.domain.Location;
import omis.offender.domain.Offender;
import omis.region.domain.State;
import omis.user.domain.UserAccount;

/**
 * Officer case assignment service.
 * 
 * @author Josh Divine
 * @version 0.1.2 (Sep 7, 2018)
 * @since OMIS 3.0
 */
public interface OfficerCaseAssignmentService {

	/**
	 * Creates a new officer case assignment.
	 * 
	 * @param offender offender
	 * @param officer officer
	 * @param startDate start date
	 * @param endDate end date
	 * @param supervisionOffice supervision office
	 * @param supervisionLevel supervision level category 
	 * @return officer case assignment
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if date conflict exists
	 */
	OfficerCaseAssignment createOfficerCaseAssignment(Offender offender, 
			UserAccount officer, Date startDate, Date endDate, 
			Location supervisionOffice, 
			SupervisionLevelCategory supervisionLevel) 
					throws DuplicateEntityFoundException, DateConflictException;
	
	/**
	 * Updates an existing officer case assignment.
	 * 
	 * @param officerCaseAssignment officer case assignment
	 * @param officer officer
	 * @param startDate start date
	 * @param endDate end date
	 * @param supervisionOffice supervision office
	 * @param supervisionLevel supervision level category
	 * @return officer case assignment
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if date conflict exists
	 */
	OfficerCaseAssignment updateOfficerCaseAssignment(
			OfficerCaseAssignment officerCaseAssignment, UserAccount officer, 
			Date startDate, Date endDate, Location supervisionOffice,
			SupervisionLevelCategory supervisionLevel) 
					throws DuplicateEntityFoundException, DateConflictException;
	
	/**
	 * Removes the specified officer case assignment.
	 * 
	 * @param officerCaseAssignment officer case assignment
	 */
	void removeOfficerCaseAssignment(
			OfficerCaseAssignment officerCaseAssignment);
	
	/**
	 * Returns a list of supervision level categories.
	 * 
	 * @return list of supervision level categories
	 */
	List<SupervisionLevelCategory> findSupervisionLevelCategories();
	
	/**
	 * Returns a list of supervisory organization locations.
	 * 
	 * @return list of supervisory organization locations
	 */
	List<Location> findCommunitySupervisionOfficeLocations();
	
	/**
	 * Returns a list of Community Supervision and Institutional Probation
	 * and Parole Offices.
	 * 
	 * @return list of Community Supervision and Institutional Probation and
	 * Parole Offices
	 */
	List<Location> 
		findCommunitySupervisionAndInstitutionalProbationParoleOffices();
	
	/**
	 * Returns a list of Institutional Probation and Parole Offices.
	 * 
	 * @return list of Institutional Probation and Parole Offices
	 */
	List<Location> findInstitutionalProbationAndParoleOffices();

	/**
	 * Returns the user account for the specified user name.
	 * 
	 * @param username user name
	 * @return user account
	 */
	UserAccount findUserAccountByUsername(String username);
	
	/**
	 * Returns a list of active officer case assignments for the specified user 
	 * account and date.
	 * 
	 * @param officer user account
	 * @param effectiveDate effective date
	 * @return list of officer case assignments
	 */
	List<OfficerCaseAssignment> findOfficerCaseAssignmentsByOfficerAndDate(
			UserAccount officer, Date effectiveDate);
	
	/**
	 * Creates a new interstate compact assignment.
	 * 
	 * @param officerCaseAssignment officer case assignment
	 * @param jurisdiction jurisdiction
 	 * @param jurisdictionStateId jurisdiction state id
	 * @param projectedEndDate projected end date
	 * @param interstateCompactStatus interstate compact correctional status
	 * @param endReason interstate compact end reason category
	 * @param interstateCompactType interstate compact type category
	 * @return interstate compact assignment
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	InterstateCompactAssignment createInterstateCompactAssignment(
			OfficerCaseAssignment officerCaseAssignment, State jurisdiction, 
			String jurisdictionStateId, Date projectedEndDate, 
			InterstateCompactCorrectionalStatus interstateCompactStatus, 
			InterstateCompactEndReasonCategory endReason, 
			InterstateCompactTypeCategory interstateCompactType) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates an existing interstate compact assignment.
	 * 
	 * @param interstateCompactAssignment interstate compact assignment
	 * @param officerCaseAssignment officer case assignment
	 * @param jurisdiction jurisdiction
 	 * @param jurisdictionStateId jurisdiction state id
	 * @param projectedEndDate projected end date
	 * @param interstateCompactStatus interstate compact correctional status
	 * @param endReason interstate compact end reason category
	 * @param interstateCompactType interstate compact type category
	 * @return interstate compact assignment
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	InterstateCompactAssignment updateInterstateCompactAssignment(
			InterstateCompactAssignment interstateCompactAssignment, 
			OfficerCaseAssignment officerCaseAssignment, State jurisdiction, 
			String jurisdictionStateId, Date projectedEndDate, 
			InterstateCompactCorrectionalStatus interstateCompactStatus, 
			InterstateCompactEndReasonCategory endReason, 
			InterstateCompactTypeCategory interstateCompactType) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified interstate compact assignment.
	 * 
	 * @param interstateCompactAssignment interstate compact assignment
	 */
	void removeInterstateCompactAssignment(
			InterstateCompactAssignment interstateCompactAssignment);
	
	/**
	 * Returns a list of interstate compact end reason categories.
	 * 
	 * @return list of interstate compact end reason categories
	 */
	List<InterstateCompactEndReasonCategory> 
			findInterstateCompactEndCategories();
	
	/**
	 * Returns a list of interstate compact correctional statuses.
	 * 
	 * @return list of interstate compact correctional statuses
	 */
	List<InterstateCompactCorrectionalStatus> 
			findInterstateCompactCorrectionalStatuses();
	
	/**
	 * Returns a list of interstate compact type categories.
	 * 
	 * @return list of interstate compact type categories
	 */
	List<InterstateCompactTypeCategory> findInterstateCompactTypeCategories();
	
	/**
	 * Returns the interstate compact assignment for the specified officer case 
	 * assignment.
	 * 
	 * @param officerCaseAssignment officer case assignment
	 * @return interstate compact assignment
	 */
	InterstateCompactAssignment 
			findInterstateCompactAssignmentByOfficerCaseAssignment(
					OfficerCaseAssignment officerCaseAssignment);
	
	/**
	 * Creates a new officer case assignment note.
	 * 
	 * @param officerCaseAssignment officer case assignment
	 * @param description description
	 * @param date date
	 * @return officer case assignment note
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	OfficerCaseAssignmentNote createOfficerCaseAssignmentNote(
			OfficerCaseAssignment officerCaseAssignment, String description, 
			Date date) throws DuplicateEntityFoundException;
	
	/**
	 * Updates an existing officer case assignment note.
	 * 
	 * @param officerCaseAssignmentNote officer case assignment note
	 * @param description description
	 * @param date date
	 * @return officer case assignment note
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	OfficerCaseAssignmentNote updateOfficerCaseAssignmentNote(
			OfficerCaseAssignmentNote officerCaseAssignmentNote, 
			String description, Date date) throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified officer case assignment note.
	 * 
	 * @param officerCaseAssignmentNote officer case assignment note
	 */
	void removeOfficerCaseAssignmentNote(
			OfficerCaseAssignmentNote officerCaseAssignmentNote);
	
	/**
	 * Returns a list of states.
	 * 
	 * @return list of states
	 */
	List<State> findAllStatesInHomeCountry();
	
	/**
	 * Returns a list of officer case assignment notes for the specified officer 
	 * case assignment.
	 * 
	 * @param officerCaseAssignment officer case assignment
	 * @return list of officer case assignment notes
	 */
	List<OfficerCaseAssignmentNote> 
			findOfficerCaseAssignmentNotesByOfficerCaseAssignment(
					OfficerCaseAssignment officerCaseAssignment);
}
