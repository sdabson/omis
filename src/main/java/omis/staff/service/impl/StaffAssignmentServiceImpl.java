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
package omis.staff.service.impl;

import java.util.Date;
import java.util.List;

import omis.country.domain.Country;
import omis.datatype.DateRange;
import omis.demographics.domain.EyeColor;
import omis.demographics.domain.HairColor;
import omis.demographics.domain.Height;
import omis.demographics.domain.PersonDemographics;
import omis.demographics.domain.Sex;
import omis.demographics.domain.Weight;
import omis.demographics.domain.component.PersonAppearance;
import omis.demographics.domain.component.PersonPhysique;
import omis.demographics.service.delegate.EyeColorDelegate;
import omis.demographics.service.delegate.HairColorDelegate;
import omis.demographics.service.delegate.PersonDemographicsDelegate;
import omis.location.domain.Location;
import omis.location.service.delegate.LocationDelegate;
import omis.media.domain.Photo;
import omis.media.exception.PhotoExistsException;
import omis.media.service.delegate.PhotoDelegate;
import omis.organization.domain.OrganizationDivision;
import omis.organization.service.delegate.OrganizationDivisionDelegate;
import omis.person.domain.Person;
import omis.person.domain.Suffix;
import omis.person.exception.PersonExistsException;
import omis.person.exception.PersonIdentityExistsException;
import omis.person.exception.PersonNameExistsException;
import omis.person.service.delegate.PersonDelegate;
import omis.person.service.delegate.SuffixDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.staff.domain.StaffAssignment;
import omis.staff.domain.StaffPhotoAssociation;
import omis.staff.domain.StaffTitle;
import omis.staff.exception.StaffAssignmentExistsException;
import omis.staff.exception.StaffPhotoAssociationExistsException;
import omis.staff.service.StaffAssignmentService;
import omis.staff.service.delegate.StaffAssignmentDelegate;
import omis.staff.service.delegate.StaffPhotoAssociationDelegate;
import omis.staff.service.delegate.StaffTitleDelegate;
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.service.delegate.SupervisoryOrganizationDelegate;

