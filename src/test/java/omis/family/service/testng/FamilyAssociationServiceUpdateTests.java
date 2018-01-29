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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.family.exception.FamilyAssociationConflictException;
import omis.family.domain.FamilyAssociation;
import omis.family.domain.FamilyAssociationCategory;
import omis.family.domain.FamilyAssociationCategoryClassification;
import omis.family.domain.component.FamilyAssociationFlags;
import omis.family.service.FamilyAssociationService;
import omis.family.service.delegate.FamilyAssociationCategoryDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests "update" of family association
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"family"})
public class FamilyAssociationServiceUpdateTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Delegates. */
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("familyAssociationCategoryDelegate")
	private FamilyAssociationCategoryDelegate familyAssociationCategoryDelegate;
	
	/* Service to test. */
	@Autowired
	@Qualifier("familyAssociationService")
	private FamilyAssociationService familyAssociationService;
	
	/**
	 * Tests overlapped family association on update with existing 
	 * start date is not null, existing end date null, new start date null, new 
	 * end date null. Existing start date is the same as new start date
	 * @throws ReflexiveRelationshipException 
	 * @throws FamilyAssociationConflictException 
	 * @throws DuplicateEntityFoundException 
	 */
	@Test()
	public void testWithExistingStartDateAndNewEndDateSame() 
		throws DuplicateEntityFoundException, ReflexiveRelationshipException, 
		FamilyAssociationConflictException 
	{
		// Arrangements
		Date existingStartDate = this.parseDateText("11/01/2017");
		Date newEndDate = this.parseDateText("11/01/2017");
		// Action
		this.testAllowedDatesOnUpdateImpl(existingStartDate, null, null, 
			newEndDate);

	}	
	
	/**
	 * Tests overlapped family association on update with existing 
	 * start date is not null, existing end date null, new start date null, new 
	 * end date null. Existing start date is different from new start date
	 * @throws ReflexiveRelationshipException 
	 * @throws FamilyAssociationConflictException 
	 * @throws DuplicateEntityFoundException 
	 */
	@Test()
	public void testWithExistingStartDateAndNewEndDate() 
		throws DuplicateEntityFoundException, ReflexiveRelationshipException, 
		FamilyAssociationConflictException 
	{
		// Arrangements
		Date existingStartDate = this.parseDateText("11/15/2017");
		Date newEndDate = this.parseDateText("11/01/2017");
		// Action
		this.testAllowedDatesOnUpdateImpl(existingStartDate, null, null, newEndDate);
	}	
	
	/**
	 * Tests overlapped family association on update with existing 
	 * start date is not null, existing end date not null, new start date not null, 
	 * new end date not null. Existing end date is same as new start date
	 * @throws ReflexiveRelationshipException 
	 * @throws FamilyAssociationConflictException 
	 * @throws DuplicateEntityFoundException 
	 */
	@Test()
	public void testWithExistingDateRangeAndNewDateRangeExistingEndDateSameAsNewStartDate() 
		throws DuplicateEntityFoundException, ReflexiveRelationshipException, 
		FamilyAssociationConflictException 
	{
		// Arrangements
		Date existingStartDate = this.parseDateText("11/01/2017");
		Date existingEndDate = this.parseDateText("11/15/2017");
		Date newEndDate = this.parseDateText("11/30/2017");
		Date newStartDate = this.parseDateText("11/15/2017");
		// Action
		this.testAllowedDatesOnUpdateImpl(
			existingStartDate, existingEndDate, newStartDate, newEndDate);
	}	
	
	/**
	 * Tests overlapped family association on update with existing 
	 * start date is not null, existing end date not null, new start date not 
	 * null, new end date not null. There is no overlap between existing and new
	 * @throws ReflexiveRelationshipException 
	 * @throws FamilyAssociationConflictException 
	 * @throws DuplicateEntityFoundException 
	 */
	@Test()
	public void testWithExistingDateRangeAndNewDateRangeNoOverlapExistingEarlier() 
		throws DuplicateEntityFoundException, ReflexiveRelationshipException, 
		FamilyAssociationConflictException 
	{
		// Arrangements
		Date existingStartDate = this.parseDateText("11/01/2017");
		Date existingEndDate = this.parseDateText("11/15/2017");
		Date newEndDate = this.parseDateText("11/30/2017");
		Date newStartDate = this.parseDateText("11/20/2017");
		// Action
		this.testAllowedDatesOnUpdateImpl(
			existingStartDate, existingEndDate, newStartDate, newEndDate);
	}	
	
	/**
	 * Tests overlapped family association on update with existing 
	 * start date is not null, existing end date not null, new start date not 
	 * null, new end date not null. Existing start date is the same as new end date
	 * @throws ReflexiveRelationshipException 
	 * @throws FamilyAssociationConflictException 
	 * @throws DuplicateEntityFoundException 
	 */
	@Test()
	public void testWithExistingDateRangeAndNewDateRangeExistingStartDateSameAsNewEndDate() 
		throws DuplicateEntityFoundException, ReflexiveRelationshipException, 
		FamilyAssociationConflictException 
	{
		// Arrangements
		Date existingStartDate = this.parseDateText("11/15/2017");
		Date existingEndDate = this.parseDateText("11/30/2017");
		Date newEndDate = this.parseDateText("11/15/2017");
		Date newStartDate = this.parseDateText("11/01/2017");
		// Action
		this.testAllowedDatesOnUpdateImpl(
			existingStartDate, existingEndDate, newStartDate, newEndDate);
	}	
	
	/**
	 * Tests overlapped family association on update with existing 
	 * start date is not null, existing end date not null, new start date not 
	 * null, new end date not null. Existing start date is the same as new end date
	 * @throws ReflexiveRelationshipException 
	 * @throws FamilyAssociationConflictException 
	 * @throws DuplicateEntityFoundException 
	 */
	@Test()
	public void testWithExistingDateRangeAndNewDateRangeNoOverlapNewEarlier() 
		throws DuplicateEntityFoundException, ReflexiveRelationshipException, 
		FamilyAssociationConflictException 
	{
		// Arrangements
		Date existingStartDate = this.parseDateText("11/20/2017");
		Date existingEndDate = this.parseDateText("11/30/2017");
		Date newEndDate = this.parseDateText("11/15/2017");
		Date newStartDate = this.parseDateText("11/01/2017");
		// Action
		this.testAllowedDatesOnUpdateImpl(
			existingStartDate, existingEndDate, newStartDate, newEndDate);
	}	
	
	private FamilyAssociation testAllowedDatesOnUpdateImpl(
		Date existingStartDate, Date existingEndDate, Date newStartDate, 
		Date newEndDate) throws DuplicateEntityFoundException, 
		FamilyAssociationConflictException, ReflexiveRelationshipException 
	{
		Offender offender = this.offenderDelegate.createWithoutIdentity("Obama",
			"Kevin", "Johns", "Mr.");
		Person familyMember = personDelegate.create("Li", "Yidong", "CIC311", 
			"Mr.");
		DateRange existingDateRange = new DateRange(existingStartDate, 
			existingEndDate);
		DateRange updatedDateRange = new DateRange(
			this.parseDateText("11/15/2015"), this.parseDateText("12/15/2015"));
		FamilyAssociationCategory category 
			= this.familyAssociationCategoryDelegate.create("testCategory", 
			true, (short) 23, FamilyAssociationCategoryClassification.CHILD);
		FamilyAssociationFlags flags = new FamilyAssociationFlags();
		flags.setCohabitant(true);
		flags.setDependent(true);
		flags.setEmergencyContact(true);
		Date marriageDate = new Date(182345678);
		Date divorceDate = new Date(1382345678);
		this.familyAssociationService.associate(offender, familyMember, 
			existingDateRange, category, flags,	marriageDate, divorceDate);
		
		FamilyAssociation updatedFamilyAssociation 
		= this.familyAssociationService.associate(offender, familyMember, 
			updatedDateRange, category, flags,	marriageDate, divorceDate);
		
		DateRange newDateRange = new DateRange(newStartDate, newEndDate);
		updatedFamilyAssociation = this.familyAssociationService.update(
			updatedFamilyAssociation, newDateRange, category, flags, 
			marriageDate, divorceDate);
		
		return updatedFamilyAssociation;
	}
	
	// Parses date text
	private Date parseDateText(final String text) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(text);
		} catch (ParseException e) {
			throw new RuntimeException("Parse error", e);
		}
	}
}