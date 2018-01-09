package omis.stg.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.stg.domain.SecurityThreatGroupActivity;
import omis.stg.domain.SecurityThreatGroupActivityInvolvement;
import omis.stg.domain.SecurityThreatGroupActivityNote;
import omis.stg.exception.InvolvedOffenderRequiredException;

/**
 * Security threat group activity.
 * 
 * @author Trevor Isles
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
	 * @throws DuplicateEntityFoundException if new activity already exists.
	 */
	SecurityThreatGroupActivity create(Date reportDate, 
			Person reportedBy, String summary) 
			throws DuplicateEntityFoundException;

	/**
	 * Updates a security threat group activity for the specified offender.
	 * 
	 * @param activity - security threat group activity
	 * @param reportedBy - person reporting
	 * @param reportDate - date of report
	 * @param summary - summary of activity
	 * @return updates a security threat group activity
	 * @throws DuplicateEntityFoundException thrown when a duplicate security
	 * threat group activity is found.
	 */	
	SecurityThreatGroupActivity update(SecurityThreatGroupActivity activity, 
			Person reportedBy, Date reportDate, String summary)
				throws DuplicateEntityFoundException;
	
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
	 * @throws DuplicateEntityFoundException thrown when a duplicate offender
	 * is found.
	 */
	SecurityThreatGroupActivityInvolvement involveOffender(
			Offender offender, SecurityThreatGroupActivity activity, 
			String narrative) throws DuplicateEntityFoundException;
	
	/**
	 * Updates a security threat group activity involvement for the specified 
	 * offender.
	 * 
	 * @param involvement - security threat group activity involvement
	 * @param narrative - narrative of the involvement
	 *
	 * @return updates a security threat group activity involvement
	 * @throws DuplicateEntityFoundException thrown when a duplicate security
	 * threat group activity involvement is found.
	 */	
	SecurityThreatGroupActivityInvolvement updateInvolvementNarrative(
			SecurityThreatGroupActivityInvolvement involvement, 
			String narrative)
				throws DuplicateEntityFoundException;
	
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
	 * @throws DuplicateEntityFoundException if new security threat group 
	 * activity note already exists.
	 */
	SecurityThreatGroupActivityNote addNote(SecurityThreatGroupActivity activity,
			Date date, String value)
				throws DuplicateEntityFoundException;
	
	/**
	 * Updates a security threat group activity note for the specified offender.
	 * 
	 * @param note security threat group activity note
	 */		
	SecurityThreatGroupActivityNote updateNote(
			SecurityThreatGroupActivityNote note,
			Date date, String value)
				throws DuplicateEntityFoundException;
	
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
