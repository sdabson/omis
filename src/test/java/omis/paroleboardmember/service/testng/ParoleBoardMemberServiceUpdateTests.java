package omis.paroleboardmember.service.testng;

import omis.paroleboardmember.service.ParoleBoardMemberService;
import omis.paroleboardmember.service.delegate.ParoleBoardMemberDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import omis.datatype.DateRange;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.paroleboardmember.domain.ParoleBoardMember;
import omis.staff.domain.StaffAssignment;
import omis.staff.domain.StaffTitle;
import omis.staff.service.delegate.StaffAssignmentDelegate;
import omis.staff.service.delegate.StaffTitleDelegate;
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.service.delegate.SupervisoryOrganizationDelegate;

/**
 * Tests method to update parole board members.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class ParoleBoardMemberServiceUpdateTests
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
	@Qualifier("paroleBoardMemberDelegate")
	private ParoleBoardMemberDelegate paroleBoardMemberDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("paroleBoardMemberService")
	private ParoleBoardMemberService paroleBoardMemberService;

	/* Test methods. */

	/**
	 * Tests the update of the start date for a parole board member.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if parole board member date range not 
	 * within staff assignment date range
	 */
	@Test
	public void testUpdateStartDate() throws DuplicateEntityFoundException, 
			DateConflictException {
		// Arrangements
		Date startDate = this.parseDateText("01/01/2017");
		Date endDate = null;
		Person staffMember = this.personDelegate.create("Smith", "John", "Jay", 
				null);
		SupervisoryOrganization supervisoryOrganization = this
				.supervisoryOrganizationDelegate.create("SupOrg", "SO", null);
		DateRange dateRange = new DateRange(startDate, endDate);
		StaffTitle title = this.staffTitleDelegate.create("Title", (short) 1, 
				true);
		StaffAssignment staffAssignment = this.staffAssignmentDelegate.create(
				staffMember, supervisoryOrganization, null, dateRange, title, 
				true, null, null, null, null, null);
		ParoleBoardMember paroleBoardMember = this.paroleBoardMemberDelegate
				.create(staffAssignment, dateRange);
		Date newStartDate = this.parseDateText("02/01/2017");
		
		// Action
		paroleBoardMember = this.paroleBoardMemberService.update(
				paroleBoardMember, newStartDate, endDate);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("staffAssignment", staffAssignment)
			.addExpectedValue("dateRange.startDate", newStartDate)
			.addExpectedValue("dateRange.endDate", endDate)
			.performAssertions(paroleBoardMember);
	}

	/**
	 * Tests the update of the end date for a parole board member.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if parole board member date range not 
	 * within staff assignment date range
	 */
	@Test
	public void testUpdateEndDate() throws DuplicateEntityFoundException, 
			DateConflictException {
		// Arrangements
		Date startDate = this.parseDateText("01/01/2017");
		Date endDate = null;
		Person staffMember = this.personDelegate.create("Smith", "John", "Jay", 
				null);
		SupervisoryOrganization supervisoryOrganization = this
				.supervisoryOrganizationDelegate.create("SupOrg", "SO", null);
		DateRange dateRange = new DateRange(startDate, endDate);
		StaffTitle title = this.staffTitleDelegate.create("Title", (short) 1, 
				true);
		StaffAssignment staffAssignment = this.staffAssignmentDelegate.create(
				staffMember, supervisoryOrganization, null, dateRange, title, 
				true, null, null, null, null, null);
		ParoleBoardMember paroleBoardMember = this.paroleBoardMemberDelegate
				.create(staffAssignment, dateRange);
		Date newEndDate = this.parseDateText("12/31/2017");
		
		// Action
		paroleBoardMember = this.paroleBoardMemberService.update(
				paroleBoardMember, startDate, newEndDate);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("staffAssignment", staffAssignment)
			.addExpectedValue("dateRange.startDate", startDate)
			.addExpectedValue("dateRange.endDate", newEndDate)
			.performAssertions(paroleBoardMember);
	}

	/**
	 * Test that {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if parole board member date range not 
	 * within staff assignment date range
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
			throws DuplicateEntityFoundException, DateConflictException {
		// Arrangements
		Date startDate = this.parseDateText("01/01/2017");
		Date endDate = null;
		Person staffMember = this.personDelegate.create("Smith", "John", "Jay", 
				null);
		SupervisoryOrganization supervisoryOrganization = this
				.supervisoryOrganizationDelegate.create("SupOrg", "SO", null);
		DateRange dateRange = new DateRange(startDate, endDate);
		StaffTitle title = this.staffTitleDelegate.create("Title", (short) 1, 
				true);
		StaffAssignment staffAssignment = this.staffAssignmentDelegate.create(
				staffMember, supervisoryOrganization, null, dateRange, title, 
				true, null, null, null, null, null);
		this.paroleBoardMemberDelegate.create(staffAssignment, dateRange);
		Date secondStartDate = this.parseDateText("02/01/2017");
		ParoleBoardMember paroleBoardMember = this.paroleBoardMemberDelegate
				.create(staffAssignment, new DateRange(secondStartDate, null));
		
		// Action
		paroleBoardMember = this.paroleBoardMemberService.update(
				paroleBoardMember, startDate, endDate);
	}

	/**
	 * Test that {@code DateConflictException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if parole board member date range not 
	 * within staff assignment date range
	 */
	@Test(expectedExceptions = {DateConflictException.class})
	public void testDateConflictException() 
			throws DuplicateEntityFoundException, DateConflictException {
		// Arrangements
		Date startDate = this.parseDateText("01/01/2017");
		Date endDate = this.parseDateText("01/31/2017");
		Person staffMember = this.personDelegate.create("Smith", "John", "Jay", 
				null);
		SupervisoryOrganization supervisoryOrganization = this
				.supervisoryOrganizationDelegate.create("SupOrg", "SO", null);
		DateRange dateRange = new DateRange(startDate, endDate);
		StaffTitle title = this.staffTitleDelegate.create("Title", (short) 1, 
				true);
		StaffAssignment staffAssignment = this.staffAssignmentDelegate.create(
				staffMember, supervisoryOrganization, null, dateRange, title, 
				true, null, null, null, null, null);
		this.paroleBoardMemberDelegate.create(staffAssignment, dateRange);
		ParoleBoardMember paroleBoardMember = this.paroleBoardMemberDelegate
				.create(staffAssignment, new DateRange(
						this.parseDateText("01/03/2017"), endDate));
		Date newStartDate = this.parseDateText("01/02/2017");
		
		// Action
		paroleBoardMember = this.paroleBoardMemberService.update(
				paroleBoardMember, newStartDate, null);
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