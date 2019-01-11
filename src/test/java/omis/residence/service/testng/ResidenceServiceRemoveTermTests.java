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
import omis.country.exception.CountryExistsException;
import omis.country.service.delegate.CountryDelegate;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.location.domain.Location;
import omis.location.exception.LocationNotAllowedException;
import omis.location.service.delegate.LocationDelegate;
import omis.organization.domain.Organization;
import omis.organization.service.delegate.OrganizationDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.exception.CityExistsException;
import omis.region.exception.StateExistsException;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.residence.dao.NonResidenceTermDao;
import omis.residence.dao.ResidenceTermDao;
import omis.residence.domain.NonResidenceTerm;
import omis.residence.domain.ResidenceStatus;
import omis.residence.domain.ResidenceTerm;
import omis.residence.exception.AllowedResidentialLocationRuleExistsException;
import omis.residence.exception.NonResidenceTermExistsException;
import omis.residence.exception.PrimaryResidenceExistsException;
import omis.residence.exception.ResidenceStatusConflictException;
import omis.residence.exception.ResidenceTermExistsException;
import omis.residence.service.ResidenceService;
import omis.residence.service.delegate.AllowedResidentialLocationRuleDelegate;
import omis.residence.service.delegate.NonResidenceTermDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.user.service.delegate.UserAccountDelegate;

/**
* Residence service remove term tests. 
* 
* @version Sheronda Vaughn
* @version 0.0.1 (Sep 12, 2018)
* @since OMIS 3.0
*/

public class ResidenceServiceRemoveTermTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Constructor. */
	
	/**
	 * Instantiates an instance residence service remove tests.
	 */
	public ResidenceServiceRemoveTermTests() {
		//Default constructor.
	}
	/* Service delegates. */
	
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
	@Qualifier("nonResidenceTermDelegate")
	private NonResidenceTermDelegate nonResidenceTermDelegate;
	
	@Autowired
	@Qualifier("addressDelegate")
	private AddressDelegate addressDelegate;
	
	@Autowired
	@Qualifier("zipCodeDelegate")
	private ZipCodeDelegate zipCodeDelegate;
	
	@Autowired
	@Qualifier("verificationMethodDelegate")
	private VerificationMethodDelegate verificationMethodDelegate;
	
	@Autowired
	@Qualifier("userAccountDelegate")
	private UserAccountDelegate accountDelegate;
	
	/*@Autowired
	@Qualifier("residenceTermDelegate")
	private ResidenceTermDelegate residenceTermDelegate;
	
	@Autowired
	@Qualifier("nonResidenceTermDelegate")
	private NonResidenceTermDelegate nonResidenceTermDelegate;*/
	
	@Autowired
	@Qualifier("locationDelegate")
	private LocationDelegate locationDelegate;

	@Autowired
	@Qualifier("organizationDelegate")
	private OrganizationDelegate organizationDelegate;
	
	@Autowired
	@Qualifier("allowedResidentialLocationRuleDelegate")
	private AllowedResidentialLocationRuleDelegate 
			allowedResidentialLocationRuleDelegate;
	
	
	/* Data access objects. */
	
	@Autowired
	@Qualifier("residenceTermDao")
	private ResidenceTermDao residenceTermDao;
	
	@Autowired
	@Qualifier("nonResidenceTermDao")
	private NonResidenceTermDao nonResidenceTermDao;
	
	/* Service to test. */
	
	@Autowired
	@Qualifier("residenceService")
	private ResidenceService residenceService;
	
	/**
	 * Tests removal of a residence term.
	 * 
	 * @throws PrimaryResidenceExistsException primary residence exists
	 * @throws ResidenceStatusConflictException residence status conflict
	 * @throws ResidenceTermExistsException residence term exists
	 * @throws NonResidenceTermExistsException non residence term exists
	 * @throws DuplicateEntityFoundException duplicate entity found
	 */
	@Test
	public void testRemoveResidenceTerm() throws PrimaryResidenceExistsException, 
		ResidenceStatusConflictException, ResidenceTermExistsException, 
		NonResidenceTermExistsException, DuplicateEntityFoundException {
		Person person = this.personDelegate.create("Smith", "John", "Jay", 
				null);
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("06/01/2017"));
		Country country = this.countryDelegate.create("Country", "C", true);
		State state = this.stateDelegate.create("State", "ST", country, true, 
				true);
		City city = this.cityDelegate.create("City", true, state, country);
		String notes = "Notes";
		ZipCode zipCode = this.zipCodeDelegate.create(city, "00001", null, 
				true);
		Address address = this.addressDelegate.findOrCreate("1 A Street", null, 
				null, null, zipCode);
		Boolean primary = true;
		Boolean fosterCare = false;
		Boolean confirmed = false;
		VerificationMethod method = this.verificationMethodDelegate.create(
				"Method", (short) 1, true);
		VerificationSignature verificationSignature = new VerificationSignature(
				this.accountDelegate.findByUsername("AUDIT"), 
				this.parseDateText("02/01/2017"), true, method);
		//Create residence term to remove
		ResidenceTerm term = this.residenceService.createResidenceTerm(
				person, dateRange, primary, address, fosterCare, confirmed, notes, verificationSignature);			
		//Action
		this.residenceService.removeResidenceTerm(term);;		
		//Assertion
		assert this.residenceTermDao.find(person, dateRange, address) == null
				: "Residence term was not removed.";
	}

	/**
	 *  Test removal of non residence term.
	 *  
	 * @throws NonResidenceTermExistsException nonResidence term exists
	 * @throws LocationNotAllowedException location not allowed 
	 * @throws ResidenceStatusConflictException residence status conflict
	 * @throws AllowedResidentialLocationRuleExistsException allowed residential location rule exists
	 * @throws DuplicateEntityFoundException duplicate entity found
	 */
	@Test
	public void testRemoveNonResidenceTerm() throws NonResidenceTermExistsException, 
		LocationNotAllowedException, ResidenceStatusConflictException, 
		AllowedResidentialLocationRuleExistsException, DuplicateEntityFoundException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", "John", "Jay", 
				null);
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("06/01/2017"));
		Country country = this.countryDelegate.create("Country", "C", true);
		State state = this.stateDelegate.create("State", "ST", country, true, 
				true);
		City city = this.cityDelegate.create("City", true, state, country);
		String notes = "Notes";
		ResidenceStatus status = ResidenceStatus.GROUP_HOME;
		ZipCode zipCode = this.zipCodeDelegate.create(city, "00001", null, 
				true);
		Address address = this.addressDelegate.findOrCreate("1 A Street", null, 
				null, null, zipCode);
		Organization organization = this.organizationDelegate.create("Org", 
				null, null);
		Location location = this.locationDelegate.create(organization, 
				dateRange, address);
		this.allowedResidentialLocationRuleDelegate.create(location, status);
		Boolean confirmed = false;		
		VerificationMethod method = this.verificationMethodDelegate.create(
				"Method", (short) 1, true);
		VerificationSignature verificationSignature = new VerificationSignature(
				this.accountDelegate.findByUsername("AUDIT"), 
				this.parseDateText("02/01/2017"), true, method);
		//Create non-residence term to remove
		NonResidenceTerm nonResidenceTerm = this.residenceService.createNonResidenceTerm(
				person, dateRange, status, location, confirmed, notes, verificationSignature);
		//Action
		this.residenceService.removeNonResidenceTerm(nonResidenceTerm);
	   //Assertion
		assert this.nonResidenceTermDao.find(person, dateRange, location, state, city, status) == null
				: "NonResidence term was not removed.";
	}
	
	/**
	 * Tests removal of a homeless term.
	 * 
	 * @throws NonResidenceTermExistsException nonResidence term exists
	 * @throws ResidenceStatusConflictException residence status conflict
	 * @throws CountryExistsException country exists
	 * @throws StateExistsException state exists
	 * @throws CityExistsException city exists
	 */
	@Test
	public void testRemoveHomelessTerm() throws NonResidenceTermExistsException, 
		ResidenceStatusConflictException, CountryExistsException, StateExistsException, 
		CityExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", "John", "Jay", 
				null);
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("06/01/2017"));
		
		Country country = this.countryDelegate.create("Country", "C", true);
		State state = this.stateDelegate.create("State", "ST", country, true, 
				true);
		City city = this.cityDelegate.create("City", true, state, country);
		String notes = "Notes";
		Boolean confirmed = false;		
		//Create homeless term to remove
		NonResidenceTerm nonResidenceTerm = this.residenceService
				.createHomelessTerm(person, dateRange, city, state, notes, 
						confirmed);
		//Action
		this.residenceService.removeNonResidenceTerm(nonResidenceTerm);
		//Assertion
		assert this.nonResidenceTermDao.find(person, dateRange, null, state, city, null) == null
				: "NonResidence term was not removed.";
	}
	
	/* Helper methods */
	
	/*
	 * Parses the specified string and returns a {@code Date} object
	 * representing the parsed {@code String}.
	 *  
	 * @param text text to parse
	 * @return date 
	 */
	private Date parseDateText(final String text) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(text);
		} catch (ParseException e) {
			throw new RuntimeException("Parse error", e);
		}
	}
}