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
package omis.stg.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.instance.factory.InstanceFactory;
import omis.stg.dao.SecurityThreatGroupActivityNoteDao;
import omis.stg.domain.SecurityThreatGroupActivity;
import omis.stg.domain.SecurityThreatGroupActivityNote;
import omis.stg.exception.SecurityThreatGroupActivityNoteExistsException;

/**
 * Security threat group affiliation note.
 * 
 * @author Trevor Isles
 * @author Josh Divine
 * @author Sheronda Vaughn
 * @version 0.1.1 (Apr 10, 2018)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupActivityNoteDelegate {

	/* Instance factories. */
	private final InstanceFactory<SecurityThreatGroupActivityNote> 
		activityNoteInstanceFactory;
	
	/* DAOs. */
	private final SecurityThreatGroupActivityNoteDao activityNoteDao;
	
	/* Audit component retriever. */
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Constructor. */
	
	/** 
	 * Instantiates delegate for security threat group activity note.
	 * 
	 * @param securityThreatGroupActivityNoteInstanceFactory security threat 
	 * group activity note instance factory
	 * @param securityThreatGroupActivityNoteDao security threat group activity 
	 * note data access object 
	 * @param auditComponentRetriever audit component retriever
	 */
	public SecurityThreatGroupActivityNoteDelegate(
			final InstanceFactory<SecurityThreatGroupActivityNote> 
				activityNoteInstanceFactory,
			final SecurityThreatGroupActivityNoteDao activityNoteDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.activityNoteInstanceFactory = activityNoteInstanceFactory;
		this.activityNoteDao = activityNoteDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/** 
	 * Creates a security threat group activity note.
	 * 
	 * @param activity security threat group activity
	 * @param date date
	 * @param value value
	 * @return security threat group activity note
	 * @throws SecurityThreatGroupActivityNoteExistsException when an activity note already 
	 * exists
	 */
	public SecurityThreatGroupActivityNote addNote(
			final SecurityThreatGroupActivity activity, 
			final Date date,
			final String value)
		throws SecurityThreatGroupActivityNoteExistsException {
		if (this.activityNoteDao.findNote(activity, date, value) != null) {
			throw new SecurityThreatGroupActivityNoteExistsException (
					"Duplicate security threat group activity note found");
		}
		SecurityThreatGroupActivityNote activityNote = 
			this.activityNoteInstanceFactory.createInstance();
				activityNote.setCreationSignature(new CreationSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate()));
			this.populateSecurityThreatGroupActivityNote(activityNote, activity, 
					date, value);
		return this.activityNoteDao.makePersistent(activityNote);
	}
	
	/**
	 * Updates a security threat group activity note.
	 * 
	 * @param note security threat group activity note
	 * @param date date
	 * @param value value
	 * @return security threat group activity note
	 * @throws SecurityThreatGroupActivityNoteExistsException if duplicate entity exists
	 */
	public SecurityThreatGroupActivityNote updateNote(
			final SecurityThreatGroupActivityNote note, final Date date, 
			final String value) throws SecurityThreatGroupActivityNoteExistsException {
		if (this.activityNoteDao.findExcluding(note.getActivity(), date, value, 
				note) != null) {
			throw new SecurityThreatGroupActivityNoteExistsException (
					"Duplicate security threat group activity note found.");
		}
		this.populateSecurityThreatGroupActivityNote(note, note.getActivity(),
				date, value);
		return this.activityNoteDao.makePersistent(note);
	}

	/** 
	 * Removes a security threat group activity.
	 * 
	 * @param note security threat group activity note
	 */
	public void remove(final SecurityThreatGroupActivityNote note) {
		this.activityNoteDao.makeTransient(note);
	}
	
	/**
	 * Returns a list of security threat group activity notes.
	 * 
	 * @return list of security threat group activity notes
	 */
	public List<SecurityThreatGroupActivityNote> findNotes(
			SecurityThreatGroupActivity activity) {
		return this.activityNoteDao.findNotes(activity);
	}
	
	// Populates the specified security threat group activity.
	private void populateSecurityThreatGroupActivityNote(
			final SecurityThreatGroupActivityNote note,
			final SecurityThreatGroupActivity activity,
			final Date date, 
			final String value) {
		note.setActivity(activity);
		note.setDate(date);
		note.setValue(value);
		note.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
	}
}