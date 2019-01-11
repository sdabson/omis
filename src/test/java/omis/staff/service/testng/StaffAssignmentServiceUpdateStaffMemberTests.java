package omis.staff.service.testng;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.testng.annotations.Test;

import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.datatype.DateRange;
import omis.demographics.domain.Sex;
import omis.person.domain.AlternativeNameCategory;
import omis.person.domain.Person;
import omis.person.domain.PersonName;
import omis.person.exception.AlternativeNameAssociationExistsException;
import omis.person.exception.PersonExistsException;
import omis.person.exception.PersonIdentityExistsException;
import omis.person.exception.PersonNameExistsException;
import omis.person.service.delegate.AlternativeIdentityCategoryDelegate;
import omis.person.service.delegate.AlternativeNameAssociationDelegate;
import omis.person.service.delegate.AlternativeNameCategoryDelegate;
import omis.person.service.delegate.PersonDelegate;
import omis.person.service.delegate.PersonIdentityDelegate;
import omis.person.service.delegate.PersonNameDelegate;
import omis.staff.service.StaffAssignmentService;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to update staff member.
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"staff", "service"})
public class StaffAssignmentServiceUpdateStaffMemberTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Delegate. */
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("personNameDelegate")
	private PersonNameDelegate personNameDelegate;
	
	@Autowired
	@Qualifier("personIdentityDelegate")
	private PersonIdentityDelegate personIdentityDelegate;
	
	@Autowired
	@Qualifier("alternativeIdentityCategoryDelegate")
	private AlternativeIdentityCategoryDelegate 
		alternativeIdentityCategoryDelegate;
	
	@Autowired
	@Qualifier("alternativeNameAssociationDelegate")
	AlternativeNameAssociationDelegate alternativeNameAssociationDelegate; 
	
	@Autowired
	@Qualifier("alternativeNameCategoryDelegate")
	AlternativeNameCategoryDelegate alternativeNameCategoryDelegate;
	
	
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
	 * Tests the update of a staff member.
	 * 
	 * @throws PersonExistsException if staff member already exists
	 * @throws PersonNameExistsException if name already exists
	 * @throws PersonIdentityExistsException if identity already exists
	 */
	public void testStaffMemberUpdate() throws PersonExistsException,
	PersonNameExistsException, PersonIdentityExistsException {
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
		
		String lastNameUpdated = "Li";
		String firstNameUpdated = "Lisa";
		String middleNameUpdated = "311CIC";
		String suffixUpdated = "Sir";
		Sex sexUpdated = Sex.FEMALE;
		Date birthDateUpdated = new Date(25000000);
		
		// Action
		this.staffAssignmentService.updateStaffMember(staffMember,
			lastNameUpdated, firstNameUpdated, middleNameUpdated,
			suffixUpdated, birthDateUpdated, sexUpdated);	
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("name.lastName", lastNameUpdated)
			.addExpectedValue("name.firstName", firstNameUpdated)
			.addExpectedValue("name.middleName", middleNameUpdated)
			.addExpectedValue("name.suffix", suffixUpdated)
			.addExpectedValue("identity.sex", sexUpdated)
			.addExpectedValue("identity.birthDate", birthDateUpdated)
			.performAssertions(staffMember);
	}

	/**
	 * Tests that {@code PersonNameExistsException} is thrown.
	 * 
	 * @throws PersonExistsException if person already exists
	 * @throws PersonIdentityExistsException if identity already exists
	 * @throws PersonNameExistsException if name already exists
	 * @throws AlternativeNameAssociationExistsException 
	 */
	@Test(expectedExceptions = {PersonNameExistsException.class})
	public void testPersonNameExistsException() 
		throws PersonExistsException, PersonNameExistsException,
		PersonIdentityExistsException,
		AlternativeNameAssociationExistsException {
		// Arrangements
		Person person = this.personDelegate.create("tempLastName",
			"tempFirstName", "tempMiddleName", "Mr.");
		DateRange dateRange = new DateRange(
			this.parseDateText("01/01/2017"),
			this.parseDateText("01/01/2018"));
		AlternativeNameCategory category
		= this.alternativeNameCategoryDelegate.create(
		"This is an alternative name category", "category",
		new Short((short) 1), true);
		PersonName name = this.personNameDelegate.create(person,
			"lastName", "firstName", "middleName", "Ms.");
		this.alternativeNameAssociationDelegate.create(
			name, dateRange, category);
		Sex sex = Sex.MALE;
		Date birthDate = new Date(20000000);
		//Action
		this.staffAssignmentService.updateStaffMember(person,
			"lastName", "firstName", "middleName", "Ms.",
			birthDate, sex);
	}
	
	/**
	 * Tests that {@code PersonIdentityExistsException} is thrown.
	 * 
	 * @throws PersonExistsException if person already exists
	 * @throws PersonIdentityExistsException 
	 * @throws PersonNameExistsException 
	 */
	@Test(expectedExceptions = {PersonIdentityExistsException.class})
	public void testPersonIdentityExistsException() 
		throws PersonExistsException, PersonNameExistsException,
		PersonIdentityExistsException {
		// Arrangement
		Person person = this.personDelegate.create("tempLastName",
			"tempFirstName", "tempMiddleName", "Mr.");
		this.personIdentityDelegate.create(person, Sex.MALE,
			new Date(20000000), null, null, null, null, null,
			null, null);
		this.alternativeIdentityCategoryDelegate.create(
			"This is alternative identity category", "Identity",
			new Short((short) 1), true);
		//Action
		this.staffAssignmentService.updateStaffMember(person,
			"tempLastName", "tempFirstName", "tempMiddleName", "Mr.",
			new Date(20000000), Sex.MALE);
	}
	
	/**
	 * Tests that {@code PersonExistsException} is thrown.
	 * 
	 * @throws PersonExistsException if person already exists
	 * @throws PersonIdentityExistsException 
	 * @throws PersonNameExistsException 
	 */
	@Test(expectedExceptions = {PersonExistsException.class},
		enabled=false)
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
		Person staffMember = this.personDelegate
		.createWithIdentity(lastName, firstName, middleName,
		suffix, sex, birthDate,	null, null, null, null, null,
		null, null);

		// Action
		this.staffAssignmentService.updateStaffMember(staffMember,
		lastName, firstName, middleName, suffix, birthDate, sex);
	}
	
	// Parses date text
	private Date parseDateText(final String dateText) {
		CustomDateEditor customEditor 
			= this.customDateEditorFactory
			.createCustomDateOnlyEditor(true);
		customEditor.setAsText(dateText);
		return (Date) customEditor.getValue();
	}
}