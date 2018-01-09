package omis.offenseterm.service.testng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.address.domain.Address;
import omis.address.domain.ZipCode;
import omis.address.service.delegate.AddressDelegate;
import omis.address.service.delegate.ZipCodeDelegate;
import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.court.domain.Court;
import omis.court.domain.CourtCategory;
import omis.court.service.delegate.CourtDelegate;
import omis.datatype.DateRange;
import omis.docket.domain.Docket;
import omis.docket.exception.DocketExistsException;
import omis.docket.service.delegate.DocketDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.location.domain.Location;
import omis.location.service.delegate.LocationDelegate;
import omis.offenseterm.service.OffenseTermDocketService;
import omis.organization.domain.Organization;
import omis.organization.service.delegate.OrganizationDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

public class OffenseTermDocketServiceUpdateTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("courtDelegate")
	private CourtDelegate courtDelegate;
	
	@Autowired
	@Qualifier("docketDelegate")
	private DocketDelegate docketDelegate;
	
	@Autowired
	@Qualifier("locationDelegate")
	private LocationDelegate locationDelegate;
	
	@Autowired
	@Qualifier("organizationDelegate")
	private OrganizationDelegate organizationDelegate;
	
	@Autowired
	@Qualifier("addressDelegate")
	private AddressDelegate addressDelegate;
	
	@Autowired
	@Qualifier("countryDelegate")
	private CountryDelegate countryDelegate;
	
	@Autowired
	@Qualifier("cityDelegate")
	private CityDelegate cityDelegate;
	
	@Autowired
	@Qualifier("stateDelegate")
	private StateDelegate stateDelegate;
	
	@Autowired
	@Qualifier("zipCodeDelegate")
	private ZipCodeDelegate zipCodeDelegate;
	/* Services. */

	@Autowired
	@Qualifier("offenseTermDocketService")
	private OffenseTermDocketService offenseTermDocketService;

	/* Test methods. */

	/**
	 * Test the update of the court for a docket.
	 * 
	 * @throws DocketExistsException if the docket exists
	 * @throws DuplicateEntityFoundException if the entity already exists
	 */
	@Test
	public void testUpdateCourt() throws DocketExistsException, 
			DuplicateEntityFoundException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", "John", "Jay", null);
		Organization organization = this.organizationDelegate.create("Org", "O", 
				null);
		Country country = this.countryDelegate.create("Country", "C", true);
		State state = this.stateDelegate.create("State", "S", country, false, 
				true);
		City city = this.cityDelegate.create("City", true, state, country);
		ZipCode zipCode = this.zipCodeDelegate.create(city , "12345", null, 
				true);
		Address address = this.addressDelegate.findOrCreate("123 Easy St.", 
				null, null, null, zipCode);
		Location location = this.locationDelegate.create(organization, 
				new DateRange(), address);
		Court court = this.courtDelegate.create("Court", CourtCategory.CITY, 
				location);
		String docketValue = "Docket";
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		Court newCourt = this.courtDelegate.create("Court2", CourtCategory.CITY, 
				location);
		
		// Action
		docket = this.offenseTermDocketService.update(docket, newCourt, 
				docketValue);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("person", person)
			.addExpectedValue("court", newCourt)
			.addExpectedValue("value", docketValue)
			.performAssertions(docket);
	}

	/**
	 * Test the update of the docket value for a docket.
	 * 
	 * @throws DocketExistsException if the docket exists
	 * @throws DuplicateEntityFoundException if the entity already exists
	 */
	@Test
	public void testUpdateDocketValue() throws DocketExistsException, 
			DuplicateEntityFoundException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", "John", "Jay", 
				null);
		Organization organization = this.organizationDelegate.create("Org", "O", 
				null);
		Country country = this.countryDelegate.create("Country", "C", true);
		State state = this.stateDelegate.create("State", "S", country, false, 
				true);
		City city = this.cityDelegate.create("City", true, state, country);
		ZipCode zipCode = this.zipCodeDelegate.create(city , "12345", null, 
				true);
		Address address = this.addressDelegate.findOrCreate("123 Easy St.", 
				null, null, null, zipCode);
		Location location = this.locationDelegate.create(organization, 
				new DateRange(), address);
		Court court = this.courtDelegate.create("Court", CourtCategory.CITY, 
				location);
		String docketValue = "Docket";
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		String newDocketValue = "New Docket";
		
		// Action
		docket = this.offenseTermDocketService.update(docket, court, 
				newDocketValue);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("person", person)
			.addExpectedValue("court", court)
			.addExpectedValue("value", newDocketValue)
			.performAssertions(docket);
	}
	
	@Test(expectedExceptions = {DocketExistsException.class})
	public void testDocketExistsException() throws DocketExistsException, 
			DuplicateEntityFoundException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", "John", "Jay", 
				null);
		Organization organization = this.organizationDelegate.create("Org", "O", 
				null);
		Country country = this.countryDelegate.create("Country", "C", true);
		State state = this.stateDelegate.create("State", "S", country, false, 
				true);
		City city = this.cityDelegate.create("City", true, state, country);
		ZipCode zipCode = this.zipCodeDelegate.create(city , "12345", null, 
				true);
		Address address = this.addressDelegate.findOrCreate("123 Easy St.", 
				null, null, null, zipCode);
		Location location = this.locationDelegate.create(organization, 
				new DateRange(), address);
		Court court = this.courtDelegate.create("Court", CourtCategory.CITY, 
				location);
		String docketValue = "Docket";
		this.docketDelegate.create(person, court, docketValue);
		String secondDocketValue = "Second Docket";
		Docket docket = this.docketDelegate.create(person, court, 
				secondDocketValue);
		
		// Action
		docket = this.offenseTermDocketService.update(docket, court, 
				docketValue);
	}

}