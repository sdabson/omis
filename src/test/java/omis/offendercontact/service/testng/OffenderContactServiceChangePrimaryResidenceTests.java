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
import omis.address.service.delegate.AddressDelegate;
import omis.address.service.delegate.ZipCodeDelegate;
import omis.audit.domain.VerificationMethod;
import omis.audit.domain.VerificationSignature;
import omis.audit.service.delegate.VerificationMethodDelegate;
import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.offendercontact.service.OffenderContactService;
import omis.person.service.delegate.PersonDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.residence.domain.ResidenceCategory;
import omis.residence.domain.ResidenceStatus;
import omis.residence.domain.ResidenceTerm;
import omis.residence.exception.NonResidenceTermConflictException;
import omis.residence.exception.ResidenceTermConflictException;
import omis.residence.exception.ResidenceTermExistsException;
import omis.residence.service.delegate.ResidenceTermDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.user.service.delegate.UserAccountDelegate;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to change primary residence..
 *
 * @author Sheronda Vaughn
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"offenderContact", "service"})
public class OffenderContactServiceChangePrimaryResidenceTests 
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
	@Qualifier("residenceTermDelegate")
	private ResidenceTermDelegate residenceTermDelegate;
	
/* Service to test. */
	
	@Autowired
	@Qualifier("offenderContactService")
	private OffenderContactService offenderContactService;
	
	@Test
	 public void testChangePrimaryResidence() throws ResidenceTermConflictException, 
	 		NonResidenceTermConflictException, ResidenceTermExistsException, 
	 		DuplicateEntityFoundException {
			//Arrangements
			Offender offender = this.offenderDelegate.createWithoutIdentity(
					"lastName", "firstName", "middleName", "suffix");
			Date effectiveDate = this.parseDateText("01/01/2017");
			Country country = this.countryDelegate.create("Country", "C", true);
			State state = this.stateDelegate.create("State", "ST", country, true, true);
			City city = this.cityDelegate.create("City", true, state, country);
			ZipCode zipCode = this.zipCodeDelegate.create(city, "00001", null, true);
			Address address = this.addressDelegate.findOrCreate("1 A Street", null, 
					null, null, zipCode);
			Boolean confirmed = false;
			VerificationMethod method = this.verificationMethodDelegate.create(
					"Method", (short) 1, true);
			VerificationSignature verificationSignature = new VerificationSignature(
					this.accountDelegate.findByUsername("AUDIT"), 
					this.parseDateText("02/01/2017"), true, method);
			DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
					this.parseDateText("04/01/2017"));			
			ResidenceTerm residenceTerm = this.residenceTermDelegate.createResidenceTerm(
					offender, dateRange, ResidenceCategory.PRIMARY, address, 
					ResidenceStatus.RESIDENT, confirmed, null, verificationSignature);
								
			//Action
			residenceTerm = this.offenderContactService.changePrimaryResidence(
					offender, address, effectiveDate);
			
			//Assertions
			PropertyValueAsserter.create()
				.addExpectedValue("person", offender)
				.addExpectedValue("address", address)
				.addExpectedValue("dateRange", dateRange)
				.performAssertions(residenceTerm);
		}
	
	@Test
	 public void testChangePrimaryResidenceUnmatchedAddress() throws ResidenceTermConflictException, 
	 		NonResidenceTermConflictException, ResidenceTermExistsException, 
	 		DuplicateEntityFoundException {
			//Arrangements
			Offender offender = this.offenderDelegate.createWithoutIdentity(
					"lastName", "firstName", "middleName", "suffix");
			Date effectiveDate = this.parseDateText("04/01/2017");
			Country country = this.countryDelegate.create("Country", "C", true);
			State state = this.stateDelegate.create("State", "ST", country, true, true);
			City city = this.cityDelegate.create("City", true, state, country);
			ZipCode zipCode = this.zipCodeDelegate.create(city, "00001", null, true);
			Address address = this.addressDelegate.findOrCreate("1 A Street", null, 
					null, null, zipCode);
			String notes = null;
			Boolean confirmed = false;
			VerificationMethod method = this.verificationMethodDelegate.create(
					"Method", (short) 1, true);
			VerificationSignature verificationSignature = new VerificationSignature(
					this.accountDelegate.findByUsername("AUDIT"), 
					this.parseDateText("02/01/2017"), true, method);
			DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), null);			
			this.residenceTermDelegate.createResidenceTerm(
					offender, dateRange, ResidenceCategory.PRIMARY, address, 
					ResidenceStatus.RESIDENT, confirmed, notes, verificationSignature);					
			Address newAddress = this.addressDelegate.findOrCreate("2 A Street", null, 
					null, null, zipCode);
			//Action
			ResidenceTerm residenceTerm = this.offenderContactService.changePrimaryResidence(
					offender, newAddress, effectiveDate);		
			
			//Assertions
			PropertyValueAsserter.create()
				.addExpectedValue("person", offender)
				.addExpectedValue("address", newAddress)
				.addExpectedValue("dateRange", new DateRange(effectiveDate, null))
				.performAssertions(residenceTerm);
	}
	
	@Test
	 public void testChangePrimaryResidenceDateRangeNull() throws ResidenceTermConflictException, 
	 		NonResidenceTermConflictException, ResidenceTermExistsException, 
	 		DuplicateEntityFoundException {
			//Arrangements
			Offender offender = this.offenderDelegate.createWithoutIdentity(
					"lastName", "firstName", "middleName", "suffix");
			Date effectiveDate = this.parseDateText("01/01/2017");
			Country country = this.countryDelegate.create("Country", "C", true);
			State state = this.stateDelegate.create("State", "ST", country, true, true);
			City city = this.cityDelegate.create("City", true, state, country);
			ZipCode zipCode = this.zipCodeDelegate.create(city, "00001", null, true);
			Address address = this.addressDelegate.findOrCreate("1 A Street", null, 
					null, null, zipCode);			
			String notes = null;
			Boolean confirmed = false;			
			VerificationMethod method = this.verificationMethodDelegate.create(
					"Method", (short) 1, true);
			VerificationSignature verificationSignature = new VerificationSignature(
					this.accountDelegate.findByUsername("AUDIT"), 
					this.parseDateText("02/01/2017"), true, method);
			DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), null);			
			ResidenceTerm residenceTerm = this.residenceTermDelegate.createResidenceTerm(
					offender, dateRange, ResidenceCategory.PRIMARY, address, 
					ResidenceStatus.RESIDENT, confirmed, notes, verificationSignature);					
			Address newAddress = this.addressDelegate.findOrCreate("2 A Street", null, 
					null, null, zipCode);
			//Action
			residenceTerm = this.offenderContactService.changePrimaryResidence(
				offender, newAddress, effectiveDate);			
			
			//Assertions
			PropertyValueAsserter.create()
				.addExpectedValue("person", offender)
				.addExpectedValue("address", newAddress)
				.addExpectedValue("dateRange", new DateRange(effectiveDate, null))
				.performAssertions(residenceTerm);
	}	
	
	@Test(expectedExceptions = {ResidenceTermConflictException.class})
	 public void testResidenceTermConflictException() throws ResidenceTermConflictException, 
	 		NonResidenceTermConflictException, ResidenceTermExistsException, 
	 		DuplicateEntityFoundException {
			//Arrangements
			Offender offender = this.offenderDelegate.createWithoutIdentity(
					"lastName", "firstName", "middleName", "suffix");
			Date effectiveDate = this.parseDateText("04/01/2017");
			Country country = this.countryDelegate.create("Country", "C", true);
			State state = this.stateDelegate.create("State", "ST", country, true, true);
			City city = this.cityDelegate.create("City", true, state, country);
			ZipCode zipCode = this.zipCodeDelegate.create(city, "00001", null, true);
			Address address = this.addressDelegate.findOrCreate("1 A Street", null, 
					null, null, zipCode);
			String notes = null;
			Boolean confirmed = false;
			VerificationMethod method = this.verificationMethodDelegate.create(
					"Method", (short) 1, true);
			VerificationSignature verificationSignature = new VerificationSignature(
					this.accountDelegate.findByUsername("AUDIT"), 
					this.parseDateText("02/01/2017"), true, method);
			DateRange dateRange = new DateRange(effectiveDate, this.parseDateText("05/01/2017"));			
			 this.residenceTermDelegate.createResidenceTerm(
					offender, dateRange, ResidenceCategory.PRIMARY, address, 
					ResidenceStatus.RESIDENT, confirmed, notes, verificationSignature);		
			 Address newAddress = this.addressDelegate.findOrCreate("2 A Street", null, 
						null, null, zipCode);
					
			//Action
			this.offenderContactService.changePrimaryResidence(
					offender, newAddress, effectiveDate);
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