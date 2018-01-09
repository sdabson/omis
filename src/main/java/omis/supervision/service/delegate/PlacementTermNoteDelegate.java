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
package omis.supervision.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.instance.factory.InstanceFactory;
import omis.supervision.dao.PlacementTermNoteDao;
import omis.supervision.domain.PlacementTerm;
import omis.supervision.domain.PlacementTermNote;
import omis.supervision.exception.PlacementTermNoteExistsException;

/**
 * Delegate for notes for placement terms.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Dec 13, 2017)
 * @since OMIS 3.0
 */
public class PlacementTermNoteDelegate {

	/* Instance factories. */
	
	private final InstanceFactory<PlacementTermNote>
	placementTermNoteInstanceFactory;
	
	/* Data access objects. */
	
	private final PlacementTermNoteDao placementTermNoteDao;
	
	/* Audit component retriever. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for notes for placement terms.
	 * 
	 * @param placementTermNoteInstanceFactory instance factory for notes
	 * for placement terms
	 * @param placementTermNoteDao data access object for notes for placement
	 * terms
	 * @param auditComponentRetriever audit component retriever
	 */
	public PlacementTermNoteDelegate(
			final InstanceFactory<PlacementTermNote>
			placementTermNoteInstanceFactory,
			final PlacementTermNoteDao placementTermNoteDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.placementTermNoteInstanceFactory
			= placementTermNoteInstanceFactory;
		this.placementTermNoteDao = placementTermNoteDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/* Methods. */
	
	/**
	 * Creates note for placement term.
	 * 
	 * @param placementTerm placement term
	 * @param date date
	 * @param value value
	 * @return note for placement term
	 * @throws PlacementTermNoteExistsException if note for placement term
	 * exists
	 */
	public PlacementTermNote create(
			final PlacementTerm placementTerm, final Date date,
			final String value)
				throws PlacementTermNoteExistsException {
		if (this.placementTermNoteDao
				.find(placementTerm, date, value) != null) {
			throw new PlacementTermNoteExistsException(
					"Placement term note exists");
		}
		PlacementTermNote note
			= this.placementTermNoteInstanceFactory.createInstance();
		note.setPlacementTerm(placementTerm);
		note.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		this.populateNote(note, date, value);
		return this.placementTermNoteDao.makePersistent(note);
	}
	
	/**
	 * Updates note for placement term.
	 * 
	 * @param note placement term note
	 * @param date date
	 * @param value value
	 * @return updated note for placement term
	 * @throws PlacementTermNoteExistsException if note for placement term exists
	 */
	public PlacementTermNote update(
			final PlacementTermNote note, final Date date,
			final String value)
				throws PlacementTermNoteExistsException {
		if (this.placementTermNoteDao.findExcluding(
				note.getPlacementTerm(), date, value, note) != null) {
			throw new PlacementTermNoteExistsException(
					"Placement term note exists");
		}
		this.populateNote(note, date, value);
		return this.placementTermNoteDao.makePersistent(note);
	}

	/**
	 * Removes note for placement term.
	 * 
	 * @param note note for placement term to remove
	 */
	public void remove(final PlacementTermNote note) {
		this.placementTermNoteDao.makeTransient(note);
	}
	
	/**
	 * Returns notes by placement term.
	 * 
	 * @param placementTerm placement term by which to return notes
	 * @return notes by placement term
	 */
	public List<PlacementTermNote> findByPlacementTerm(
			final PlacementTerm placementTerm) {
		return this.placementTermNoteDao.findByPlacementTerm(placementTerm);
	}
	
	/* Helper methods. */
	
	// Populates note
	private void populateNote(
			final PlacementTermNote note, final Date date, final String value) {
		note.setDate(date);
		note.setValue(value);
		note.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
	}
}