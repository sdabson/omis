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

import omis.exception.DuplicateEntityFoundException;
import omis.offenderrelationship.service.UpdateOffenderRelationService;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.relationship.domain.Relationship;
import omis.relationship.domain.RelationshipNote;
import omis.relationship.domain.RelationshipNoteCategory;
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.relationship.exception.RelationshipNoteExistsException;
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
	 * @throws ReflexiveRelationshipException ReflexiveRelationshipException
	 * @throws DuplicateEntityFoundException DuplicateEntityFoundException
	 * @throws RelationshipNoteExistsException RelationshipNoteExistsException
	 */
	@Test
	public void testOffenderRelationshipUpdate() 
		throws DuplicateEntityFoundException, ReflexiveRelationshipException,
		RelationshipNoteExistsException {
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
	 * @throws RelationshipNoteExistsException RelationshipNoteExistsException
	 */
	@Test(expectedExceptions = {RelationshipNoteExistsException.class})
	public void testDuplicateRelationshipNoteUpdate() 
		throws DuplicateEntityFoundException, ReflexiveRelationshipException,
		RelationshipNoteExistsException {
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