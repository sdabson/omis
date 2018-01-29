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
package omis.hearingparticipant.service.delegate;

import java.util.Date;
import java.util.List;
import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.hearingparticipant.dao.HearingParticipantNoteDao;
import omis.hearingparticipant.domain.HearingParticipant;
import omis.hearingparticipant.domain.HearingParticipantNote;
import omis.instance.factory.InstanceFactory;

/**
 * Hearing Participant Note Delegate.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 16, 2018)
 *@since OMIS 3.0
 *
 */
public class HearingParticipantNoteDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Hearing Participant Note already exists with given description "
			+ "and date for specified Hearing Participant";
	
	private final HearingParticipantNoteDao hearingParticipantNoteDao;
	
	private final InstanceFactory<HearingParticipantNote> 
		hearingParticipantNoteInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for HearingParticipantNoteDelegate.
	 * @param hearingParticipantNoteDao - Hearing Participant Note Dao
	 * @param hearingParticipantNoteInstanceFactory - Hearing Participant Note
	 * Instance Factory
	 * @param auditComponentRetriever - Audit Component Retriever
	 */
	public HearingParticipantNoteDelegate(
			final HearingParticipantNoteDao hearingParticipantNoteDao,
			final InstanceFactory<HearingParticipantNote> 
				hearingParticipantNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.hearingParticipantNoteDao = hearingParticipantNoteDao;
		this.hearingParticipantNoteInstanceFactory =
				hearingParticipantNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a Hearing Participant Note with the given properties.
	 * 
	 * @param hearingParticipant - Hearing Participant
	 * @param description - String description
	 * @param date - Date
	 * @return Newly created Hearing Participant Note
	 * @throws DuplicateEntityFoundException - When a Hearing Participant Note
	 * already exists with the given date and description for the specified
	 * Hearing Participant.
	 */
	public HearingParticipantNote create(
			final HearingParticipant hearingParticipant,
			final String description, final Date date)
				throws DuplicateEntityFoundException {
		if (this.hearingParticipantNoteDao.find(description, date,
				hearingParticipant) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		HearingParticipantNote hearingParticipantNote = 
				this.hearingParticipantNoteInstanceFactory.createInstance();
		
		hearingParticipantNote.setDescription(description);
		hearingParticipantNote.setDate(date);
		hearingParticipantNote.setParticipant(hearingParticipant);
		hearingParticipantNote.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		hearingParticipantNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.hearingParticipantNoteDao.makePersistent(
				hearingParticipantNote);
	}
	
	/**
	 * Updates the specified Hearing Participant Note with the given
	 * properties.
	 * 
	 * @param hearingParticipantNote - Hearing Participant Note to update
	 * @param hearingParticipant - Hearing Participant
	 * @param description - String description
	 * @param date - Date
	 * @return Updated Hearing Participant Note
	 * @throws DuplicateEntityFoundException - When a Hearing Participant Note
	 * already exists with the given date and description for the specified
	 * Hearing Participant.
	 */
	public HearingParticipantNote update(
			final HearingParticipantNote hearingParticipantNote,
			final HearingParticipant hearingParticipant,
			final String description, final Date date)
				throws DuplicateEntityFoundException {
		if (this.hearingParticipantNoteDao.findExcluding(description, date,
				hearingParticipant, hearingParticipantNote) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		hearingParticipantNote.setDescription(description);
		hearingParticipantNote.setDate(date);
		hearingParticipantNote.setParticipant(hearingParticipant);
		hearingParticipantNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.hearingParticipantNoteDao
				.makePersistent(hearingParticipantNote);
	}
	
	/**
	 * Removes the specified Hearing Participant Note.
	 * 
	 * @param hearingParticipantNote - Hearing Participant Note to remove.
	 */
	public void remove(final HearingParticipantNote hearingParticipantNote) {
		this.hearingParticipantNoteDao.makeTransient(hearingParticipantNote);
	}
	
	/**
	 * Returns a list of all Hearing Participant Notes for the specified
	 * Hearing Participant.
	 * 
	 * @param hearingParticipant - Hearing Participant
	 * @return List of all Hearing Participant Notes for the specified
	 * Hearing Participant.
	 */
	public List<HearingParticipantNote> findByHearingParticipant(
			final HearingParticipant hearingParticipant) {
		return this.hearingParticipantNoteDao
				.findByHearingParticipant(hearingParticipant);
	}
	
}
