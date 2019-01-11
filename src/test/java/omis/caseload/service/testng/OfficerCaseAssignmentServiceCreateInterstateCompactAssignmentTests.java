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
package omis.caseload.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.address.domain.Address;
import omis.address.domain.ZipCode;
import omis.address.service.delegate.AddressDelegate;
import omis.address.service.delegate.ZipCodeDelegate;
import omis.caseload.domain.InterstateCompactAssignment;
import omis.caseload.domain.InterstateCompactCorrectionalStatus;
import omis.caseload.domain.InterstateCompactEndReasonCategory;
import omis.caseload.domain.InterstateCompactTypeCategory;
import omis.caseload.domain.OfficerCaseAssignment;
import omis.caseload.domain.SupervisionLevelCategory;
import omis.caseload.service.OfficerCaseAssignmentService;
import omis.caseload.service.delegate.InterstateCompactAssignmentDelegate;
import omis.caseload.service.delegate.InterstateCompactCorrectionalStatusDelegate;
import omis.caseload.service.delegate.InterstateCompactEndReasonCategoryDelegate;
import omis.caseload.service.delegate.InterstateCompactTypeCategoryDelegate;
import omis.caseload.service.delegate.OfficerCaseAssignmentDelegate;
import omis.caseload.service.delegate.SupervisionLevelCategoryDelegate;
import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.datatype.DateRange;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.location.domain.Location;
import omis.location.service.delegate.LocationDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.organization.domain.Organization;
import omis.organization.service.delegate.OrganizationDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.service.delegate.CorrectionalStatusDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to create interstate compact assignments.
 *
 * @author Josh Divine
 * @version 0.1.0 (Sep 7, 2018)
 * @since OMIS 3.0
 */
