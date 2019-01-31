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

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.datatype.DateRange;
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
import omis.util.DateUtility;

/**
 * Tests method to look up person identities by social security number of
 * person identity delegate.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Jan 23, 2019)
 * @since OMIS 3.0
 */
@Test(groups = {"personIdentity", "delegate"})
public class PersonIdentityDelegateFindBySocialSecurityNumberTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Service delegates. */

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
	@Qualifier("dateUtility")
	private DateUtility dateUtility;
	
	/* Test cases. */

	/** Tests look up.
	 * 
	 * @throws PersonIdentityExistsException if person identity exists
	 * @throws AlternativeIdentityAssociationExistsException if alternative
	 * identity association exists
	 */
	public void test()
			throws PersonIdentityExistsException,
				AlternativeIdentityAssociationExistsException {
		
		// Arranges Auric and Ernst to have the same SSN, Fransisco to have
		// a different one and Emilio to have an alternative identity same
		// as that of Auric and Ernst
		// Different birth dates are used to prevent duplicate identities from
		// being reported
		Integer sharedSsn = 987654321;
		Person auric = this.createPerson("Largo", "Auric", sharedSsn,
				this.dateUtility.parseDateText("12/12/1971"));
		Person ernst = this.createPerson("Le Chiffre", "Ernst", sharedSsn,
				this.dateUtility.parseDateText("12/12/1972"));
		Person fransisco = this.createPerson("Blofeld", "Fransisco",
				789456123, this.dateUtility.parseDateText("12/12/1973"));
		Person emilio = this.createPerson("Kananga", "Emilio", 123456789,
				this.dateUtility.parseDateText("12/12/1974"));
		PersonIdentity fraudulentEmilioIdentity = this.createIdentity(
				emilio, sharedSsn,
				this.dateUtility.parseDateText("12/12/1974"));
		AlternativeIdentityCategory fraudulentCategory
			= this.createFraudulentCategory();
		this.alternativeIdentityAssociationDelegate.create(
				fraudulentEmilioIdentity,
				new DateRange(this.dateUtility.parseDateText("12/12/2018"),
						null),
				fraudulentCategory, null);
		
		// Action - looks up person identities by SSN
		List<PersonIdentity> identities = this.personIdentityDelegate
				.findBySocialSecurityNumber(sharedSsn);
		
		// Asserts number expected number of identities are returned
		long expectedSize = 2;
		assert expectedSize == identities.size() : String.format(
				"Wrong number of identities returned - %d expected; %d found",
				expectedSize, identities.size());
		
		// Asserts that found identities contains Auric and Ernst
		assert identities.contains(auric.getIdentity())
			: String.format("Found identities do not contain %s", auric);
		assert identities.contains(ernst.getIdentity())
			: String.format("Found identities do not contain %s", ernst);
		
		// Asserts that found identities does not contain Fransisco and Emilio
		// Neither Fransisco or Emilio use the duplicate SSN as their actual
		// SSN (person.identity.socialSecurityNumber)
		assert !identities.contains(fransisco.getIdentity())
			: String.format("Found identities contains %s", fransisco);
		assert !identities.contains(emilio.getIdentity())
			: String.format("Found identities countains %s", emilio);
	}
	
	/**
	 * Tests look up with excluded identities.
	 * 
	 * @throws PersonIdentityExistsException if person identity exists 
	 * @throws AlternativeIdentityAssociationExistsException if alternative
	 * identity association exists
	 */
	public void testExcluding()
			throws PersonIdentityExistsException,
				AlternativeIdentityAssociationExistsException {
		
		// As in test(), arranges Auric and Ernst to have the same SSN,
		// Fransisco to have a different one and Emilio to have an alternative
		// identity same as that of Auric and Ernst
		// Different birth dates are used to prevent duplicate identities from
		// being reported
		Integer sharedSsn = 987654321;
		Person auric = this.createPerson("Largo", "Auric", sharedSsn,
				this.dateUtility.parseDateText("12/12/1971"));
		Person ernst = this.createPerson("Le Chiffre", "Ernst", sharedSsn,
				this.dateUtility.parseDateText("12/12/1972"));
		Person fransisco = this.createPerson("Blofeld", "Fransisco",
				789456123, this.dateUtility.parseDateText("12/12/1973"));
		Person emilio = this.createPerson("Kananga", "Emilio", 123456789,
				this.dateUtility.parseDateText("12/12/1974"));
		PersonIdentity fraudulentEmilioIdentity = this.createIdentity(
				emilio, sharedSsn,
				this.dateUtility.parseDateText("12/12/1974"));
		AlternativeIdentityCategory fraudulentCategory
			= this.createFraudulentCategory();
		this.alternativeIdentityAssociationDelegate.create(
				fraudulentEmilioIdentity,
				new DateRange(this.dateUtility.parseDateText("12/12/2018"),
						null),
				fraudulentCategory, null);
		
		// Action - looks up person identities by SSN, excludes Auric
		List<PersonIdentity> identities = this.personIdentityDelegate
				.findBySocialSecurityNumber(sharedSsn, auric.getIdentity());
		
		// Asserts number expected number of identities are returned
		long expectedSize = 1;
		assert expectedSize == identities.size() : String.format(
				"Wrong number of identities returned - %d expected; %d found",
				expectedSize, identities.size());
		
		// Asserts that found identities contains Ernst
		assert identities.contains(ernst.getIdentity())
			: String.format("Found identities do not contain %s", ernst);
		
		// Asserts that found identities does not contain Auric, Fransisco and
		// Emilio
		// Neither Fransisco or Emilio use the duplicate SSN as their actual
		// SSN (person.identity.socialSecurityNumber), Auric was deliberatly
		// excluded
		assert !identities.contains(auric.getIdentity())
			: String.format("Found identities do not contain %s", auric);
		assert !identities.contains(fransisco.getIdentity())
			: String.format("Found identities contains %s", fransisco);
		assert !identities.contains(emilio.getIdentity())
			: String.format("Found identities countains %s", emilio);
	}
	
	/* Helper methods. */
	
	// Returns person
	private Person createPerson(final String lastName, final String firstName,
			final Integer socialSecurityNumber, final Date birthDate) {
		return this.personDelegate.createWithIdentity(lastName, firstName, null,
				null, null, birthDate, null, null, null, socialSecurityNumber,
				null, null, null);
	}
	
	// Returns "fraudulent" identity category
	private AlternativeIdentityCategory createFraudulentCategory() {
		return this.alternativeIdentityCategoryDelegate
				.create("Fraudulent", "Fraudulent", (short) 1, true);
	}
	
	// Returns identity
	private PersonIdentity createIdentity(final Person person,
			final Integer socialSecurityNumber, final Date birthDate)
					throws PersonIdentityExistsException {
		return this.personIdentityDelegate.create(person, null, birthDate, null,
				null, null, socialSecurityNumber, null, null, null);
	}
}