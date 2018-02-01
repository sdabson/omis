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
package omis.offenderrelationship.service.testng;

import java.util.Date;

import omis.datatype.DateRange;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.family.domain.FamilyAssociation;
import omis.family.domain.FamilyAssociationCategory;
import omis.family.domain.FamilyAssociationCategoryClassification;
import omis.family.domain.component.FamilyAssociationFlags;
import omis.family.exception.FamilyAssociationConflictException;
import omis.family.exception.FamilyAssociationExistsException;
import omis.family.service.delegate.FamilyAssociationCategoryDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.offenderrelationship.service.CreateRelationshipsService;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

/**
 * Test association of family association. 
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"offenderrelationship"})
public class OffenderRelationshipAssociateFamilyMemberTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Delegates. */
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("familyAssociationCategoryDelegate")
	private FamilyAssociationCategoryDelegate familyAssociationCategoryDelegate;
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	/* Services */
	@Autowired
	@Qualifier("createRelationshipsService")
	private CreateRelationshipsService createRelationshipsService;
	
	/**
	 * Tests associate family member.
	 * @throws DuplicateEntityFoundException DuplicateEntityFoundException
	 * @throws DateConflictException DateConflictException
	 * @throws FamilyAssociationConflictException
	 * * FamilyAssociationConflictException
	 * @throws ReflexiveRelationshipException ReflexiveRelationshipException
	 * @throws FamilyAssociationExistsException family association exists
	 * exception
	 */
	@Test
	public void testAssociateFamilyMember() 
		throws DuplicateEntityFoundException, FamilyAssociationExistsException,
		ReflexiveRelationshipException,	DateConflictException,
		FamilyAssociationConflictException {
		// Arrangement
		final int shortNumber = 1;
		final int endDateInt = 222222;
		final int marriageDateInt = 113456789;
		final int divorceDateInt = 122222221;
		Offender offender = this.offenderDelegate.createWithoutIdentity("Wang",
			"Kevin", "Johns", "Mr.");
		Date startDate = new Date(1);
		Date endDate = new Date(endDateInt);
		DateRange dateRange = new DateRange(startDate, endDate);
		FamilyAssociationCategory category 
			= this.familyAssociationCategoryDelegate.create("Wife", true, 
			(short) shortNumber,
			FamilyAssociationCategoryClassification.SPOUSE);
		Date marriageDate = new Date(marriageDateInt);
		Date divorceDate = new Date(divorceDateInt);
		FamilyAssociationFlags flags 
			= new FamilyAssociationFlags(true, true, true);
		Person familyMember = this.personDelegate.create("lastName", 
			"firstName", "middleName", "Mr.");
		
		// Action
		FamilyAssociation association = this.createRelationshipsService
			.associateFamilyMember(offender, familyMember, dateRange, category, 
			flags, marriageDate, divorceDate);
		
		// Assertions
		assert startDate.equals(association.getDateRange().getStartDate())
		: String.format("Wrong Start Date: #%s expected; #%s found",
			startDate, association.getDateRange().getStartDate());
		assert endDate.equals(association.getDateRange().getEndDate())
		: String.format("Wrong End Date: #%s expected; #%s found",
			endDate, association.getDateRange().getEndDate());
		assert marriageDate.equals(association.getMarriageDate())
		: String.format("Wrong Birth Date: #%s expected; #%s found",
			marriageDate, association.getMarriageDate());
		assert divorceDate.equals(association.getDivorceDate())
		: String.format("Wrong Divorce Date: #%s expected; #%s found",
			divorceDate, association.getDivorceDate());
		assert offender.equals((Offender) association.getRelationship()
			.getFirstPerson())
		: String.format("Wrong Offender: #%s expected; #%s found",
			offender.getOffenderNumber(), ((Offender) association
			.getRelationship().getFirstPerson()).getOffenderNumber()); 
		assert familyMember.equals(association.getRelationship()
			.getSecondPerson())
		: String.format("Wrong Family Member: #%s expected; #%s found",
			familyMember.getName(), 
			association.getRelationship().getSecondPerson().getName());
		assert category.equals(association.getCategory())
		: String.format("Wrong Category: #%s expected; #%s found",
			category.getName(), association.getCategory().getName());
		assert flags.getCohabitant().equals(association.getFlags()
			.getCohabitant())
		: String.format("Wrong Cohabitant Flag: #%s expected; #%s found",
			flags.getCohabitant().toString(), 
			association.getFlags().getCohabitant().toString());
		assert flags.getDependent().equals(association.getFlags()
			.getDependent())
		: String.format("Wrong Dependent Flag: #%s expected; #%s found",
			flags.getDependent().toString(), 
			association.getFlags().getDependent().toString());
		assert flags.getEmergencyContact().equals(association.getFlags()
			.getEmergencyContact())
		: String.format("Wrong Emergency Contact Flag: #%s expected; #%s found",
			flags.getEmergencyContact().toString(), 
			association.getFlags().getEmergencyContact().toString());
	}
	
	/**
	 * Tests duplicate family member association.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate relationships exist
	 * @throws ReflexiveRelationshipException ReflexiveRelationshipException
	 * @throws DateConflictException DateConflictException
	 * @throws FamilyAssociationConflictException 
	 * FamilyAssociationConflictException
	 * @throws FamilyAssociationExistsException FamilyAssociationExistsException
	 */
	@Test(expectedExceptions = {FamilyAssociationExistsException.class, 
		ReflexiveRelationshipException.class, DateConflictException.class})
	public void testDuplicateAssociateFamilyMember() 
		throws DuplicateEntityFoundException, FamilyAssociationExistsException,
		ReflexiveRelationshipException, DateConflictException,
		FamilyAssociationConflictException {
		// Assignment
		Offender offender = this.offenderDelegate.createWithoutIdentity("Wang",
			"Kevin", "Johns", "Mr.");
		final int endDateInt = 222222;
		final int shortInt = 111;
		final int marriageDateInt = 113456789;
		final int divorceDateInt = 122222221;
		Date startDate = new Date(1);
		Date endDate = new Date(endDateInt);
		DateRange dateRange = new DateRange(startDate, endDate);
		FamilyAssociationCategory category 
			= this.familyAssociationCategoryDelegate.create("Wife", true, 
			(short) shortInt, FamilyAssociationCategoryClassification.SPOUSE);
		Date marriageDate = new Date(marriageDateInt);
		Date divorceDate = new Date(divorceDateInt);
		FamilyAssociationFlags flags 
			= new FamilyAssociationFlags(true, true, true);
		Person familyMember = this.personDelegate.create("lastName",
			"firstName", "middleName", "Mr.");
		
		// Action
		this.createRelationshipsService
			.associateFamilyMember(offender, familyMember, dateRange, category, 
			flags, marriageDate, divorceDate);
		this.createRelationshipsService
			.associateFamilyMember(offender, familyMember, dateRange, category, 
			flags, marriageDate, divorceDate);
	}
}