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
package omis.caseload.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.caseload.domain.InterstateCompactAssignment;
import omis.caseload.domain.InterstateCompactCorrectionalStatus;
import omis.caseload.domain.InterstateCompactEndReasonCategory;
import omis.caseload.domain.InterstateCompactTypeCategory;
import omis.caseload.domain.OfficerCaseAssignment;
import omis.caseload.domain.OfficerCaseAssignmentNote;
import omis.caseload.domain.SupervisionLevelCategory;
import omis.caseload.service.OfficerCaseAssignmentService;
import omis.caseload.service.delegate.InterstateCompactAssignmentDelegate;
import omis.caseload.service.delegate.InterstateCompactCorrectionalStatusDelegate;
import omis.caseload.service.delegate.InterstateCompactEndReasonCategoryDelegate;
import omis.caseload.service.delegate.InterstateCompactTypeCategoryDelegate;
import omis.caseload.service.delegate.OfficerCaseAssignmentDelegate;
import omis.caseload.service.delegate.OfficerCaseAssignmentNoteDelegate;
import omis.caseload.service.delegate.SupervisionLevelCategoryDelegate;
import omis.communitysupervision.domain.CommunitySupervisionOffice;
import omis.communitysupervision.service.delegate.CommunitySupervisionOfficeDelegate;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.ippo.domain.InstitutionalProbationAndParoleOffice;
import omis.ippo.service.delegate.InstitutionalProbationAndParoleOfficeDelegate;
import omis.location.domain.Location;
import omis.offender.domain.Offender;
import omis.region.domain.State;
import omis.region.service.delegate.StateDelegate;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;

/**
 * Implementation of officer case assignment service.
 * 
 * @author Josh Divine
 * @version 0.1.2 (Sep 7, 2018)
 * @since OMIS 3.0
 */
