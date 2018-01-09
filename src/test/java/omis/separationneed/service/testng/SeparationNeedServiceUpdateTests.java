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
import omis.separationneed.domain.SeparationNeedRemovalReason;
import omis.separationneed.service.SeparationNeedService;
import omis.separationneed.service.delegate.SeparationNeedDelegate;
import omis.separationneed.service.delegate.SeparationNeedRemovalReasonDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

public class SeparationNeedServiceUpdateTests
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

	/* Services. */

	@Autowired
	@Qualifier("separationNeedService")
	private SeparationNeedService separationNeedService;

	/* Test methods. */

	/**
	 * Test the update of the relationship for a separation need.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if overlapping dates exists
	 * @throws ReflexiveRelationshipException if the relationship is created 
	 * with the same person
	 */
	@Test
	public void testUpdateRelationship() throws DuplicateEntityFoundException,
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
		Offender newTarget = this.offenderDelegate.createWithoutIdentity(
				"Target", "Guy", null, null);
		Relationship newRelationship = this.relationshipDelegate.create(
				offender, newTarget);
		
		// Action
		separationNeed = this.separationNeedService.update(separationNeed, 
				newRelationship, date, creationComment, reportingStaff, 
				removalDate, removalReason, removalComment);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("relationship", newRelationship)
			.addExpectedValue("date", date)
			.addExpectedValue("creationComment", creationComment)
			.addExpectedValue("reportingStaff", reportingStaff)
			.addExpectedValue("removal.date", removalDate)
			.addExpectedValue("removal.reason", removalReason)
			.addExpectedValue("removal.comment", removalComment)
			.performAssertions(separationNeed);
	}
	
	/**
	 * Test the update of the date for a separation need.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if overlapping dates exists
	 * @throws ReflexiveRelationshipException if the relationship is created 
	 * with the same person
	 */
	@Test
	public void testUpdateDate() throws DuplicateEntityFoundException,
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
		Date newDate = this.parseDateText("01/05/2017");
		
		// Action
		separationNeed = this.separationNeedService.update(separationNeed, 
				relationship, newDate, creationComment, reportingStaff, 
				removalDate, removalReason, removalComment);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("relationship", relationship)
			.addExpectedValue("date", newDate)
			.addExpectedValue("creationComment", creationComment)
			.addExpectedValue("reportingStaff", reportingStaff)
			.addExpectedValue("removal.date", removalDate)
			.addExpectedValue("removal.reason", removalReason)
			.addExpectedValue("removal.comment", removalComment)
			.performAssertions(separationNeed);
	}
	
	/**
	 * Test the update of the creation comment for a separation need.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if overlapping dates exists
	 * @throws ReflexiveRelationshipException if the relationship is created 
	 * with the same person
	 */
	@Test
	public void testUpdateCreationComment() 
			throws DuplicateEntityFoundException,
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
		String newCreationComment = "New creation comment";
		
		// Action
		separationNeed = this.separationNeedService.update(separationNeed, 
				relationship, date, newCreationComment, reportingStaff, 
				removalDate, removalReason, removalComment);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("relationship", relationship)
			.addExpectedValue("date", date)
			.addExpectedValue("creationComment", newCreationComment)
			.addExpectedValue("reportingStaff", reportingStaff)
			.addExpectedValue("removal.date", removalDate)
			.addExpectedValue("removal.reason", removalReason)
			.addExpectedValue("removal.comment", removalComment)
			.performAssertions(separationNeed);
	}
	
	/**
	 * Test the update of the reporting staff for a separation need.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if overlapping dates exists
	 * @throws ReflexiveRelationshipException if the relationship is created 
	 * with the same person
	 */
	@Test
	public void testUpdateReportingStaff() throws DuplicateEntityFoundException,
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
		Person newReportingStaff = this.personDelegate.create("Staff", "John", 
				null, null);
		
		// Action
		separationNeed = this.separationNeedService.update(separationNeed, 
				relationship, date, creationComment, newReportingStaff, 
				removalDate, removalReason, removalComment);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("relationship", relationship)
			.addExpectedValue("date", date)
			.addExpectedValue("creationComment", creationComment)
			.addExpectedValue("reportingStaff", newReportingStaff)
			.addExpectedValue("removal.date", removalDate)
			.addExpectedValue("removal.reason", removalReason)
			.addExpectedValue("removal.comment", removalComment)
			.performAssertions(separationNeed);
	}
	
	/**
	 * Test the update of the removal date for a separation need.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if overlapping dates exists
	 * @throws ReflexiveRelationshipException if the relationship is created 
	 * with the same person
	 */
	@Test
	public void testUpdateRemovalDate() throws DuplicateEntityFoundException,
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
		Date newRemovalDate = this.parseDateText("04/01/2017");
		
		// Action
		separationNeed = this.separationNeedService.update(separationNeed, 
				relationship, date, creationComment, reportingStaff, 
				newRemovalDate, removalReason, removalComment);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("relationship", relationship)
			.addExpectedValue("date", date)
			.addExpectedValue("creationComment", creationComment)
			.addExpectedValue("reportingStaff", reportingStaff)
			.addExpectedValue("removal.date", newRemovalDate)
			.addExpectedValue("removal.reason", removalReason)
			.addExpectedValue("removal.comment", removalComment)
			.performAssertions(separationNeed);
	}
	
	/**
	 * Test the update of the removal reason for a separation need.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if overlapping dates exists
	 * @throws ReflexiveRelationshipException if the relationship is created 
	 * with the same person
	 */
	@Test
	public void testUpdateRemovalReason() throws DuplicateEntityFoundException,
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
		SeparationNeedRemovalReason newRemovalReason = this
				.separationNeedRemovalReasonDelegate.create("Reason 2", 
						(short) 1, true);
		
		// Action
		separationNeed = this.separationNeedService.update(separationNeed, 
				relationship, date, creationComment, reportingStaff, 
				removalDate, newRemovalReason, removalComment);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("relationship", relationship)
			.addExpectedValue("date", date)
			.addExpectedValue("creationComment", creationComment)
			.addExpectedValue("reportingStaff", reportingStaff)
			.addExpectedValue("removal.date", removalDate)
			.addExpectedValue("removal.reason", newRemovalReason)
			.addExpectedValue("removal.comment", removalComment)
			.performAssertions(separationNeed);
	}
	
	/**
	 * Test the update of the removal comment for a separation need.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if overlapping dates exists
	 * @throws ReflexiveRelationshipException if the relationship is created 
	 * with the same person
	 */
	@Test
	public void testUpdateRemovalComment() throws DuplicateEntityFoundException,
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
		String newRemovalComment = "New removal comment";
		
		// Action
		separationNeed = this.separationNeedService.update(separationNeed, 
				relationship, date, creationComment, reportingStaff, 
				removalDate, removalReason, newRemovalComment);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("relationship", relationship)
			.addExpectedValue("date", date)
			.addExpectedValue("creationComment", creationComment)
			.addExpectedValue("reportingStaff", reportingStaff)
			.addExpectedValue("removal.date", removalDate)
			.addExpectedValue("removal.reason", removalReason)
			.addExpectedValue("removal.comment", newRemovalComment)
			.performAssertions(separationNeed);
	}

	/**
	 * Tests {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if overlapping dates exists
	 * @throws ReflexiveRelationshipException if the relationship is created 
	 * with the same person
	 */	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
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
		this.separationNeedDelegate.create(relationship, date, creationComment, 
				reportingStaff, removalDate, removalReason, removalComment);
		Date secondDate = this.parseDateText("04/01/2017");
		Date secondRemovalDate = this.parseDateText("05/01/2017");
		SeparationNeed separationNeed = this.separationNeedDelegate.create(
				relationship, secondDate, creationComment, reportingStaff, 
				secondRemovalDate, removalReason, removalComment);
		
		// Action
		separationNeed = this.separationNeedService.update(separationNeed, 
				relationship, date, creationComment, reportingStaff, 
				removalDate, removalReason, removalComment);
	}

	/**
	 * Tests {@code DateConflictException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if overlapping dates exists
	 * @throws ReflexiveRelationshipException if the relationship is created 
	 * with the same person
	 */
	@Test(expectedExceptions = {DateConflictException.class})
	public void testDateConflictException() 
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
		this.separationNeedDelegate.create(relationship, date, creationComment, 
				reportingStaff, removalDate, removalReason, removalComment);
		Date secondDate = this.parseDateText("04/01/2017");
		Date secondRemovalDate = this.parseDateText("05/01/2017");
		SeparationNeed separationNeed = this.separationNeedDelegate.create(
				relationship, secondDate, creationComment, reportingStaff, 
				secondRemovalDate, removalReason, removalComment);
		Date newDate = this.parseDateText("01/05/2017");
		
		// Action
		separationNeed = this.separationNeedService.update(separationNeed, 
				relationship, newDate, creationComment, reportingStaff, 
				secondRemovalDate, removalReason, removalComment);
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