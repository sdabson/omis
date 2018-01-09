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
import omis.family.service.delegate.FamilyAssociationDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.relationship.domain.Relationship;
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.relationship.service.delegate.RelationshipDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests family association creation 
 *
 * @author Yidong Li
 * @author Josh Divine
 * @version 0.0.2
 * @since OMIS 3.0
 */
@Test(groups = {"family"})
public class FamilyAssociationServiceFamilyAssociationCreationTests
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
	
	@Autowired
	@Qualifier("familyAssociationDelegate")
	private FamilyAssociationDelegate familyAssociationDelegate;
	
	@Autowired
	@Qualifier("relationshipDelegate")
	private RelationshipDelegate relationshipDelegate;
	
	/* Service to test. */
	@Autowired
	@Qualifier("familyAssociationService")
	private FamilyAssociationService familyAssociationService;
	
	/**
	 * Tests the creation of family association.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate asset exists
	 * @throws ReflexiveRelationshipException if relationship is between same 
	 * person
	 * @throws FamilyAssociationConflictException if association overlaps 
	 */
	@Test
	public void testAssociate() 
		throws DuplicateEntityFoundException, ReflexiveRelationshipException,
		FamilyAssociationConflictException {
		// Arrangement
		Offender offender = this.offenderDelegate.createWithoutIdentity("Obama",
			"Kevin", "Johns", "Mr.");
		Person familyMember = personDelegate.create("Li", "Yidong", "CIC311", 
			"Mr.");
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("12/01/2017"));
		FamilyAssociationCategory category = this
				.familyAssociationCategoryDelegate.create("testName", true, 
						(short) 1, 
						FamilyAssociationCategoryClassification.CHILD);
		FamilyAssociationFlags flags = new FamilyAssociationFlags(true, true, 
				true);
		Date marriageDate = this.parseDateText("01/01/2017");
		Date divorceDate = this.parseDateText("12/01/2017");
		
		// Action
		FamilyAssociation familyAssociation = this.familyAssociationService
				.associate(offender, familyMember, dateRange, category, flags,
						marriageDate, divorceDate);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("relationship.firstPerson", offender)
			.addExpectedValue("relationship.secondPerson", familyMember)
			.addExpectedValue("dateRange", dateRange)
			.addExpectedValue("category", category)
			.addExpectedValue("flags", flags)
			.addExpectedValue("marriageDate", marriageDate)
			.addExpectedValue("divorceDate", divorceDate)
			.performAssertions(familyAssociation);
	}
	
	/**
	 * Tests {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate asset exists
	 * @throws ReflexiveRelationshipException if relationship is between same 
	 * person
	 * @throws FamilyAssociationConflictException if association overlaps
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
		throws DuplicateEntityFoundException, ReflexiveRelationshipException,
		FamilyAssociationConflictException {
		// Arrangement
		Offender offender = this.offenderDelegate.createWithoutIdentity("Obama",
			"Kevin", "Johns", "Mr.");
		Person familyMember = personDelegate.create("Li", "Yidong", "CIC311", 
			"Mr.");
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("12/01/2017"));
		FamilyAssociationCategory category = this
				.familyAssociationCategoryDelegate.create("testName", true, 
						(short) 1, 
						FamilyAssociationCategoryClassification.CHILD);
		FamilyAssociationFlags flags = new FamilyAssociationFlags(true, true, 
				true);
		Date marriageDate = this.parseDateText("01/01/2017");
		Date divorceDate = this.parseDateText("12/01/2017");
		Relationship relationship = this.relationshipDelegate.findOrCreate(
				offender, familyMember);
		this.familyAssociationDelegate.create(dateRange, category, flags, 
				marriageDate, divorceDate, relationship);
		
		// Action
		this.familyAssociationService.associate(offender, familyMember, 
			dateRange, category, flags,	marriageDate, divorceDate);
	}
	
	/**
	 * Tests {@code FamilyAssociationConflictException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate asset exists
	 * @throws ReflexiveRelationshipException if relationship is between same 
	 * person
	 * @throws FamilyAssociationConflictException if association overlaps
	 */
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void testFamilyAssociationConflictException() 
		throws DuplicateEntityFoundException, ReflexiveRelationshipException,
		FamilyAssociationConflictException {
		// Arrangement
		Offender offender = this.offenderDelegate.createWithoutIdentity("Obama",
			"Kevin", "Johns", "Mr.");
		Person familyMember = personDelegate.create("Li", "Yidong", "CIC311", 
			"Mr.");
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("12/01/2017"));
		FamilyAssociationCategory category = this
				.familyAssociationCategoryDelegate.create("testName", true, 
						(short) 1, 
						FamilyAssociationCategoryClassification.CHILD);
		FamilyAssociationFlags flags = new FamilyAssociationFlags(true, true, 
				true);
		Date marriageDate = this.parseDateText("01/01/2017");
		Date divorceDate = this.parseDateText("12/01/2017");
		Relationship relationship = this.relationshipDelegate.findOrCreate(
				offender, familyMember);
		this.familyAssociationDelegate.create(dateRange, category, flags, 
				marriageDate, divorceDate, relationship);
		dateRange = new DateRange(this.parseDateText("05/01/2017"), 
				this.parseDateText("12/31/2017"));
		
		// Action
		this.familyAssociationService.associate(offender, familyMember, 
			dateRange, category, flags,	marriageDate, divorceDate);
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