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
package omis.staff.service;

import java.util.Date;
import java.util.List;

import omis.datatype.DateRange;
import omis.demographics.domain.EyeColor;
import omis.demographics.domain.HairColor;
import omis.demographics.domain.Height;
import omis.demographics.domain.PersonDemographics;
import omis.demographics.domain.Sex;
import omis.demographics.domain.Weight;
import omis.location.domain.Location;
import omis.media.domain.Photo;
import omis.media.exception.PhotoExistsException;
import omis.organization.domain.OrganizationDivision;
import omis.person.domain.Person;
import omis.person.domain.Suffix;
import omis.person.exception.PersonExistsException;
import omis.person.exception.PersonIdentityExistsException;
import omis.person.exception.PersonNameExistsException;
import omis.staff.domain.StaffAssignment;
import omis.staff.domain.StaffPhotoAssociation;
import omis.staff.domain.StaffTitle;
import omis.staff.exception.StaffAssignmentExistsException;
import omis.staff.exception.StaffPhotoAssociationExistsException;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * Service for staff assignments.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Aug 10, 2018)
 * @since OMIS 3.0
 */
public interface StaffAssignmentService {

	/**
	 * Creates staff member.
	 * 
	 * <p>Name properties are required, identity fields are optional. A name
	 * is always created. If identity properties are all {@code null}, an
	 * identity is not created.
	 * 
	 * @param lastName last name
	 * @param firstName first name
	 * @param middleName middle name
	 * @param suffix suffix
	 * @param birthDate birth date
	 * @param sex sex
	 * @return staff member
	 * @throws PersonExistsException if person exists
	 */
	Person createStaffMember(String lastName, String firstName,
			String middleName, String suffix, Date birthDate, Sex sex)
		throws PersonExistsException;
	
	/**
	 * Updates staff member.
	 * 
	 * <p>Name properties are required, identity fields are optional. If
	 * identity properties are all {@code null}, an identity is not created
	 * if ones does not already exist. If identity properties are all
	 * {@code null} and an identity exists without other none-{@code null}
	 * properties (country of birth, SSN, etc), the identity is removed. 
	 * 
	 * <p>If the person has an identity, unspecified properties of the identity
	 * will be unaffected.
	 * 
	 * @param staffMember staff member
	 * @param lastName last name
	 * @param firstName first name
	 * @param middleName middle name
	 * @param suffix suffix
	 * @param birthDate birth date
	 * @param sex sex
	 * @return updated staff member
	 * @throws PersonExistsException if person exists
	 * @throws PersonNameExistsException if person name exists
	 * @throws PersonIdentityExistsException if person identity exists
	 */
	Person updateStaffMember(Person staffMember, String lastName,
			String firstName, String middleName, String suffix, Date birthDate,
			Sex sex)
		throws PersonExistsException,
			PersonNameExistsException,
			PersonIdentityExistsException;
	
	/**
	 * Associates demographics with staff member.
	 * 
	 * <p>If all properties are {@code null} and no other demographics exist
	 * for the staff member, the {@code PersonDemographics} instance will be
	 * removed if it exists - in which case {@code null} will be returned.
	 * 
	 * <p>If a demographics record exists for the staff member, unspecified
	 * properties are unaffected.
	 * 
	 * @param staffMember staff member
	 * @param eyeColor eye color
	 * @param hairColor hair color
	 * @param height height
	 * @param weight weight
	 * @return associated demographics or {@code null} if demographics for
	 * the staff member do not exists on completion
	 */
	PersonDemographics associateDemographics(Person staffMember,
			EyeColor eyeColor, HairColor hairColor, Height height,
			Weight weight);
	
