package omis.separationneed.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.relationship.domain.Relationship;
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.relationship.service.delegate.RelationshipDelegate;
import omis.separationneed.domain.SeparationNeed;
import omis.separationneed.domain.SeparationNeedNote;
import omis.separationneed.domain.SeparationNeedRemovalReason;
import omis.separationneed.service.SeparationNeedService;
import omis.separationneed.service.delegate.SeparationNeedDelegate;
import omis.separationneed.service.delegate.SeparationNeedNoteDelegate;
import omis.separationneed.service.delegate.SeparationNeedRemovalReasonDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to update separation need notes.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class SeparationNeedServiceUpdateNoteTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("relationshipDelegate")
	private RelationshipDelegate relationshipDelegate;
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("separationNeedRemovalReasonDelegate")
	private SeparationNeedRemovalReasonDelegate 
		separationNeedRemovalReasonDelegate;
	
	@Autowired
	@Qualifier("separationNeedDelegate")
	private SeparationNeedDelegate separationNeedDelegate;
	
	@Autowired
	@Qualifier("separationNeedNoteDelegate")
	private SeparationNeedNoteDelegate separationNeedNoteDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("separationNeedService")
	private SeparationNeedService separationNeedService;

	/* Test methods. */

	/**
	 * Test the update of the date for a separation need note.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if overlapping dates exists
	 * @throws ReflexiveRelationshipException if the relationship is created 
	 * with the same person
	 */
	@Test
	public void testUpdateNoteDate() throws DuplicateEntityFoundException, 
			DateConflictException, ReflexiveRelationshipException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		Offender targetOffender = this.offenderDelegate.createWithoutIdentity(
				"Smith", "Jane", "June", null);
		Relationship relationship = this.relationshipDelegate.create(offender, 
				targetOffender);
		Date date = this.parseDateText("01/01/2017");
		String creationComment = "Creation comment";
		Person reportingStaff = this.personDelegate.create("Doe", "John", null, 
				null);
		Date removalDate = this.parseDateText("03/01/2017");
		SeparationNeedRemovalReason removalReason = this
				.separationNeedRemovalReasonDelegate.create("Reason", (short) 1,
						true);
		String removalComment = "Removal comment";
		SeparationNeed separationNeed = this.separationNeedDelegate.create(
				relationship, date, creationComment, reportingStaff, 
				removalDate, removalReason, removalComment);
		Date noteDate = this.parseDateText("01/05/2017");;
		String value = "Note";
		SeparationNeedNote note = this.separationNeedNoteDelegate.create(
				separationNeed, noteDate, value);
		Date newNoteDate = this.parseDateText("01/06/2017");;
		
		// Action
		note = this.separationNeedService.updateNote(note, newNoteDate, value);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("separationNeed", separationNeed)
			.addExpectedValue("date", newNoteDate)
			.addExpectedValue("value", value)
			.performAssertions(note);
	}
	
	/**
	 * Test the update of the value for a separation need note.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if overlapping dates exists
	 * @throws ReflexiveRelationshipException if the relationship is created 
	 * with the same person
	 */
	@Test
	public void testUpdateNoteValue() throws DuplicateEntityFoundException, 
			DateConflictException, ReflexiveRelationshipException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		Offender targetOffender = this.offenderDelegate.createWithoutIdentity(
				"Smith", "Jane", "June", null);
		Relationship relationship = this.relationshipDelegate.create(offender, 
				targetOffender);
		Date date = this.parseDateText("01/01/2017");
		String creationComment = "Creation comment";
		Person reportingStaff = this.personDelegate.create("Doe", "John", null, 
				null);
		Date removalDate = this.parseDateText("03/01/2017");
		SeparationNeedRemovalReason removalReason = this
				.separationNeedRemovalReasonDelegate.create("Reason", (short) 1,
						true);
		String removalComment = "Removal comment";
		SeparationNeed separationNeed = this.separationNeedDelegate.create(
				relationship, date, creationComment, reportingStaff, 
				removalDate, removalReason, removalComment);
		Date noteDate = this.parseDateText("01/05/2017");;
		String value = "Note";
		SeparationNeedNote note = this.separationNeedNoteDelegate.create(
				separationNeed, noteDate, value);
		String newValue = "New note";
		
		// Action
		note = this.separationNeedService.updateNote(note, noteDate, newValue);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("separationNeed", separationNeed)
			.addExpectedValue("date", noteDate)
			.addExpectedValue("value", newValue)
			.performAssertions(note);
	}

	/**
	 * Tests {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if overlapping dates exists
	 * @throws ReflexiveRelationshipException if the relationship is created 
	 * with the same person
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
			throws DuplicateEntityFoundException, DateConflictException, 
			ReflexiveRelationshipException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		Offender targetOffender = this.offenderDelegate.createWithoutIdentity(
				"Smith", "Jane", "June", null);
		Relationship relationship = this.relationshipDelegate.create(offender, 
				targetOffender);
		Date date = this.parseDateText("01/01/2017");
		String creationComment = "Creation comment";
		Person reportingStaff = this.personDelegate.create("Doe", "John", null, 
				null);
		Date removalDate = this.parseDateText("03/01/2017");
		SeparationNeedRemovalReason removalReason = this
				.separationNeedRemovalReasonDelegate.create("Reason", (short) 1,
						true);
		String removalComment = "Removal comment";
		SeparationNeed separationNeed = this.separationNeedDelegate.create(
				relationship, date, creationComment, reportingStaff, 
				removalDate, removalReason, removalComment);
		Date noteDate = this.parseDateText("01/05/2017");;
		String value = "Note";
		this.separationNeedNoteDelegate.create(separationNeed, noteDate, value);
		String secondValue = "Second note";
		SeparationNeedNote note = this.separationNeedNoteDelegate.create(
				separationNeed, noteDate, secondValue);
		
		// Action
		note = this.separationNeedService.updateNote(note, noteDate, value);
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