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
package omis.paroleeligibility.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.paroleeligibility.dao.ParoleEligibilityNoteDao;
import omis.paroleeligibility.domain.ParoleEligibility;
import omis.paroleeligibility.domain.ParoleEligibilityNote;

/**
 * Delegate for the parole eligibility note.
 *
 * @author Trevor Isles
 * @version 0.1.0 (Nov 9, 2017)
 * @since OMIS 3.0
 */
public class ParoleEligibilityNoteDelegate {

	/* Instance factories. */
	private final InstanceFactory<ParoleEligibilityNote> 
		paroleEligibilityNoteInstanceFactory;
	
	/* DAOs. */
	private final ParoleEligibilityNoteDao paroleEligibilityNoteDao;
	
	/* Audit component retriever. */
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Constructor. */
	
	public ParoleEligibilityNoteDelegate(
			final InstanceFactory<ParoleEligibilityNote> 
					paroleEligibilityNoteInstanceFactory,
			final ParoleEligibilityNoteDao paroleEligibilityNoteDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.paroleEligibilityNoteInstanceFactory = 
				paroleEligibilityNoteInstanceFactory;
		this.paroleEligibilityNoteDao = paroleEligibilityNoteDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a parole eligibility note.
	 * 
	 * @param paroleEligibility
	 * @param description
	 * @param date
	 * @return
	 * @throws DuplicateEntityFoundException
	 */
	public ParoleEligibilityNote create(
			final ParoleEligibility paroleEligibility,
			final String description,
			final Date date)
		throws DuplicateEntityFoundException {
		if (this.paroleEligibilityNoteDao.findNote(paroleEligibility, 
				description, date) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate parole eligibility note found.");
		}
		
		ParoleEligibilityNote eligibilityNote = 
			this.paroleEligibilityNoteInstanceFactory.createInstance();
				eligibilityNote.setCreationSignature(new CreationSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate()));
			this.populateParoleEligibilityNote(eligibilityNote, 
					paroleEligibility, description, date);
		return this.paroleEligibilityNoteDao.makePersistent(eligibilityNote);
	}
	
	/**
	 * Updates a parole eligibility note.
	 * 
	 * @param paroleEligibilityNote
	 * @param paroleEligibility
	 * @param description
	 * @param date
	 * @return
	 * @throws DuplicateEntityFoundException
	 */
	public ParoleEligibilityNote update(
			final ParoleEligibilityNote paroleEligibilityNote,
			final String description,
			final Date date)
		throws DuplicateEntityFoundException {
		if (this.paroleEligibilityNoteDao.findNoteExcluding(
				paroleEligibilityNote, 
				paroleEligibilityNote.getParoleEligibility(), description, date) 
					!= null) {
			throw new DuplicateEntityFoundException(
					"Duplicate parole eligibility found.");
		}
		this.populateParoleEligibilityNote(paroleEligibilityNote, 
				paroleEligibilityNote.getParoleEligibility(), description, 
				date);
		return this.paroleEligibilityNoteDao.makePersistent(
				paroleEligibilityNote);
	}
	
	/**
	 * Removes a parole eligibility note.
	 * 
	 * @param paroleEligibilityNote
	 */
	public void remove(final ParoleEligibilityNote paroleEligibilityNote) {
		this.paroleEligibilityNoteDao.makeTransient(paroleEligibilityNote);
	}
	
	public List<ParoleEligibilityNote> 
		findParoleEligibilityNotesByParoleEligibility(
			ParoleEligibility paroleEligibility) {
		return this.paroleEligibilityNoteDao
			.findParoleEligibilityNotesByParoleEligibility(paroleEligibility);
	}
	
	/**
	 * Populates the specified parole eligibility note.
	 * 
	 * @param paroleEligibilityNote
	 * @param paroleEligibility
	 * @param description
	 * @param date
	 */
	private void populateParoleEligibilityNote(
			final ParoleEligibilityNote paroleEligibilityNote,
			final ParoleEligibility paroleEligibility,
			final String description,
			final Date date) {
		paroleEligibilityNote.setParoleEligibility(paroleEligibility);
		paroleEligibilityNote.setDescription(description);
		paroleEligibilityNote.setDate(date);
		paroleEligibilityNote.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
	}
	
}
