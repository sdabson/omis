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
package omis.stg.service;

import java.util.Date;
import java.util.List;

import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.stg.domain.SecurityThreatGroupActivity;
import omis.stg.domain.SecurityThreatGroupActivityInvolvement;
import omis.stg.domain.SecurityThreatGroupActivityNote;
import omis.stg.exception.InvolvedOffenderRequiredException;
import omis.stg.exception.SecurityThreatGroupActivityExistsException;
import omis.stg.exception.SecurityThreatGroupActivityInvolvementExistsException;
import omis.stg.exception.SecurityThreatGroupActivityNoteExistsException;

/**
 * Security threat group activity.
 * 
 * @author Trevor Isles
 * @author Sheronda Vaughn
 * @version 0.1.0 (Nov 28, 2016)
 * @since OMIS 3.0
 */

public interface SecurityThreatGroupActivityService {

	/**
	 * Creates a new security threat group activity.
	 * 
	 * @param reportDate reported date
	 * @param reportedBy reported by
	 * @param summary summary
	 * @return created new security threat group activity
	 * @throws SecurityThreatGroupActivityExistsException if new activity already exists.
	 */
	SecurityThreatGroupActivity create(Date reportDate, 
			Person reportedBy, String summary) 
			throws SecurityThreatGroupActivityExistsException;

	/**
	 * Updates a security threat group activity for the specified offender.
	 * 
	 * @param activity - security threat group activity
	 * @param reportedBy - person reporting
	 * @param reportDate - date of report
	 * @param summary - summary of activity
	 * @return updates a security threat group activity
	 * @throws SecurityThreatGroupActivityExistsException thrown when a duplicate security
	 * threat group activity is found.
	 */	
	SecurityThreatGroupActivity update(SecurityThreatGroupActivity activity, 
			Person reportedBy, Date reportDate, String summary)
				throws SecurityThreatGroupActivityExistsException;
	
	/**
	 * Removes a security threat group activity for the specified offender.
	 * 
	 * @param activity security threat group activity
	 */		
	void remove(SecurityThreatGroupActivity activity);
	
	/**
	 * Creates a security threat group activity involvement.
	 * @param Offender - offender
	 * @param String - narrative
	 * @return 
	 * @throws SecurityThreatGroupActivityInvolvementExistsException thrown when a duplicate offender
	 * is found.
	 */
	SecurityThreatGroupActivityInvolvement involveOffender(
			Offender offender, SecurityThreatGroupActivity activity, 
			String narrative) throws SecurityThreatGroupActivityInvolvementExistsException;
	
	/**
	 * Updates a security threat group activity involvement for the specified 
	 * offender.
	 * 
	 * @param involvement - security threat group activity involvement
	 * @param narrative - narrative of the involvement
	 *
	 * @return updates a security threat group activity involvement
	 * @throws SecurityThreatGroupActivityInvolvementExistsException thrown when a duplicate security
	 * threat group activity involvement is found.
	 */	
	SecurityThreatGroupActivityInvolvement updateInvolvementNarrative(
			SecurityThreatGroupActivityInvolvement involvement, 
			String narrative)
				throws SecurityThreatGroupActivityInvolvementExistsException;
	
	/**
	 * Removes a security threat group activity involvement for the specified 
	 * offender.
	 * 
	 * @param involvement security threat group activity involvement
	 * @throws InvolvedOffenderRequiredException at least one involved offender
	 * is required
	 */	
	void removeInvolvement(
			SecurityThreatGroupActivityInvolvement involvement)
				throws InvolvedOffenderRequiredException; 
	
	/**
	 * Creates a new security threat group activity note.
	 * 
	 * @param SecurityThreatGroupActivity - activity
	 * @param Date - date
	 * @param String - value
	 * 
	 * @return created new security threat group activity note
	 * @throws SecurityThreatGroupActivityNoteExistsException if new security threat group 
	 * activity note already exists.
	 */
	SecurityThreatGroupActivityNote addNote(SecurityThreatGroupActivity activity,
			Date date, String value)
				throws SecurityThreatGroupActivityNoteExistsException;
	
	/**
	 * Updates a security threat group activity note for the specified offender.
	 * 
	 * @param note security threat group activity note
	 */		
	SecurityThreatGroupActivityNote updateNote(
			SecurityThreatGroupActivityNote note,
			Date date, String value)
				throws SecurityThreatGroupActivityNoteExistsException;
	
	/**
	 * Removes a security threat group activity note for the specified offender.
	 * 
	 * @param note security threat group activity note
	 */	
	void removeNote(SecurityThreatGroupActivityNote note);
	
	/**
	 * Returns a list of security threat group activity involvements.
	 * 
	 * @return list of security threat group activity involvements
	 */
	List<SecurityThreatGroupActivityInvolvement> findInvolvements(
			SecurityThreatGroupActivity activity);
	
	/**
	 * Returns a list of security threat group activity notes.
	 * 
	 * @return list of security threat group activity notes
	 */
	List<SecurityThreatGroupActivityNote> findNotes(
			SecurityThreatGroupActivity activity);	
}