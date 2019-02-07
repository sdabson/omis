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
 * Tests method to change offender identity without social security number.
 *
 * @author Sheronda Vaughn
 * @author Stephen Abson
 * @version 0.0.1 (Feb 21, 2018)
 * @since OMIS 3.0
 */
@Test(groups = {"offender", "service"})
public class OffenderPersonalDetailsServiceChangeIdentityWithoutSsnTests
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

	@Test
	public void testChangeIdentityWithoutSsn() 
			throws PersonIdentityExistsException, 
				DuplicateEntityFoundException,
				StateIdNumberExistsException {
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
				.changeIdentityWithoutSsn(offender, birthDate, birthCountry, 
						birthState, birthPlace, stateIdNumber, sex, deceased, 
						deathDate);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("person", offender)
			.addExpectedValue("birthDate", birthDate)
			.addExpectedValue("birthCountry", birthCountry)
			.addExpectedValue("birthState", birthState)
			.addExpectedValue("birthPlace", birthPlace)
			.addExpectedValue("stateIdNumber", stateIdNumber)
			.addExpectedValue("sex", sex)
			.addExpectedValue("deceased", deceased)
			.addExpectedValue("deathDate", deathDate)
			.performAssertions(personIdentity);
	}

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
	 * Tests that attempt to change State ID number to one already used is
	 * prevented with {@code StateIdNumberExistsException}.
	 * 
	 * @throws StateIdNumberExistsException if State ID number is used
	 * - asserted
	 * @throws PersonIdentityExistsException if person identity exists
	 */
	@Test(expectedExceptions = {StateIdNumberExistsException.class})
	public void testWithExistingStateIdNumber()
			throws PersonIdentityExistsException, StateIdNumberExistsException {
		
		// Arranges two offenders
		String ernstStateIdNumber = "MT321654987";
		this.offenderDelegate.create("Largo", "Ernst", null, null, null,
				ernstStateIdNumber, this.parseDateText("12/12/1982"), null,
				null, null, null);
		Date juliusBirthDate = this.parseDateText("12/09/2984");
		Offender julius = this.offenderDelegate
				.create("Blofeld", "Julius", null, null, null, "MT777888999",
						juliusBirthDate, null, null, null, null);
		
		// Action - attempts to change one offender's State ID number to
		// the other's
		this.offenderPersonalDetailsService.changeIdentityWithoutSsn(julius,
				juliusBirthDate, null, null, null, ernstStateIdNumber,
				null, null, null);
	}
	
	/**
	 * Tests that attempt to change State ID number to one already used by
	 * multiple people is prevented with {@code StateIdNumberExistsException}.
	 * 
	 * @throws PersonIdentityExistsException if person identity exists
	 * @throws StateIdNumberExistsException if State ID number is already used
	 * - asserted
	 */
	@Test(expectedExceptions = {StateIdNumberExistsException.class})
	public void testWithMultipleExistingStateIdNumbers()
			throws PersonIdentityExistsException, StateIdNumberExistsException {
		
		// Arranges three offenders - two with the same State ID number
		String sharedStateIdNumber = "MT321654987";
		this.offenderDelegate.create("Largo", "Ernst", null, null, null,
				sharedStateIdNumber, this.parseDateText("12/12/1984"), null,
				null, null, null);
		this.offenderDelegate.create("Le Chiffre", "Julius", null, "X", null,
				sharedStateIdNumber, this.parseDateText("12/09/1983"), null,
				null, null, null);
		Date emilioBirthDate = this.parseDateText("01/09/1967");
		Offender emilio = this.offenderDelegate.create("Blofeld", "Emilio",
				null, "X", null, "MT098789876", emilioBirthDate, null, null,
				null, null);
		
		// Action - attempts to change the third offender's State ID number
		// to that of the rest
		this.offenderPersonalDetailsService.changeIdentityWithoutSsn(emilio,
				emilioBirthDate, null, null, null, sharedStateIdNumber, null,
				null, null);
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