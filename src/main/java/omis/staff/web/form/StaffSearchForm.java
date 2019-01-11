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
package omis.staff.web.form;

import java.io.Serializable;

import omis.organization.domain.OrganizationDivision;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * Staff search form.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Dec 02, 2015)
 * @since OMIS 3.0 
 */
public class StaffSearchForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String firstName; 
	
	private String lastName;
	
	private OrganizationDivision division;
	
	private SupervisoryOrganization workLocation;
	
	private Long employeeId;
	
	private Boolean searchActiveStaff;
	
	private Boolean displayStaffPhoto;
	
	/** Instantiates a default offender form. */
	public StaffSearchForm() {
		//Default instantiation
	}
	
	/**
	 * Returns the first name.
	 * 
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 * 
	 * @param firstName the first name to set
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the last name.
	 * 
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 * 
	 * @param lastName the last name to set
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns the division.
	 * 
	 * @return the division
	 */
	public OrganizationDivision getDivision() {
		return division;
	}

	/**
	 * Sets the division.
	 * 
	 * @param division the division to set
	 */
	public void setDivision(final OrganizationDivision division) {
		this.division = division;
	}

	/**
	 * Returns the work locations organization.
	 * 
	 * @return the workLocation
	 */
	public SupervisoryOrganization getWorkLocation() {
		return workLocation;
	}

	/**
	 * Sets the work location organization.
	 * 
	 * @param workLocation the work location to set
	 */
	public void setWorkLocation(final SupervisoryOrganization workLocation) {
		this.workLocation = workLocation;
	}

	/**
	 * Returns the employee ID.
	 * 
	 * @return the employeeId
	 */
	public Long getEmployeeId() {
		return employeeId;
	}

	/** 
	 * Sets the employee ID.
	 * 
	 * @param employeeId the employee Id to set
	 */
	public void setEmployeeId(final Long employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * Returns the search active staff boolean value.
	 * 
	 * @return the searchActiveStaff
	 */
	public Boolean getSearchActiveStaff() {
		return searchActiveStaff;
	}

	/**
	 * Sets the search active staff boolean value.
	 * 
	 * @param searchActiveStaff the search active staff to set
	 */
	public void setSearchActiveStaff(final Boolean searchActiveStaff) {
		this.searchActiveStaff = searchActiveStaff;
	}

	/**
	 * Returns the display staff photo.
	 * 
	 * @return the displayStaffPhoto
	 */
	public Boolean getDisplayStaffPhoto() {
		return displayStaffPhoto;
	}

	/**
	 * Sets the display staff photo.
	 * 
	 * @param displayStaffPhoto the display staff photo to set
	 */
	public void setDisplayStaffPhoto(final Boolean displayStaffPhoto) {
		this.displayStaffPhoto = displayStaffPhoto;
	}
}