/**
* Staff assignment service implementation.
* 
* @author Sheronda Vaughn
* @author Stephen Abson
* @version 0.0.1 (Aug 21, 2018)
* @since OMIS 3.0
*/
public class StaffAssignmentServiceImpl 
		implements StaffAssignmentService {

	private final StaffAssignmentDelegate staffAssignmentDelegate;
	
	private final PersonDelegate personDelegate;
	
	private final SuffixDelegate suffixDelegate;
	
	private final PersonDemographicsDelegate personDemographicsDelegate;
	
	private final StaffPhotoAssociationDelegate staffPhotoAssociationDelegate;
	
	private final PhotoDelegate photoDelegate;	
	
	private final EyeColorDelegate eyeColorDelegate;
	
	private final HairColorDelegate hairColorDelegate;
	
	private final StaffTitleDelegate staffTitleDelegate;
	
	private final SupervisoryOrganizationDelegate
	supervisoryOrganizationDelegate;
	
	private final LocationDelegate locationDelegate;
	
	private final OrganizationDivisionDelegate organizationDivisionDelegate;

	/**
	 * Instantiates an implementation of staff assignment service.
	 * 
	 * @param staffAssignmentDelegate delegate for staff assignments
	 * @param personDelegate delegate for persons
	 * @param suffixDelegate delegate for suffixes
	 * @param personDemographicDelegate delegate for person demographics
	 * @param staffPhotoAssociationDelegate delegate for staff photo
	 * associations
	 * @param photoDelegate delegate for photos
	 * @param eyeColorDelegate delegate for eye colors
	 * @param hairColorDelegate delegate for hair colors
	 * @param staffTitleDelegate delegate for staff titles
	 * @param supervisoryOrganizationDelegate delegate for supervisory
	 * organizations
	 * @param locationDelegate delegate for locations
	 * @param organizationDivisionDelegate delegate for organization divisions
	 */
	public StaffAssignmentServiceImpl(
			final StaffAssignmentDelegate staffAssignmentDelegate, 
			final PersonDelegate personDelegate,
			final SuffixDelegate suffixDelegate,
			final PersonDemographicsDelegate personDemographicsDelegate,
			final StaffPhotoAssociationDelegate staffPhotoAssociationDelegate, 
			final PhotoDelegate photoDelegate,
			final EyeColorDelegate eyeColorDelegate,
			final HairColorDelegate hairColorDelegate,
			final StaffTitleDelegate staffTitleDelegate,
			final SupervisoryOrganizationDelegate
				supervisoryOrganizationDelegate,
			final LocationDelegate locationDelegate,
			final OrganizationDivisionDelegate organizationDivisionDelegate) {
		this.staffAssignmentDelegate = staffAssignmentDelegate;
		this.personDelegate = personDelegate;
		this.suffixDelegate = suffixDelegate;
		this.personDemographicsDelegate = personDemographicsDelegate;
		this.staffPhotoAssociationDelegate = staffPhotoAssociationDelegate;
		this.photoDelegate  = photoDelegate;
		this.eyeColorDelegate = eyeColorDelegate;
		this.hairColorDelegate = hairColorDelegate;		
		this.staffTitleDelegate = staffTitleDelegate;
		this.supervisoryOrganizationDelegate = supervisoryOrganizationDelegate;
		this.locationDelegate = locationDelegate;
		this.organizationDivisionDelegate = organizationDivisionDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public Person createStaffMember(final String lastName,
			final String firstName, final String middleName,
			final String suffix, final Date birthDate, final Sex sex)
					throws PersonExistsException {
		return this.personDelegate.createWithIdentity(lastName, firstName,
				middleName, suffix, sex, birthDate, null, null, null, null,
				null, null, null);
	}
	
	/** {@inheritDoc} */
	@Override
	public Person updateStaffMember(final Person staffMember,
			final String lastName, final String firstName,
			final String middleName, final String suffix, final Date birthDate,
			final Sex sex)
					throws PersonExistsException,
						PersonNameExistsException,
						PersonIdentityExistsException {
		Country birthCountry;
		State birthState;
		City birthCity;
		Integer socialSecurityNumber;
		String stateId;
		Boolean deceased;
		Date deathDate;
		if (staffMember.getIdentity() != null) {
			birthCountry = staffMember.getIdentity().getBirthCountry();
			birthState = staffMember.getIdentity().getBirthState();
			birthCity = staffMember.getIdentity().getBirthPlace();
			socialSecurityNumber = staffMember.getIdentity()
					.getSocialSecurityNumber();
			stateId = staffMember.getIdentity().getStateIdNumber();
			deceased = staffMember.getIdentity().getDeceased();
			deathDate = staffMember.getIdentity().getDeathDate();
		} else {
			birthCountry = null;
			birthState = null;
			birthCity = null;
			socialSecurityNumber = null;
			stateId = null;
			deceased = null;
			deathDate = null;
		}
		return this.personDelegate.updateWithIdentity(staffMember, lastName,
				firstName, middleName, suffix, sex, birthDate,
				birthCountry, birthState, birthCity, socialSecurityNumber,
				stateId, deceased, deathDate);
	}

	/** {@inheritDoc} */
	@Override
	public PersonDemographics associateDemographics(final Person staffMember,
			final EyeColor eyeColor, final HairColor hairColor,
			final Height height, final Weight weight) {
		PersonDemographics demographics = this.personDemographicsDelegate
				.find(staffMember);
		if (demographics != null && eyeColor == null && hairColor == null
				&& height == null && weight == null) {
			this.personDemographicsDelegate.remove(demographics);
			return null;
		} else {
			PersonPhysique physique = new PersonPhysique();
			physique.setHeight(height);
			physique.setWeight(weight);
			PersonAppearance appearance = new PersonAppearance();
			appearance.setEyeColor(eyeColor);
			appearance.setHairColor(hairColor);
			if (demographics != null) {
				return this.personDemographicsDelegate.update(
						demographics, appearance, demographics.getRace(),
						demographics.getHispanicEthnicity(), physique,
						demographics.getDominantSide(),
						demographics.getMaritalStatus(),
						demographics.getTribe());
			} else {
				return this.personDemographicsDelegate.create(staffMember,
						appearance, null, null, physique, null, null, null);
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public StaffPhotoAssociation associatePhoto(final Person staffMember,
			final Date photoDate, final String filename)
					throws StaffPhotoAssociationExistsException,
						PhotoExistsException {
		Photo photo = this.photoDelegate.create(filename, photoDate);
		return this.staffPhotoAssociationDelegate.create(staffMember, photo);
	}
	
	/** {@inheritDoc} */
	@Override
	public Photo updatePhoto(
			final Photo photo, final Date photoDate) {
		try {
			return this.photoDelegate.update(
					photo, photoDate, photo.getFilename());
		} catch (PhotoExistsException e) {
			
			// Logically impossible as business key property is not updated
			throw new AssertionError("Logically impossible condition", e);
		}
	}

	/** {@inheritDoc} */
	@Override
	public List<Suffix> findSuffixes() {
		return this.suffixDelegate.findAll();
	}
	
	/** {@inheritDoc} */
	@Override
	public StaffAssignment createStaffAssignment(final Person staffMember, 
			final SupervisoryOrganization supervisoryOrganization,
			final Location location,
			final OrganizationDivision organizationDivision,
			final DateRange dateRange, final StaffTitle title,
			final Boolean supervisory, final String staffId)
					throws StaffAssignmentExistsException {
		return this.staffAssignmentDelegate.create(staffMember,
				supervisoryOrganization, location, dateRange, title,
				supervisory, organizationDivision, staffId, null, null, null);
	}

	/** {@inheritDoc} */
	@Override
	public StaffAssignment updateStaffAssignment(
			final StaffAssignment staffAssignment,
			final SupervisoryOrganization supervisoryOrganization,
			final Location location,
			final OrganizationDivision organizationDivision,
			final DateRange dateRange, final StaffTitle title,
			final Boolean supervisory, final String staffId)
					throws StaffAssignmentExistsException {
		return this.staffAssignmentDelegate.update(staffAssignment,
				staffAssignment.getStaffMember(), 
				supervisoryOrganization, location, dateRange, title,
				supervisory, organizationDivision, staffId, null, null, null);
	}

	/** {@inheritDoc} */
	@Override
	public PersonDemographics findDemographics(final Person staffMember) {
		return this.personDemographicsDelegate.find(staffMember);
	}
	
	/** {@inheritDoc} */
	@Override
	public StaffPhotoAssociation findPhotoAssociation(
			final Person staffMember) {
		return this.staffPhotoAssociationDelegate
				.findByStaffMember(staffMember);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<EyeColor> findEyeColors() {
		return this.eyeColorDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<HairColor> findHairColors() {
		return this.hairColorDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<StaffTitle> findTitles() {
		return this.staffTitleDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<SupervisoryOrganization> findSupervisoryOrganizations() {
		return this.supervisoryOrganizationDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<Location> findLocationsBySupervisoryOrganization(
			final SupervisoryOrganization supervisoryOrganization) {
		return this.locationDelegate.findByOrganization(
				supervisoryOrganization);
	}

	/** {@inheritDoc} */
	@Override
	public List<OrganizationDivision> findDivisionsBySupervisoryOrganization(
			final SupervisoryOrganization supervisoryOrganization) {
		return this.organizationDivisionDelegate
				.findByOrganization(supervisoryOrganization);
	}
	
	/** {@inheritDoc} */
	@Override
	public void remove(final StaffAssignment staffAssignment) {
		StaffPhotoAssociation association = this.staffPhotoAssociationDelegate
				.findByStaffMember(staffAssignment.getStaffMember());
		if (association != null) {
			Photo photo = association.getPhoto();
			this.staffPhotoAssociationDelegate.remove(association);
			this.photoDelegate.remove(photo);
		}
		this.staffAssignmentDelegate.remove(staffAssignment);
	}
}