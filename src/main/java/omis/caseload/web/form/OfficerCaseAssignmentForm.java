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
package omis.caseload.web.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import omis.caseload.domain.InterstateCompactCorrectionalStatus;
import omis.caseload.domain.InterstateCompactEndReasonCategory;
import omis.caseload.domain.InterstateCompactTypeCategory;
import omis.caseload.domain.SupervisionLevelCategory;
import omis.location.domain.Location;
import omis.offender.domain.Offender;
import omis.region.domain.State;
import omis.user.domain.UserAccount;

/**
 * Officer case assignment form.
 * 
 * @author Josh Divine
 * @author Trevor Isles
 * @version 0.1.4 (Dec 6, 2018)
 * @since OMIS 3.0
 */
public class OfficerCaseAssignmentForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Offender selectedOffender;
	
	private UserAccount officer;
	
	private Date startDate;
	
	private Date startTime;
	
	private Date endDate;
	
	private Date endTime;
	
	private SupervisionLevelCategory supervisionLevelCategory;
	
	private Location location;
	
	private List<OfficerCaseAssignmentNoteItem> officerCaseAssignmentNoteItems;
	
	private Boolean interstateCaseload;
	
	private InterstateCompactCorrectionalStatus interstateCompactStatus;
	
	private InterstateCompactTypeCategory interstateCompactType;
	
	private State jurisdiction;
	
	private String jurisdictionStateId;
	
	private Date projectedEndDate;
	
	private InterstateCompactEndReasonCategory endReason;

	private Boolean allowInterstateCompact;
	
	private OfficeFilter officeFilter;
	
	/**
	 * Instantiates a default officer case assignment form. 
	 */
	public OfficerCaseAssignmentForm() {
		//Default constructor.
	}

	/**
	 * Returns the offender.
	 *
	 * @return offender
	 */
	public Offender getSelectedOffender() {
		return selectedOffender;
	}

	/**
	 * Sets the offender.
	 *
	 * @param offender offender
	 */
	public void setSelectedOffender(final Offender offender) {
		this.selectedOffender = offender;
	}

	/**
	 * Returns the officer.
	 *
	 * @return officer
	 */
	public UserAccount getOfficer() {
		return officer;
	}

	/**
	 * Sets the officer.
	 *
	 * @param officer officer
	 */
	public void setOfficer(final UserAccount officer) {
		this.officer = officer;
	}

	/**
	 * Returns the start date.
	 *
	 * @return start date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate start date
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Returns the start time.
	 *
	 * @return start time
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * Sets the start time.
	 *
	 * @param startTime start time
	 */
	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * Returns the end date.
	 *
	 * @return end date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate end date
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Returns the end time.
	 *
	 * @return end time
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * Sets the end time.
	 *
	 * @param endTime end time
	 */
	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * Returns the supervision level category.
	 *
	 * @return supervision level category
	 */
	public SupervisionLevelCategory getSupervisionLevelCategory() {
		return supervisionLevelCategory;
	}

	/**
	 * Sets the supervision level category.
	 *
	 * @param supervisionLevelCategory supervision level category
	 */
	public void setSupervisionLevelCategory(
			final SupervisionLevelCategory supervisionLevelCategory) {
		this.supervisionLevelCategory = supervisionLevelCategory;
	}

	/**
	 * Returns the location.
	 *
	 * @return location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location location
	 */
	public void setLocation(final Location location) {
		this.location = location;
	}

	/**
	 * Returns the officer case assignment note items.
	 *
	 * @return officer case assignment note items
	 */
	public List<OfficerCaseAssignmentNoteItem> 
			getOfficerCaseAssignmentNoteItems() {
		return officerCaseAssignmentNoteItems;
	}

	/**
	 * Sets the officer case assignment note items.
	 *
	 * @param officerCaseAssignmentNoteItems officer case assignment note items
	 */
	public void setOfficerCaseAssignmentNoteItems(
			List<OfficerCaseAssignmentNoteItem> officerCaseAssignmentNoteItems) {
		this.officerCaseAssignmentNoteItems = officerCaseAssignmentNoteItems;
	}

	/**
	 * Returns whether the officer case assignment is an interstate compact.
	 *
	 * @return whether the officer case assignment is an interstate compact
	 */
	public Boolean getInterstateCaseload() {
		return interstateCaseload;
	}

	/**
	 * Sets whether the officer case assignment is an interstate compact.
	 *
	 * @param interstateCaseload interstate compact
	 */
	public void setInterstateCaseload(Boolean interstateCaseload) {
		this.interstateCaseload = interstateCaseload;
	}

	/**
	 * Returns the interstate compact status.
	 *
	 * @return interstate compact status
	 */
	public InterstateCompactCorrectionalStatus getInterstateCompactStatus() {
		return interstateCompactStatus;
	}

	/**
	 * Sets the interstate compact status.
	 *
	 * @param interstateCompactStatus interstate compact status
	 */
	public void setInterstateCompactStatus(
			InterstateCompactCorrectionalStatus interstateCompactStatus) {
		this.interstateCompactStatus = interstateCompactStatus;
	}

	/**
	 * Returns the interstate compact type.
	 *
	 * @return interstate compact type
	 */
	public InterstateCompactTypeCategory getInterstateCompactType() {
		return interstateCompactType;
	}

	/**
	 * Sets the interstate compact type.
	 *
	 * @param interstateCompactType interstate compact type
	 */
	public void setInterstateCompactType(
			InterstateCompactTypeCategory interstateCompactType) {
		this.interstateCompactType = interstateCompactType;
	}

	/**
	 * Returns the jurisdiction.
	 *
	 * @return jurisdiction
	 */
	public State getJurisdiction() {
		return jurisdiction;
	}

	/**
	 * Sets the jurisdiction.
	 *
	 * @param jurisdiction jurisdiction
	 */
	public void setJurisdiction(State jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	/**
	 * Returns the jurisdiction state id.
	 *
	 * @return jurisdiction state id
	 */
	public String getJurisdictionStateId() {
		return jurisdictionStateId;
	}

	/**
	 * Sets the jurisdiction state id.
	 *
	 * @param jurisdictionStateId jurisdiction state id
	 */
	public void setJurisdictionStateId(String jurisdictionStateId) {
		this.jurisdictionStateId = jurisdictionStateId;
	}

	/**
	 * Returns the projected end date.
	 *
	 * @return projected end date
	 */
	public Date getProjectedEndDate() {
		return projectedEndDate;
	}

	/**
	 * Sets the projected end date.
	 *
	 * @param projectedEndDate projected end date
	 */
	public void setProjectedEndDate(Date projectedEndDate) {
		this.projectedEndDate = projectedEndDate;
	}

	/**
	 * Returns the end reason.
	 *
	 * @return end reason
	 */
	public InterstateCompactEndReasonCategory getEndReason() {
		return endReason;
	}

	/**
	 * Sets the end reason.
	 *
	 * @param endReason end reason
	 */
	public void setEndReason(InterstateCompactEndReasonCategory endReason) {
		this.endReason = endReason;
	}

	/**
	 * Returns whether to allow interstate compact.
	 *
	 * @return whether to allow interstate compact
	 */
	public Boolean getAllowInterstateCompact() {
		return allowInterstateCompact;
	}

	/**
	 * Sets whether to allow interstate compact.
	 *
	 * @param allowInterstateCompact whether to allow interstate compact
	 */
	public void setAllowInterstateCompact(Boolean allowInterstateCompact) {
		this.allowInterstateCompact = allowInterstateCompact;
	}

	/**
	 * Returns office filter.
	 * 
	 * @return office filter
	 */
	public OfficeFilter getOfficeFilter() {
		return officeFilter;
	}

	/**
	 * Sets office filter.
	 * 
	 * @param officeFilter office filter
	 */
	public void setOfficeFilter(OfficeFilter officeFilter) {
		this.officeFilter = officeFilter;
	}
}