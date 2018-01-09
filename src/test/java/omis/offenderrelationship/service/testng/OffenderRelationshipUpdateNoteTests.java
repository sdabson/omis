package omis.offenderrelationship.service.testng;

import java.util.Date;

import omis.exception.DuplicateEntityFoundException;
import omis.offenderrelationship.service.UpdateOffenderRelationService;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.relationship.domain.Relationship;
import omis.relationship.domain.RelationshipNote;
import omis.relationship.domain.RelationshipNoteCategory;
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.relationship.service.delegate.RelationshipDelegate;
import omis.relationship.service.delegate.RelationshipNoteCategoryDelegate;
import omis.relationship.service.delegate.RelationshipNoteDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

/**
 * Tests "update" of offender relationship note.
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"offenderrelationship"})
public class OffenderRelationshipUpdateNoteTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Delegates. */
	@Autowired
	@Qualifier("relationshipDelegate")
	private RelationshipDelegate relationshipDelegate;
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
		
	@Autowired
	@Qualifier("relationshipNoteCategoryDelegate")
	private RelationshipNoteCategoryDelegate relationshipNoteCategoryDelegate;
	
	@Autowired
	@Qualifier("relationshipNoteDelegate")
	private RelationshipNoteDelegate relationshipNoteDelegate;
	
	/* Service */
	@Autowired
	@Qualifier("updateOffenderRelationService")
	private UpdateOffenderRelationService updateOffenderRelationService;
	
	/**
	 * Offender relationship note update.
	 * @throws ReflexiveRelationshipException 
	 * @throws DuplicateEntityFoundException 
	 */
	@Test
	public void testOffenderRelationshipUpdate() 
		throws DuplicateEntityFoundException, ReflexiveRelationshipException {
		// Arrangements
		Person firstPerson = this.personDelegate.create("testLastName", 
			"testFirstName", "testMiddleName1", "Mr.");
		Person secondPerson = this.personDelegate.create("testLastName", 
			"testFirstName", "testMiddleName2", "Ms.");
		Relationship relationship = this.relationshipDelegate.create(
			firstPerson, secondPerson);
		final int shortInt = 12;
		RelationshipNoteCategory relationshipNoteCategory1 
			= this.relationshipNoteCategoryDelegate.create(
			"relationshipNoteCategory1", new Short((short) shortInt));
		final int date1Int = 111111111;
		Date date1 = new Date(date1Int);
		RelationshipNote relationshipNote = this.relationshipNoteDelegate
			.create(relationship, relationshipNoteCategory1, "Value1", date1);
		final int shortInt1 = 23;
		RelationshipNoteCategory relationshipNoteCategory2 
			= this.relationshipNoteCategoryDelegate.create(
			"relationshipNoteCategory2", new Short((short) shortInt1));
		final int date2Int = 222222222;
		Date date2 = new Date(date2Int);
		
		// Action
		this.updateOffenderRelationService.updateNote(relationshipNote, 
			relationshipNoteCategory2, "Value2", date2);
		
		// Assertions
		assert date2.equals(relationshipNote.getDate())
		: String.format("Wrong Date: #%s expected; #%s found",
			date2, relationshipNote.getDate());
		assert "Value2".equals(relationshipNote.getValue())
		: String.format("Wrong Value: #%s expected; #%s found",
			"Value2", relationshipNote.getValue());
		assert date2.equals(relationshipNote.getDate())
		: String.format("Wrong Birth Country: #%s expected; #%s found",
			date2, relationshipNote.getDate());
	}	
		
	/**
	 * Tests duplicate relationship note on update.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate relationships note 
	 * exists
	 * @throws ReflexiveRelationshipException ReflexiveRelationshipException
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateRelationshipNoteUpdate() 
		throws DuplicateEntityFoundException, ReflexiveRelationshipException {
		// Arrangements
		Person firstPerson = this.personDelegate.create("testLastName", 
			"testFirstName", "testMiddleName1", "Mr.");
		Person secondPerson = this.personDelegate.create("testLastName", 
			"testFirstName", "testMiddleName2", "Ms.");
		Relationship relationship = this.relationshipDelegate.create(
			firstPerson, secondPerson);
		final int shortInt = 12;
		RelationshipNoteCategory relationshipNoteCategory1 
			= this.relationshipNoteCategoryDelegate.create(
			"relationshipNoteCategory1", new Short((short) shortInt));
		final int date1Int = 111111111;
		Date date1 = new Date(date1Int);
		RelationshipNote relationshipNote1 = this.relationshipNoteDelegate
			.create(relationship, relationshipNoteCategory1, "Value1", 
			date1);
		final int shortInt1 = 23;
		RelationshipNoteCategory relationshipNoteCategory2 
			= this.relationshipNoteCategoryDelegate.create(
			"relationshipNoteCategory2", new Short((short) shortInt1));
		final int date2Int = 222222222;
		Date date2 = new Date(date2Int);
		this.relationshipNoteDelegate.create(relationship, 
			relationshipNoteCategory2, "Value2", date2);
		
		// Action
		this.updateOffenderRelationService.updateNote(relationshipNote1, 
			relationshipNoteCategory2, "Value2", date2);
	}	
}