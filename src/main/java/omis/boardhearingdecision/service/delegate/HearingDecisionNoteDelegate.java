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
package omis.boardhearingdecision.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.boardhearingdecision.dao.HearingDecisionNoteDao;
import omis.boardhearingdecision.domain.BoardHearingDecision;
import omis.boardhearingdecision.domain.HearingDecisionNote;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for hearing decision note.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 22, 2018)
 * @since OMIS 3.0
 */
public class HearingDecisionNoteDelegate {

	/* Data access objects. */
	
	private final HearingDecisionNoteDao hearingDecisionNoteDao;

	/* Instance factories. */
	
	private final InstanceFactory<HearingDecisionNote> 
			hearingDecisionNoteInstanceFactory;
	
	/* Component Retrievers. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates an implementation of hearing decision note delegate with the 
	 * specified data access object, instance factory and audit component 
	 * retriever.
	 * 
	 * @param hearingDecisionNoteDao hearing decision note data access object
	 * @param hearingDecisionNoteInstanceFactory hearing decision note instance 
	 * factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public HearingDecisionNoteDelegate(
			final HearingDecisionNoteDao hearingDecisionNoteDao,
			final InstanceFactory<HearingDecisionNote> 
					hearingDecisionNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.hearingDecisionNoteDao = hearingDecisionNoteDao;
		this.hearingDecisionNoteInstanceFactory = 
				hearingDecisionNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a hearing decision note with the specified board hearing 
	 * decision, date and description.
	 * 
	 * @param boardDecision board hearing decision
	 * @param date date
	 * @param description description
	 * @return hearing decision note
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public HearingDecisionNote create(final BoardHearingDecision boardDecision, 
			final Date date, final String description) 
					throws DuplicateEntityFoundException {
		if (this.hearingDecisionNoteDao.find(boardDecision, date, description) 
				!= null) {
			throw new DuplicateEntityFoundException(
					"Hearing decision note already exists.");
		}
		HearingDecisionNote hearingDecisionNote = 
				this.hearingDecisionNoteInstanceFactory.createInstance();
		populateHearingDecisionNote(hearingDecisionNote, boardDecision, date, 
				description);
		hearingDecisionNote.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		return this.hearingDecisionNoteDao.makePersistent(hearingDecisionNote);
	}

	/**
	 * Updates a hearing decision note with the specified board hearing 
	 * decision, date and description.
	 * 
	 * @param hearingDecisionNote hearing decision note
	 * @param boardDecision board hearing decision
	 * @param date date
	 * @param description description
	 * @return hearing decision note
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public HearingDecisionNote update(
			final HearingDecisionNote hearingDecisionNote, 
			final BoardHearingDecision boardDecision, 
			final Date date, final String description) 
					throws DuplicateEntityFoundException {
		if (this.hearingDecisionNoteDao.findExcluding(boardDecision, date, 
				description, hearingDecisionNote) != null) {
			throw new DuplicateEntityFoundException(
					"Hearing decision note already exists.");
		}
		populateHearingDecisionNote(hearingDecisionNote, boardDecision, date, 
				description);
		return this.hearingDecisionNoteDao.makePersistent(hearingDecisionNote);
	}

	/**
	 * Removes the specified hearing decision note.
	 * 
	 * @param hearingDecisionNote hearing decision note
	 */
	public void remove(final HearingDecisionNote hearingDecisionNote) {
		this.hearingDecisionNoteDao.makeTransient(hearingDecisionNote);
	}

	/**
	 * Returns a list of hearing decision notes for the specified board hearing 
	 * decision.
	 * 
	 * @param boardDecision board hearing decision
	 * @return list of hearing decision notes
	 */
	public List<HearingDecisionNote> findByBoardDecision(
			final BoardHearingDecision boardDecision) {
		return this.hearingDecisionNoteDao.findByBoardDecision(boardDecision);
	}

	// Populates a hearing decision note
	private void populateHearingDecisionNote(
			final HearingDecisionNote hearingDecisionNote,
			final BoardHearingDecision boardDecision, 
			final Date date, final String description) {
		hearingDecisionNote.setBoardDecision(boardDecision);
		hearingDecisionNote.setDate(date);
		hearingDecisionNote.setDescription(description);
		hearingDecisionNote.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
	}
}
