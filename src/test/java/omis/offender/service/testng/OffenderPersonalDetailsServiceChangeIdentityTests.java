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
package omis.offender.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.demographics.domain.Sex;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.OffenderPersonalDetailsService;
import omis.offender.service.delegate.OffenderDelegate;
import omis.person.domain.PersonIdentity;
import omis.person.exception.PersonIdentityExistsException;
import omis.person.exception.SocialSecurityNumberExistsException;
import omis.person.exception.StateIdNumberExistsException;
import omis.person.service.delegate.PersonIdentityDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to change offender person details.
 *
 * @author Sheronda Vaughn
 * @author Stephen Abson
 * @version 0.0.1 (Feb 21, 2018)
 * @since OMIS 3.0
 */
@Test(groups = {"offender", "service"})
public class OffenderPersonalDetailsServiceChangeIdentityTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("personIdentityDelegate")
	private PersonIdentityDelegate personIdentityDelegate;
	
	@Autowired
	@Qualifier("countryDelegate")
	private CountryDelegate countryDelegate;
	
	@Autowired
	@Qualifier("stateDelegate")
	private StateDelegate stateDelegate;
	
	@Autowired
	@Qualifier("cityDelegate")
	private CityDelegate cityDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("offenderPersonalDetailsService")
	private OffenderPersonalDetailsService offenderPersonalDetailsService;

	/* Test methods. */

	/**
	 * Test change offender identity.
	 *
	 *
	 * @throws PersonIdentityExistsException person identity exists
	 * @throws DuplicateEntityFoundException duplicate entity found
	 * @throws SocialSecurityNumberExistsException if social security number
	 * exists
	 * @throws StateIdNumberExistsException if State ID number exists 
	 */
	@Test
	public void testChangeIdentity() throws PersonIdentityExistsException, 
			DuplicateEntityFoundException,
			StateIdNumberExistsException,
			SocialSecurityNumberExistsException {
		
		// Arrangements
		final String lastName = "LastName";
		final String firstName = "FirstName";
		final String middleName = "MiddleName";
		Date birthDate = this.parseDateText("01/01/1968");
		final Country birthCountry = this.countryDelegate.create(
				"United Status", "US", true);
		final State birthState = this.stateDelegate.create(
				"Montana", "MT", birthCountry, true, true);
		City birthPlace = this.cityDelegate.create("Helena", true, 
				birthState, birthCountry);
		Integer socialSecurityNumber = 123456789;
		String stateIdNumber = "12345";
		Sex sex = Sex.FEMALE;
		Boolean deceased = false;
		Date deathDate = null;		
		Offender offender = this.offenderDelegate.create(
				lastName, firstName, middleName, null, socialSecurityNumber, 
				stateIdNumber, birthDate, birthCountry, birthState, birthPlace, 
				sex);

		// Action
		PersonIdentity personIdentity = this.offenderPersonalDetailsService
				.changeIdentity(offender, birthDate, birthCountry, 
						birthState, birthPlace, socialSecurityNumber, 
						stateIdNumber, sex, deceased, deathDate);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("person", offender)
			.addExpectedValue("birthDate", birthDate)
			.addExpectedValue("birthCountry", birthCountry)
			.addExpectedValue("birthState", birthState)
			.addExpectedValue("birthPlace", birthPlace)
			.addExpectedValue("socialSecurityNumber", socialSecurityNumber)
			.addExpectedValue("stateIdNumber", stateIdNumber)
			.addExpectedValue("sex", sex)
			.addExpectedValue("deceased", deceased)
			.addExpectedValue("deathDate", deathDate)
			.performAssertions(personIdentity);
	}

	/**
	 * Test person identity exists exception.
	 *
	 *
	 * @throws DuplicateEntityFoundException duplicate entity found
	 * @throws SocialSecurityNumberExistsException if social security number
	 * exists
	 * @throws StateIdNumberExistsException if State ID number exists 
	 */
	@Test(expectedExceptions = {PersonIdentityExistsException.class})
	public void testPersonIdentityExistsException() 
			throws DuplicateEntityFoundException,
				StateIdNumberExistsException,
				SocialSecurityNumberExistsException {
		
		// Arrangements
		final String lastName = "LastName";
		final String firstName = "FirstName";
		final String middleName = "MiddleName";
		Date birthDate = this.parseDateText("01/01/1968");
		final Country birthCountry = this.countryDelegate.create(
				"United Status", "US", true);
		final State birthState = this.stateDelegate.create(
				"Montana", "MT", birthCountry, true, true);
		City birthPlace = this.cityDelegate.create("Helena", true, 
				birthState, birthCountry);
		Integer socialSecurityNumber = 123456789;
		String stateIdNumber = "12345";
		Sex sex = Sex.FEMALE;
		Boolean deceased = false;
		Date deathDate = null;
		Offender offender = this.offenderDelegate.createWithoutIdentity(
				lastName, firstName, middleName, null);
		this.personIdentityDelegate.create(
				offender, sex, birthDate, birthCountry, birthState, birthPlace, 
				socialSecurityNumber, stateIdNumber, deceased, deathDate);
		// Action
		this.offenderPersonalDetailsService
			.changeIdentity(offender, birthDate, birthCountry, birthState, 
				birthPlace, socialSecurityNumber, stateIdNumber, sex, 
				deceased, deathDate);
	}
	
	/**
	 * Tests that attempts to change State ID number to a State ID number that
	 * is already used is prevented with a {@code StateIdNumberExistsException}.
	 * 
	 * @throws SocialSecurityNumberExistsException if social security number
	 * is used 
	 * @throws StateIdNumberExistsException if State ID number is used
	 * - asserted
	 * @throws PersonIdentityExistsException if person identity exists 
	 */
	@Test(expectedExceptions = {StateIdNumberExistsException.class})
	public void testWithExistingStateIdNumber()
			throws PersonIdentityExistsException,
				StateIdNumberExistsException,
				SocialSecurityNumberExistsException {
		
		// Arranges two offenders with different State ID numbers
		String juliusIdNumber = "MT123456789";
		this.offenderDelegate.create("Blofeld", "Julius", null, null, null,
				juliusIdNumber, this.parseDateText("12/12/1984"), null, null,
				null, null);
		Date ernstBirthDate = this.parseDateText("02/22/1984");
		Offender ernst = this.offenderDelegate.create("Large", "Ernst", null,
				"X", null, "MT654987321", ernstBirthDate,
				null, null, null, null);
		
		// Action - attempts to change the State ID number of one offender to
		// that of the other
		this.offenderPersonalDetailsService.changeIdentity(ernst,
				ernstBirthDate, null, null, null, null, juliusIdNumber, null,
				null, null);
	}
	
	/**
	 * Tests that attempt to change State ID number to a State ID number that is
	 * used multiple times is prevented with a
	 * {@code StateIdNumberExistsException}.
	 * 
	 * @throws SocialSecurityNumberExistsException if social security number
	 * exists 
	 * @throws StateIdNumberExistsException if State ID number is already used
	 * - asserted
	 * @throws PersonIdentityExistsException if person identity exists 
	 */
	@Test(expectedExceptions = {StateIdNumberExistsException.class})
	public void testWithMultipleExistingStateIdNumbers()
			throws PersonIdentityExistsException,
				StateIdNumberExistsException,
				SocialSecurityNumberExistsException {

		// Arranges three offenders - two with a shared State ID number
		String stateIdNumber = "MT123456789";
		this.offenderDelegate.create("Blofeld", "Julius", null, null, null,
				stateIdNumber, this.parseDateText("12/12/1981"), null, null,
				null, null);
		this.offenderDelegate.create("Largo", "Enrst", null, "XX", null, stateIdNumber,
				this.parseDateText("12/01/1981"), null, null, null, null);
		Date emilioBirthDate = this.parseDateText("12/03/1981");
		Offender emilio = this.offenderDelegate.create("No", "Emilio", null,
				null, null, "MT456321987", emilioBirthDate,
				null, null, null, null);
		
		// Action - attempts to change the State ID number of one offender to
		// that of the other two
		this.offenderPersonalDetailsService.changeIdentity(emilio,
				emilioBirthDate, null, null, null, null, stateIdNumber, null,
				null, null);
	}
	
	/**
	 * Tests that attempt to change SSN to an SSN that is already used is
	 * prevented with a {@code SocialSecurityNumberExistsException}.
	 * 
	 * @throws SocialSecurityNumberExistsException if social security number
	 * exists - asserted
	 * @throws StateIdNumberExistsException if State ID number exists 
	 * @throws PersonIdentityExistsException if person identity exists
	 */
	@Test(expectedExceptions = {SocialSecurityNumberExistsException.class})
	public void testChangeIdentityWithExisingSocialSecurityNumber()
			throws PersonIdentityExistsException,
				StateIdNumberExistsException,
				SocialSecurityNumberExistsException {
		
		// Arranges two offender's with different SSNs
		Integer emilioSsn = 123456789;
		this.offenderDelegate
				.create("Blofeld", "Emilio", "Francis", "XII", emilioSsn, null,
						null, null, null, null, Sex.MALE);
		
		Offender julius = this.offenderDelegate
				.create("Scaramanga", "Julius", null, null, 987654321, null,
						null, null, null, null, Sex.MALE);
		
		// Action - attempts to change one offender's SSN to that of the other
		this.offenderPersonalDetailsService
			.changeIdentity(julius, null, null, null, null, emilioSsn, null,
					Sex.MALE, null, null);
	}
	
	/**
	 * Tests that attempts to change SSN to an SSN that is already used by
	 * multiple other people is prevented with a
	 * {@code SocialSecurityNumberExistsException}.
	 * 
	 * @throws SocialSecurityNumberExistsException if social security number
	 * exists - asserted 
	 * @throws StateIdNumberExistsException if State ID number exists 
	 * @throws PersonIdentityExistsException if person identity exists
	 * 
	 */
	@Test(expectedExceptions = {SocialSecurityNumberExistsException.class})
	public void testChangeIdentityWithMultipleExistingSocialSecurityNumbers()
			throws PersonIdentityExistsException,
				StateIdNumberExistsException,
				SocialSecurityNumberExistsException {
		
		// Arranges three offenders - two with the same SSN
		Integer sharedSsn = 123456789;
		this.offenderDelegate.create("Blofeld", "Auric", "Francis", null,
				sharedSsn, null, null, null, null, null, null);
		this.offenderDelegate.create("Scaramanga", "Emilio", null, "X",
				sharedSsn, null, null, null, null, null, null);
		Offender julius = this.offenderDelegate.create("Julius", "Ernst", null,
				null, 321654987, null, null, null, null, null, null);
		
		// Action - attempts to change third offender's SSN to that of others
		this.offenderPersonalDetailsService.changeIdentity(julius, null, null,
				null, null, sharedSsn, null, null, null, null);
	}
	
	/* Helper methods */
	
	// Parses date text
	private Date parseDateText(final String text) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(text);
		} catch (ParseException e) {
			throw new RuntimeException("Parse error", e);
		}
	}
}