package omis.family.service.testng;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.family.exception.FamilyAssociationConflictException;
import omis.family.domain.FamilyAssociation;
import omis.family.domain.FamilyAssociationCategory;
import omis.family.domain.FamilyAssociationCategoryClassification;
import omis.family.domain.FamilyAssociationNote;
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
 * Tests "update" of family association note
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"family"})
public class FamilyAssociationServiceFamilyAssociationNoteUpdateTests
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
	
	/* Property editors. */
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory datePropertyEditorFactory;
	
	/**
	 * Family association note update.
	 */
	@Test
	public void testFamilyAssociationNoteUpdate() throws DuplicateEntityFoundException, 
		ReflexiveRelationshipException, FamilyAssociationConflictException{
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
		
		FamilyAssociationNote familyAssociationNote= 
			this.familyAssociationService.addNote(familyAssociation, 
			familyAssociationNoteDate, value);
		
		DateRange newFamilyAssociationDateRange = new DateRange();
		Date newFamilyAssociationStartDate = new Date(14511111);
		Date newFamilyAssociationEndDate = new Date(27845678);
		newFamilyAssociationDateRange.setEndDate(newFamilyAssociationEndDate);
		newFamilyAssociationDateRange.setStartDate(newFamilyAssociationStartDate);
		FamilyAssociationFlags newFlags = new FamilyAssociationFlags();
		newFlags.setCohabitant(false);
		newFlags.setDependent(false);
		newFlags.setEmergencyContact(false);
		Date newFamilyAssociationNoteDate = new Date(21991111);
		String newValue = "New testing note creation";
		
		this.familyAssociationService.updateNote(familyAssociationNote, 
			newFamilyAssociationNoteDate, newValue);
		
		// Assertions
		assert newFamilyAssociationNoteDate.equals(familyAssociationNote.getDate())
		: String.format("Wrong note date: #%s expected; #%s found",
			newFamilyAssociationNoteDate, familyAssociationNote.getDate());
		assert newValue.equals(familyAssociationNote.getValue())
		: String.format("Wrong value: #%s expected; #%s found",
			newValue, familyAssociationNote.getValue());
	}
	
	/**
	 * Tests duplicate family association note on update.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate term exists
	 * @throws ReflexiveRelationshipException, FamilyAssociationConflictException 
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class, 
			ReflexiveRelationshipException.class})
	public void testDuplicateFamilyAssociationNoteUpdate() 
		throws DuplicateEntityFoundException, ReflexiveRelationshipException,
		FamilyAssociationConflictException {
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
		
		FamilyAssociationNote familyAssociationNote= 
			this.familyAssociationService.addNote(familyAssociation, 
			familyAssociationNoteDate, value);
		
		DateRange newFamilyAssociationDateRange = new DateRange();
		Date newFamilyAssociationStartDate = new Date(21111111);
		Date newFamilyAssociationEndDate = new Date(43345678);
		newFamilyAssociationDateRange.setEndDate(newFamilyAssociationEndDate);
		newFamilyAssociationDateRange.setStartDate(newFamilyAssociationStartDate);
		
		FamilyAssociationFlags newFlags = new FamilyAssociationFlags();
		newFlags.setCohabitant(false);
		newFlags.setDependent(false);
		newFlags.setEmergencyContact(false);
				
		Date newFamilyAssociationNoteDate = new Date(29111111);
		String newValue = "New testing note creation";
	
		this.familyAssociationService.addNote(familyAssociation, 
			newFamilyAssociationNoteDate, newValue);
		
		// Action
		this.familyAssociationService.updateNote(familyAssociationNote, 
			newFamilyAssociationNoteDate, newValue);
	}	
}