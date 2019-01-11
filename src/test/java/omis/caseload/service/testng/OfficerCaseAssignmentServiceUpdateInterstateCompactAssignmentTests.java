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
 * Tests method to update an interstate compact assignment.
 *
 * @author Josh Divine
 * @version 0.1.0 (Sep 7, 2018)
 * @since OMIS 3.0
 */
@Test
public class OfficerCaseAssignmentServiceUpdateInterstateCompactAssignmentTests
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
	 * Tests the update of the jurisdiction for an interstate compact 
	 * assignment.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if date conflict exists
	 */
	@Test
	public void testUpdateInterstateCompactAssignment() 
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
		InterstateCompactAssignment interstateCompactAssignment = this
				.interstateCompactAssignmentDelegate.create(
						officerCaseAssignment, jurisdiction, 
						jurisdictionStateId, projectedEndDate, 
						interstateCompactStatus, endReason, 
						interstateCompactType);
		State newJurisdiction = this.stateDelegate.create("State3", "S3", 
				country, false, true);
		
		// Action
		interstateCompactAssignment = this.officerCaseAssignmentService
				.updateInterstateCompactAssignment(interstateCompactAssignment, 
						officerCaseAssignment, newJurisdiction, 
						jurisdictionStateId, projectedEndDate,
						interstateCompactStatus, endReason, 
						interstateCompactType);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("officerCaseAssignment", officerCaseAssignment)
			.addExpectedValue("jurisdiction", newJurisdiction)
			.addExpectedValue("jurisdictionStateId", jurisdictionStateId)
			.addExpectedValue("projectedEndDate", projectedEndDate)
			.addExpectedValue("interstateCompactStatus", 
					interstateCompactStatus)
			.addExpectedValue("endReason", endReason)
			.addExpectedValue("interstateCompactType", interstateCompactType)
			.performAssertions(interstateCompactAssignment);
	}
	
	/**
	 * Tests the update of the jurisdiction state id for an interstate compact 
	 * assignment.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if date conflict exists
	 */
	@Test
	public void testUpdateInterstateCompactAssignmentJurisdictionStateId() 
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
		InterstateCompactAssignment interstateCompactAssignment = this
				.interstateCompactAssignmentDelegate.create(
						officerCaseAssignment, jurisdiction, 
						jurisdictionStateId, projectedEndDate, 
						interstateCompactStatus, endReason, 
						interstateCompactType);
		String newJurisdictionStateId = "State ID#2";
		
		// Action
		interstateCompactAssignment = this.officerCaseAssignmentService
				.updateInterstateCompactAssignment(interstateCompactAssignment, 
						officerCaseAssignment, jurisdiction, 
						newJurisdictionStateId, projectedEndDate,
						interstateCompactStatus, endReason, 
						interstateCompactType);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("officerCaseAssignment", officerCaseAssignment)
			.addExpectedValue("jurisdiction", jurisdiction)
			.addExpectedValue("jurisdictionStateId", newJurisdictionStateId)
			.addExpectedValue("projectedEndDate", projectedEndDate)
			.addExpectedValue("interstateCompactStatus", 
					interstateCompactStatus)
			.addExpectedValue("endReason", endReason)
			.addExpectedValue("interstateCompactType", interstateCompactType)
			.performAssertions(interstateCompactAssignment);
	}
	
	/**
	 * Tests the update of the projected end date for an interstate compact 
	 * assignment.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if date conflict exists
	 */
	@Test
	public void testUpdateInterstateCompactAssignmentProjectedEndDate() 
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
		InterstateCompactAssignment interstateCompactAssignment = this
				.interstateCompactAssignmentDelegate.create(
						officerCaseAssignment, jurisdiction, 
						jurisdictionStateId, projectedEndDate, 
						interstateCompactStatus, endReason, 
						interstateCompactType);
		Date newProjectedEndDate = this.parseDateText("09/08/2018");
		
		// Action
		interstateCompactAssignment = this.officerCaseAssignmentService
				.updateInterstateCompactAssignment(interstateCompactAssignment, 
						officerCaseAssignment, jurisdiction, 
						jurisdictionStateId, newProjectedEndDate,
						interstateCompactStatus, endReason, 
						interstateCompactType);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("officerCaseAssignment", officerCaseAssignment)
			.addExpectedValue("jurisdiction", jurisdiction)
			.addExpectedValue("jurisdictionStateId", jurisdictionStateId)
			.addExpectedValue("projectedEndDate", newProjectedEndDate)
			.addExpectedValue("interstateCompactStatus", 
					interstateCompactStatus)
			.addExpectedValue("endReason", endReason)
			.addExpectedValue("interstateCompactType", interstateCompactType)
			.performAssertions(interstateCompactAssignment);
	}
	
	/**
	 * Tests the update of the interstate compact correctional status for an 
	 * interstate compact assignment.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if date conflict exists
	 */
	@Test
	public void testUpdateInterstateCompactAssignmentInterstateCompactStatus() 
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
		InterstateCompactAssignment interstateCompactAssignment = this
				.interstateCompactAssignmentDelegate.create(
						officerCaseAssignment, jurisdiction, 
						jurisdictionStateId, projectedEndDate, 
						interstateCompactStatus, endReason, 
						interstateCompactType);
		CorrectionalStatus newCorrectionalStatus = this
				.correctionalStatusDelegate.create("Status2", "S2", false, 
						(short) 1, true);
		InterstateCompactCorrectionalStatus newInterstateCompactStatus = this
				.interstateCompactCorrectionalStatusDelegate.create(
						newCorrectionalStatus, true);
		
		// Action
		interstateCompactAssignment = this.officerCaseAssignmentService
				.updateInterstateCompactAssignment(interstateCompactAssignment, 
						officerCaseAssignment, jurisdiction, 
						jurisdictionStateId, projectedEndDate,
						newInterstateCompactStatus, endReason, 
						interstateCompactType);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("officerCaseAssignment", officerCaseAssignment)
			.addExpectedValue("jurisdiction", jurisdiction)
			.addExpectedValue("jurisdictionStateId", jurisdictionStateId)
			.addExpectedValue("projectedEndDate", projectedEndDate)
			.addExpectedValue("interstateCompactStatus", 
					newInterstateCompactStatus)
			.addExpectedValue("endReason", endReason)
			.addExpectedValue("interstateCompactType", interstateCompactType)
			.performAssertions(interstateCompactAssignment);
	}
	
	/**
	 * Tests the update of the interstate compact end reason category for an 
	 * interstate compact assignment.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if date conflict exists
	 */
	@Test
	public void testUpdateInterstateCompactAssignmentEndReason() 
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
		InterstateCompactAssignment interstateCompactAssignment = this
				.interstateCompactAssignmentDelegate.create(
						officerCaseAssignment, jurisdiction, 
						jurisdictionStateId, projectedEndDate, 
						interstateCompactStatus, endReason, 
						interstateCompactType);
		InterstateCompactEndReasonCategory newEndReason = this
				.interstateCompactEndReasonCategoryDelegate.create("Reason2", 
						true);
		
		// Action
		interstateCompactAssignment = this.officerCaseAssignmentService
				.updateInterstateCompactAssignment(interstateCompactAssignment, 
						officerCaseAssignment, jurisdiction, 
						jurisdictionStateId, projectedEndDate,
						interstateCompactStatus, newEndReason, 
						interstateCompactType);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("officerCaseAssignment", officerCaseAssignment)
			.addExpectedValue("jurisdiction", jurisdiction)
			.addExpectedValue("jurisdictionStateId", jurisdictionStateId)
			.addExpectedValue("projectedEndDate", projectedEndDate)
			.addExpectedValue("interstateCompactStatus", 
					interstateCompactStatus)
			.addExpectedValue("endReason", newEndReason)
			.addExpectedValue("interstateCompactType", interstateCompactType)
			.performAssertions(interstateCompactAssignment);
	}
	
	/**
	 * Tests the update of the interstate compact type category for an 
	 * interstate compact assignment.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if date conflict exists
	 */
	@Test
	public void testUpdateInterstateCompactAssignmentInterstateCompactType() 
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
		InterstateCompactAssignment interstateCompactAssignment = this
				.interstateCompactAssignmentDelegate.create(
						officerCaseAssignment, jurisdiction, 
						jurisdictionStateId, projectedEndDate, 
						interstateCompactStatus, endReason, 
						interstateCompactType);
		InterstateCompactTypeCategory newInterstateCompactType = this
				.interstateCompactTypeCategoryDelegate.create("Type2", true);
		
		// Action
		interstateCompactAssignment = this.officerCaseAssignmentService
				.updateInterstateCompactAssignment(interstateCompactAssignment, 
						officerCaseAssignment, jurisdiction, 
						jurisdictionStateId, projectedEndDate,
						interstateCompactStatus, endReason, 
						newInterstateCompactType);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("officerCaseAssignment", officerCaseAssignment)
			.addExpectedValue("jurisdiction", jurisdiction)
			.addExpectedValue("jurisdictionStateId", jurisdictionStateId)
			.addExpectedValue("projectedEndDate", projectedEndDate)
			.addExpectedValue("interstateCompactStatus", 
					interstateCompactStatus)
			.addExpectedValue("endReason", endReason)
			.addExpectedValue("interstateCompactType", newInterstateCompactType)
			.performAssertions(interstateCompactAssignment);
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