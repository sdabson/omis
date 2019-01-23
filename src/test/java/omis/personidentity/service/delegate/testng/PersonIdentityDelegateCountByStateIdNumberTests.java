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
 * Tests method to count person identities by State ID number of delegate for
 * person identities.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Jan 17, 2019)
 * @since OMIS 3.0
 */
@Test(groups = {"personIdentity", "delegate"})
public class PersonIdentityDelegateCountByStateIdNumberTests
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
	
	/**
	 * Tests counting person identities with duplicate State ID numbers. Note
	 * that alternative identities with duplicate State ID numbers are omitted
	 * from the count. This test verifies that.
	 * 
	 * @throws PersonIdentityExistsException if person identity exists 
	 * @throws AlternativeIdentityAssociationExistsException if alternative
	 * identity association exists
	 */
	public void test()
			throws PersonIdentityExistsException,
				AlternativeIdentityAssociationExistsException {
		
		// Arranges four people - two with a shared State ID number, another
		// with an alternative identification with the shared State ID number
		String sharedIdNumber = "MT123456789";
		this.createPerson("Blofeld", "Auric",
				this.dateUtility.parseDateText("12/12/1981"), sharedIdNumber);
		this.createPerson("Scaramanga", "Ernst",
				this.dateUtility.parseDateText("12/12/1982"), sharedIdNumber);
		this.createPerson("Kanaga", "Fransisco",
				this.dateUtility.parseDateText("12/12/1983"), "MT123987456");
		Person julius = this.createPerson("Le Chiffre", "Julius",
				this.dateUtility.parseDateText("12/12/1984"), "MT789654321");
		PersonIdentity juliusAlternativeIdentity
			= this.createPersonIdentity(julius,
				this.dateUtility.parseDateText("12/12/1985"), sharedIdNumber);
		AlternativeIdentityCategory fakeIdentityCategory
			= this.createFakeIdentityCategory();
		this.alternativeIdentityAssociationDelegate.create(
				juliusAlternativeIdentity,
				new DateRange(
						this.dateUtility.parseDateText("12/12/2001"), null),
				fakeIdentityCategory, null);
		
		// Action - count identities
		long count = this.personIdentityDelegate.countByStateIdNumber(
				sharedIdNumber);
		
		// Asserts that expected count was returned
		long expectedCount = 2;
		assert count == expectedCount : String.format(
				"Wrong count - %d expected; %d returned", expectedCount, count);
	}
	
	/**
	 * Tests counting person identities with duplicate State ID numbers. Note
	 * that alternative identities with duplicate State IDs and supplied
	 * person identities are omitted from the count. This test verifies both
	 * these requirements.
	 * 
	 * @throws PersonIdentityExistsException if person identity exists 
	 * @throws AlternativeIdentityAssociationExistsException if alternative
	 * identity association exists
	 */
	public void testExcluding()
			throws PersonIdentityExistsException,
				AlternativeIdentityAssociationExistsException {
		
		// Arranges four people - two with a shared State ID number, another
		// with an alternative identification with the shared State ID number
		String sharedIdNumber = "MT123456789";
		this.createPerson("Blofeld", "Auric",
				this.dateUtility.parseDateText("12/12/1981"), sharedIdNumber);
		Person ernst = this.createPerson("Scaramanga", "Ernst",
				this.dateUtility.parseDateText("12/12/1982"), sharedIdNumber);
		this.createPerson("Kanaga", "Fransisco",
				this.dateUtility.parseDateText("12/12/1983"), "MT123987456");
		Person julius = this.createPerson("Le Chiffre", "Julius",
				this.dateUtility.parseDateText("12/12/1984"), "MT789654321");
		PersonIdentity juliusAlternativeIdentity
			= this.createPersonIdentity(julius,
				this.dateUtility.parseDateText("12/12/1985"), sharedIdNumber);
		AlternativeIdentityCategory fakeIdentityCategory
			= this.createFakeIdentityCategory();
		this.alternativeIdentityAssociationDelegate.create(
				juliusAlternativeIdentity,
				new DateRange(
						this.dateUtility.parseDateText("12/12/2001"), null),
				fakeIdentityCategory, null);
		
		// Action - count identities with Ernst excluded
		long count = this.personIdentityDelegate.countByStateIdNumber(
				sharedIdNumber, ernst.getIdentity());
		
		// Asserts that expected count was returned
		long expectedCount = 1;
		assert count == expectedCount : String.format(
				"Wrong count - %d expected; %d returned", expectedCount, count);
	}
	
	/* Helper methods. */
	
	// Creates person - birth date is allowed to avoid identity duplicate
	// violations
	private Person createPerson(
			final String lastName, final String firstName,
			final Date birthDate, final String stateIdNumber) {
		return this.personDelegate.createWithIdentity(lastName, firstName, null,
				null, null, birthDate, null, null, null, null, stateIdNumber,
				null, null);
	}
	
	// Creates person identity - birth date is allowed to avoid identity
	// duplicate violations
	private PersonIdentity createPersonIdentity(
			final Person person, final Date birthDate,
			final String stateIdNumber)
					throws PersonIdentityExistsException {
		return this.personIdentityDelegate.create(person, null, birthDate, null,
				null, null, null, stateIdNumber, null, null);
	}
	
	// Creates fake identity category
	private AlternativeIdentityCategory createFakeIdentityCategory() {
		return this.alternativeIdentityCategoryDelegate.create(
				"Fake", "Fake", (short) 1, true);
	}
}