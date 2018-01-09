package omis.visitation.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.demographics.domain.Sex;
import omis.exception.DuplicateEntityFoundException;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.person.service.delegate.PersonIdentityDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;
import omis.visitation.service.VisitationAssociationService;

/**
 * Tests method to update visitors.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"visitation", "service"})
public class VisitationAssociationServiceUpdateVisitorTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("countryDelegate")
	private CountryDelegate countryDelegate;

	@Autowired
	@Qualifier("stateDelegate")
	private StateDelegate stateDelegate;

	@Autowired
	@Qualifier("cityDelegate")
	private CityDelegate cityDelegate;
	
	@Autowired
	@Qualifier("personIdentityDelegate")
	private PersonIdentityDelegate personIdentityDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("visitationAssociationService")
	private VisitationAssociationService visitationAssociationService;

	/* Test methods. */

	/**
	 * Tests the update of the last name for a visitor.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateVisitorLastName() 
			throws DuplicateEntityFoundException {
		// Arrangements
		String lastName = "Smith";
		String firstName = "John";
		String middleName = "Jay";
		String suffix = "C";
		Integer socialSecurityNumber = 123456789;
		Date birthDate = this.parseDateText("01/01/1980");
		Country birthCountry = this.countryDelegate.create("Country", "C", 
				true);
		State birthState = this.stateDelegate.create("State", "S", birthCountry, 
				false, true);
		City birthCity = this.cityDelegate.create("City", true, birthState, 
				birthCountry);
		Sex sex = Sex.MALE;
		String stateIdNumber = "1234567890";
		Date deathDate = null;
		Boolean deceased = false;
		Person visitor = this.personDelegate.createWithIdentity(lastName, 
				firstName, middleName, suffix, sex, birthDate, birthCountry, 
				birthState, birthCity, socialSecurityNumber, stateIdNumber, 
				deceased, deathDate);
		String newLastName = "Smithy";
		
		// Action
		visitor = this.visitationAssociationService.updateVisitor(visitor, 
				newLastName, firstName, middleName, suffix,
				socialSecurityNumber, birthDate, birthCity, birthState, 
				birthCountry, sex, stateIdNumber, deathDate, deceased);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("name.lastName", newLastName)
			.addExpectedValue("name.firstName", firstName)
			.addExpectedValue("name.middleName", middleName)
			.addExpectedValue("name.suffix", suffix)
			.addExpectedValue("identity.socialSecurityNumber", 
					socialSecurityNumber)
			.addExpectedValue("identity.birthDate", birthDate)
			.addExpectedValue("identity.birthPlace", birthCity)
			.addExpectedValue("identity.birthState", birthState)
			.addExpectedValue("identity.birthCountry", birthCountry)
			.addExpectedValue("identity.sex", sex)
			.addExpectedValue("identity.stateIdNumber", stateIdNumber)
			.addExpectedValue("identity.deathDate", deathDate)
			.addExpectedValue("identity.deceased", deceased)
			.performAssertions(visitor);
	}

	/**
	 * Tests the update of the first name for a visitor.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateVisitorFirstName() 
			throws DuplicateEntityFoundException {
		// Arrangements
		String lastName = "Smith";
		String firstName = "John";
		String middleName = "Jay";
		String suffix = "C";
		Integer socialSecurityNumber = 123456789;
		Date birthDate = this.parseDateText("01/01/1980");
		Country birthCountry = this.countryDelegate.create("Country", "C", 
				true);
		State birthState = this.stateDelegate.create("State", "S", birthCountry, 
				false, true);
		City birthCity = this.cityDelegate.create("City", true, birthState, 
				birthCountry);
		Sex sex = Sex.MALE;
		String stateIdNumber = "1234567890";
		Date deathDate = null;
		Boolean deceased = false;
		Person visitor = this.personDelegate.createWithIdentity(lastName, 
				firstName, middleName, suffix, sex, birthDate, birthCountry, 
				birthState, birthCity, socialSecurityNumber, stateIdNumber, 
				deceased, deathDate);
		String newFirstName = "Johnathan";
		
		// Action
		visitor = this.visitationAssociationService.updateVisitor(visitor, 
				lastName, newFirstName, middleName, suffix, 
				socialSecurityNumber, birthDate, birthCity, birthState, 
				birthCountry, sex, stateIdNumber, deathDate, deceased);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("name.lastName", lastName)
			.addExpectedValue("name.firstName", newFirstName)
			.addExpectedValue("name.middleName", middleName)
			.addExpectedValue("name.suffix", suffix)
			.addExpectedValue("identity.socialSecurityNumber", 
					socialSecurityNumber)
			.addExpectedValue("identity.birthDate", birthDate)
			.addExpectedValue("identity.birthPlace", birthCity)
			.addExpectedValue("identity.birthState", birthState)
			.addExpectedValue("identity.birthCountry", birthCountry)
			.addExpectedValue("identity.sex", sex)
			.addExpectedValue("identity.stateIdNumber", stateIdNumber)
			.addExpectedValue("identity.deathDate", deathDate)
			.addExpectedValue("identity.deceased", deceased)
			.performAssertions(visitor);
	}
	
	/**
	 * Tests the update of the middle name for a visitor.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateVisitorMiddleName() 
			throws DuplicateEntityFoundException {
		// Arrangements
		String lastName = "Smith";
		String firstName = "John";
		String middleName = "Jay";
		String suffix = "C";
		Integer socialSecurityNumber = 123456789;
		Date birthDate = this.parseDateText("01/01/1980");
		Country birthCountry = this.countryDelegate.create("Country", "C", 
				true);
		State birthState = this.stateDelegate.create("State", "S", birthCountry, 
				false, true);
		City birthCity = this.cityDelegate.create("City", true, birthState, 
				birthCountry);
		Sex sex = Sex.MALE;
		String stateIdNumber = "1234567890";
		Date deathDate = null;
		Boolean deceased = false;
		Person visitor = this.personDelegate.createWithIdentity(lastName, 
				firstName, middleName, suffix, sex, birthDate, birthCountry, 
				birthState, birthCity, socialSecurityNumber, stateIdNumber, 
				deceased, deathDate);
		String newMiddleName = "Jeff";
		
		// Action
		visitor = this.visitationAssociationService.updateVisitor(visitor, 
				lastName, firstName, newMiddleName, suffix, 
				socialSecurityNumber, birthDate, birthCity, birthState, 
				birthCountry, sex, stateIdNumber, deathDate, deceased);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("name.lastName", lastName)
			.addExpectedValue("name.firstName", firstName)
			.addExpectedValue("name.middleName", newMiddleName)
			.addExpectedValue("name.suffix", suffix)
			.addExpectedValue("identity.socialSecurityNumber", 
					socialSecurityNumber)
			.addExpectedValue("identity.birthDate", birthDate)
			.addExpectedValue("identity.birthPlace", birthCity)
			.addExpectedValue("identity.birthState", birthState)
			.addExpectedValue("identity.birthCountry", birthCountry)
			.addExpectedValue("identity.sex", sex)
			.addExpectedValue("identity.stateIdNumber", stateIdNumber)
			.addExpectedValue("identity.deathDate", deathDate)
			.addExpectedValue("identity.deceased", deceased)
			.performAssertions(visitor);
	}
	
	/**
	 * Tests the update of the suffix for a visitor.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateVisitorSuffix() throws DuplicateEntityFoundException {
		// Arrangements
		String lastName = "Smith";
		String firstName = "John";
		String middleName = "Jay";
		String suffix = "C";
		Integer socialSecurityNumber = 123456789;
		Date birthDate = this.parseDateText("01/01/1980");
		Country birthCountry = this.countryDelegate.create("Country", "C", 
				true);
		State birthState = this.stateDelegate.create("State", "S", birthCountry, 
				false, true);
		City birthCity = this.cityDelegate.create("City", true, birthState, 
				birthCountry);
		Sex sex = Sex.MALE;
		String stateIdNumber = "1234567890";
		Date deathDate = null;
		Boolean deceased = false;
		Person visitor = this.personDelegate.createWithIdentity(lastName, 
				firstName, middleName, suffix, sex, birthDate, birthCountry, 
				birthState, birthCity, socialSecurityNumber, stateIdNumber, 
				deceased, deathDate);
		String newSuffix = "X";
		
		// Action
		visitor = this.visitationAssociationService.updateVisitor(visitor, 
				lastName, firstName, middleName, newSuffix, 
				socialSecurityNumber, birthDate, birthCity, birthState, 
				birthCountry, sex, stateIdNumber, deathDate, deceased);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("name.lastName", lastName)
			.addExpectedValue("name.firstName", firstName)
			.addExpectedValue("name.middleName", middleName)
			.addExpectedValue("name.suffix", newSuffix)
			.addExpectedValue("identity.socialSecurityNumber", 
					socialSecurityNumber)
			.addExpectedValue("identity.birthDate", birthDate)
			.addExpectedValue("identity.birthPlace", birthCity)
			.addExpectedValue("identity.birthState", birthState)
			.addExpectedValue("identity.birthCountry", birthCountry)
			.addExpectedValue("identity.sex", sex)
			.addExpectedValue("identity.stateIdNumber", stateIdNumber)
			.addExpectedValue("identity.deathDate", deathDate)
			.addExpectedValue("identity.deceased", deceased)
			.performAssertions(visitor);
	}
	
	/**
	 * Tests the update of the social security number for a visitor.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateVisitorSocialSecurityNumber() 
			throws DuplicateEntityFoundException {
		// Arrangements
		String lastName = "Smith";
		String firstName = "John";
		String middleName = "Jay";
		String suffix = "C";
		Integer socialSecurityNumber = 123456789;
		Date birthDate = this.parseDateText("01/01/1980");
		Country birthCountry = this.countryDelegate.create("Country", "C", 
				true);
		State birthState = this.stateDelegate.create("State", "S", birthCountry, 
				false, true);
		City birthCity = this.cityDelegate.create("City", true, birthState, 
				birthCountry);
		Sex sex = Sex.MALE;
		String stateIdNumber = "1234567890";
		Date deathDate = null;
		Boolean deceased = false;
		Person visitor = this.personDelegate.createWithIdentity(lastName, 
				firstName, middleName, suffix, sex, birthDate, birthCountry, 
				birthState, birthCity, socialSecurityNumber, stateIdNumber, 
				deceased, deathDate);
		Integer newSocialSecurityNumber = 987654321;
		
		// Action
		visitor = this.visitationAssociationService.updateVisitor(visitor, 
				lastName, firstName, middleName, suffix, 
				newSocialSecurityNumber, birthDate, birthCity, birthState, 
				birthCountry, sex, stateIdNumber, deathDate, deceased);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("name.lastName", lastName)
			.addExpectedValue("name.firstName", firstName)
			.addExpectedValue("name.middleName", middleName)
			.addExpectedValue("name.suffix", suffix)
			.addExpectedValue("identity.socialSecurityNumber", 
					newSocialSecurityNumber)
			.addExpectedValue("identity.birthDate", birthDate)
			.addExpectedValue("identity.birthPlace", birthCity)
			.addExpectedValue("identity.birthState", birthState)
			.addExpectedValue("identity.birthCountry", birthCountry)
			.addExpectedValue("identity.sex", sex)
			.addExpectedValue("identity.stateIdNumber", stateIdNumber)
			.addExpectedValue("identity.deathDate", deathDate)
			.addExpectedValue("identity.deceased", deceased)
			.performAssertions(visitor);
	}
	
	/**
	 * Tests the update of the birth date for a visitor.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateVisitorBirthDate() 
			throws DuplicateEntityFoundException {
		// Arrangements
		String lastName = "Smith";
		String firstName = "John";
		String middleName = "Jay";
		String suffix = "C";
		Integer socialSecurityNumber = 123456789;
		Date birthDate = this.parseDateText("01/01/1980");
		Country birthCountry = this.countryDelegate.create("Country", "C", 
				true);
		State birthState = this.stateDelegate.create("State", "S", birthCountry, 
				false, true);
		City birthCity = this.cityDelegate.create("City", true, birthState, 
				birthCountry);
		Sex sex = Sex.MALE;
		String stateIdNumber = "1234567890";
		Date deathDate = null;
		Boolean deceased = false;
		Person visitor = this.personDelegate.createWithIdentity(lastName, 
				firstName, middleName, suffix, sex, birthDate, birthCountry, 
				birthState, birthCity, socialSecurityNumber, stateIdNumber, 
				deceased, deathDate);
		Date newBirthDate = this.parseDateText("02/01/1980");
		
		// Action
		visitor = this.visitationAssociationService.updateVisitor(visitor, 
				lastName, firstName, middleName, suffix, socialSecurityNumber, 
				newBirthDate, birthCity, birthState, birthCountry, sex, 
				stateIdNumber, deathDate, deceased);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("name.lastName", lastName)
			.addExpectedValue("name.firstName", firstName)
			.addExpectedValue("name.middleName", middleName)
			.addExpectedValue("name.suffix", suffix)
			.addExpectedValue("identity.socialSecurityNumber", 
					socialSecurityNumber)
			.addExpectedValue("identity.birthDate", newBirthDate)
			.addExpectedValue("identity.birthPlace", birthCity)
			.addExpectedValue("identity.birthState", birthState)
			.addExpectedValue("identity.birthCountry", birthCountry)
			.addExpectedValue("identity.sex", sex)
			.addExpectedValue("identity.stateIdNumber", stateIdNumber)
			.addExpectedValue("identity.deathDate", deathDate)
			.addExpectedValue("identity.deceased", deceased)
			.performAssertions(visitor);
	}
	
	/**
	 * Tests the update of the birth city for a visitor.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateVisitorBirthCity() 
			throws DuplicateEntityFoundException {
		// Arrangements
		String lastName = "Smith";
		String firstName = "John";
		String middleName = "Jay";
		String suffix = "C";
		Integer socialSecurityNumber = 123456789;
		Date birthDate = this.parseDateText("01/01/1980");
		Country birthCountry = this.countryDelegate.create("Country", "C", 
				true);
		State birthState = this.stateDelegate.create("State", "S", birthCountry, 
				false, true);
		City birthCity = this.cityDelegate.create("City", true, birthState, 
				birthCountry);
		Sex sex = Sex.MALE;
		String stateIdNumber = "1234567890";
		Date deathDate = null;
		Boolean deceased = false;
		Person visitor = this.personDelegate.createWithIdentity(lastName, 
				firstName, middleName, suffix, sex, birthDate, birthCountry, 
				birthState, birthCity, socialSecurityNumber, stateIdNumber, 
				deceased, deathDate);
		City newBirthCity = this.cityDelegate.create("City 2", true, birthState, 
				birthCountry);
		
		// Action
		visitor = this.visitationAssociationService.updateVisitor(visitor, 
				lastName, firstName, middleName, suffix, socialSecurityNumber, 
				birthDate, newBirthCity, birthState, birthCountry, sex, 
				stateIdNumber, deathDate, deceased);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("name.lastName", lastName)
			.addExpectedValue("name.firstName", firstName)
			.addExpectedValue("name.middleName", middleName)
			.addExpectedValue("name.suffix", suffix)
			.addExpectedValue("identity.socialSecurityNumber", 
					socialSecurityNumber)
			.addExpectedValue("identity.birthDate", birthDate)
			.addExpectedValue("identity.birthPlace", newBirthCity)
			.addExpectedValue("identity.birthState", birthState)
			.addExpectedValue("identity.birthCountry", birthCountry)
			.addExpectedValue("identity.sex", sex)
			.addExpectedValue("identity.stateIdNumber", stateIdNumber)
			.addExpectedValue("identity.deathDate", deathDate)
			.addExpectedValue("identity.deceased", deceased)
			.performAssertions(visitor);
	}
	
	/**
	 * Tests the update of the birth state for a visitor.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateVisitorBirthState() 
			throws DuplicateEntityFoundException {
		// Arrangements
		String lastName = "Smith";
		String firstName = "John";
		String middleName = "Jay";
		String suffix = "C";
		Integer socialSecurityNumber = 123456789;
		Date birthDate = this.parseDateText("01/01/1980");
		Country birthCountry = this.countryDelegate.create("Country", "C", 
				true);
		State birthState = this.stateDelegate.create("State", "S", birthCountry, 
				false, true);
		City birthCity = this.cityDelegate.create("City", true, birthState, 
				birthCountry);
		Sex sex = Sex.MALE;
		String stateIdNumber = "1234567890";
		Date deathDate = null;
		Boolean deceased = false;
		Person visitor = this.personDelegate.createWithIdentity(lastName, 
				firstName, middleName, suffix, sex, birthDate, birthCountry, 
				birthState, birthCity, socialSecurityNumber, stateIdNumber, 
				deceased, deathDate);
		State newBirthState = this.stateDelegate.create("State 2", "S2", 
				birthCountry, false, true);
		City newBirthCity = this.cityDelegate.create("City 2", true, 
				newBirthState, birthCountry);
		
		// Action
		visitor = this.visitationAssociationService.updateVisitor(visitor, 
				lastName, firstName, middleName, suffix, socialSecurityNumber, 
				birthDate, newBirthCity, newBirthState, birthCountry, sex, 
				stateIdNumber, deathDate, deceased);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("name.lastName", lastName)
			.addExpectedValue("name.firstName", firstName)
			.addExpectedValue("name.middleName", middleName)
			.addExpectedValue("name.suffix", suffix)
			.addExpectedValue("identity.socialSecurityNumber", 
					socialSecurityNumber)
			.addExpectedValue("identity.birthDate", birthDate)
			.addExpectedValue("identity.birthPlace", newBirthCity)
			.addExpectedValue("identity.birthState", newBirthState)
			.addExpectedValue("identity.birthCountry", birthCountry)
			.addExpectedValue("identity.sex", sex)
			.addExpectedValue("identity.stateIdNumber", stateIdNumber)
			.addExpectedValue("identity.deathDate", deathDate)
			.addExpectedValue("identity.deceased", deceased)
			.performAssertions(visitor);
	}
	
	/**
	 * Tests the update of the birth country for a visitor.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateVisitorBirthCountry() 
			throws DuplicateEntityFoundException {
		// Arrangements
		String lastName = "Smith";
		String firstName = "John";
		String middleName = "Jay";
		String suffix = "C";
		Integer socialSecurityNumber = 123456789;
		Date birthDate = this.parseDateText("01/01/1980");
		Country birthCountry = this.countryDelegate.create("Country", "C", 
				true);
		State birthState = this.stateDelegate.create("State", "S", birthCountry, 
				false, true);
		City birthCity = this.cityDelegate.create("City", true, birthState, 
				birthCountry);
		Sex sex = Sex.MALE;
		String stateIdNumber = "1234567890";
		Date deathDate = null;
		Boolean deceased = false;
		Person visitor = this.personDelegate.createWithIdentity(lastName, 
				firstName, middleName, suffix, sex, birthDate, birthCountry, 
				birthState, birthCity, socialSecurityNumber, stateIdNumber, 
				deceased, deathDate);
		Country newBirthCountry = this.countryDelegate.create("Country 2", "C2", 
				true);
		State newBirthState = this.stateDelegate.create("State 2", "S2", 
				newBirthCountry, false, true);
		City newBirthCity = this.cityDelegate.create("City 2", true, 
				newBirthState, newBirthCountry);
		
		// Action
		visitor = this.visitationAssociationService.updateVisitor(visitor, 
				lastName, firstName, middleName, suffix, socialSecurityNumber, 
				birthDate, newBirthCity, newBirthState, newBirthCountry, sex, 
				stateIdNumber, deathDate, deceased);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("name.lastName", lastName)
			.addExpectedValue("name.firstName", firstName)
			.addExpectedValue("name.middleName", middleName)
			.addExpectedValue("name.suffix", suffix)
			.addExpectedValue("identity.socialSecurityNumber", 
					socialSecurityNumber)
			.addExpectedValue("identity.birthDate", birthDate)
			.addExpectedValue("identity.birthPlace", newBirthCity)
			.addExpectedValue("identity.birthState", newBirthState)
			.addExpectedValue("identity.birthCountry", newBirthCountry)
			.addExpectedValue("identity.sex", sex)
			.addExpectedValue("identity.stateIdNumber", stateIdNumber)
			.addExpectedValue("identity.deathDate", deathDate)
			.addExpectedValue("identity.deceased", deceased)
			.performAssertions(visitor);
	}
	
	/**
	 * Tests the update of the sex for a visitor.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateVisitorSex() throws DuplicateEntityFoundException {
		// Arrangements
		String lastName = "Smith";
		String firstName = "John";
		String middleName = "Jay";
		String suffix = "C";
		Integer socialSecurityNumber = 123456789;
		Date birthDate = this.parseDateText("01/01/1980");
		Country birthCountry = this.countryDelegate.create("Country", "C", 
				true);
		State birthState = this.stateDelegate.create("State", "S", birthCountry, 
				false, true);
		City birthCity = this.cityDelegate.create("City", true, birthState, 
				birthCountry);
		Sex sex = Sex.MALE;
		String stateIdNumber = "1234567890";
		Date deathDate = null;
		Boolean deceased = false;
		Person visitor = this.personDelegate.createWithIdentity(lastName, 
				firstName, middleName, suffix, sex, birthDate, birthCountry, 
				birthState, birthCity, socialSecurityNumber, stateIdNumber, 
				deceased, deathDate);
		Sex newSex = Sex.FEMALE;
		
		// Action
		visitor = this.visitationAssociationService.updateVisitor(visitor, 
				lastName, firstName, middleName, suffix, socialSecurityNumber, 
				birthDate, birthCity, birthState, birthCountry, newSex, 
				stateIdNumber, deathDate, deceased);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("name.lastName", lastName)
			.addExpectedValue("name.firstName", firstName)
			.addExpectedValue("name.middleName", middleName)
			.addExpectedValue("name.suffix", suffix)
			.addExpectedValue("identity.socialSecurityNumber", 
					socialSecurityNumber)
			.addExpectedValue("identity.birthDate", birthDate)
			.addExpectedValue("identity.birthPlace", birthCity)
			.addExpectedValue("identity.birthState", birthState)
			.addExpectedValue("identity.birthCountry", birthCountry)
			.addExpectedValue("identity.sex", newSex)
			.addExpectedValue("identity.stateIdNumber", stateIdNumber)
			.addExpectedValue("identity.deathDate", deathDate)
			.addExpectedValue("identity.deceased", deceased)
			.performAssertions(visitor);
	}
	
	/**
	 * Tests the update of the state id number for a visitor.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateVisitorStateIdNumber() 
			throws DuplicateEntityFoundException {
		// Arrangements
		String lastName = "Smith";
		String firstName = "John";
		String middleName = "Jay";
		String suffix = "C";
		Integer socialSecurityNumber = 123456789;
		Date birthDate = this.parseDateText("01/01/1980");
		Country birthCountry = this.countryDelegate.create("Country", "C", 
				true);
		State birthState = this.stateDelegate.create("State", "S", birthCountry, 
				false, true);
		City birthCity = this.cityDelegate.create("City", true, birthState, 
				birthCountry);
		Sex sex = Sex.MALE;
		String stateIdNumber = "1234567890";
		Date deathDate = null;
		Boolean deceased = false;
		Person visitor = this.personDelegate.createWithIdentity(lastName, 
				firstName, middleName, suffix, sex, birthDate, birthCountry, 
				birthState, birthCity, socialSecurityNumber, stateIdNumber, 
				deceased, deathDate);
		String newStateIdNumber = "0123456789";
		
		// Action
		visitor = this.visitationAssociationService.updateVisitor(visitor, 
				lastName, firstName, middleName, suffix, socialSecurityNumber, 
				birthDate, birthCity, birthState, birthCountry, sex, 
				newStateIdNumber, deathDate, deceased);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("name.lastName", lastName)
			.addExpectedValue("name.firstName", firstName)
			.addExpectedValue("name.middleName", middleName)
			.addExpectedValue("name.suffix", suffix)
			.addExpectedValue("identity.socialSecurityNumber", 
					socialSecurityNumber)
			.addExpectedValue("identity.birthDate", birthDate)
			.addExpectedValue("identity.birthPlace", birthCity)
			.addExpectedValue("identity.birthState", birthState)
			.addExpectedValue("identity.birthCountry", birthCountry)
			.addExpectedValue("identity.sex", sex)
			.addExpectedValue("identity.stateIdNumber", newStateIdNumber)
			.addExpectedValue("identity.deathDate", deathDate)
			.addExpectedValue("identity.deceased", deceased)
			.performAssertions(visitor);
	}
	
	/**
	 * Tests the update of the death date for a visitor.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateVisitorDeathDate() 
			throws DuplicateEntityFoundException {
		// Arrangements
		String lastName = "Smith";
		String firstName = "John";
		String middleName = "Jay";
		String suffix = "C";
		Integer socialSecurityNumber = 123456789;
		Date birthDate = this.parseDateText("01/01/1980");
		Country birthCountry = this.countryDelegate.create("Country", "C", 
				true);
		State birthState = this.stateDelegate.create("State", "S", birthCountry, 
				false, true);
		City birthCity = this.cityDelegate.create("City", true, birthState, 
				birthCountry);
		Sex sex = Sex.MALE;
		String stateIdNumber = "1234567890";
		Date deathDate = null;
		Boolean deceased = false;
		Person visitor = this.personDelegate.createWithIdentity(lastName, 
				firstName, middleName, suffix, sex, birthDate, birthCountry, 
				birthState, birthCity, socialSecurityNumber, stateIdNumber, 
				deceased, deathDate);
		Date newDeathDate = this.parseDateText("01/01/2017");
		deceased = true;
		
		// Action
		visitor = this.visitationAssociationService.updateVisitor(visitor, 
				lastName, firstName, middleName, suffix, socialSecurityNumber, 
				birthDate, birthCity, birthState, birthCountry, sex, 
				stateIdNumber, newDeathDate, deceased);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("name.lastName", lastName)
			.addExpectedValue("name.firstName", firstName)
			.addExpectedValue("name.middleName", middleName)
			.addExpectedValue("name.suffix", suffix)
			.addExpectedValue("identity.socialSecurityNumber", 
					socialSecurityNumber)
			.addExpectedValue("identity.birthDate", birthDate)
			.addExpectedValue("identity.birthPlace", birthCity)
			.addExpectedValue("identity.birthState", birthState)
			.addExpectedValue("identity.birthCountry", birthCountry)
			.addExpectedValue("identity.sex", sex)
			.addExpectedValue("identity.stateIdNumber", stateIdNumber)
			.addExpectedValue("identity.deathDate", newDeathDate)
			.addExpectedValue("identity.deceased", deceased)
			.performAssertions(visitor);
	}
	
	/**
	 * Tests the update of the deceased flag for a visitor.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateVisitorDeceased() 
			throws DuplicateEntityFoundException {
		// Arrangements
		String lastName = "Smith";
		String firstName = "John";
		String middleName = "Jay";
		String suffix = "C";
		Integer socialSecurityNumber = 123456789;
		Date birthDate = this.parseDateText("01/01/1980");
		Country birthCountry = this.countryDelegate.create("Country", "C", 
				true);
		State birthState = this.stateDelegate.create("State", "S", birthCountry, 
				false, true);
		City birthCity = this.cityDelegate.create("City", true, birthState, 
				birthCountry);
		Sex sex = Sex.MALE;
		String stateIdNumber = "1234567890";
		Date deathDate = null;
		Boolean deceased = false;
		Person visitor = this.personDelegate.createWithIdentity(lastName, 
				firstName, middleName, suffix, sex, birthDate, birthCountry, 
				birthState, birthCity, socialSecurityNumber, stateIdNumber, 
				deceased, deathDate);
		Boolean newDeceased = true;

		// Action
		visitor = this.visitationAssociationService.updateVisitor(visitor, 
				lastName, firstName, middleName, suffix, socialSecurityNumber, 
				birthDate, birthCity, birthState, birthCountry, sex, 
				stateIdNumber, deathDate, newDeceased);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("name.lastName", lastName)
			.addExpectedValue("name.firstName", firstName)
			.addExpectedValue("name.middleName", middleName)
			.addExpectedValue("name.suffix", suffix)
			.addExpectedValue("identity.socialSecurityNumber", 
					socialSecurityNumber)
			.addExpectedValue("identity.birthDate", birthDate)
			.addExpectedValue("identity.birthPlace", birthCity)
			.addExpectedValue("identity.birthState", birthState)
			.addExpectedValue("identity.birthCountry", birthCountry)
			.addExpectedValue("identity.sex", sex)
			.addExpectedValue("identity.stateIdNumber", stateIdNumber)
			.addExpectedValue("identity.deathDate", deathDate)
			.addExpectedValue("identity.deceased", newDeceased)
			.performAssertions(visitor);
	}
	
	/**
	 * Tests {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
			throws DuplicateEntityFoundException {
		// Arrangements
		String lastName = "Smith";
		String firstName = "John";
		String middleName = "Jay";
		String suffix = "C";
		Integer socialSecurityNumber = 123456789;
		Date birthDate = this.parseDateText("01/01/1980");
		Country birthCountry = this.countryDelegate.create("Country", "C", 
				true);
		State birthState = this.stateDelegate.create("State", "S", birthCountry, 
				false, true);
		City birthCity = this.cityDelegate.create("City", true, birthState, 
				birthCountry);
		Sex sex = Sex.MALE;
		String stateIdNumber = "1234567890";
		Date deathDate = null;
		Boolean deceased = false;
		Person visitor = this.personDelegate.createWithIdentity(lastName, 
				firstName, middleName, suffix, sex, birthDate, birthCountry, 
				birthState, birthCity, socialSecurityNumber, stateIdNumber, 
				deceased, deathDate);
		Sex secondSex = Sex.FEMALE;
		this.personIdentityDelegate.create(visitor, secondSex, birthDate, 
				birthCountry, birthState, birthCity, socialSecurityNumber, 
				stateIdNumber, deceased, deathDate);

		// Action
		visitor = this.visitationAssociationService.updateVisitor(visitor, 
				lastName, firstName, middleName, suffix, socialSecurityNumber, 
				birthDate, birthCity, birthState, birthCountry, secondSex, 
				stateIdNumber, deathDate, deceased);
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