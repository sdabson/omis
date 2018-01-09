package omis.visitation.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.datatype.DateRange;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.relationship.domain.Relationship;
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.relationship.service.delegate.RelationshipDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;
import omis.visitation.domain.VisitationApproval;
import omis.visitation.domain.VisitationAssociation;
import omis.visitation.domain.VisitationAssociationCategory;
import omis.visitation.domain.VisitationAssociationFlags;
import omis.visitation.service.VisitationAssociationService;
import omis.visitation.service.delegate.VisitationAssociationCategoryDelegate;
import omis.visitation.service.delegate.VisitationAssociationDelegate;

/**
 * Tests method to update a visitation association.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"visitation", "service"})
public class VisitationAssociationServiceUpdateTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("visitationAssociationCategoryDelegate")
	private VisitationAssociationCategoryDelegate 
		visitationAssociationCategoryDelegate;
	
	@Autowired
	@Qualifier("visitationAssociationDelegate")
	private VisitationAssociationDelegate visitationAssociationDelegate;
	
	@Autowired
	@Qualifier("relationshipDelegate")
	private RelationshipDelegate relationshipDelegate;

	/* Services. */

	@Autowired
	@Qualifier("visitationAssociationService")
	private VisitationAssociationService visitationAssociationService;

	/* Test methods. */

	/**
	 * Test the update of the category for a visitation association.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if overlapping dates exist
	 * @throws ReflexiveRelationshipException if reflexive relationship exists
	 */
	@Test
	public void testUpdateCategory() throws DuplicateEntityFoundException, 
			DateConflictException, ReflexiveRelationshipException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		Person visitor = this.personDelegate.create("Smith", "John", "Jay", 
				"Jr.");
		VisitationAssociationCategory category = 
				this.visitationAssociationCategoryDelegate.create("Category", 
						(short) 1, true);
		VisitationApproval approval = new VisitationApproval(true, 
				this.parseDateText("01/01/2017"));
		Date startDate = this.parseDateText("01/01/2017");
		Date endDate = null;
		VisitationAssociationFlags flags = new VisitationAssociationFlags(false, 
				false, false, false);
		String notes = "Notes";
		String guardianship = new String("guardianship text");
		
		Relationship relationship = this.relationshipDelegate.create(offender, 
				visitor);
		VisitationAssociation association = this.visitationAssociationDelegate
				.create(relationship, category, approval, startDate, endDate, 
						flags, notes, guardianship);
		VisitationAssociationCategory newCategory = 
				this.visitationAssociationCategoryDelegate.create("Category 2", 
						(short) 2, true);
		
		// Action
		association = this.visitationAssociationService.update(association, 
				newCategory, approval, startDate, endDate, flags, notes, guardianship);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("relationship.firstPerson", offender)
			.addExpectedValue("relationship.secondPerson", visitor)
			.addExpectedValue("category", newCategory)
			.addExpectedValue("approval", approval)
			.addExpectedValue("dateRange", new DateRange(startDate, endDate))
			.addExpectedValue("flags", flags)
			.addExpectedValue("notes", notes)
			.performAssertions(association);
	}
	
	/**
	 * Test the update of the approval for a visitation association.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if overlapping dates exist
	 * @throws ReflexiveRelationshipException if reflexive relationship exists
	 */
	@Test
	public void testUpdateApproval() throws DuplicateEntityFoundException, 
			DateConflictException, ReflexiveRelationshipException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		Person visitor = this.personDelegate.create("Smith", "John", "Jay", 
				"Jr.");
		VisitationAssociationCategory category = 
				this.visitationAssociationCategoryDelegate.create("Category", 
						(short) 1, true);
		VisitationApproval approval = new VisitationApproval(true, 
				this.parseDateText("01/01/2017"));
		Date startDate = this.parseDateText("01/01/2017");
		Date endDate = null;
		VisitationAssociationFlags flags = new VisitationAssociationFlags(false, 
				false, false, false);
		String notes = "Notes";
		String guardianship = new String("guardianship text");
		Relationship relationship = this.relationshipDelegate.create(offender, 
				visitor);
		VisitationAssociation association = this.visitationAssociationDelegate
				.create(relationship, category, approval, startDate, endDate, 
						flags, notes, guardianship);
		VisitationApproval newApproval = new VisitationApproval(true, 
				this.parseDateText("01/01/2016"));
		
		// Action
		association = this.visitationAssociationService.update(association, 
				category, newApproval, startDate, endDate, flags, notes, guardianship);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("relationship.firstPerson", offender)
			.addExpectedValue("relationship.secondPerson", visitor)
			.addExpectedValue("category", category)
			.addExpectedValue("approval", newApproval)
			.addExpectedValue("dateRange", new DateRange(startDate, endDate))
			.addExpectedValue("flags", flags)
			.addExpectedValue("notes", notes)
			.performAssertions(association);
	}
	
	/**
	 * Test the update of the start date for a visitation association.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if overlapping dates exist
	 * @throws ReflexiveRelationshipException if reflexive relationship exists
	 */
	@Test
	public void testUpdateStartDate() throws DuplicateEntityFoundException, 
			DateConflictException, ReflexiveRelationshipException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		Person visitor = this.personDelegate.create("Smith", "John", "Jay", 
				"Jr.");
		VisitationAssociationCategory category = 
				this.visitationAssociationCategoryDelegate.create("Category", 
						(short) 1, true);
		VisitationApproval approval = new VisitationApproval(true, 
				this.parseDateText("01/01/2017"));
		Date startDate = this.parseDateText("01/01/2017");
		Date endDate = null;
		VisitationAssociationFlags flags = new VisitationAssociationFlags(false, 
				false, false, false);
		String notes = "Notes";
		String guardianship = new String("guardianship text");
		Relationship relationship = this.relationshipDelegate.create(offender, 
				visitor);
		VisitationAssociation association = this.visitationAssociationDelegate
				.create(relationship, category, approval, startDate, endDate, 
						flags, notes, guardianship);
		Date newStartDate = this.parseDateText("01/03/2017");
		
		// Action
		association = this.visitationAssociationService.update(association, 
				category, approval, newStartDate, endDate, flags, notes, guardianship);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("relationship.firstPerson", offender)
			.addExpectedValue("relationship.secondPerson", visitor)
			.addExpectedValue("category", category)
			.addExpectedValue("approval", approval)
			.addExpectedValue("dateRange", new DateRange(newStartDate, endDate))
			.addExpectedValue("flags", flags)
			.addExpectedValue("notes", notes)
			.performAssertions(association);
	}
	
	/**
	 * Test the update of the end date for a visitation association.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if overlapping dates exist
	 * @throws ReflexiveRelationshipException if reflexive relationship exists
	 */
	@Test
	public void testUpdateEndDate() throws DuplicateEntityFoundException, 
			DateConflictException, ReflexiveRelationshipException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		Person visitor = this.personDelegate.create("Smith", "John", "Jay", 
				"Jr.");
		VisitationAssociationCategory category = 
				this.visitationAssociationCategoryDelegate.create("Category", 
						(short) 1, true);
		VisitationApproval approval = new VisitationApproval(true, 
				this.parseDateText("01/01/2017"));
		Date startDate = this.parseDateText("01/01/2017");
		Date endDate = null;
		VisitationAssociationFlags flags = new VisitationAssociationFlags(false, 
				false, false, false);
		String notes = "Notes";
		String guardianship = new String("guardianship text");
		Relationship relationship = this.relationshipDelegate.create(offender, 
				visitor);
		VisitationAssociation association = this.visitationAssociationDelegate
				.create(relationship, category, approval, startDate, endDate, 
						flags, notes, guardianship);
		Date newEndDate = this.parseDateText("05/05/2017");
		
		// Action
		association = this.visitationAssociationService.update(association, 
				category, approval, startDate, newEndDate, flags, notes, guardianship);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("relationship.firstPerson", offender)
			.addExpectedValue("relationship.secondPerson", visitor)
			.addExpectedValue("category", category)
			.addExpectedValue("approval", approval)
			.addExpectedValue("dateRange", new DateRange(startDate, newEndDate))
			.addExpectedValue("flags", flags)
			.addExpectedValue("notes", notes)
			.performAssertions(association);
	}
	
	/**
	 * Test the update of the flags for a visitation association.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if overlapping dates exist
	 * @throws ReflexiveRelationshipException if reflexive relationship exists
	 */
	@Test
	public void testUpdateFlags() throws DuplicateEntityFoundException, 
			DateConflictException, ReflexiveRelationshipException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		Person visitor = this.personDelegate.create("Smith", "John", "Jay", 
				"Jr.");
		VisitationAssociationCategory category = 
				this.visitationAssociationCategoryDelegate.create("Category", 
						(short) 1, true);
		VisitationApproval approval = new VisitationApproval(true, 
				this.parseDateText("01/01/2017"));
		Date startDate = this.parseDateText("01/01/2017");
		Date endDate = null;
		VisitationAssociationFlags flags = new VisitationAssociationFlags(false, 
				false, false, false);
		String notes = "Notes";
		String guardianship = new String("guardianship text");
		Relationship relationship = this.relationshipDelegate.create(offender, 
				visitor);
		VisitationAssociation association = this.visitationAssociationDelegate
				.create(relationship, category, approval, startDate, endDate, 
						flags, notes, guardianship);
		VisitationAssociationFlags newFlags = new VisitationAssociationFlags(
				true, false, true, false);
		// Action
		association = this.visitationAssociationService.update(association, 
				category, approval, startDate, endDate, newFlags, notes, guardianship);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("relationship.firstPerson", offender)
			.addExpectedValue("relationship.secondPerson", visitor)
			.addExpectedValue("category", category)
			.addExpectedValue("approval", approval)
			.addExpectedValue("dateRange", new DateRange(startDate, endDate))
			.addExpectedValue("flags", newFlags)
			.addExpectedValue("notes", notes)
			.performAssertions(association);
	}
	
	/**
	 * Test the update of the notes for a visitation association.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if overlapping dates exist
	 * @throws ReflexiveRelationshipException if reflexive relationship exists
	 */
	@Test
	public void testUpdateNotes() throws DuplicateEntityFoundException, 
			DateConflictException, ReflexiveRelationshipException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		Person visitor = this.personDelegate.create("Smith", "John", "Jay", 
				"Jr.");
		VisitationAssociationCategory category = 
				this.visitationAssociationCategoryDelegate.create("Category", 
						(short) 1, true);
		VisitationApproval approval = new VisitationApproval(true, 
				this.parseDateText("01/01/2017"));
		Date startDate = this.parseDateText("01/01/2017");
		Date endDate = null;
		VisitationAssociationFlags flags = new VisitationAssociationFlags(false, 
				false, false, false);
		String notes = "Notes";
		String guardianship = new String("guardianship text");
		Relationship relationship = this.relationshipDelegate.create(offender, 
				visitor);
		VisitationAssociation association = this.visitationAssociationDelegate
				.create(relationship, category, approval, startDate, endDate, 
						flags, notes, guardianship);
		String newNotes = "New notes";
		
		// Action
		association = this.visitationAssociationService.update(association, 
				category, approval, startDate, endDate, flags, newNotes, guardianship);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("relationship.firstPerson", offender)
			.addExpectedValue("relationship.secondPerson", visitor)
			.addExpectedValue("category", category)
			.addExpectedValue("approval", approval)
			.addExpectedValue("dateRange", new DateRange(startDate, endDate))
			.addExpectedValue("flags", flags)
			.addExpectedValue("notes", newNotes)
			.performAssertions(association);
	}

	/**
	 * Tests {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if overlapping dates exist
	 * @throws ReflexiveRelationshipException if reflexive relationship exists
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
			throws DuplicateEntityFoundException, DateConflictException, 
			ReflexiveRelationshipException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		Person visitor = this.personDelegate.create("Smith", "John", "Jay", 
				"Jr.");
		VisitationAssociationCategory category = 
				this.visitationAssociationCategoryDelegate.create("Category", 
						(short) 1, true);
		VisitationApproval approval = new VisitationApproval(true, 
				this.parseDateText("01/01/2017"));
		Date startDate = this.parseDateText("01/01/2017");
		Date endDate = this.parseDateText("01/02/2017");
		VisitationAssociationFlags flags = new VisitationAssociationFlags(false, 
				false, false, false);
		String notes = "Notes";
		String guardianship = new String("guardianship text");
		Relationship relationship = this.relationshipDelegate.create(offender, 
				visitor);
		this.visitationAssociationDelegate.create(relationship, category, 
				approval, startDate, endDate, flags, notes, guardianship);
		Date secondStartDate = this.parseDateText("01/03/2017");
		VisitationAssociation association = this.visitationAssociationDelegate
				.create(relationship, category, approval, secondStartDate, null, 
						flags, notes, guardianship);
		
		// Action
		association = this.visitationAssociationService.update(association, 
				category, approval, startDate, endDate, flags, notes, guardianship);
	}

	/**
	 * Tests {@code DateConflictException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if overlapping dates exist
	 * @throws ReflexiveRelationshipException if reflexive relationship exists
	 */
	@Test(expectedExceptions = {DateConflictException.class})
	public void testDateConflictException() 
			throws DuplicateEntityFoundException, DateConflictException,
			ReflexiveRelationshipException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		Person visitor = this.personDelegate.create("Smith", "John", "Jay", 
				"Jr.");
		VisitationAssociationCategory category = 
				this.visitationAssociationCategoryDelegate.create("Category", 
						(short) 1, true);
		VisitationApproval approval = new VisitationApproval(true, 
				this.parseDateText("01/01/2017"));
		Date startDate = this.parseDateText("01/01/2017");
		Date endDate = this.parseDateText("06/01/2017");
		VisitationAssociationFlags flags = new VisitationAssociationFlags(false, 
				false, false, false);
		String notes = "Notes";
		String guardianship = new String("guardianship text");
		Relationship relationship = this.relationshipDelegate.create(offender, 
				visitor);
		this.visitationAssociationDelegate.create(relationship, category, 
				approval, startDate, endDate, flags, notes, guardianship);
		Date secondStartDate = this.parseDateText("06/01/2017");
		VisitationAssociation association = this.visitationAssociationDelegate
				.create(relationship, category, approval, secondStartDate, null, 
						flags, notes, guardianship);
		Date newStartDate = this.parseDateText("03/01/2017");
		
		// Action
		association = this.visitationAssociationService.update(association, 
				category, approval, newStartDate, null, flags, notes, guardianship);
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