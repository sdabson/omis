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
import omis.exception.DuplicateEntityFoundException;
import omis.family.exception.FamilyAssociationCategoryExistsException;
import omis.family.exception.FamilyAssociationConflictException;
import omis.family.exception.FamilyAssociationExistsException;
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
 * @author Sheronda Vaughn
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"family"})
public class FamilyAssociationServiceFamilyAssociationUpdateTests
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
	 * Family association update.
	 * @throws FamilyAssociationCategoryExistsException 
	 */
	@Test
	public void testFamilyAssociationUpdate() 
			throws FamilyAssociationExistsException, 
		ReflexiveRelationshipException, FamilyAssociationConflictException, FamilyAssociationCategoryExistsException{
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity(
			"Obama", "Kevin", "Johns", "Mr.");
		DateRange originalDateRange = new DateRange();
		Date orignalStartDate = new Date(105120000);
		Date originalEndDate = new Date(205120000);
		originalDateRange.setStartDate(orignalStartDate);
		originalDateRange.setEndDate(originalEndDate);
		Date originalMarriageDate = new Date(115120000);
		Date originalDivorceDate = new Date(215120000);
		Person originalFamilyMember = personDelegate.create("Li", "Yidong", 
			"CIC311", "Mr.");
		FamilyAssociationCategory originalCategory 
		= this.familyAssociationCategoryDelegate.create("originalCategory", 
		(Boolean)true, new Short((short) 23), 
		FamilyAssociationCategoryClassification.CHILD);
		FamilyAssociationFlags originalFlags = new FamilyAssociationFlags();
		originalFlags.setCohabitant(true);
		originalFlags.setDependent(true);
		originalFlags.setEmergencyContact(true);
		
		DateRange newDateRange = new DateRange();
		Date newStartDate = new Date(5120000);
		Date newEndDate = new Date(105120000);
		newDateRange.setStartDate(newStartDate);
		newDateRange.setEndDate(newEndDate);
		Date newMarriageDate = new Date(105120000);
		Date newDivorceDate = new Date(205120000);
		FamilyAssociationCategory newCategory 
			= this.familyAssociationCategoryDelegate.create("newCategory", 
			(Boolean)true, new Short((short) 57), 
			FamilyAssociationCategoryClassification.SIBLING);
		FamilyAssociationFlags newFlags = new FamilyAssociationFlags();
		newFlags.setCohabitant(false);
		newFlags.setDependent(false);
		newFlags.setEmergencyContact(false);
		
		// Action
		FamilyAssociation familyAssociation = this.familyAssociationService
			.associate(offender, originalFamilyMember, originalDateRange, 
			originalCategory, originalFlags, originalMarriageDate, 
			originalDivorceDate);
		this.familyAssociationService.update(familyAssociation, newDateRange, 
			newCategory, newFlags, newMarriageDate, newDivorceDate);
		
		// Assertions
		assert newStartDate.equals(familyAssociation.getDateRange().getStartDate())
		: String.format("Wrong start date: #%s expected; #%s found",
			newStartDate, familyAssociation.getDateRange().getStartDate());
		assert newEndDate.equals(familyAssociation.getDateRange().getEndDate())
		: String.format("Wrong end date: #%s expected; #%s found",
			newEndDate, familyAssociation.getDateRange().getEndDate());
		assert newMarriageDate.equals(familyAssociation.getMarriageDate())
		: String.format("Wrong marriage date: #%s expected; #%s found",
			newMarriageDate, familyAssociation.getMarriageDate());
		assert newDivorceDate.equals(familyAssociation.getDivorceDate())
		: String.format("Wrong divorce date: #%s expected; #%s found",
			newDivorceDate, familyAssociation.getDivorceDate());
		assert newCategory.equals(familyAssociation.getCategory())
		: String.format("Wrong category: #%s expected; #%s found",
			newCategory.getName(),	familyAssociation.getCategory()
			.getName());
		assert newFlags.getCohabitant().equals(familyAssociation.getFlags()
			.getCohabitant())
			: String.format("Wrong cohabitant flag: #%s expected; #%s found",
			newFlags.getCohabitant(),
			familyAssociation.getFlags().getCohabitant());
		assert newFlags.getDependent().equals(familyAssociation.getFlags()
			.getDependent())
			: String.format("Wrong dependent flag: #%s expected; #%s found",
				newFlags.getDependent(),
				familyAssociation.getFlags().getDependent());
		assert newFlags.getEmergencyContact().equals(familyAssociation.getFlags()
			.getEmergencyContact())
			: String.format("Wrong emergency contact flag: #%s expected; #%s found",
				newFlags.getEmergencyContact(),
				familyAssociation.getFlags().getEmergencyContact());
	}
	
	/**
	 * Tests duplicate family association on update.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate term exists
	 * @throws ReflexiveRelationshipException 
	 * @throws FamilyAssociationCategoryExistsException 
	 */
	@Test(expectedExceptions = {FamilyAssociationExistsException.class, 
		ReflexiveRelationshipException.class})
	public void testDuplicateFamilyAssociationUpdate() 
		throws FamilyAssociationExistsException, ReflexiveRelationshipException, 
		FamilyAssociationConflictException, 
		FamilyAssociationCategoryExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity(
			"Obama", "Kevin", "Johns", "Mr.");
		DateRange dateRange1 = new DateRange();
		Date startDate1 = new Date(105120000);
		Date endDate1 = new Date(205120000);
		dateRange1.setStartDate(startDate1);
		dateRange1.setEndDate(endDate1);
		Date marriageDate1 = new Date(115120000); 
		Date divorceDate1 = new Date(215120000);
		Person familyMember1 = personDelegate.create("Li", "Yidong", 
			"CIC311", "Mr.");
		FamilyAssociationCategory category1 
		= this.familyAssociationCategoryDelegate.create("category", 
		(Boolean)true, new Short((short) 23), 
		FamilyAssociationCategoryClassification.CHILD);
		FamilyAssociationFlags flags1 = new FamilyAssociationFlags();
		flags1.setCohabitant(true);
		flags1.setDependent(true);
		flags1.setEmergencyContact(true);
		this.familyAssociationService
			.associate(offender, familyMember1, dateRange1, category1, flags1, 
			marriageDate1, divorceDate1);
		
		DateRange dateRange2 = new DateRange();
		Date startDate2 = new Date(155120000);
		Date endDate2 = new Date(255120000);
		dateRange2.setStartDate(startDate2);
		dateRange2.setEndDate(endDate2);
		Date marriageDate2 = new Date(175120000); 
		Date divorceDate2 = new Date(275120000);
		FamilyAssociationCategory category2 
		= this.familyAssociationCategoryDelegate.create("newCategory", 
		(Boolean)true, new Short((short) 23), 
		FamilyAssociationCategoryClassification.PARENT);
		FamilyAssociationFlags flags2 = new FamilyAssociationFlags();
		flags2.setCohabitant(false);
		flags2.setDependent(false);
		flags2.setEmergencyContact(false);
		FamilyAssociation familyAssociation1 = this.familyAssociationService.associate(offender, familyMember1, 
			dateRange2, category2, flags2, marriageDate2, divorceDate2);
		
		// Action
		this.familyAssociationService.update(familyAssociation1, dateRange2, category1, flags2, 
				marriageDate2, divorceDate2);
	}	
	
	/**
	 * Tests overlapped family association on update.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate term exists
	 * @throws ReflexiveRelationshipException 
	 * @throws FamilyAssociationCategoryExistsException 
	 */
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void testOverlappedFamilyAssociationUpdate() 
		throws FamilyAssociationExistsException, ReflexiveRelationshipException, 
		FamilyAssociationConflictException, FamilyAssociationCategoryExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity(
			"Obama", "Kevin", "Johns", "Mr.");
		DateRange dateRange1 = new DateRange();
		Date startDate1 = new Date(105120000);
		Date endDate1 = new Date(205120000);
		dateRange1.setStartDate(startDate1);
		dateRange1.setEndDate(endDate1);
		Date marriageDate1 = new Date(115120000); 
		Date divorceDate1 = new Date(215120000);
		Person familyMember1 = personDelegate.create("Li", "Yidong", 
			"CIC311", "Mr.");
		FamilyAssociationCategory category1 
		= this.familyAssociationCategoryDelegate.create("category1", 
		(Boolean)true, new Short((short) 23), 
		FamilyAssociationCategoryClassification.CHILD);
		FamilyAssociationFlags flags1 = new FamilyAssociationFlags();
		flags1.setCohabitant(true);
		flags1.setDependent(true);
		flags1.setEmergencyContact(true);
		FamilyAssociation familyAssociation1 = this.familyAssociationService
			.associate(offender, familyMember1, dateRange1, category1, flags1, 
			marriageDate1, divorceDate1);
		
		DateRange dateRange2 = new DateRange();
		Date startDate2 = new Date(255120000);
		Date endDate2 = new Date(305120000);
		dateRange2.setStartDate(startDate2);
		dateRange2.setEndDate(endDate2);
		Date marriageDate2 = new Date(255120000); 
		Date divorceDate2 = new Date(305120000);
		FamilyAssociationCategory category2 
		= this.familyAssociationCategoryDelegate.create("category2", 
		(Boolean)true, new Short((short) 23), 
		FamilyAssociationCategoryClassification.PARENT);
		FamilyAssociationFlags flags2 = new FamilyAssociationFlags();
		flags2.setCohabitant(false);
		flags2.setDependent(false);
		flags2.setEmergencyContact(false);
		this.familyAssociationService.associate(offender, familyMember1, 
			dateRange2, category2, flags2, marriageDate2, divorceDate2);
		
		DateRange dateRange3 = new DateRange();
		Date startDate3 = new Date(275120000);
		Date endDate3 = new Date(295120000);
		dateRange3.setStartDate(startDate3);
		dateRange3.setEndDate(endDate3);
		// Action
		this.familyAssociationService.update(familyAssociation1, dateRange3, 
			category2, flags2, marriageDate2, divorceDate2);
	}	
}