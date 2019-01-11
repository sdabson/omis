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
import omis.staff.exception.StaffAssignmentExistsException;
import omis.staff.service.StaffAssignmentService;
import omis.staff.service.delegate.StaffAssignmentDelegate;
import omis.staff.service.delegate.StaffTitleDelegate;
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.service.delegate.SupervisoryOrganizationDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to create staff assignment.
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = { "staff", "service" })
public class StaffAssignmentServiceCreateStaffAssignmentTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Delegate */
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
	 * Tests the creation of a staff assignment.
	 * 
	 * @throws DuplicateEntityFoundException
	 */
	public void testStaffAssignmentCreation()
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

		// Action
		StaffAssignment staffAssignment = this.staffAssignmentService
			.createStaffAssignment(staffMember,
			supervisoryOrganization, location,
			organizationDivision, staffAssignmentDateRange, title, true, "1");

		// Assertions
		PropertyValueAsserter.create().addExpectedValue(
			"staffMember", staffMember)
			.addExpectedValue("supervisoryOrganization",
				supervisoryOrganization)
			.addExpectedValue("dateRange", staffAssignmentDateRange)
			.addExpectedValue("supervisory", true)
			.addExpectedValue("staffId", "1").addExpectedValue(
			"organizationDivision", organizationDivision)
			.performAssertions(staffAssignment);
	}
	
	/**
	 * Tests that {@StaffAssignmentExistsException} is thrown.
	 * @throws DuplicateEntityFoundException 
	 * @throws StaffAssignmentExistsException
	 */
	@Test(expectedExceptions = {StaffAssignmentExistsException.class})
	public void testStaffAssignmentExistsException()
		throws StaffAssignmentExistsException,
		DuplicateEntityFoundException {
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
		Organization organization = this.organizationDelegate.create(
			"Organization", "Org", null);
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

		this.staffAssignmentDelegate.create(staffMember,
			supervisoryOrganization, location, staffAssignmentDateRange,
			title, true, organizationDivision, "1", null, null, null);
		
		// Action
		this.staffAssignmentService.createStaffAssignment(staffMember,
			supervisoryOrganization, location, organizationDivision,
			staffAssignmentDateRange, title, true, "1");
	}

	// Parses date text
	private Date parseDateText(final String dateText) {
		CustomDateEditor customEditor = this.customDateEditorFactory
		.createCustomDateOnlyEditor(true);
		customEditor.setAsText(dateText);
		return (Date) customEditor.getValue();
	}
}