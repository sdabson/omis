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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.offender.service.AlternativeOffenderNameService;
import omis.offender.service.delegate.OffenderDelegate;
import omis.person.domain.AlternativeNameAssociation;
import omis.person.domain.AlternativeNameCategory;
import omis.person.domain.PersonName;
import omis.person.exception.AlternativeNameAssociationExistsException;
import omis.person.exception.PersonNameExistsException;
import omis.person.service.delegate.AlternativeNameAssociationDelegate;
import omis.person.service.delegate.AlternativeNameCategoryDelegate;
import omis.person.service.delegate.PersonNameDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.DateRangeUtility;
import omis.util.PropertyValueAsserter;

/**
 * Tests for associating alternative offender names.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 30, 2018)
 * @since OMIS 3.0
 */
@Test(groups = {"alternativeName", "service"})
public class AlternativeOffenderNameServiceAssociateTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("alternativeNameCategoryDelegate")
	private AlternativeNameCategoryDelegate alternativeNameCategoryDelegate;
	
	@Autowired
	@Qualifier("alternativeNameAssociationDelegate")
	private AlternativeNameAssociationDelegate
	alternativeNameAssociationDelegate;
	
	@Autowired
	@Qualifier("personNameDelegate")
	private PersonNameDelegate personNameDelegate;
	
	/* Service. */
	
	@Autowired
	@Qualifier("alternativeOffenderNameService")
	private AlternativeOffenderNameService alternativeOffenderNameService;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("dateRangeUtility")
	private DateRangeUtility dateRangeUtility;
	
	/* Tests. */
	
	/**
	 * Tests successful association of alternative offender name.
	 * 
	 * @throws AlternativeNameAssociationExistsException  if alternative name
	 * association exists
	 * @throws PersonNameExistsException if person name exists
	 */
	public void testAssociation()
			throws PersonNameExistsException,
				AlternativeNameAssociationExistsException {
		
		// Arranges creation of offender
		Offender offender = this.createOffender();
		
		// Action - associates alias as alternative name
		String aliasLastName = "Scaramanga";
		String aliasFirstName = "Ernst";
		String aliasMiddleName = "Kananga";
		String aliasSuffix = "XIV";
		DateRange aliasDateRange
			= this.dateRangeUtility.parseDateTexts("12/12/2012", null);
		AlternativeNameCategory aliasCategory = this.createAliasCategory();
		AlternativeNameAssociation alternativeNameAssociation
			= this.alternativeOffenderNameService
				.associate(offender, aliasLastName, aliasFirstName,
						aliasMiddleName, aliasSuffix, aliasDateRange,
						aliasCategory);
		
		// Asserts that alternative name has supplied values
		PropertyValueAsserter.create()
			.addExpectedValue("name.lastName", aliasLastName)
			.addExpectedValue("name.firstName", aliasFirstName)
			.addExpectedValue("name.middleName", aliasMiddleName)
			.addExpectedValue("name.suffix", aliasSuffix)
			.addExpectedValue("category", aliasCategory)
			.addExpectedValue("dateRange", aliasDateRange)
			.performAssertions(alternativeNameAssociation);
	}
	
	/**
	 * Tests the attempt to associate alternative name with values of existing
	 * name for offender cause {@code PersonNameExistsException}.
	 * 
	 * @throws PersonNameExistsException if name exists with values of
	 * alternative name - asserted
	 * @throws AlternativeNameAssociationExistsException if alternative name
	 * association exists
	 */
	@Test(expectedExceptions = {PersonNameExistsException.class})
	public void testPersonNameExistsException()
			throws PersonNameExistsException,
				AlternativeNameAssociationExistsException {
		
		// Arranges creation of offender
		Offender offender = this.createOffender();
		
		// Action - attempts to associate alias as alternative name using values
		// from offender name
		this.alternativeOffenderNameService.associate(offender,
				offender.getName().getLastName(),
				offender.getName().getFirstName(),
				offender.getName().getMiddleName(),
				offender.getName().getSuffix(),
				this.dateRangeUtility.parseDateTexts("12/12/2012", null),
				this.createAliasCategory());
	}
	
	/**
	 * Tests that duplicate alternative name associations are prevented with
	 * {@code AlternativeNameAssociationExistsException}.
	 * 
	 * @throws PersonNameExistsException if person name exists
	 * @throws AlternativeNameAssociationExistsException if alternative name
	 * association exists - asserted
	 */
	// TODO Determine if its possible to throw target exception - SA
	// Remove test case and exception declaration from service if not.
	// Test currently throws PersonNameExistsException first.
	@Test(expectedExceptions
			= {AlternativeNameAssociationExistsException.class},
		enabled = false)
	public void testAlternativeNameAssociationExistsException()
			throws PersonNameExistsException,
				AlternativeNameAssociationExistsException {
		
		// Arranges creation of offender with alias
		Offender offender = this.createOffender();
		String aliasLastName = "Scaramanga";
		String aliasFirstName = "Ernst";
		String aliasMiddleName = "Kananga";
		String aliasSuffix = "XIV";
		PersonName aliasPersonName = this.personNameDelegate
				.create(offender, aliasLastName, aliasFirstName,
						aliasMiddleName, aliasSuffix);
		DateRange aliasDateRange
			= this.dateRangeUtility.parseDateTexts("12/12/2012", null);
		AlternativeNameCategory aliasCategory = this.createAliasCategory();
		this.alternativeNameAssociationDelegate
				.create(aliasPersonName, aliasDateRange, aliasCategory);
		
		// Action - attempts to associate duplicate alternative name
		this.alternativeOffenderNameService.associate(offender, aliasLastName,
				aliasFirstName, aliasMiddleName, aliasSuffix, aliasDateRange,
				aliasCategory);
	}
	
	/* Helpers methods. */
	
	// Returns offender
	private Offender createOffender() {
		return this.offenderDelegate.createWithoutIdentity(
				"Oberhauser", "Julius", null, null);
	}
	
	// Returns alias category
	private AlternativeNameCategory createAliasCategory() {
		return this.alternativeNameCategoryDelegate.create(
				"Alias", "Alias", (short) 1, true);
	}
}