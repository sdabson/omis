/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.residence.service.testng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.address.domain.Address;
import omis.address.domain.BuildingCategory;
import omis.address.domain.ZipCode;
import omis.address.exception.ZipCodeExistsException;
import omis.address.service.delegate.AddressDelegate;
import omis.address.service.delegate.ZipCodeDelegate;
import omis.contact.exception.ContactExistsException;
import omis.contact.service.delegate.ContactDelegate;
import omis.country.domain.Country;
import omis.country.exception.CountryExistsException;
import omis.country.service.delegate.CountryDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.exception.CityExistsException;
import omis.region.exception.StateExistsException;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.residence.service.ResidenceService;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests finding mailing address.
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"residence", "service"})
public class ResidenceServiceFindMailingAddressTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Service delegates. */
	@Autowired
	@Qualifier("contactDelegate")
	private ContactDelegate contactDelegate;
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
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
	
	/* Service to test. */
	@Autowired
	@Qualifier("residenceService")
	private ResidenceService residenceService;
	
	/* Test methods. */
	
	/**
	 * Tests finding mailing address.
	 * @throws CountryExistsException 
	 * @throws StateExistsException 
	 * @throws CityExistsException 
	 * @throws ZipCodeExistsException 
	 * @throws ContactExistsException 
	 */
	@Test
	public void testFindingMailingAddress()
		throws CountryExistsException, StateExistsException,
		CityExistsException, ZipCodeExistsException,
		ContactExistsException {
		// Arrangements
		Person person = this.personDelegate.create(
			"Smith", "John", "Ryan", "Mr");
		Country country = this.countryDelegate.create("Country", "C",
			true);
		State state = this.stateDelegate.create("State", "ST",
			country, true, true);
		City city = this.cityDelegate.create("City", true, state,
			country);
		ZipCode zipCode = this.zipCodeDelegate.create(city, "59602",
			null, true);
		Address mailingAddress = this.addressDelegate
			.findOrCreate("1234", null, null, BuildingCategory.HOUSE,
			zipCode);
		this.contactDelegate.create(person,	mailingAddress, null);
		
		// Action
		Address expectedMailingAddress
			= this.residenceService.findMailingAddress(person);
		
		// Assertions
		assert expectedMailingAddress.equals(mailingAddress)
		: "Mailing address is wrong";
	}
}
