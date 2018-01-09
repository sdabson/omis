package omis.stg.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.stg.domain.SecurityThreatGroupActivity;
import omis.stg.domain.SecurityThreatGroupActivityNote;

/**
 * Data access object for security threat group activity note.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Nov 22, 2016)
 * @since OMIS 3.0
 */
public interface SecurityThreatGroupActivityNoteDao
		extends GenericDao<SecurityThreatGroupActivityNote> {

	/**
	 * Returns a list of security threat group activity notes.
	 * 
	 * @param SecurityThreatGroupActivity - activity
	 * @return list of security threat group activity notes.
	 */
	List<SecurityThreatGroupActivityNote> 
		findNotes(SecurityThreatGroupActivity activity);
	
	/**
	 * Finds security threat group activity notes excluding the specified 
	 * activity note.
	 * 
	 * @param SecurityThreatGroupActivity - activity
	 * @param Date - date
	 * @param String - value
	 * 
	 * @return Finds a security threat group activity.
	 */
	SecurityThreatGroupActivityNote findExcluding( 
			SecurityThreatGroupActivityNote excludedNote, 
			Date date, String value);
	
	/**
	 * Finds security threat group activity note.
	 * 
	 * @param Date - date
	 * @param String - value
	 * 
	 * @return Finds a security threat group activity.
	 */
	SecurityThreatGroupActivityNote findNote(
			SecurityThreatGroupActivity activity, Date date, String value);
}
