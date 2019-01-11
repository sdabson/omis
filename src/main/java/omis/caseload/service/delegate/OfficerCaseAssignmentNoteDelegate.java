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
package omis.caseload.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.caseload.dao.OfficerCaseAssignmentNoteDao;
import omis.caseload.domain.OfficerCaseAssignment;
import omis.caseload.domain.OfficerCaseAssignmentNote;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Officer case assignment note delegate.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Sep 7, 2018)
 * @since OMIS 3.0
 */
public class OfficerCaseAssignmentNoteDelegate {

	/* DAOs */
	private final OfficerCaseAssignmentNoteDao officerCaseAssignmentNoteDao;
	
	/* Instance factory. */
	private final InstanceFactory<OfficerCaseAssignmentNote> 
					officerCaseAssignmentNoteInstanceFactory;
	
	/* Helpers. */
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates an implementation of officer case assignment note delegate 
	 * with the specified data access object, instance factory and audit 
	 * component retriever.
	 * 
	 * @param officerCaseAssignmentNoteDao officer case assignment note data 
	 * access object
	 * @param officerCaseAssignmentNoteInstanceFactory officer case assignment 
	 * note instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public OfficerCaseAssignmentNoteDelegate(
			final OfficerCaseAssignmentNoteDao officerCaseAssignmentNoteDao,
			final InstanceFactory<OfficerCaseAssignmentNote> 
					officerCaseAssignmentNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.officerCaseAssignmentNoteDao = officerCaseAssignmentNoteDao;
		this.officerCaseAssignmentNoteInstanceFactory = 
				officerCaseAssignmentNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a new officer case assignment note.
	 * 
	 * @param officerCaseAssignment officer case assignment
	 * @param description description
	 * @param date date
	 * @return officer case assignment note
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public OfficerCaseAssignmentNote create(
			final OfficerCaseAssignment officerCaseAssignment, 
			final String description, final Date date) 
					throws DuplicateEntityFoundException {
		if (this.officerCaseAssignmentNoteDao.find(officerCaseAssignment, 
				description, date) != null) {
			throw new DuplicateEntityFoundException(
					"Officer case assignment note already exists.");
		}
		OfficerCaseAssignmentNote officerCaseAssignmentNote = this
				.officerCaseAssignmentNoteInstanceFactory.createInstance();
		populateOfficerCaseAssignmentNote(officerCaseAssignmentNote,
				officerCaseAssignment, description, date);
		officerCaseAssignmentNote.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		return this.officerCaseAssignmentNoteDao.makePersistent(
				officerCaseAssignmentNote);
	}
	
	/**
	 * Updates an existing officer case assignment note.
	 * 
	 * @param officerCaseAssignmentNote officer case assignment note
	 * @param description description
	 * @param date date
	 * @return officer case assignment note
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public OfficerCaseAssignmentNote update(
			final OfficerCaseAssignmentNote officerCaseAssignmentNote, 
			final OfficerCaseAssignment officerCaseAssignment, 
			final String description, final Date date) 
					throws DuplicateEntityFoundException {
		if (this.officerCaseAssignmentNoteDao.findExcluding(
				officerCaseAssignment, description, date, 
				officerCaseAssignmentNote) != null) {
			throw new DuplicateEntityFoundException(
					"Officer case assignment note already exists.");
		}
		populateOfficerCaseAssignmentNote(officerCaseAssignmentNote,
				officerCaseAssignment, description, date);
		return this.officerCaseAssignmentNoteDao.makePersistent(
				officerCaseAssignmentNote);
	}
	
	/**
	 * Removes the specified officer case assignment note.
	 * 
	 * @param officerCaseAssignmentNote officer case assignment note
	 */
	public void remove(OfficerCaseAssignmentNote officerCaseAssignmentNote) {
		this.officerCaseAssignmentNoteDao.makeTransient(
				officerCaseAssignmentNote);
	}

	/**
	 * Returns a list of officer case assignment notes for the specified officer 
	 * case assignment.
	 * 
	 * @param officerCaseAssignment officer case assignment
	 * @return list of officer case assignment notes
	 */
	public List<OfficerCaseAssignmentNote> findByOfficerCaseAssignment(
			final OfficerCaseAssignment officerCaseAssignment) {
		return this.officerCaseAssignmentNoteDao.findByOfficerCaseAssignment(
				officerCaseAssignment);
	}
	
	//Populates an officer case assignment note
	private void populateOfficerCaseAssignmentNote(
			final OfficerCaseAssignmentNote officerCaseAssignmentNote,
			final OfficerCaseAssignment officerCaseAssignment,
			final String description, final Date date) {
		officerCaseAssignmentNote.setOfficerCaseAssignment(
				officerCaseAssignment);
		officerCaseAssignmentNote.setDescription(description);
		officerCaseAssignmentNote.setDate(date);
		officerCaseAssignmentNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
	}
}