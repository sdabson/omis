package omis.visitation.service.testng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.address.domain.Address;
import omis.address.domain.ZipCode;
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
import omis.visitation.service.VisitationAssociationService;

/**
 * Tests method to create address.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"visitation", "service"})
public class VisitationAssociationServiceCreateAddressTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

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
	@Qualifier("zipCodeDelegate")
	private ZipCodeDelegate zipCodeDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("visitationAssociationService")
	private VisitationAssociationService visitationAssociationService;

	/* Test methods. */

	/**
	 * Tests the creation of a new address.
	 * 
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	@Test
	public void testCreateAddress() throws DuplicateEntityFoundException {
		// Arrangements
		Country country = this.countryDelegate.create("Country", "C", true);
		State state = this.stateDelegate.create("State", "S", country, false, 
				true);
		City city = this.cityDelegate.create("City", true, state, country);
		ZipCode zipCode = this.zipCodeDelegate.create(city , "12345", null, 
				true);
		String houseNumber = "123 East St.";

		// Action
		Address address = this.visitationAssociationService.createAddress(
				houseNumber, zipCode);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("value", houseNumber)
			.addExpectedValue("zipCode", zipCode)
			.performAssertions(address);
	}
}