package omis.victim.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.person.domain.Person;
import omis.victim.domain.VictimAssociation;
import omis.victim.domain.VictimNote;
import omis.victim.domain.VictimNoteCategory;

/**
 * Data access object for victim notes.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jul 22, 2015)
 * @since OMIS 3.0
 */
public interface VictimNoteDao
		extends GenericDao<VictimNote> {

	/**
	 * Finds victim note.
	 * 
	 * @param victim victim
	 * @param category category
	 * @param date date
	 * @return victim note
	 */
	VictimNote find(Person victim, VictimNoteCategory category, Date date);
	
	/**
	 * Finds victim note excluding notes.
	 * 
	 * @param victim victim
	 * @param category category
	 * @param date date
	 * @param excludedNotes notes to exclude
	 * @return victim note existing notes
	 */
	VictimNote findExcluding(Person victim, VictimNoteCategory category,
			Date date, VictimNote... excludedNotes);

	/**
	 * Returns victim notes by victim
	 * 
	 * @param victim victim
	 * @return victim notes by victim
	 */
	List<VictimNote> findByVictim(Person victim);

	/**
	 * Returns victim notes by association.
	 * 
	 * @param association association
	 * @return victim notes by association
	 */
	List<VictimNote> findByAssociation(VictimAssociation association);
	
	/**
	 * Returns count of notes by association.
	 * 
	 * @param association association
	 * @return count of notes by association
	 */
	long countNotes(VictimAssociation association);
}