@Test
public class OfficerCaseAssignmentServiceCreateInterstateCompactAssignmentTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

	@Autowired
	private InterstateCompactAssignmentDelegate 
			interstateCompactAssignmentDelegate;
	
	@Autowired
	private OfficerCaseAssignmentDelegate officerCaseAssignmentDelegate;

	@Autowired
	private StateDelegate stateDelegate;

	@Autowired
	private InterstateCompactCorrectionalStatusDelegate 
			interstateCompactCorrectionalStatusDelegate;

	@Autowired
	private InterstateCompactEndReasonCategoryDelegate 
			interstateCompactEndReasonCategoryDelegate;

	@Autowired
	private InterstateCompactTypeCategoryDelegate 
			interstateCompactTypeCategoryDelegate;

	@Autowired
	private OffenderDelegate offenderDelegate;

	@Autowired
	private UserAccountDelegate userAccountDelegate;

	@Autowired
	private LocationDelegate locationDelegate;

	@Autowired
	private SupervisionLevelCategoryDelegate supervisionLevelCategoryDelegate;

	@Autowired
	private PersonDelegate personDelegate;
	
	@Autowired
	private OrganizationDelegate organizationDelegate;
	
	@Autowired
	private AddressDelegate addressDelegate;
	
	@Autowired
	private CountryDelegate countryDelegate;
	
	@Autowired
	private CityDelegate cityDelegate;
	
	@Autowired
	private ZipCodeDelegate zipCodeDelegate;
	
	@Autowired
	private CorrectionalStatusDelegate correctionalStatusDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("officerCaseAssignmentService")
	private OfficerCaseAssignmentService officerCaseAssignmentService;

	/* Test methods. */

	/**
	 * Tests the creation of interstate compact assignments.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if date conflict exists
	 */
	@Test
	public void testCreateInterstateCompactAssignment() 
			throws DuplicateEntityFoundException, DateConflictException {
		// Arrangements
		Country country = this.countryDelegate.create(
				"Country", "USA", true);
		OfficerCaseAssignment officerCaseAssignment = 
				createOfficerCaseAssignment(country);
		State jurisdiction = this.stateDelegate.create("State2", "S2", country, 
				false, true);
		String jurisdictionStateId = "State ID#1";
		Date projectedEndDate = this.parseDateText("09/07/2018");
		CorrectionalStatus correctionalStatus = this.correctionalStatusDelegate
				.create("Status", "S", false, (short) 1, true);
		InterstateCompactCorrectionalStatus interstateCompactStatus = this
				.interstateCompactCorrectionalStatusDelegate.create(
						correctionalStatus, true);
		InterstateCompactEndReasonCategory endReason = this
				.interstateCompactEndReasonCategoryDelegate.create("Reason", 
						true);
		InterstateCompactTypeCategory interstateCompactType = this
				.interstateCompactTypeCategoryDelegate.create("Type", true);

		// Action
		InterstateCompactAssignment interstateCompactAssignment = this
				.officerCaseAssignmentService.createInterstateCompactAssignment(
						officerCaseAssignment, jurisdiction, 
						jurisdictionStateId, projectedEndDate, 
						interstateCompactStatus, endReason, 
						interstateCompactType);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("officerCaseAssignment", officerCaseAssignment)
			.addExpectedValue("jurisdiction", jurisdiction)
			.addExpectedValue("jurisdictionStateId", jurisdictionStateId)
			.addExpectedValue("projectedEndDate", projectedEndDate)
			.addExpectedValue("interstateCompactStatus", 
					interstateCompactStatus)
			.addExpectedValue("endReason", endReason)
			.addExpectedValue("interstateCompactType", interstateCompactType)
			.performAssertions(interstateCompactAssignment);
	}

	/**
	 * Tests {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if date conflict exists
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
			throws DuplicateEntityFoundException, DateConflictException {
		// Arrangements
		Country country = this.countryDelegate.create("Country", "USA", true);
		OfficerCaseAssignment officerCaseAssignment = 
				createOfficerCaseAssignment(country);
		State jurisdiction = this.stateDelegate.create("State2", "S2", country, 
				false, true);
		String jurisdictionStateId = "State ID#1";
		Date projectedEndDate = this.parseDateText("09/07/2018");
		CorrectionalStatus correctionalStatus = this.correctionalStatusDelegate
				.create("Status", "S", false, (short) 1, true);
		InterstateCompactCorrectionalStatus interstateCompactStatus = this
				.interstateCompactCorrectionalStatusDelegate.create(
						correctionalStatus, true);
		InterstateCompactEndReasonCategory endReason = this
				.interstateCompactEndReasonCategoryDelegate.create("Reason", 
						true);
		InterstateCompactTypeCategory interstateCompactType = this
				.interstateCompactTypeCategoryDelegate.create("Type", true);
		this.interstateCompactAssignmentDelegate.create(officerCaseAssignment, 
				jurisdiction, jurisdictionStateId, projectedEndDate, 
				interstateCompactStatus, endReason, interstateCompactType);

		// Action
		this.officerCaseAssignmentService.createInterstateCompactAssignment(
				officerCaseAssignment, jurisdiction, jurisdictionStateId, 
				projectedEndDate, interstateCompactStatus, endReason,
				interstateCompactType);
	}

	// Creates an officer case assignment
	private OfficerCaseAssignment createOfficerCaseAssignment(Country country) 
			throws DuplicateEntityFoundException, DateConflictException {
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", null, null);
		Person user = this.personDelegate.create("Smith", "Ted", null, null);
		UserAccount officer = this.userAccountDelegate.create(user, "Username", 
				"", null, 0, true);
		Date startDate = this.parseDateText("01/01/2018");
		Date endDate = this.parseDateText("12/31/2018");
		Organization organization = this.organizationDelegate.create(
				"Organization", "org", null);
		State state = this.stateDelegate.create("State", "ST", country, true, 
				true);
		City city = this.cityDelegate.create("City", true, state, country);
		ZipCode zipCode = this.zipCodeDelegate.create(city, "12345", null, true);
		Address address = this.addressDelegate.findOrCreate("123", "321", null, 
				null, zipCode);
		Location supervisionOffice = this.locationDelegate.create(organization, 
				new DateRange(this.parseDateText("01/01/2000"), null), address);
		SupervisionLevelCategory supervisionLevel = this
				.supervisionLevelCategoryDelegate.create("Description", true);
		return this.officerCaseAssignmentDelegate.create(offender, officer, 
				startDate, endDate, supervisionOffice, supervisionLevel);
	}
	
	// Parses date text
	private Date parseDateText(final String text) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(text);
		} catch (ParseException e) {
			throw new RuntimeException("Parse error", e);
		}
	}
}