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

import omis.offender.domain.Offender;
import omis.offender.service.AlternativeOffenderNameService;
import omis.offender.service.delegate.OffenderDelegate;
import omis.person.domain.AlternativeNameAssociation;
import omis.person.domain.AlternativeNameCategory;
import omis.person.exception.AlternativeNameAssociationExistsException;
import omis.person.exception.PersonNameExistsException;
import omis.person.service.delegate.AlternativeNameAssociationDelegate;
import omis.person.service.delegate.AlternativeNameCategoryDelegate;
import omis.person.service.delegate.PersonNameDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.DateRangeUtility;

/**
 * Tests for removals of alternative name associations.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 30, 2018)
 * @since OMIS 3.0
 */
@Test(groups = {"alternativeName", "service"})
public class AlternativeOffenderNameServiceRemoveTests
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
	 * Tests removal of alternative name association.
	 * 
	 * @throws PersonNameExistsException if person name exists
	 * @throws AlternativeNameAssociationExistsException if alternative name
	 * association exists
	 */
	public void testRemoval()
			throws AlternativeNameAssociationExistsException,
				PersonNameExistsException {
		
		// Arranges offender with alias
		Offender offender = this.createOffender();
		AlternativeNameAssociation alternativeNameAssociation
			= this.alternativeNameAssociationDelegate.create(
				this.personNameDelegate.create(offender,
						"No", "Felix", "Fransisco", "X"),
				null, this.createAliasCategory());
		
		// Action - removes alternative name association
		this.alternativeOffenderNameService.remove(alternativeNameAssociation);
		
		// Asserts that no alternative names exist
		assert this.alternativeNameAssociationDelegate.findByPerson(offender)
			.size() == 0 : "Alternative names exist for offender";
	}
	
	/* Helper methods. */
	
	// Returns offender
	private Offender createOffender() {
		return this.offenderDelegate.createWithoutIdentity(
				"No", "Ernst", "Franz", null);
	}
	
	// Returns alias category
	private AlternativeNameCategory createAliasCategory() {
		return this.alternativeNameCategoryDelegate.create(
				"Alias", "Alias", (short) 1, true);
	}
}