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
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;
import omis.visitation.service.VisitationAssociationService;

/**
 * Tests method to create visitors.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"visitation", "service"})
public class VisitationAssociationServiceCreateVisitorTests
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
	
	/* Services. */

	@Autowired
	@Qualifier("visitationAssociationService")
	private VisitationAssociationService visitationAssociationService;

	/* Test methods. */

	/**
	 * Tests the creation of a new visitor.
	 * 
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	@Test
	public void testCreateVisitor() throws DuplicateEntityFoundException {
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
		this.personDelegate.createWithIdentity(lastName, firstName, middleName, 
				suffix, sex, birthDate, birthCountry, birthState, birthCity, 
				socialSecurityNumber, stateIdNumber, deceased, deathDate);
		// Action
		Person person = this.visitationAssociationService.createVisitor(
				lastName, firstName, middleName, suffix, socialSecurityNumber, 
				birthDate, birthCity, birthState, birthCountry, sex, 
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
			.addExpectedValue("identity.sex", sex)
			.addExpectedValue("identity.stateIdNumber", stateIdNumber)
			.addExpectedValue("identity.deathDate", deathDate)
			.addExpectedValue("identity.deceased", deceased)
			.performAssertions(person);
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