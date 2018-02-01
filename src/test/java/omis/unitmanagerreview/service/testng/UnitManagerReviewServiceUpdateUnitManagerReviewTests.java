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
package omis.unitmanagerreview.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.staff.domain.StaffAssignment;
import omis.staff.domain.StaffTitle;
import omis.staff.service.delegate.StaffAssignmentDelegate;
import omis.staff.service.delegate.StaffTitleDelegate;
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.service.delegate.SupervisoryOrganizationDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.unitmanagerreview.domain.UnitManagerReview;
import omis.unitmanagerreview.service.UnitManagerReviewService;
import omis.unitmanagerreview.service.delegate.UnitManagerReviewDelegate;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to update unit manager reviews.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test
public class UnitManagerReviewServiceUpdateUnitManagerReviewTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

	@Autowired
	@Qualifier("staffAssignmentDelegate")
	private StaffAssignmentDelegate staffAssignmentDelegate;
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("supervisoryOrganizationDelegate")
	private SupervisoryOrganizationDelegate supervisoryOrganizationDelegate;
	
	@Autowired
	@Qualifier("staffTitleDelegate")
	private StaffTitleDelegate staffTitleDelegate;
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("unitManagerReviewDelegate")
	private UnitManagerReviewDelegate unitManagerReviewDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("unitManagerReviewService")
	private UnitManagerReviewService unitManagerReviewService;

	/* Test methods. */

	/**
	 * Tests the update of the staff assignment for a unit manger review.
	 * 
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	@Test
	public void testUpdateUnitManagerReviewStaffAssignment() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Person staffMember = this.personDelegate.create("Smith", "John", "Jay", 
				null);
		SupervisoryOrganization supervisoryOrganization = this
				.supervisoryOrganizationDelegate.create("SupOrg", "SO", null);
		StaffTitle title = this.staffTitleDelegate.create("Title", (short) 1, 
				true);
		StaffAssignment staffAssignment = this.staffAssignmentDelegate.create(
				staffMember, supervisoryOrganization, null, 
				new DateRange(this.parseDateText("01/01/2017"), null), title, 
				true, null, null, null, null, null);
		Date date = this.parseDateText("01/01/2018");
		String text = "Text";
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"Tom", "Harry", null);
		UnitManagerReview unitManagerReview = this.unitManagerReviewDelegate
				.create(staffAssignment, date, text, offender);
		Person newStaffMember = this.personDelegate.create("Smith", "Jeff", 
				"Bob", null);
		StaffAssignment newStaffAssignment = this.staffAssignmentDelegate
				.create(newStaffMember, supervisoryOrganization, null, 
				new DateRange(this.parseDateText("01/01/2017"), null), title, 
				true, null, null, null, null, null);
		
		// Action
		unitManagerReview = this.unitManagerReviewService
				.updateUnitManagerReview(unitManagerReview, newStaffAssignment, 
						date, text);

		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("staffAssignment", newStaffAssignment)
		.addExpectedValue("date", date)
		.addExpectedValue("text", text)
		.addExpectedValue("offender", offender)
		.performAssertions(unitManagerReview);
	}
	
	/**
	 * Tests the update of the date for a unit manger review.
	 * 
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	@Test
	public void testUpdateUnitManagerReviewDate() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Person staffMember = this.personDelegate.create("Smith", "John", "Jay", 
				null);
		SupervisoryOrganization supervisoryOrganization = this
				.supervisoryOrganizationDelegate.create("SupOrg", "SO", null);
		StaffTitle title = this.staffTitleDelegate.create("Title", (short) 1, 
				true);
		StaffAssignment staffAssignment = this.staffAssignmentDelegate.create(
				staffMember, supervisoryOrganization, null, 
				new DateRange(this.parseDateText("01/01/2017"), null), title, 
				true, null, null, null, null, null);
		Date date = this.parseDateText("01/01/2018");
		String text = "Text";
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"Tom", "Harry", null);
		UnitManagerReview unitManagerReview = this.unitManagerReviewDelegate
				.create(staffAssignment, date, text, offender);
		Date newDate = this.parseDateText("01/02/2018");
		
		// Action
		unitManagerReview = this.unitManagerReviewService
				.updateUnitManagerReview(unitManagerReview, staffAssignment, 
						newDate, text);

		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("staffAssignment", staffAssignment)
		.addExpectedValue("date", newDate)
		.addExpectedValue("text", text)
		.addExpectedValue("offender", offender)
		.performAssertions(unitManagerReview);
	}


	/**
	 * Tests the update of the text for a unit manger review.
	 * 
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	@Test
	public void testUpdateUnitManagerReviewText() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Person staffMember = this.personDelegate.create("Smith", "John", "Jay", 
				null);
		SupervisoryOrganization supervisoryOrganization = this
				.supervisoryOrganizationDelegate.create("SupOrg", "SO", null);
		StaffTitle title = this.staffTitleDelegate.create("Title", (short) 1, 
				true);
		StaffAssignment staffAssignment = this.staffAssignmentDelegate.create(
				staffMember, supervisoryOrganization, null, 
				new DateRange(this.parseDateText("01/01/2017"), null), title, 
				true, null, null, null, null, null);
		Date date = this.parseDateText("01/01/2018");
		String text = "Text";
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"Tom", "Harry", null);
		UnitManagerReview unitManagerReview = this.unitManagerReviewDelegate
				.create(staffAssignment, date, text, offender);
		String newText = "New text";
		
		// Action
		unitManagerReview = this.unitManagerReviewService
				.updateUnitManagerReview(unitManagerReview, staffAssignment, 
						date, newText);

		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("staffAssignment", staffAssignment)
		.addExpectedValue("date", date)
		.addExpectedValue("text", newText)
		.addExpectedValue("offender", offender)
		.performAssertions(unitManagerReview);
	}
	
	/**
	 * Tests that {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Person staffMember = this.personDelegate.create("Smith", "John", "Jay", 
				null);
		SupervisoryOrganization supervisoryOrganization = this
				.supervisoryOrganizationDelegate.create("SupOrg", "SO", null);
		StaffTitle title = this.staffTitleDelegate.create("Title", (short) 1, 
				true);
		StaffAssignment staffAssignment = this.staffAssignmentDelegate.create(
				staffMember, supervisoryOrganization, null, 
				new DateRange(this.parseDateText("01/01/2017"), null), title, 
				true, null, null, null, null, null);
		Date date = this.parseDateText("01/01/2018");
		String text = "Text";
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"Tom", "Harry", null);
		this.unitManagerReviewDelegate.create(staffAssignment, date, text, 
				offender);
		Person secondStaffMember = this.personDelegate.create("Smith", "Jeff", 
				"Bob", null);
		StaffAssignment secondStaffAssignment = this.staffAssignmentDelegate
				.create(secondStaffMember, supervisoryOrganization, null, 
				new DateRange(this.parseDateText("01/01/2017"), null), title, 
				true, null, null, null, null, null);
		UnitManagerReview unitManagerReview = this.unitManagerReviewDelegate
				.create(secondStaffAssignment, date, text, offender);
		
		// Action
		unitManagerReview = this.unitManagerReviewService
				.updateUnitManagerReview(unitManagerReview, staffAssignment, 
						date, text);
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