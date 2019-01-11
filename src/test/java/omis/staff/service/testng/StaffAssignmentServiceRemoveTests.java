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
package omis.staff.service.testng;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.testng.annotations.Test;

import omis.address.domain.Address;
import omis.address.domain.BuildingCategory;
import omis.address.domain.ZipCode;
import omis.address.service.delegate.AddressDelegate;
import omis.address.service.delegate.ZipCodeDelegate;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.location.domain.Location;
import omis.location.service.delegate.LocationDelegate;
import omis.media.domain.Photo;
import omis.media.service.delegate.PhotoDelegate;
import omis.organization.domain.Organization;
import omis.organization.domain.OrganizationDivision;
import omis.organization.service.delegate.OrganizationDelegate;
import omis.organization.service.delegate.OrganizationDivisionDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.staff.domain.StaffAssignment;
import omis.staff.domain.StaffTitle;
import omis.staff.service.StaffAssignmentService;
import omis.staff.service.delegate.StaffAssignmentDelegate;
import omis.staff.service.delegate.StaffPhotoAssociationDelegate;
import omis.staff.service.delegate.StaffTitleDelegate;
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.service.delegate.SupervisoryOrganizationDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests method to remove staff assignment.
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = { "staff", "service" })
public class StaffAssignmentServiceRemoveTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Delegate */
	@Autowired
	@Qualifier("staffPhotoAssociationDelegate")
	private StaffPhotoAssociationDelegate
	staffPhotoAssociationDelegate;
	
	@Autowired
	@Qualifier("photoDelegate")
	private PhotoDelegate photoDelegate;
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;

	@Autowired
	@Qualifier("supervisoryOrganizationDelegate")
	private SupervisoryOrganizationDelegate supervisoryOrganizationDelegate;

	@Autowired
	@Qualifier("organizationDelegate")
	private OrganizationDelegate organizationDelegate;

	@Autowired
	@Qualifier("locationDelegate")
	private LocationDelegate locationDelegate;

	@Autowired
	@Qualifier("addressDelegate")
	private AddressDelegate addressDelegate;

	@Autowired
	@Qualifier("zipCodeDelegate")
	private ZipCodeDelegate zipCodeDelegate;

	@Autowired
	@Qualifier("cityDelegate")
	private CityDelegate cityDelegate;

	@Autowired
	@Qualifier("stateDelegate")
	private StateDelegate stateDelegate;

	@Autowired
	@Qualifier("countryDelegate")
	private CountryDelegate countryDelegate;

	@Autowired
	@Qualifier("staffTitleDelegate")
	private StaffTitleDelegate staffTitleDelegate;

	@Autowired
	@Qualifier("organizationDivisionDelegate")
	private OrganizationDivisionDelegate organizationDivisionDelegate;
	
	@Autowired
	@Qualifier("staffAssignmentDelegate")
	private StaffAssignmentDelegate staffAssignmentDelegate;

	/* Service */
	@Autowired
	@Qualifier("staffAssignmentService")
	private StaffAssignmentService staffAssignmentService;

	/* Property editor factory. */
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;

	/* Test methods. */

	/**
	 * Tests the removal of a staff assignment.
	 * 
	 * @throws DuplicateEntityFoundException
	 */
	public void testStaffAssignmentRemove()
		throws DuplicateEntityFoundException {
		// Arrangements
		String lastName = "Smith";
		String firstName = "Yidong";
		String middleName = "CIC311";
		String suffix = "Mr.";

		Person staffMember = this.personDelegate.create(lastName,
			firstName, middleName, suffix);
		SupervisoryOrganization supervisoryOrganization
			= this.supervisoryOrganizationDelegate
				.create("supervisoryOrganization", "alias", null);
		Organization organization = this.organizationDelegate
			.create("Organization", "Org", null);
		DateRange locationDateRange = new DateRange(
			this.parseDateText("01/01/2017"),
			this.parseDateText("01/01/2018"));
		Country country = this.countryDelegate.create("Country",
			"CTY", true);
		State state = this.stateDelegate.create("state", "STE",
			country, true, true);
		City city = this.cityDelegate.create("City", true, state,
			country);
		ZipCode zipCode = this.zipCodeDelegate.create(city,
			"1234567890", "3564", true);
		Address address = this.addressDelegate.findOrCreate("value",
			"designator", "coordinates",
			BuildingCategory.HOUSE, zipCode);
		Location location = this.locationDelegate.create(organization,
			locationDateRange, address);
		OrganizationDivision organizationDivision
			= this.organizationDivisionDelegate.create(organization,
				"Organization division", true);
		DateRange staffAssignmentDateRange = new DateRange(
			this.parseDateText("02/01/2017"),
			this.parseDateText("02/01/2018"));
		StaffTitle title = this.staffTitleDelegate.create("Title",
			(short) 1, true);
		StaffAssignment staffAssignment = this.staffAssignmentDelegate
			.create(staffMember, supervisoryOrganization, location,
			staffAssignmentDateRange, title, true,
			organizationDivision, null, null, null,	null);
		Date photoDate = new Date(20000000);
		String fileName = "photo filename";
		Photo photo = this.photoDelegate.create(fileName, photoDate);
		this.staffPhotoAssociationDelegate.create(staffMember, photo);
		// Action
		this.staffAssignmentService.remove(staffAssignment);

		// Assertions
		assert this.staffAssignmentDelegate
			.findByStaffMember(staffMember).contains(staffAssignment)!=true 
			: "staff assignment was not removed";
		
		assert this.staffPhotoAssociationDelegate.findByStaffMember(
			staffMember) == null : "staff photo association was not"
					+ "removed";
		
		assert this.photoDelegate.findByFileName(fileName) == null
			: "photo was not removed";
	}

	// Parses date text
	private Date parseDateText(final String dateText) {
		CustomDateEditor customEditor = this.customDateEditorFactory
		.createCustomDateOnlyEditor(true);
		customEditor.setAsText(dateText);
		return (Date) customEditor.getValue();
	}
}