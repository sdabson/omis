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
package omis.boardhearing.service.delegate;

import java.util.Date;
import java.util.List;
import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.boardhearing.dao.BoardHearingNoteDao;
import omis.boardhearing.domain.BoardHearing;
import omis.boardhearing.domain.BoardHearingNote;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Board Hearing Note Delegate.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 29, 2017)
 *@since OMIS 3.0
 *
 */
public class BoardHearingNoteDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"A Board Hearing Note already exists with the given description "
			+ "and date for the specified Board Hearing.";
	
	private final BoardHearingNoteDao boardHearingNoteDao;
	
	private final InstanceFactory<BoardHearingNote> 
		boardHearingNoteInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for BoardHearingNoteDelegate.
	 * @param boardHearingNoteDao - Board Hearing Note DAO
	 * @param boardHearingNoteInstanceFactory - Board Hearing Note Instance
	 * Factory
	 * @param auditComponentRetriever - Audit Component Retriever
	 */
	public BoardHearingNoteDelegate(
			final BoardHearingNoteDao boardHearingNoteDao,
			final InstanceFactory<BoardHearingNote> 
				boardHearingNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.boardHearingNoteDao = boardHearingNoteDao;
		this.boardHearingNoteInstanceFactory = boardHearingNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a Board Hearing Note with the specified properties.
	 * 
	 * @param hearing - Board Hearing
	 * @param description - String Description
	 * @param date - Date
	 * @return Newly Created Board Hearing Note
	 * @throws DuplicateEntityFoundException - When a Board Hearing Note already
	 * exists with the given description and date for the specified
	 * Board Hearing.
	 */
	public BoardHearingNote create(final BoardHearing hearing,
			final String description, final Date date)
				throws DuplicateEntityFoundException {
		if (this.boardHearingNoteDao.find(hearing, description, date) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		BoardHearingNote boardHearingNote = 
				this.boardHearingNoteInstanceFactory.createInstance();
		
		boardHearingNote.setDate(date);
		boardHearingNote.setDescription(description);
		boardHearingNote.setHearing(hearing);
		boardHearingNote.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		boardHearingNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.boardHearingNoteDao.makePersistent(boardHearingNote);
	}
	
	/**
	 * Updates the specified Board Hearing Note with the given properties.
	 * 
	 * @param boardHearingNote - Board Hearing Note to update
	 * @param description - String Description
	 * @param date - Date
	 * @return Updated Board Hearing Note
	 * @throws DuplicateEntityFoundException - When a Board Hearing Note already
	 * exists with the given description and date for the specified
	 * Board Hearing.
	 */
	public BoardHearingNote update(final BoardHearingNote boardHearingNote,
			final String description, final Date date)
				throws DuplicateEntityFoundException {
		if (this.boardHearingNoteDao.findExcluding(
				boardHearingNote.getHearing(), description, date,
				boardHearingNote) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		boardHearingNote.setDate(date);
		boardHearingNote.setDescription(description);
		boardHearingNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.boardHearingNoteDao.makePersistent(boardHearingNote);
	}
	
	/**
	 * Removes the specified Board Hearing Note.
	 * 
	 * @param boardHearingNote - Board Hearing Note to remove.
	 */
	public void remove(final BoardHearingNote boardHearingNote) {
		this.boardHearingNoteDao.makeTransient(boardHearingNote);
	}
	
	/**
	 * Returns a list of Board Hearing Notes from the specified Board Hearing.
	 * 
	 * @param hearing - Board Hearing
	 * @return List of Board Hearing Notes from the specified Board Hearing.
	 */
	public List<BoardHearingNote> findByHearing(final BoardHearing hearing) {
		return this.boardHearingNoteDao.findByHearing(hearing);
	}
	
}
