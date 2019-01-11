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
 * Tests for updates offender alternative name associations.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 30, 2018)
 * @since OMIS 3.0
 */
@Test(groups = {"alternativeName", "service"})
public class AlternativeOffenderNameServiceUpdateAssociationTests
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
	
	/* Tests */
	
	/**
	 * Tests update of alternative offender name association.
	 * 
	 * @throws PersonNameExistsException if person name exists
	 * @throws AlternativeNameAssociationExistsException if alternative name
	 * is already associated
	 */
	public void testUpdateAssociation()
			throws PersonNameExistsException,
				AlternativeNameAssociationExistsException {
		
		// Arranges creation of offender with court name
		Offender offender = this.createOffender();
		PersonName courtName = this.personNameDelegate
				.create(offender, "Oberhauser", "Mallory", "Franz", "XXIV");
		AlternativeNameAssociation alternativeNameAssociation
			= this.alternativeNameAssociationDelegate
				.create(courtName,
						this.dateRangeUtility.parseDateTexts(
								"12/12/2012", "01/01/2013"),
						this.createCourtNameCategory());
		
		// Action - updates court name to alias
		String aliasLastName = "Kananga";
		String aliasFirstName = "Felix";
		String aliasMiddleName = "Mallory";
		String aliasSuffix = null;
		AlternativeNameCategory aliasCategory = this.createAliasCategory();
		DateRange aliasDateRange = this.dateRangeUtility
				.parseDateTexts("02/02/2014", "03/03/2015");
		alternativeNameAssociation = this.alternativeOffenderNameService
				.updateAssociation(alternativeNameAssociation, aliasLastName,
						aliasFirstName, aliasMiddleName, aliasSuffix,
						aliasDateRange, aliasCategory);
		
		// Asserts alternative name values were updated
		PropertyValueAsserter.create()
			.addExpectedValue("name.lastName", aliasLastName)
			.addExpectedValue("name.firstName", aliasFirstName)
			.addExpectedValue("name.middleName",  aliasMiddleName)
			.addExpectedValue("name.suffix", aliasSuffix)
			.addExpectedValue("category", aliasCategory)
			.addExpectedValue("dateRange", aliasDateRange)
			.performAssertions(alternativeNameAssociation);
	}
	
	/**
	 * Tests the attempt to update association of alternative name with values
	 * of existing name for offender cause {@code PersonNameExistsException}.
	 * 
	 * @throws PersonNameExistsException if person name exists - asserted
	 * @throws AlternativeNameAssociationExistsException if alternative name
	 * already exists
	 */
	@Test(expectedExceptions = {PersonNameExistsException.class})
	public void testPersonNameExistsException()
			throws PersonNameExistsException,
				AlternativeNameAssociationExistsException {
		
		// Arranges creation of offender with court name
		Offender offender = this.createOffender();
		PersonName courtName = this.personNameDelegate
				.create(offender, "Grant", "Mallory", null, "XV");
		AlternativeNameAssociation alternativeNameAssociation
			= this.alternativeNameAssociationDelegate
				.create(courtName, this.dateRangeUtility
							.parseDateTexts("12/12/2012", null),
						this.createCourtNameCategory());
		
		// Action - attempts to update court name to name existing name values
		this.alternativeOffenderNameService.updateAssociation(
				alternativeNameAssociation,
				offender.getName().getLastName(),
				offender.getName().getFirstName(),
				offender.getName().getMiddleName(),
				offender.getName().getSuffix(),
				this.dateRangeUtility.parseDateTexts(
						"12/12/2014", "12/13/2015"),
				this.createAliasCategory());
	}
	
	/**
	 * Tests that duplicate alternative name associations are prevented on
	 * association update with
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
		
		// Arranges offender with alias and court name
		Offender offender = this.createOffender();
		String aliasLastName = "Scaramanga";
		String aliasFirstName = "Julius";
		String aliasMiddleName = null;
		String aliasSuffix = "XXV";
		PersonName aliasPersonName = this.personNameDelegate
				.create(offender, aliasLastName, aliasFirstName,
						aliasMiddleName, aliasSuffix);
		DateRange aliasDateRange = this.dateRangeUtility
				.parseDateTexts("12/12/2012", null);
		AlternativeNameCategory aliasCategory = this.createAliasCategory();
		this.alternativeNameAssociationDelegate.create(
				aliasPersonName, aliasDateRange, aliasCategory);
		AlternativeNameAssociation courtNameAssociation
			= this.alternativeNameAssociationDelegate.create(
					this.personNameDelegate.create(
							offender, "No", "Stavros", null, null),
					this.dateRangeUtility.parseDateTexts("12/12/2012", null),
					this.createCourtNameCategory());
		
		// Action - attempts to update court name with alias values
		this.alternativeOffenderNameService.updateAssociation(
				courtNameAssociation, aliasLastName, aliasFirstName,
				aliasMiddleName, aliasSuffix, aliasDateRange, aliasCategory);
	}
	
	/* Helper methods. */
	
	// Returns offender
	private Offender createOffender() {
		return this.offenderDelegate.createWithoutIdentity(
				"No", "Ernst", "Hans", null);
	}
	
	// Returns alias category
	private AlternativeNameCategory createAliasCategory() {
		return this.alternativeNameCategoryDelegate.create(
				"Alias", "Alias", (short) 1, true);
	}
	
	// Returns court name category
	private AlternativeNameCategory createCourtNameCategory() {
		return this.alternativeNameCategoryDelegate.create(
				"Court Name", "Court Name", (short) 1, true);
	}
}