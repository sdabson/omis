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
package omis.offendercontact.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.address.domain.Address;
import omis.address.domain.ZipCode;
import omis.address.exception.ZipCodeExistsException;
import omis.address.service.delegate.AddressDelegate;
import omis.address.service.delegate.ZipCodeDelegate;
import omis.audit.service.delegate.VerificationMethodDelegate;
import omis.country.domain.Country;
import omis.country.exception.CountryExistsException;
import omis.country.service.delegate.CountryDelegate;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.offendercontact.service.OffenderContactService;
import omis.person.service.delegate.PersonDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.exception.CityExistsException;
import omis.region.exception.StateExistsException;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.residence.domain.NonResidenceTerm;
import omis.residence.domain.ResidenceTerm;
import omis.residence.exception.NonResidenceTermConflictException;
import omis.residence.exception.NonResidenceTermExistsException;
import omis.residence.exception.ResidenceTermConflictException;
import omis.residence.exception.ResidenceTermExistsException;
import omis.residence.service.delegate.NonResidenceTermDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.user.service.delegate.UserAccountDelegate;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to change primary residence, homeless.
 *
 * @author Sheronda Vaughn
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"offenderContact", "service"})
public class OffenderContactServiceChangePrimaryResidenceHomelessTests 
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Delegates. */

	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("cityDelegate")
	private CityDelegate cityDelegate;
	
	@Autowired
	@Qualifier("stateDelegate")
	private StateDelegate stateDelegate;
	
	@Autowired
	@Qualifier("countryDelegate")
	private CountryDelegate countryDelegate;
	
	@Autowired
	@Qualifier("addressDelegate")
	private AddressDelegate addressDelegate;
	
	@Autowired
	@Qualifier("zipCodeDelegate")
	private ZipCodeDelegate zipCodeDelegate;
	
	@Autowired
	@Qualifier("userAccountDelegate")
	private UserAccountDelegate accountDelegate;
	
	@Autowired
	@Qualifier("verificationMethodDelegate")
	private VerificationMethodDelegate verificationMethodDelegate;
	
	@Autowired
	@Qualifier("nonResidenceTermDelegate")
	private NonResidenceTermDelegate  nonResidenceTermDelegate;
	
/* Service to test. */
	
	@Autowired
	@Qualifier("offenderContactService")
	private OffenderContactService offenderContactService;
	
	@Test
	 public void testChangePrimaryResidenceHomeless() throws ResidenceTermConflictException, 
	 		NonResidenceTermConflictException, ResidenceTermExistsException, 
	 		NonResidenceTermExistsException, CountryExistsException, StateExistsException, 
	 		CityExistsException, ZipCodeExistsException {
			//Arrangements
			Offender offender = this.offenderDelegate.createWithoutIdentity(
					"lastName", "firstName", "middleName", "suffix");
			Date effectiveDate = this.parseDateText("03/01/2017");
			Country country = this.countryDelegate.create("Country", "C", true);
			State state = this.stateDelegate.create("State", "ST", country, true, true);
			City city = this.cityDelegate.create("City", true, state, country);
			ZipCode zipCode = this.zipCodeDelegate.create(city, "00001", null, true);
			Address address = this.addressDelegate.findOrCreate("1 A Street", null, 
					null, null, zipCode);
			String notes = null;
			Boolean confirmed = false;
			Date startDate = this.parseDateText("01/01/2017");
			DateRange dateRange = new DateRange(startDate, null);			
			NonResidenceTerm homelessTerm =  this.nonResidenceTermDelegate.createHomelessTerm(
					offender, dateRange, city, state, notes, confirmed);
								
			//Action
			ResidenceTerm residenceTerm = this.offenderContactService.changePrimaryResidence(
					offender, address, effectiveDate);

		
			//Assertions
			PropertyValueAsserter.create()
				.addExpectedValue("person", offender)
				.addExpectedValue("address", address)
				.addExpectedValue("dateRange", new DateRange(effectiveDate, null))
				.performAssertions(residenceTerm);
			
			PropertyValueAsserter.create()
				.addExpectedValue("person", offender)
				.addExpectedValue("city", city)
				.addExpectedValue("state", state)
				.addExpectedValue("dateRange", new DateRange(startDate, effectiveDate))
				.performAssertions(homelessTerm);			
	}
		
	@Test(expectedExceptions = {NonResidenceTermConflictException.class})
	 public void testNonResidenceTermConflictException() throws ResidenceTermConflictException, 
	 		NonResidenceTermConflictException, ResidenceTermExistsException,  
	 		NonResidenceTermExistsException, CountryExistsException, StateExistsException, 
	 		CityExistsException, ZipCodeExistsException {
			//Arrangements
			Offender offender = this.offenderDelegate.createWithoutIdentity(
					"lastName", "firstName", "middleName", "suffix");
			Date effectiveDate = this.parseDateText("03/01/2017");
			Country country = this.countryDelegate.create("Country", "C", true);
			State state = this.stateDelegate.create("State", "ST", country, true, true);
			City city = this.cityDelegate.create("City", true, state, country);
			ZipCode zipCode = this.zipCodeDelegate.create(city, "00001", null, true);
			Address address = this.addressDelegate.findOrCreate("1 A Street", null, 
					null, null, zipCode);			
			String notes = null;
			Boolean confirmed = false;			
			DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
					this.parseDateText("05/01/2017"));			
			this.nonResidenceTermDelegate.createHomelessTerm(
					offender, dateRange, city, state, notes, confirmed);
			 
			//Action
			this.offenderContactService.changePrimaryResidence(
					offender, address, effectiveDate);			
	}
	
	 @Test(expectedExceptions = {AssertionError.class})
	 public void testAssertionError() throws ResidenceTermConflictException, 
	 		NonResidenceTermConflictException, ResidenceTermExistsException, 
	 		NonResidenceTermExistsException, CountryExistsException, StateExistsException, 
	 		ZipCodeExistsException, CityExistsException {
			//Arrangements
				Offender offender = this.offenderDelegate.createWithoutIdentity(
						"lastName", "firstName", "middleName", "suffix");
				Date effectiveDate = this.parseDateText("03/01/2017");
				Country country = this.countryDelegate.create("Country", "C", true);
				State state = this.stateDelegate.create("State", "ST", country, true, true);
				City city = this.cityDelegate.create("City", true, state, country);
				ZipCode zipCode = this.zipCodeDelegate.create(city, "00001", null, true);
				Address address = this.addressDelegate.findOrCreate("1 A Street", null, 
						null, null, zipCode);			
				String notes = null;
				Boolean confirmed = false;			
				DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), null);			
				this.nonResidenceTermDelegate.createHomelessTerm(
						offender, dateRange, city, state, notes, confirmed);
				DateRange anotherDateRange = new DateRange(this.parseDateText("02/02/2017"), null);			
				this.nonResidenceTermDelegate.createHomelessTerm(
						offender, anotherDateRange, city, state, notes, confirmed);
				 
				//Action
				this.offenderContactService.changePrimaryResidence(
						offender, address, effectiveDate);
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