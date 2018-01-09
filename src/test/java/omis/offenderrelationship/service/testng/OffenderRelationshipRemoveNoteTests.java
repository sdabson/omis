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
 * Relationship note removal test.
 * 
 *@author Yidong Li
 *@version 0.1.0 (Nov 16, 2017)
 *@since OMIS 3.0
 *
 */
@Test(groups = {"offenderrelationship"})
public class OffenderRelationshipRemoveNoteTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Delegates. */
	@Autowired
	@Qualifier("relationshipDelegate")
	private RelationshipDelegate relationshipDelegate;
	
	@Autowired
	@Qualifier("relationshipNoteDelegate")
	private RelationshipNoteDelegate relationshipNoteDelegate;
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("relationshipNoteCategoryDelegate")
	private RelationshipNoteCategoryDelegate relationshipNoteCategoryDelegate;
	
	/* Services */
	/*@Autowired
	@Qualifier("createRelationshipsService")
	private CreateRelationshipsService createRelationshipsService;*/
	
	@Autowired
	@Qualifier("updateOffenderRelationService")
	private UpdateOffenderRelationService updateOffenderRelationService;
	
	/**
	 * Tests the removal of offender relationship note.
	 * @throws DuplicateEntityFoundException DuplicateEntityFoundException
	 * @throws ReflexiveRelationshipException ReflexiveRelationshipException
	 */
	@Test
	public void noteRemoveTest() throws DuplicateEntityFoundException, 
		ReflexiveRelationshipException {
		// Arrangement
		Person firstPerson = this.personDelegate.create("testLastName1", 
			"testFirstName1", "testMiddleName1", "Mr.");
		Person secondPerson = this.personDelegate.create("testLastName2", 
			"testFirstName2", "testMiddleName2", "Ms.");
		Relationship relationship = this.relationshipDelegate.create(
			firstPerson, secondPerson);
		final int shortInt = 12;
		RelationshipNoteCategory relationshipNoteCategory 
			= this.relationshipNoteCategoryDelegate.create(
			"relationshipNoteCategory1", new Short((short) shortInt));
		final int dateInt = 111111111;
		Date date = new Date(dateInt);
		RelationshipNote relationshipNote = this.relationshipNoteDelegate
			.create(relationship, relationshipNoteCategory, "Note tests", 
			date);
		
		// Action
		this.updateOffenderRelationService.removeNote(relationshipNote);
		
		assert !(this.relationshipNoteDelegate.findByRelationship(relationship)
			.contains(relationshipNote)) : "Relationship Note Was Not Removed.";
	}
}
