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
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests "update" of person identity
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"family"})
public class PersonIdentityDelegateUpdateTests
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
	 * Person identity update.
	 */
	@Test
	public void testPersonIdentityUpdate() throws DuplicateEntityFoundException{
		// Arrangements
		Date originalBirthDate = new Date(111111111);
		Date originalDeathDate = new Date(1222222222);
		Person person = this.personDelegate.create("Smith", "John", 
			"Don", "Mr.");
		Country originalBirthCountry = this.countryDelegate.create(
			"United States", "U.S.", true);
		State originalBirthState = this.stateDelegate.create("Montana", "MT", 
			originalBirthCountry, true, true);
		City originalBirthCity = this.cityDelegate.create("Helena", true, 
			originalBirthState,	originalBirthCountry);
		Integer originalSocialSecurityNumber = 123456789;
		String originalStateId = "234";
		
		Date newBirthDate = new Date(1000000000);
		Date newDeathDate = new Date(1220000000);
		Country newBirthCountry = this.countryDelegate.create(
			"AAA BBB", "A.B.", true);
		State newBirthState = this.stateDelegate.create("CCDD", "CD", 
			newBirthCountry, true, true);
		City newBirthCity = this.cityDelegate.create("EEFF", true, newBirthState,	
			newBirthCountry);
		Integer newSocialSecurityNumber = 234567891;
		String newStateId = "299";
	
		// Action
		PersonIdentity personIdentity= this.personIdentityDelegate.create(
			person,	Sex.MALE, originalBirthDate, originalBirthCountry, 
			originalBirthState, originalBirthCity, originalSocialSecurityNumber, 
			originalStateId, true, originalDeathDate);
		this.personIdentityDelegate.update(personIdentity, Sex.FEMALE, 
			newBirthDate, newBirthCountry, newBirthState, newBirthCity, 
			newSocialSecurityNumber, newStateId, true, newDeathDate);

		// Assertions
		assert newBirthDate.equals(personIdentity.getBirthDate())
		: String.format("Wrong Dirth Date: #%s expected; #%s found",
			newBirthDate.toString(), personIdentity.getBirthDate().toString());
		assert newDeathDate.equals(personIdentity.getDeathDate())
		: String.format("Wrong Death Date: #%s expected; #%s found",
			newDeathDate.toString(), personIdentity.getDeathDate().toString());
		assert newBirthCountry.equals(personIdentity.getBirthCountry())
		: String.format("Wrong Birth Country: #%s expected; #%s found",
			newBirthCountry.getName(), personIdentity.getBirthCountry().getName());
		assert newBirthState.equals(personIdentity.getBirthState())
		: String.format("Wrong Birth State: #%s expected; #%s found",
			newBirthState.getName(), personIdentity.getBirthState().getName());
		assert newBirthCity.equals(personIdentity.getBirthPlace())
		: String.format("Wrong Birth City: #%s expected; #%s found",
			newBirthCity.getName(), personIdentity.getBirthPlace().getName());
		assert newStateId.equals(personIdentity.getStateIdNumber())
		: String.format("Wrong State ID: #%s expected; #%s found",
			newStateId, personIdentity.getStateIdNumber());
		assert newSocialSecurityNumber.equals(
			personIdentity.getSocialSecurityNumber())
		: String.format("Wrong Social Security Number: #%s expected; #%s found",
			newSocialSecurityNumber.toString(), 
			personIdentity.getSocialSecurityNumber().toString());
		assert Sex.FEMALE.equals(personIdentity.getSex())
		: String.format("Wrong Sex: #%s expected; #%s found",
			Sex.FEMALE.toString(), personIdentity.getSex().toString());
		assert personIdentity.getDeceased()
		: String.format("Wrong Deceased: #%s expected; #%s found",
			true, personIdentity.getDeceased().toString());
	}
	
	/**
	 * Tests duplicate person identity on update.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate term exists
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class, 
		ReflexiveRelationshipException.class})
	public void testDuplicatePersonIdentityUpdate() 
		throws DuplicateEntityFoundException {
		// Arrangements
		//
		Date birthDate1 = new Date(111111111);
		Date deathDate1 = new Date(1222222222);
		Person person = this.personDelegate.create("Smith", "John", "Don", "Mr.");
		Country birthCountry1 = this.countryDelegate.create("America", 
			"U.S.", true);
		State birthState1 = this.stateDelegate.create("Montana", "MT", 
			birthCountry1, true, true);
		City birthCity1 = this.cityDelegate.create("Helena", true, birthState1, 
			birthCountry1);
		Integer socialSecurityNumber1 = 123456789;
		String stateId1 = "234";
		
		Date birthDate2 = new Date(1144444444);
		Date deathDate2 = new Date(1555555555);
		Country birthCountry2 = this.countryDelegate.create("America", 
			"U.S.", true);
		State birthState2 = this.stateDelegate.create("Montana", "MT", 
			birthCountry2, true, true);
		City birthCity2 = this.cityDelegate.create("Helena", true, birthState2, 
			birthCountry2);
		Integer socialSecurityNumber2 = 123456777;
		String stateId2 = "343";
	
		// Action
		PersonIdentity identity1 = this.personIdentityDelegate.create(person, 
			Sex.FEMALE, birthDate1, birthCountry1, birthState1, birthCity1, 
			socialSecurityNumber1, stateId1, true, deathDate1);
		this.personIdentityDelegate.create(person, Sex.MALE, birthDate2, 
			birthCountry2, birthState2, birthCity2,	socialSecurityNumber2, 
			stateId2, true, deathDate2);
		this.personIdentityDelegate.update(identity1, Sex.MALE, birthDate2, 
			birthCountry2, birthState2, birthCity2, socialSecurityNumber2, 
			stateId2, true, deathDate2);
	}	
}