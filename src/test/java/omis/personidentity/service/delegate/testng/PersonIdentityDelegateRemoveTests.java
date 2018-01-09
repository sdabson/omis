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
 * Test person identity removal
 * 
 *@author Yidong Li 
 *@version 0.1.0 (July 11, 2017)
 *@since OMIS 3.0
 *
 */
@Test(groups = {"personidentity"})
public class PersonIdentityDelegateRemoveTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Delegate */
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
	
	@Test
	public void testPersonIdentityRemove() throws DuplicateEntityFoundException{
		// Arrangements
		Date birthDate = new Date(111111111);
		Date deathDate = new Date(1222222222);
		Person person = this.personDelegate.create("Smith", "John", "Don", "Mr.");
		Country birthCountry = this.countryDelegate.create("United States", 
			"U.S.", true);
		State birthState = this.stateDelegate.create("Montana", "MT", 
			birthCountry, true, true);
		City birthCity = this.cityDelegate.create("Helena", true, birthState, 
			birthCountry);
		Integer socialSecurityNumber = 123456789;
		String stateId = "234";
		PersonIdentity personIdentity= this.personIdentityDelegate.create(person, 
			Sex.MALE, birthDate, birthCountry, birthState, birthCity, 
			socialSecurityNumber, stateId, true, deathDate);
		
		// Action
		this.personIdentityDelegate.remove(personIdentity);
		
		// Assertion
		assert (this.personIdentityDelegate.findByPerson(person)==null)
			: "Person identity was not removed!";
	}
}
