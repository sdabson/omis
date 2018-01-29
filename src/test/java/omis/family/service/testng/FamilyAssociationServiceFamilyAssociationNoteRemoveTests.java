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
package omis.family.service.testng;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.datatype.DateRange;
import omis.family.domain.FamilyAssociation;
import omis.family.domain.FamilyAssociationCategory;
import omis.family.domain.FamilyAssociationCategoryClassification;
import omis.family.domain.FamilyAssociationNote;
import omis.family.domain.component.FamilyAssociationFlags;
import omis.family.exception.FamilyAssociationCategoryExistsException;
import omis.family.exception.FamilyAssociationConflictException;
import omis.family.exception.FamilyAssociationExistsException;
import omis.family.exception.FamilyAssociationNoteExistsException;
import omis.family.service.FamilyAssociationService;
import omis.family.service.delegate.FamilyAssociationCategoryDelegate;
import omis.family.service.delegate.FamilyAssociationNoteDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests family association note removal
 * 
 *@author Yidong Li 
 *@author Sheronda Vaughn
 *@version 0.1.0 (June 29, 2017)
 *@since OMIS 3.0
 *
 */
@Test(groups = {"family"})
public class FamilyAssociationServiceFamilyAssociationNoteRemoveTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Delegate */
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("familyAssociationCategoryDelegate")
	private FamilyAssociationCategoryDelegate familyAssociationCategoryDelegate;
	
	@Autowired
	@Qualifier("familyAssociationNoteDelegate")
	private FamilyAssociationNoteDelegate familyAssociationNoteDelegate;
	
	/* Service */
	@Autowired
	@Qualifier("familyAssociationService")
	private FamilyAssociationService familyAssociationService;
	
	@Test
	public void testFamilyAssociationNoteRemove() 
			throws FamilyAssociationNoteExistsException, 
		ReflexiveRelationshipException, FamilyAssociationConflictException, 
		FamilyAssociationCategoryExistsException, 
		FamilyAssociationExistsException{
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Obama",
			"Kevin", "Johns", "Mr.");
		Person familyMember = personDelegate.create("Li", "Yidong", "CIC311", 
			"Mr.");
		DateRange familyAssociationDateRange = new DateRange();
		Date familyAssociationStartDate = new Date(11111111);
		Date familyAssociationEndDate = new Date(22345678);
		familyAssociationDateRange.setEndDate(familyAssociationEndDate);
		familyAssociationDateRange.setStartDate(familyAssociationStartDate);
		FamilyAssociationCategory category 
			= this.familyAssociationCategoryDelegate.create("testName", 
			(Boolean)true, new Short((short) 23), 
			FamilyAssociationCategoryClassification.CHILD);
		FamilyAssociationFlags flags = new FamilyAssociationFlags();
		flags.setCohabitant(true);
		flags.setDependent(true);
		flags.setEmergencyContact(true);
		Date marriageDate = new Date(1113333);
		Date divorceDate = new Date(2113333);
		
		FamilyAssociation familyAssociation= this.familyAssociationService
			.associate(offender, familyMember, familyAssociationDateRange, 
			category, flags, marriageDate, divorceDate);
		
		Date familyAssociationNoteDate = new Date(21111111);
		String value = "Testing note creation";
		
		// Action
		FamilyAssociationNote familyAssociationNote= 
			this.familyAssociationService.addNote(familyAssociation, 
			familyAssociationNoteDate, value);
		
		// Action
		this.familyAssociationService.removeNote(familyAssociationNote);
		
		// Assertion
		assert !(this.familyAssociationNoteDelegate.findExists()
			.contains(familyAssociationNote)): "Family association note was "
					+ "not removed!";
	}
}