	/**
	 * Associates photo with staff member.
	 * 
	 * @param staffMember staff member
	 * @param photoDate photo date
	 * @param filename file name
	 * @return association of photo with staff member
	 * @throws StaffPhotoAssociationExistsException if photo already exists
	 * for staff member
	 * @throws PhotoExistsException if photo exists
	 */
	StaffPhotoAssociation associatePhoto(Person staffMember, Date photoDate,
			String filename)
					throws StaffPhotoAssociationExistsException,
						PhotoExistsException;
	
	/**
	 * Updates photo.
	 * 
	 * @param photo photo to update
	 * @param photoDate photo date
	 * @return updated photo
	 */
	Photo updatePhoto(Photo photo, Date photoDate);
	
	/**
	 * Creates staff assignment.
	 * 
	 * @param staffMember staff member
	 * @param supervisoryOrganization supervisory organization
	 * @param location location
	 * @param organizationDivision organization division
	 * @param dateRange date range
	 * @param title title
	 * @param supervisory whether supervisory
	 * @param staffId staff ID number
	 * @return staff assignment
	 * @throws StaffAssignmentExistsException
	 */
	StaffAssignment createStaffAssignment(Person staffMember,
			SupervisoryOrganization supervisoryOrganization,
			Location location, OrganizationDivision organizationDivision,
			DateRange dateRange, StaffTitle title, Boolean supervisory,
			String staffId)
				throws StaffAssignmentExistsException;
	
	/**
	 * Updates staff assignment.
	 * 
	 * @param staffAssignment staff assignment
	 * @param supervisoryOrganization supervisory organization
	 * @param organizationDivision organization division
	 * @param location location
	 * @param dateRange date range
	 * @param title title
	 * @param supervisory whether supervisory
	 * @param staffId staff ID number
	 * @return staff assignment
	 * @throws StaffAssignmentExistsException if staff assignment exists
	 */
	StaffAssignment updateStaffAssignment(StaffAssignment staffAssignment,
			SupervisoryOrganization supervisoryOrganization,
			Location location, OrganizationDivision organizationDivision,
			DateRange dateRange, StaffTitle title, Boolean supervisory,
			String staffId)
				throws StaffAssignmentExistsException;
	
	/**
	 * Returns demographics.
	 * 
	 * @param staffMember staff member
	 * @return demographics
	 */
	PersonDemographics findDemographics(Person staffMember);
	
	/**
	 * Returns association of photo to staff member.
	 * 
	 * @param staffMember staff member
	 * @return association of photo to staff member
	 */
	StaffPhotoAssociation findPhotoAssociation(Person staffMember);
	
	/**
	 * Returns person name suffixes.
	 * 
	 * @return person name suffixes
	 */
	List<Suffix> findSuffixes();
	
	/**
	 * Returns eye colors.
	 * 
	 * @return eye colors
	 */
	List<EyeColor> findEyeColors();
	
	/**
	 * Returns hair colors.
	 * 
	 * @return hair colors
	 */
	List<HairColor> findHairColors();
	
	/**
	 * Returns titles.
	 * 
	 * @return titles
	 */
	List<StaffTitle> findTitles();
	
	/**
	 * Returns supervisory organizations.
	 * 
	 * @return supervisory organizations
	 */
	List<SupervisoryOrganization> findSupervisoryOrganizations();
	
	/**
	 * Returns locations by supervisory organization.
	 * 
	 * @param supervisoryOrganization supervisory organization
	 * @return locations by supervisory organization
	 */
	List<Location> findLocationsBySupervisoryOrganization(
			SupervisoryOrganization supervisoryOrganization);
	
	/**
	 * Returns organization divisions by supervisory organization.
	 * 
	 * @param supervisoryOrganization supervisory organization
	 * @return organization divisions by supervisory organization
	 */
	List<OrganizationDivision> findDivisionsBySupervisoryOrganization(
			SupervisoryOrganization supervisoryOrganization);
	
	/**
	 * Removes staff assignment and associated photo where applicable.
	 * 
	 * @param staffAssignment staff assignment to remove
	 */
	void remove(StaffAssignment staffAssignment);
}