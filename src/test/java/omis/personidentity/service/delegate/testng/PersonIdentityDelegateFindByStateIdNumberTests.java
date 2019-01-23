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
package omis.personidentity.service.delegate.testng;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.person.domain.AlternativeIdentityCategory;
import omis.person.domain.Person;
import omis.person.domain.PersonIdentity;
import omis.person.exception.AlternativeIdentityAssociationExistsException;
import omis.person.exception.PersonIdentityExistsException;
import omis.person.service.delegate.AlternativeIdentityAssociationDelegate;
import omis.person.service.delegate.AlternativeIdentityCategoryDelegate;
import omis.person.service.delegate.PersonDelegate;
import omis.person.service.delegate.PersonIdentityDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.DateRangeUtility;

/**
 * Tests method to find person identities by State ID number in delegate for
 * person identities.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Jan 17, 2019)
 * @since OMIS 3.0
 */
@Test(groups = {"personIdentity", "delegate"})
public class PersonIdentityDelegateFindByStateIdNumberTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests{

	/* Delegates. */
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("personIdentityDelegate")
	private PersonIdentityDelegate personIdentityDelegate;
	
	@Autowired
	@Qualifier("alternativeIdentityCategoryDelegate")
	private AlternativeIdentityCategoryDelegate
	alternativeIdentityCategoryDelegate;
	
	@Autowired
	@Qualifier("alternativeIdentityAssociationDelegate")
	private AlternativeIdentityAssociationDelegate
	alternativeIdentityAssociationDelegate;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("dateRangeUtility")
	private DateRangeUtility dateRangeUtility;
	
	/* Test cases. */
	
	/**
	 * Test look up of person identities by State ID number.
	 * 
	 * <p>Verifies that alternatives are omitted from results.
	 * 
	 * @throws PersonIdentityExistsException if person identity exists
	 * @throws AlternativeIdentityAssociationExistsException  if alternative
	 * identity association exists
	 */
	public void test()
			throws PersonIdentityExistsException,
				AlternativeIdentityAssociationExistsException {
		
		// Creates four people - two of which share a State ID, another with
		// an alternative identity sharing the same State ID
		String sharedIdNumber = "MT123456789";
		Person ernst = this.createPerson(
				"Scaramanga", "Ernst", 111111111, sharedIdNumber);
		Person julius = this.createPerson(
				"Kananga", "Julius", 222222222, sharedIdNumber);
		Person fransisco = this.createPerson(
				"Blofeld", "Fransisco", 333333333, "MT987654321");
		Person auric = this.createPerson(
				"Le Chiffre", "Auric", 444444444, "MT321654987");
		AlternativeIdentityCategory stolenIdentity
			= this.createStolenIdentityCategory();
		PersonIdentity stolenPersonIdentity
			= this.createIdentity(auric, 555555555, sharedIdNumber);
		this.alternativeIdentityAssociationDelegate
			.create(stolenPersonIdentity,
					this.dateRangeUtility.parseDateTexts("12/12/2012", null),
					stolenIdentity, null);
		
		// Action - looks ups person identities by State ID number
		List<PersonIdentity> identities = this.personIdentityDelegate
				.findByStateIdNumber(sharedIdNumber);
		
		// Asserts that expected number of identities were returned and that
		// identities were of expected people
		int expectedSize = 2;
		assert identities.size() == expectedSize : String.format(
				"Wrong number of identities - %d expected; %d found",
				expectedSize, identities.size());
		assert identities.contains(ernst.getIdentity()) : String.format(
			"%s was not included despite having a none-unique State ID number",
			ernst);
		assert identities.contains(julius.getIdentity()) : String.format(
			"%s was not included despite having a none-unique State ID number",
			julius);
		assert !identities.contains(fransisco.getIdentity()) : String.format(
			"%s was included despite having a unique State ID number",
			fransisco);
		assert !identities.contains(auric.getIdentity()) : String.format(
			"%s was included despite only having a matching alternative"
			+ " State ID number", auric);
	}

	/**
	 * Test look up of person identities by State ID number with some identities
	 * omitted.
	 * 
	 * <p>Verifies that alternatives are omitted from results.
	 * 
	 * @throws PersonIdentityExistsException if person identity exists
	 * @throws AlternativeIdentityAssociationExistsException  if alternative
	 * identity association exists
	 */
	public void testExcluding()
			throws PersonIdentityExistsException,
				AlternativeIdentityAssociationExistsException {
	
		
		// Creates four people - two of which share a State ID, another with
		// an alternative identity sharing the same State ID
		String sharedIdNumber = "MT123456789";
		Person ernst = this.createPerson(
				"Scaramanga", "Ernst", 111111111, sharedIdNumber);
		Person julius = this.createPerson(
				"Kananga", "Julius", 222222222, sharedIdNumber);
		Person fransisco = this.createPerson(
				"Blofeld", "Fransisco", 333333333, "MT987654321");
		Person auric = this.createPerson(
				"Le Chiffre", "Auric", 444444444, "MT321654987");
		AlternativeIdentityCategory stolenIdentity
			= this.createStolenIdentityCategory();
		PersonIdentity stolenPersonIdentity
			= this.createIdentity(auric, 555555555, sharedIdNumber);
		this.alternativeIdentityAssociationDelegate
			.create(stolenPersonIdentity,
					this.dateRangeUtility.parseDateTexts("12/12/2012", null),
					stolenIdentity, null);
		
		// Action - looks ups person identities by State ID number, excludes
		// one of the person's identity
		List<PersonIdentity> identities = this.personIdentityDelegate
				.findByStateIdNumber(sharedIdNumber, julius.getIdentity());
		
		// Asserts that expected number of identities were returned and that
		// identities were of expected people
		int expectedSize = 1;
		assert identities.size() == expectedSize : String.format(
				"Wrong number of identities - %d expected; %d found",
				expectedSize, identities.size());
		assert identities.contains(ernst.getIdentity()) : String.format(
			"%s was not included despite having a none-unique State ID number",
			ernst);
		assert !identities.contains(julius.getIdentity()) : String.format(
			"%s was included despite having a none-unique State ID number"
				+ " but being excluded", julius);
		assert !identities.contains(fransisco.getIdentity()) : String.format(
			"%s was included despite having a unique State ID number",
			fransisco);
		assert !identities.contains(auric.getIdentity()) : String.format(
			"%s was included despite only having a matching alternative"
			+ " State ID number", auric);
	}
	
	/* Helper method. */
	
	// Creates person with State ID number - includes SSN so as to not violate
	// person identity business key constraints
	private Person createPerson(
			final String lastName, final String firstName,
			final Integer socialSecurityNumber, final String stateIdNumber) {
		return this.personDelegate.createWithIdentity(lastName, firstName, null,
				null, null, null, null, null, null, socialSecurityNumber,
				stateIdNumber, null, null);
	}
	
	// Creates stolen identity alternative category
	private AlternativeIdentityCategory createStolenIdentityCategory() {
		return this.alternativeIdentityCategoryDelegate.create(
				"Stolen ID", "Stolen ID", (short) 1, true);
	}
	
	// Creates identity with social security number so as to not violate the
	// business key of person identity
	private PersonIdentity createIdentity(
			final Person person, final Integer socialSecurityNumber,
			final String stateIdNumber) throws PersonIdentityExistsException {
		return this.personIdentityDelegate
			.create(person, null, null, null, null, null, socialSecurityNumber,
					stateIdNumber, null, null);
	}
}