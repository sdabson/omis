package omis.offenderrelationship.service.testng;

import java.util.Date;

import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.demographics.domain.Sex;
import omis.exception.DuplicateEntityFoundException;
import omis.offenderrelationship.service.CreateRelationshipsService;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.person.service.delegate.PersonIdentityDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

/**
 * Tests "update" of offender relationship.
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"offenderrelationship"})
public class OffenderRelationshipUpdateTests
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
	
	/* Service */
	@Autowired
	@Qualifier("createRelationshipsService")
	private CreateRelationshipsService createRelationshipsService;
	
	/**
	 * Offender relationship update.
	 * @throws DuplicateEntityFoundException DuplicateEntityFoundException
	 */
	@Test
	public void testOffenderRelationshipUpdate() 
		throws DuplicateEntityFoundException {
		// Arrangements
		final int originalBirthDateInt = 111111111;
		final int originalDeathDateInt = 1222222222;
		Date originalBirthDate = new Date(originalBirthDateInt);
		Date originalDeathDate = new Date(originalDeathDateInt);
		Country originalBirthCountry = this.countryDelegate.create(
			"United States", "U.S.", true);
		State originalBirthState = this.stateDelegate.create("Montana", "MT", 
			originalBirthCountry, true, true);
		City originalBirthCity = this.cityDelegate.create("Helena", true, 
			originalBirthState,	originalBirthCountry);
		final Integer originalSocialSecurityNumber = 123456789;
		String originalStateId = "234";
		
		Person person = this.createRelationshipsService
			.createRelation("Smith", "John", "Don", "Mr.", Sex.MALE, 
			originalBirthDate, originalBirthCountry, originalBirthState, 
			originalBirthCity, originalSocialSecurityNumber, originalStateId, 
			true, originalDeathDate);
		
		final int newBirthDateInt = 111115555;
		final int newDeathDateInt = 1222229999;
		Date newBirthDate = new Date(newBirthDateInt);
		Date newDeathDate = new Date(newDeathDateInt);
		Country newBirthCountry = this.countryDelegate.create("Country 2", 
				"C.Y.", true);
		State newBirthState = this.stateDelegate.create("Indiana", "IN", 
			newBirthCountry, true, true);
		City newBirthCity = this.cityDelegate.create("Columbus", true, 
			newBirthState, newBirthCountry);
		final Integer newSocialSecurityNumber = 553456789;
		String newStateId = "678";
		
		// Action
		this.createRelationshipsService.updateRelation(person, "AAA", "BBB", 
			"CCC", "Mr.", Sex.FEMALE, newBirthDate, newBirthState, newBirthCity,
			newSocialSecurityNumber, newStateId, true, newDeathDate);
		
		// Assertions
		assert newBirthDate.equals(person.getIdentity().getBirthDate())
		: String.format("Wrong Birth Date: #%s expected; #%s found",
			newBirthDate, person.getIdentity().getBirthDate());
		assert newDeathDate.equals(person.getIdentity().getDeathDate())
		: String.format("Wrong Death Date: #%s expected; #%s found",
			newDeathDate, person.getIdentity().getDeathDate());
		assert newBirthCountry.equals(person.getIdentity().getBirthCountry())
		: String.format("Wrong Birth Country: #%s expected; #%s found",
			newBirthCountry.getName(), 
			person.getIdentity().getBirthCountry().getName());
		assert newBirthState.equals(person.getIdentity().getBirthState())
		: String.format("Wrong Birth State: #%s expected; #%s found",
			newBirthState.getName(), person.getIdentity().getBirthState()
			.getName());
		assert newBirthCity.equals(person.getIdentity().getBirthPlace())
		: String.format("Wrong Birth City: #%s expected; #%s found",
			newBirthCity.getName(), person.getIdentity().getBirthPlace()
			.getName());
		assert newStateId.equals(person.getIdentity().getStateIdNumber())
		: String.format("Wrong State ID: #%s expected; #%s found",
			newStateId, person.getIdentity().getStateIdNumber());
		assert newSocialSecurityNumber.equals(
			person.getIdentity().getSocialSecurityNumber())
		: String.format("Wrong Social Security Number: #%s expected; #%s found",
			newSocialSecurityNumber.toString(), 
			person.getIdentity().getSocialSecurityNumber().toString());
		assert Sex.FEMALE.equals(person.getIdentity().getSex())
		: String.format("Wrong Sex: #%s expected; #%s found",
			Sex.FEMALE.toString(), person.getIdentity().getSex().toString());
		assert person.getIdentity().getDeceased()
		: String.format("Wrong Deceased: #%s expected; #%s found",
			true, person.getIdentity().getDeceased().toString());
		assert "AAA".equals(person.getName().getLastName())
		: String.format("Wrong Sex: #%s expected; #%s found",
			"AAA", person.getName().getLastName());
		assert "BBB".equals(person.getName().getFirstName())
		: String.format("Wrong Sex: #%s expected; #%s found",
			"BBB", person.getName().getFirstName());
		assert "CCC".equals(person.getName().getMiddleName())
		: String.format("Wrong Sex: #%s expected; #%s found",
			"CCC", person.getName().getMiddleName());
	}
	
	/**
	 * Tests duplicate offender relationship on update.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate relationships exists
	 */
	@Test
	public void testDuplicateOffenderRelationshipUpdate() 
		throws DuplicateEntityFoundException {
		// Arrangements
		final int originalBirthDateInt = 111111111;
		final int originalDeathDateInt = 1222222222;
		Date originalBirthDate = new Date(originalBirthDateInt);
		Date originalDeathDate = new Date(originalDeathDateInt);
		Country originalBirthCountry = this.countryDelegate.create(
			"United States", "U.S.", true);
		State originalBirthState = this.stateDelegate.create("Montana", "MT", 
			originalBirthCountry, true, true);
		City originalBirthCity = this.cityDelegate.create("Helena", true, 
			originalBirthState,	originalBirthCountry);
		final Integer originalSocialSecurityNumber = 123456789;
		String originalStateId = "234";
		
		Person person = this.createRelationshipsService
			.createRelation("Smith", "John", "Don", "Mr.", Sex.MALE, 
			originalBirthDate, originalBirthCountry, originalBirthState, 
			originalBirthCity, originalSocialSecurityNumber, originalStateId, 
			true, originalDeathDate);
		
		final int newBirthDateInt = 111115555;
		final int newDeathDateInt = 1222229999;
		Date newBirthDate = new Date(newBirthDateInt);
		Date newDeathDate = new Date(newDeathDateInt);
		Country newBirthCountry = this.countryDelegate.create("Country 2", 
				"C.Y.", true);
		State newBirthState = this.stateDelegate.create("Indiana", "IN", 
			newBirthCountry, true, true);
		City newBirthCity = this.cityDelegate.create("Columbus", true, 
			newBirthState, newBirthCountry);
		final Integer newSocialSecurityNumber = 553456789;
		String newStateId = "678";
		this.createRelationshipsService.createRelation("AAA", "BBB", "CCC", 
			"Mr.", Sex.FEMALE, newBirthDate, newBirthCountry, newBirthState, 
			newBirthCity, newSocialSecurityNumber, newStateId, true,
			newDeathDate);
		
		// Action
		this.createRelationshipsService.updateRelation(person, "AAA", "BBB", 
			"CCC", "Mr.", Sex.FEMALE, newBirthDate, newBirthState, newBirthCity,
			newSocialSecurityNumber, newStateId, true, newDeathDate);
	}	
}