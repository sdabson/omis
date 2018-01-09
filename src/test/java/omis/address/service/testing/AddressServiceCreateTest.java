package omis.address.service.testing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.address.domain.Address;
import omis.address.domain.ZipCode;
import omis.address.service.AddressService;
import omis.address.service.delegate.AddressDelegate;
import omis.address.service.delegate.ZipCodeDelegate;
import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Address service create unit test.
 * 
 * @author Joel Norris
 * @version 0.1.0 (July 5, 2017)
 * @since OMIS 3.0
 */
@Test(groups = {"address", "service"})
public class AddressServiceCreateTest 
	extends AbstractHibernateTransactionalTestNGSpringContextTests{
	
	/* Delegates. */
	
	@Autowired
	@Qualifier("addressDelegate")
	private AddressDelegate addressDelegate;
	
	@Autowired
	@Qualifier("zipCodeDelegate")
	private ZipCodeDelegate zipCodeDelegate;
	
	@Autowired
	@Qualifier("cityDelegate")
	private CityDelegate cityDelegate;
	
	@Autowired
	@Qualifier("stateDelegate")
	private StateDelegate stateDelegate;
	
	@Autowired
	@Qualifier("countryDelegate")
	private CountryDelegate countryDelegate;
	
	/* Services. */
	
	@Autowired
	@Qualifier("addressService")
	private AddressService addressService;
	
	/**
	 * Instaniates a default instance of address service create test.
	 */
	public AddressServiceCreateTest() {
		//Default constructor.
	}
	
	/**
	 * Test the create method of AddressService.
	 * 
	 * @throws DuplicateEntityFoundException thrown when a duplicate city, state, zip code, or
	 * address if found.
	 */
	public void testCreation() throws DuplicateEntityFoundException {
		
		/* Create Country */
		
		String countryName = "United Land Masses of \'Murica!";
		String countryAbbreviation = "ULM\'M";
		Boolean countryValid = true;
		Country country = this.countryDelegate.create(countryName, countryAbbreviation, countryValid);
		
		/* Create State */
		
		String stateName = "Montana";
		String stateAbbreviation = "MT";
		Boolean stateValid = true;
		Boolean homeValid = true;
		State state = this.stateDelegate.create(stateName, stateAbbreviation, 
				country, homeValid, stateValid);
		
		/* Create City */
		
		String cityName = "CityName";
		Boolean cityValid = true;
		City city = this.cityDelegate.create(cityName, cityValid, state, country);
		
		/* Create ZipCode */
		
		String zipCodeValue = "47365";
		String zipCodeExtension = "456";
		Boolean active = true;
		ZipCode zipCode = this.zipCodeDelegate.create(city, zipCodeValue, zipCodeExtension, active);
	
		/* Create address. */
		
		String addressValue = "123 Value St";
		Address address = this.addressService.create(addressValue, zipCode);
		
		/* Assertion */
		
		PropertyValueAsserter.create()
			.addExpectedValue("value", addressValue)
			.addExpectedValue("zipCode", zipCode)
			.performAssertions(address);
	}
}