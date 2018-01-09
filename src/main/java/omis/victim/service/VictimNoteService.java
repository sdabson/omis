package omis.victim.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.person.domain.Person;
import omis.victim.domain.VictimAssociation;
import omis.victim.domain.VictimNote;
import omis.victim.domain.VictimNoteCategory;

/**
 * Service for victim notes.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jul 22, 2015)
 * @since OMIS 3.0
 */
public interface VictimNoteService {

	/**
	 * Creates victim note.
	 * 
	 * @param victim victim
	 * @param category category
	 * @param association association
	 * @param date date
	 * @param value value
	 * @return created victim note
	 * @throws DuplicateEntityFoundException if victim note exists
	 */
	VictimNote create(Person victim, VictimNoteCategory category,
			VictimAssociation association, Date date, String value)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates victim note.
	 * 
	 * @param victimNote victim note to update
	 * @param category category
	 * @param association association
	 * @param date date
	 * @param value value
	 * @return updated victim note
	 * @throws DuplicateEntityFoundException if victim note exists
	 */
	VictimNote update(VictimNote victimNote, VictimNoteCategory category,
			VictimAssociation association, Date date, String value)
					throws DuplicateEntityFoundException;
	
	/**
	 * Returns victim notes by victim.
	 * 
	 * @param victim victim
	 * @return victim notes by victim
	 */
	List<VictimNote> findByVictim(Person victim);
	
	/**
	 * Returns associations of victim.
	 * 
	 * @param victim victim
	 * @return associations of victim
	 */
	List<VictimAssociation> findAssociationsForVictim(Person victim);
	
	/**
	 * Returns categories.
	 * 
	 * @return categories
	 */
	List<VictimNoteCategory> findCategories();
	
	/**
	 * Removes victim note.
	 * 
	 * @param victimNote victim note to remove
	 */
	void remove(VictimNote victimNote);
}