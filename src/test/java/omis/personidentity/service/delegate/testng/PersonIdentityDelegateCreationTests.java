package omis.personidentity.service.delegate.testng;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.demographics.domain.Sex;
import omis.exception.DuplicateEntityFoundException;
import omis.person.domain.Person;
import omis.person.domain.PersonIdentity;
import omis.person.service.delegate.PersonDelegate;
import omis.person.service.delegate.PersonIdentityDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Test person identity creation 
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"personidentity"})
public class PersonIdentityDelegateCreationTests
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
	
	/**
	 * Tests the creation of person identity.
	 * @throws DuplicateEntityFoundException 
	 */
	@Test
	public void testPersonIdentityCreation() 
		throws DuplicateEntityFoundException {
		// Arrangement
		Date birthDate = new Date(111111111);
		Date deathDate = new Date(1222222222);
		Person person = this.personDelegate.create("Smith", "John", "Don", "Mr.");
		Country birthCountry = this.countryDelegate.create("United States", 
			"U.S.", true);
		State birthState = this.stateDelegate.create("Montana", "MT", birthCountry, 
			true, true);
		City birthCity = this.cityDelegate.create("Helena", true, birthState, 
			birthCountry);
		Integer socialSecurityNumber = 123456789;
		String stateId = "234";
	
		// Action
		PersonIdentity personIdentity= this.personIdentityDelegate.create(person, 
			Sex.MALE, birthDate, birthCountry, birthState, birthCity, 
			socialSecurityNumber, stateId, true, deathDate);
		
		// Assertions
		assert birthDate.equals(personIdentity.getBirthDate())
		: String.format("Wrong Birth Date: #%s expected; #%s found",
			birthDate, personIdentity.getBirthDate());
		assert deathDate.equals(personIdentity.getDeathDate())
		: String.format("Wrong Death Date: #%s expected; #%s found",
			deathDate, personIdentity.getDeathDate());
		assert person.equals(personIdentity.getPerson())
		: String.format("Wrong Person: #%s expected; #%s found",
			person.getName().getFirstName()
			+ person.getName().getMiddleName()
			+ person.getName().getLastName(),
			personIdentity.getPerson().getName().getFirstName()
			+personIdentity.getPerson().getName().getMiddleName()
			+personIdentity.getPerson().getName().getLastName());
		assert birthCountry.equals(personIdentity.getBirthCountry())
		: String.format("Wrong Birth Country: #%s expected; #%s found",
			birthCountry.getName(), personIdentity.getBirthCountry().getName());
		assert birthState.equals(personIdentity.getBirthState())
		: String.format("Wrong Birth State: #%s expected; #%s found",
			birthState.getName(), personIdentity.getBirthState().getName());
		assert birthCity.equals(personIdentity.getBirthPlace())
		: String.format("Wrong Birth City: #%s expected; #%s found",
			birthCity.getName(), personIdentity.getBirthPlace().getName());
		assert stateId.equals(personIdentity.getStateIdNumber())
		: String.format("Wrong State ID: #%s expected; #%s found",
			stateId, personIdentity.getStateIdNumber());
		assert socialSecurityNumber.equals(
			personIdentity.getSocialSecurityNumber())
		: String.format("Wrong Social Security Number: #%s expected; #%s found",
			socialSecurityNumber.toString(), 
			personIdentity.getSocialSecurityNumber().toString());
		assert Sex.MALE.equals(personIdentity.getSex())
		: String.format("Wrong Sex: #%s expected; #%s found",
			Sex.MALE.toString(), personIdentity.getSex().toString());
		assert personIdentity.getDeceased()
		: String.format("Wrong Deceased: #%s expected; #%s found",
			true, personIdentity.getDeceased().toString());
	}
	
	/**
	 * Tests duplicate person identities on creation.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate identity exist
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicatePersonIdentityCreate() 
		throws DuplicateEntityFoundException {
		// Assignment
		Date birthDate = new Date(111111111);
		Date deathDate = new Date(1222222222);
		Person person = this.personDelegate.create("Smith", "John", "Don", "Mr.");
		Country birthCountry = this.countryDelegate.create("United States", 
			"U.S.", true);
		State birthState = this.stateDelegate.create("Montana", "MT", birthCountry, 
			true, true);
		City birthCity = this.cityDelegate.create("Helena", true, birthState, 
			birthCountry);
		Integer socialSecurityNumber = 123456789;
		String stateId = "234";
	
		// Action
		this.personIdentityDelegate.create(person, Sex.MALE, birthDate, 
			birthCountry, birthState, birthCity, socialSecurityNumber, stateId, 
			true, deathDate);
		this.personIdentityDelegate.create(person, Sex.MALE, birthDate, 
			birthCountry, birthState, birthCity, socialSecurityNumber, stateId, 
			true, deathDate);
	}
}