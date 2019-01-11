package omis.staff.service.testng;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.demographics.domain.Sex;
import omis.person.domain.Person;
import omis.person.exception.PersonExistsException;
import omis.person.exception.PersonIdentityExistsException;
import omis.person.exception.PersonNameExistsException;
import omis.person.service.delegate.PersonDelegate;
import omis.staff.service.StaffAssignmentService;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to create staff member.
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"staff", "service"})
public class StaffAssignmentServiceCreateStaffMemberTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Delegate */
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;

	/* Service */
	@Autowired
	@Qualifier("staffAssignmentService")
	private StaffAssignmentService staffAssignmentService;
	
	/* Test methods. */

	/**
	 * Tests the creation of a staff member.
	 * 
	 * @throws PersonExistsException if staff member already exists
	 */
	public void testStaffMemberCreation() throws PersonExistsException {
		// Arrangements
		String lastName = "Smith";
		String firstName = "Yidong";
		String middleName = "CIC311";
		String suffix = "Mr.";
		Sex sex = Sex.MALE;
		Date birthDate = new Date(20000000);
		
		// Action
		Person staffMember = this.staffAssignmentService
			.createStaffMember(lastName, firstName, middleName,
			suffix, birthDate, sex);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("name.lastName", lastName)
			.addExpectedValue("name.firstName", firstName)
			.addExpectedValue("name.middleName", middleName)
			.addExpectedValue("name.suffix", suffix)
			.addExpectedValue("identity.sex", sex)
			.addExpectedValue("identity.birthDate", birthDate)
			.performAssertions(staffMember);
	}
	
	/**
	 * Tests that {@code PersonExistsException} is thrown.
	 * 
	 * @throws PersonExistsException if person already exists
	 * @throws PersonIdentityExistsException 
	 * @throws PersonNameExistsException 
	 */
	@Test(expectedExceptions = {PersonExistsException.class},
		enabled = false)
	public void testPersonExistsException() 
		throws PersonExistsException, PersonNameExistsException,
		PersonIdentityExistsException  {
		// Arrangements
		String lastName = "Smith";
		String firstName = "Yidong";
		String middleName = "CIC311";
		String suffix = "Mr.";
		Sex sex = Sex.MALE;
		Date birthDate = new Date(20000000);
		Person staffMember = this.personDelegate.createWithIdentity(
			lastName, firstName, middleName, suffix, sex, birthDate,
			null, null, null, null, null, null, null);

		// Action
		this.staffAssignmentService.updateStaffMember(
			staffMember, lastName, firstName, middleName, suffix,
			birthDate, sex);
	}
}