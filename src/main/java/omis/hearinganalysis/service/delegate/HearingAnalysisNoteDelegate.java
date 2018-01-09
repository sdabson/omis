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
package omis.hearinganalysis.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.hearinganalysis.dao.HearingAnalysisNoteDao;
import omis.hearinganalysis.domain.HearingAnalysis;
import omis.hearinganalysis.domain.HearingAnalysisNote;
import omis.instance.factory.InstanceFactory;

/**
 * Hearing analysis note delegate.
 *
 * @author Josh Divine
 * @version 0.1.0 (Dec 18, 2017)
 * @since OMIS 3.0
 */
public class HearingAnalysisNoteDelegate {

	/* Data access objects. */
	
	private final HearingAnalysisNoteDao hearingAnalysisNoteDao;

	/* Instance factories. */
	
	private final InstanceFactory<HearingAnalysisNote> 
			hearingAnalysisNoteInstanceFactory;
	
	/* Component Retrievers. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/** Instantiates an implementation of hearing analysis note delegate with 
	 * the specified date access object and instance factory.
	 * 
	 * @param hearingAnalysisNoteDao hearing analysis data access object
	 * @param hearingAnalysisNoteInstanceFactory hearing analysis note instance 
	 * factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public HearingAnalysisNoteDelegate(
			final HearingAnalysisNoteDao hearingAnalysisNoteDao,
			final InstanceFactory<HearingAnalysisNote> 
				hearingAnalysisNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.hearingAnalysisNoteDao = hearingAnalysisNoteDao;
		this.hearingAnalysisNoteInstanceFactory = 
				hearingAnalysisNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a new hearing analysis note.
	 * 
	 * @param hearingAnalysis hearing analysis
	 * @param date date
	 * @param description description
	 * @return hearing analysis note
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public HearingAnalysisNote create(final HearingAnalysis hearingAnalysis, 
			final String description, final Date date) 
					throws DuplicateEntityFoundException {
		if (this.hearingAnalysisNoteDao.find(hearingAnalysis, date, description)
				!= null) {
			throw new DuplicateEntityFoundException(
					"Hearing analysis note already exists");
		}
		HearingAnalysisNote hearingAnalysisNote = 
				this.hearingAnalysisNoteInstanceFactory.createInstance();
		hearingAnalysisNote.setHearingAnalysis(hearingAnalysis);
		hearingAnalysisNote.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		populateHearingAnalysisNote(hearingAnalysisNote, date, description);
		return this.hearingAnalysisNoteDao.makePersistent(hearingAnalysisNote);
	}

	/**
	 * Updates an existing hearing analysis note.
	 * 
	 * @param hearingAnalysisNote hearing analysis note
	 * @param description description
	 * @param date date
	 * @return hearing analysis note
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public HearingAnalysisNote update(
			final HearingAnalysisNote hearingAnalysisNote, 
			final String description, final Date date) 
					throws DuplicateEntityFoundException {
		if (this.hearingAnalysisNoteDao.findExcluding(
				hearingAnalysisNote.getHearingAnalysis(), date, description, 
				hearingAnalysisNote) != null) {
			throw new DuplicateEntityFoundException(
					"Hearing analysis note already exists");
		}
		populateHearingAnalysisNote(hearingAnalysisNote, date, description);
		return this.hearingAnalysisNoteDao.makePersistent(hearingAnalysisNote);
	}

	/**
	 * Removes a hearing analysis note.
	 * 
	 * @param hearingAnalysisNote hearing analysis note
	 */
	public void remove(final HearingAnalysisNote hearingAnalysisNote) {
		this.hearingAnalysisNoteDao.makeTransient(hearingAnalysisNote);
	}

	/**
	 * Returns a list of hearing analysis notes for the specified hearing 
	 * analysis.
	 * 
	 * @param hearingAnalysis hearing analysis
	 * @return list of hearing analysis notes
	 */
	public List<HearingAnalysisNote> findByHearingAnalysis(
			final HearingAnalysis hearingAnalysis) {
		return this.hearingAnalysisNoteDao.findByHearingAnalysis(
				hearingAnalysis);
	}

	// Populates a hearing analysis note
	private void populateHearingAnalysisNote(
			final HearingAnalysisNote hearingAnalysisNote, final Date date,
			final String description) {
		hearingAnalysisNote.setDate(date);
		hearingAnalysisNote.setDescription(description);
		hearingAnalysisNote.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
	}
}
