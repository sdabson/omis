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
 * Tests method to count person identities by social security number of person
 * identity delegate.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Jan 23, 2019)
 * @since OMIS 3.0
 */
@Test(groups = {"personIdentity", "delegate"})
public class PersonIdentityDelegateCountBySocialSecurityTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Service delegates. */
	
	@Autowired
	@Qualifier("personIdentityDelegate")
	private PersonIdentityDelegate personIdentityDelegate;
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("alternativeIdentityCategoryDelegate")
	private AlternativeIdentityCategoryDelegate
	alternativeIdentityCategoryDelegate;
	
	@Autowired
	@Qualifier("alternativeIdentityAssociationDelegate")
	private AlternativeIdentityAssociationDelegate
	alternativeIdentityAssociationDelegate;
	
	@Autowired
	@Qualifier("dateUtility")
	private DateUtility dateUtility;
	
	/* Helpers. */
	
	/* Test cases. */

	/**
	 * Tests count.
	 * 
	 * @throws PersonIdentityExistsException if person identity exists 
	 * @throws AlternativeIdentityAssociationExistsException if alternative
	 * identity association exists
	 */
	public void test()
			throws AlternativeIdentityAssociationExistsException,
				PersonIdentityExistsException {
		
		// Arranges two people with shared SSNs, another without the shared
		// SSNs and a fourth with the shared SSN as part of a fraudulent
		// identity
		Integer sharedSsn = 123456789;
		this.createPerson("Blofeld", "Emilio", sharedSsn,
				this.dateUtility.parseDateText("12/12/1981"));
		this.createPerson("Kananga", "Auric", sharedSsn,
				this.dateUtility.parseDateText("12/12/1980"));
		this.createPerson("Le Chiffre", "Ernst", 987654321,
				this.dateUtility.parseDateText("12/12/1984"));
		Person fransisco = this.createPerson("Kananga", "Fransisco", 789987789,
				this.dateUtility.parseDateText("12/12/1987"));
		this.alternativeIdentityAssociationDelegate.create(
				this.createIdentity(fransisco, sharedSsn,
						this.dateUtility.parseDateText("12/12/1988")),
				new DateRange(this.dateUtility.parseDateText("12/12/2018"),
						null),
				this.createFraudulentCategory(), null);
		
		// Action - counts identities with shared SSN
		long count = this.personIdentityDelegate
				.countBySocialSecurityNumber(sharedSsn);
		
		// Asserts that expected number of identities was counted
		long expectedCount = 2L;
		assert count == expectedCount : String.format(
				"Wrong number of identities - %d expected; %d found",
				expectedCount, count);
	}
	
	/**
	 * Tests count with excluded identities.
	 *  
	 * @throws PersonIdentityExistsException if person identity exists 
	 * @throws AlternativeIdentityAssociationExistsException if alternative
	 * identity association exists
	 */
	public void testExcluding()
			throws AlternativeIdentityAssociationExistsException,
				PersonIdentityExistsException {
	
		// As in test, Arranges two people with shared SSNs, another without the
		// shared  SSNs and a fourth with the shared SSN as part of a fraudulent
		// identity
		Integer sharedSsn = 123456789;
		this.createPerson("Blofeld", "Emilio", sharedSsn,
				this.dateUtility.parseDateText("12/12/1981"));
		Person kananga = this.createPerson("Kananga", "Auric", sharedSsn,
				this.dateUtility.parseDateText("12/12/1980"));
		this.createPerson("Le Chiffre", "Ernst", 987654321,
				this.dateUtility.parseDateText("12/12/1984"));
		Person fransisco = this.createPerson("Kananga", "Fransisco", 789987789,
				this.dateUtility.parseDateText("12/12/1987"));
		this.alternativeIdentityAssociationDelegate.create(
				this.createIdentity(fransisco, sharedSsn,
						this.dateUtility.parseDateText("12/12/1988")),
				new DateRange(this.dateUtility.parseDateText("12/12/2018"),
						null),
				this.createFraudulentCategory(), null);
		
		// Action - counts identities with shared SSN, excludes person with
		// shared SSN as that of person.identity
		long count = this.personIdentityDelegate
				.countBySocialSecurityNumber(sharedSsn, kananga.getIdentity());
		
		// Asserts that expected number of identities was counted
		long expectedCount = 1L;
		assert count == expectedCount : String.format(
				"Wrong number of identities - %d expected; %d found",
				expectedCount, count);
	}
	
	/* Helper methods. */
	
	// Creates person
	private Person createPerson(final String lastName, final String firstName,
			final Integer socialSecurityNumber, final Date birthDate) {
		return this.personDelegate.createWithIdentity(lastName, firstName, null,
				null, null, birthDate, null, null, null, socialSecurityNumber,
				null, null, null);
	}
	
	// Creates fraudulent identity category
	private AlternativeIdentityCategory createFraudulentCategory() {
		return this.alternativeIdentityCategoryDelegate
				.create("Fraudulent", "Fraudulent", (short) 1, true);
	}
	
	// Creates person identity
	private PersonIdentity createIdentity(final Person person,
			final Integer socialSecurityNumber, final Date birthDate)
					throws PersonIdentityExistsException {
		return this.personIdentityDelegate.create(person, null, birthDate, null,
				null, null, socialSecurityNumber, null, null, null);
	}
}