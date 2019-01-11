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
import java.util.Date;

import omis.demographics.domain.EyeColor;
import omis.demographics.domain.HairColor;
import omis.demographics.domain.Sex;
import omis.location.domain.Location;
import omis.organization.domain.OrganizationDivision;
import omis.person.domain.Person;
import omis.staff.domain.StaffTitle;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * Form for staff assignments.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 5, 2014)
 * @since OMIS 3.0
 */
public class StaffAssignmentForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private StaffAssignmentPersonOperation personOperation;
	
	private Person staffMember;
	
	private String lastName;
	
	private String firstName;
	
	private String middleName;
	
	private String suffix;
	
	private Date birthDate;
	
	private Sex sex;
	
	private EyeColor eyeColor;
	
	private HairColor hairColor;
	
	private Integer heightFeet;
	
	private Integer heightInches;
	
	private Integer weightPounds;
	
	private String staffId;
	
	private SupervisoryOrganization supervisoryOrganization;
	
	private Location location;
	
	private StaffTitle title;
	
	private OrganizationDivision organizationDivision;
	
	private Boolean supervisory;
	
	private Date startDate;
	
	private Date endDate;
	
	private Date photoDate;
	
	private byte[] photoData;
	
	private Boolean newPhoto;
	
	/** Instantiates a form for staff assignments. */
	public StaffAssignmentForm() {
		// Default instantiation
	}

	/**
	 * Returns operation to be performed to associate person to staff
	 * assignment.
	 * 
	 * @return operation to be performed to associate person to staff
	 * assignment
	 */
	public StaffAssignmentPersonOperation getPersonOperation() {
		return this.personOperation;
	}
	
	/**
	 * Sets operation to be performed to associate person to staff
	 * assignment.
	 * 
	 * @param personOperation operation to be performed to associate
	 * person to staff assignment
	 */
	public void setPersonOperation(
			final StaffAssignmentPersonOperation personOperation) {
		this.personOperation = personOperation;
	}
	
	/**
	 * Returns staff member.
	 * 
	 * @return staff member
	 */
	public Person getStaffMember() {
		return this.staffMember;
	}
	
	/**
	 * Sets staff member.
	 * 
	 * @param staffMember staff member
	 */
	public void setStaffMember(final Person staffMember) {
		this.staffMember = staffMember;
	}
	
	/**
	 * Returns last name.
	 * 
	 * @return last name
	 */
	public String getLastName() {
		return this.lastName;
	}
	
	/**
	 * Sets last name.
	 * 
	 * @param lastName last name
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Returns first name.
	 * 
	 * @return first name
	 */
	public String getFirstName() {
		return this.firstName;
	}
	
	/**
	 * Sets first name.
	 * 
	 * @param firstName first name
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}
	
	
	/**
	 * Returns middle name.
	 * 
	 * @return middle name
	 */
	public String getMiddleName() {
		return this.middleName;
	}
	
	/**
	 * Sets middle name.
	 * 
	 * @param middleName middle name
	 */
	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}
	
	/**
	 * Returns suffix.
	 * 
	 * @return suffix
	 */
	public String getSuffix() {
		return this.suffix;
	}
	
	/**
	 * Sets suffix.
	 * 
	 * @param suffix suffix
	 */
	public void setSuffix(final String suffix) {
		this.suffix = suffix;
	}
	
	/**
	 * Returns birth date. 
	 * 
	 * @return birth date
	 */
	public Date getBirthDate() {
		return this.birthDate;
	}
	
	/**
	 * Sets birth date.
	 * 
	 * @param birthDate birth date
	 */
	public void setBirthDate(final Date birthDate) {
		this.birthDate = birthDate;
	}
	
	/**
	 * Returns sex.
	 * 
	 * @return sex
	 */
	public Sex getSex() {
		return this.sex;
	}
	
	/**
	 * Sets sex.
	 * 
	 * @param sex sex
	 */
	public void setSex(final Sex sex) {
		this.sex = sex;
	}
	
	/**
	 * Returns eye color.
	 * 
	 * @return eye color
	 */
	public EyeColor getEyeColor() {
		return this.eyeColor;
	}
	
	/**
	 * Sets eye color.
	 * 
	 * @param eyeColor eye color
	 */
	public void setEyeColor(final EyeColor eyeColor) {
		this.eyeColor = eyeColor;
	}
	
	/**
	 * Returns hair color.
	 * 
	 * @return hair color
	 */
	public HairColor getHairColor() {
		return this.hairColor;
	}
	
	/**
	 * Sets hair color.
	 * 
	 * @param hairColor hair color
	 */
	public void setHairColor(final HairColor hairColor) {
		this.hairColor = hairColor;
	}
	
	/**
	 * Returns height feet.
	 * 
	 * @return height feet
	 */
	public Integer getHeightFeet() {
		return this.heightFeet;
	}
	
	/**
	 * Sets height feet.
	 * 
	 * @param heightFeet height feet
	 */
	public void setHeightFeet(final Integer heightFeet) {
		this.heightFeet = heightFeet;
	}
	
	/**
	 * Returns height inches.
	 * 
	 * @return height inches
	 */
	public Integer getHeightInches() {
		return this.heightInches;
	}
	
	/**
	 * Sets height inches.
	 * 
	 * @param heightInches height inches
	 */
	public void setHeightInches(final Integer heightInches) {
		this.heightInches = heightInches;
	}
	
	/**
	 * Returns weight pounds.
	 * 
	 * @return weight pounds
	 */
	public Integer getWeightPounds() {
		return this.weightPounds;
	}
	
	/**
	 * Sets weight pounds.
	 * 
	 * @param weightPounds weight pounds
	 */
	public void setWeightPounds(final Integer weightPounds) {
		this.weightPounds = weightPounds;
	}
	
	/**
	 * Returns photo date.
	 * 
	 * @return photo date
	 */
	public Date getPhotoDate() {
		return this.photoDate;
	}
	
	/**
	 * Sets photo date.
	 * 
	 * @param photoDate photo date
	 */
	public void setPhotoDate(final Date photoDate) {
		this.photoDate = photoDate;
	}
	
	/**
	 * Returns staff ID number.
	 * 
	 * @return staff ID number
	 */
	public String getStaffId() {
		return this.staffId;
	}
	
	/**
	 * Sets staff ID number.
	 * 
	 * @param staffId staff ID number
	 */
	public void setStaffId(final String staffId) {
		this.staffId = staffId;
	}
	
	/**
	 * Returns the supervisory organization.
	 * 
	 * @return supervisory organization
	 */
	public SupervisoryOrganization getSupervisoryOrganization() {
		return this.supervisoryOrganization;
	}

	/**
	 * Sets the supervisory organization.
	 * 
	 * @param supervisoryOrganization supervisory organization
	 */
	public void setSupervisoryOrganization(
			final SupervisoryOrganization supervisoryOrganization) {
		this.supervisoryOrganization = supervisoryOrganization;
	}

	/**
	 * Returns the location.
	 * 
	 * @return location
	 */
	public Location getLocation() {
		return this.location;
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
	 * Returns the title.
	 * 
	 * @return title
	 */
	public StaffTitle getTitle() {
		return this.title;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title title
	 */
	public void setTitle(final StaffTitle title) {
		this.title = title;
	}

	/**
	 * Returns organization division.
	 * 
	 * @return organization division
	 */
	public OrganizationDivision getOrganizationDivision() {
		return this.organizationDivision;
	}
	
	/**
	 * Sets organization division.
	 * 
	 * @param organizationDivision organization division
	 */
	public void setOrganizationDivision(
			final OrganizationDivision organizationDivision) {
		this.organizationDivision = organizationDivision;
	}
	
	/**
	 * Returns whether the assignment is supervisory.
	 * 
	 * @return whether assignment is supervisory
	 */
	public Boolean getSupervisory() {
		return this.supervisory;
	}

	/**
	 * Sets whether the assignment is supervisory.
	 * 
	 * @param supervisory whether assignment is supervisory
	 */
	public void setSupervisory(final Boolean supervisory) {
		this.supervisory = supervisory;
	}

	/**
	 * Returns the start date.
	 * 
	 * @return start date
	 */
	public Date getStartDate() {
		return this.startDate;
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
	 * Returns the end date.
	 * 
	 * @return end date
	 */
	public Date getEndDate() {
		return this.endDate;
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
	 * Returns photo data.
	 * 
	 * @return photo data
	 */
	public byte[] getPhotoData() {
		return this.photoData;
	}
	
	/**
	 * Sets photo data.
	 * 
	 * @param photoData photo data
	 */
	public void setPhotoData(final byte[] photoData) {
		this.photoData = photoData;
	}

	/**
	 * Returns whether new photo applies.
	 * 
	 * @return new photo
	 */
	public Boolean getNewPhoto() {
		return this.newPhoto;
	}

	/**
	 * Sets whether new photo applies.
	 * 
	 * @param newPhoto new photo
	 */
	public void setNewPhoto(final Boolean newPhoto) {
		this.newPhoto = newPhoto;
	}
}