public class OfficerCaseAssignmentServiceImpl 
		implements OfficerCaseAssignmentService {
	
	private final OfficerCaseAssignmentDelegate officerCaseAssignmentDelegate;
	
	private final SupervisionLevelCategoryDelegate 
			supervisionLevelCategoryDelegate;
	
	private final CommunitySupervisionOfficeDelegate 
			communitySupervisionOfficeDelegate;
	
	private final UserAccountDelegate userAccountDelegate;
	
	private final InterstateCompactAssignmentDelegate 
			interstateCompactAssignmentDelegate;
	
	private final InterstateCompactCorrectionalStatusDelegate 
			interstateCompactCorrectionalStatusDelegate;
	
	private final InterstateCompactEndReasonCategoryDelegate 
			interstateCompactEndReasonCategoryDelegate;
	
	private final InterstateCompactTypeCategoryDelegate 
			interstateCompactTypeCategoryDelegate;
	
	private final OfficerCaseAssignmentNoteDelegate
			officerCaseAssignmentNoteDelegate;
	
	private final StateDelegate stateDelegate;
	
	private final InstitutionalProbationAndParoleOfficeDelegate
			institutionalProbationAndParoleOfficeDelegate;

	/**
	 * Instantiates an officer case assignment service implementation with the 
	 * specified delegates.
	 * 
	 * @param officerCaseAssignmentDelegate officer case assignment delegate
	 * @param supervisionLevelCategoryDelegate supervision level category 
	 * delegate
	 * @param locationDelegate location delegate
	 * @param communitySupervisionOfficeDelegate community supervision office 
	 * delegate
	 * @param userAccountDelegate user account delegate
	 * @param interstateCompactAssignmentDelegate interstate compact assignment 
	 * delegate
	 * @param interstateCompactCorrectionalStatusDelegate interstate compact 
	 * correctional status delegate
	 * @param interstateCompactEndReasonCategoryDelegate interstate compact end 
	 * reason category delegate
	 * @param interstateCompactTypeCategoryDelegate interstate compact type 
	 * category delegate
	 * @param officerCaseAssignmentNoteDelegate officer case assignment note 
	 * delegate
	 * @param stateDelegate state delegate
	 */
	public OfficerCaseAssignmentServiceImpl(
			final OfficerCaseAssignmentDelegate officerCaseAssignmentDelegate,
			final SupervisionLevelCategoryDelegate 
					supervisionLevelCategoryDelegate,
			final CommunitySupervisionOfficeDelegate 
					communitySupervisionOfficeDelegate,
			final UserAccountDelegate userAccountDelegate,
			final InterstateCompactAssignmentDelegate 
					interstateCompactAssignmentDelegate,
			final InterstateCompactCorrectionalStatusDelegate 
					interstateCompactCorrectionalStatusDelegate,
			final InterstateCompactEndReasonCategoryDelegate 
					interstateCompactEndReasonCategoryDelegate,
			final InterstateCompactTypeCategoryDelegate 
					interstateCompactTypeCategoryDelegate,
			final OfficerCaseAssignmentNoteDelegate
					officerCaseAssignmentNoteDelegate,
			final StateDelegate stateDelegate,
			final InstitutionalProbationAndParoleOfficeDelegate 
					institutionalProbationAndParoleOfficeDelegate) {
		this.officerCaseAssignmentDelegate = officerCaseAssignmentDelegate;
		this.supervisionLevelCategoryDelegate = 
				supervisionLevelCategoryDelegate;
		this.communitySupervisionOfficeDelegate = 
				communitySupervisionOfficeDelegate;
		this.userAccountDelegate = userAccountDelegate;
		this.interstateCompactAssignmentDelegate = 
				interstateCompactAssignmentDelegate;
		this.interstateCompactCorrectionalStatusDelegate = 
				interstateCompactCorrectionalStatusDelegate;
		this.interstateCompactEndReasonCategoryDelegate = 
				interstateCompactEndReasonCategoryDelegate;
		this.interstateCompactTypeCategoryDelegate = 
				interstateCompactTypeCategoryDelegate;
		this.officerCaseAssignmentNoteDelegate = 
				officerCaseAssignmentNoteDelegate;
		this.stateDelegate = stateDelegate;
		this.institutionalProbationAndParoleOfficeDelegate = 
				institutionalProbationAndParoleOfficeDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public OfficerCaseAssignment createOfficerCaseAssignment(
			final Offender offender, final UserAccount officer, 
			final Date startDate, final Date endDate, 
			final Location supervisionOffice, 
			final SupervisionLevelCategory supervisionLevel) 
					throws DuplicateEntityFoundException, DateConflictException {
		return this.officerCaseAssignmentDelegate.create(offender, officer, 
				startDate, endDate, supervisionOffice, supervisionLevel);
	}

	/** {@inheritDoc} */
	@Override
	public OfficerCaseAssignment updateOfficerCaseAssignment(
			final OfficerCaseAssignment officerCaseAssignment,
			final UserAccount officer, final Date startDate, 
			final  Date endDate, final Location supervisionOffice, 
			final SupervisionLevelCategory supervisionLevel) 
					throws DuplicateEntityFoundException, DateConflictException {
		return this.officerCaseAssignmentDelegate.update(officerCaseAssignment, 
				officerCaseAssignment.getOffender(), officer, startDate, 
				endDate, supervisionOffice, supervisionLevel);
	}

	/** {@inheritDoc} */
	@Override
	public void removeOfficerCaseAssignment(
			final OfficerCaseAssignment officerCaseAssignment) {
		this.officerCaseAssignmentDelegate.remove(officerCaseAssignment);
	}

	/** {@inheritDoc} */
	@Override
	public List<SupervisionLevelCategory> findSupervisionLevelCategories() {
		return this.supervisionLevelCategoryDelegate.findValid();
	}

	/** {@inheritDoc} */
	@Override
	public List<Location> findCommunitySupervisionOfficeLocations() {
		List<CommunitySupervisionOffice> offices = this
				.communitySupervisionOfficeDelegate.findAll();
		List<Location> locations = new ArrayList<>();
		for (CommunitySupervisionOffice office : offices) {
			locations.add(office.getLocation());
		}
		return locations;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Location> findInstitutionalProbationAndParoleOffices() {
		List<InstitutionalProbationAndParoleOffice> ippoOffices 
				= this.institutionalProbationAndParoleOfficeDelegate.findAll();
		List<Location> ippoLocations = new ArrayList<>();
		for (InstitutionalProbationAndParoleOffice ippoOffice : ippoOffices) {
			ippoLocations.add(ippoOffice.getLocation());
		}
		return ippoLocations;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Location> 
		findCommunitySupervisionAndInstitutionalProbationParoleOffices() {
		List<Location> locations = new ArrayList<>();
		
		List<InstitutionalProbationAndParoleOffice> ippoOffices 
				= this.institutionalProbationAndParoleOfficeDelegate.findAll();
		List<CommunitySupervisionOffice> comOffices 
				= this.communitySupervisionOfficeDelegate.findAll();
		
		for (CommunitySupervisionOffice office : comOffices) {
			locations.add(office.getLocation());
			}
		for (InstitutionalProbationAndParoleOffice ippoOffice : ippoOffices) {
			locations.add(ippoOffice.getLocation()); {	
			}
		}
		return locations;
	}

	/** {@inheritDoc} */
	@Override
	public UserAccount findUserAccountByUsername(final String username) {
		return this.userAccountDelegate.findByUsername(username);
	}

	/** {@inheritDoc} */
	@Override
	public List<OfficerCaseAssignment> 
			findOfficerCaseAssignmentsByOfficerAndDate(final UserAccount officer,
					final Date effectiveDate) {
		return this.officerCaseAssignmentDelegate.findByUserAccountOnDate(
				officer, effectiveDate);
	}

	/** {@inheritDoc} */
	@Override
	public InterstateCompactAssignment createInterstateCompactAssignment(
			final OfficerCaseAssignment officerCaseAssignment, 
			final State jurisdiction, final String jurisdictionStateId, 
			final Date projectedEndDate,
			final InterstateCompactCorrectionalStatus interstateCompactStatus,
			final InterstateCompactEndReasonCategory endReason,
			final InterstateCompactTypeCategory interstateCompactType) 
					throws DuplicateEntityFoundException {
		return this.interstateCompactAssignmentDelegate.create(
				officerCaseAssignment, jurisdiction, jurisdictionStateId, 
				projectedEndDate, interstateCompactStatus, endReason, 
				interstateCompactType);
	}

	/** {@inheritDoc} */
	@Override
	public InterstateCompactAssignment updateInterstateCompactAssignment(
			final InterstateCompactAssignment interstateCompactAssignment,
			final OfficerCaseAssignment officerCaseAssignment, 
			final State jurisdiction, final String jurisdictionStateId, 
			final Date projectedEndDate,
			final InterstateCompactCorrectionalStatus interstateCompactStatus,
			final InterstateCompactEndReasonCategory endReason,
			final InterstateCompactTypeCategory interstateCompactType)
			throws DuplicateEntityFoundException {
		return this.interstateCompactAssignmentDelegate.update(
				interstateCompactAssignment, officerCaseAssignment, 
				jurisdiction, jurisdictionStateId, projectedEndDate, 
				interstateCompactStatus, endReason, interstateCompactType);
	}

	/** {@inheritDoc} */
	@Override
	public void removeInterstateCompactAssignment(
			final InterstateCompactAssignment interstateCompactAssignment) {
		this.interstateCompactAssignmentDelegate.remove(
				interstateCompactAssignment);
	}

	/** {@inheritDoc} */
	@Override
	public List<InterstateCompactEndReasonCategory> 
			findInterstateCompactEndCategories() {
		return this.interstateCompactEndReasonCategoryDelegate.findActive();
	}

	/** {@inheritDoc} */
	@Override
	public List<InterstateCompactCorrectionalStatus> 
			findInterstateCompactCorrectionalStatuses() {
		return this.interstateCompactCorrectionalStatusDelegate.findActive();
	}

	/** {@inheritDoc} */
	@Override
	public List<InterstateCompactTypeCategory> 
			findInterstateCompactTypeCategories() {
		return this.interstateCompactTypeCategoryDelegate.findActive();
	}

	/** {@inheritDoc} */
	@Override
	public InterstateCompactAssignment 
			findInterstateCompactAssignmentByOfficerCaseAssignment(
			final OfficerCaseAssignment officerCaseAssignment) {
		return this.interstateCompactAssignmentDelegate
				.findByOfficerCaseAssignment(officerCaseAssignment);
	}

	/** {@inheritDoc} */
	@Override
	public OfficerCaseAssignmentNote createOfficerCaseAssignmentNote(
			final OfficerCaseAssignment officerCaseAssignment, 
			final String description, final Date date) 
					throws DuplicateEntityFoundException {
		return this.officerCaseAssignmentNoteDelegate.create(
				officerCaseAssignment, description, date);
	}

	/** {@inheritDoc} */
	@Override
	public OfficerCaseAssignmentNote updateOfficerCaseAssignmentNote(
			final OfficerCaseAssignmentNote officerCaseAssignmentNote,
			final String description, final Date date) 
					throws DuplicateEntityFoundException {
		return this.officerCaseAssignmentNoteDelegate.update(
				officerCaseAssignmentNote, 
				officerCaseAssignmentNote.getOfficerCaseAssignment(), 
				description, date);
	}

	/** {@inheritDoc} */
	@Override
	public void removeOfficerCaseAssignmentNote(
			final OfficerCaseAssignmentNote officerCaseAssignmentNote) {
		this.officerCaseAssignmentNoteDelegate.remove(officerCaseAssignmentNote);
	}

	/** {@inheritDoc} */
	@Override
	public List<State> findAllStatesInHomeCountry() {
		return this.stateDelegate.findInHomeCountry();
	}

	/** {@inheritDoc} */
	@Override
	public List<OfficerCaseAssignmentNote> 
			findOfficerCaseAssignmentNotesByOfficerCaseAssignment(
					final OfficerCaseAssignment officerCaseAssignment) {
		return this.officerCaseAssignmentNoteDelegate
				.findByOfficerCaseAssignment(officerCaseAssignment);
	}
}