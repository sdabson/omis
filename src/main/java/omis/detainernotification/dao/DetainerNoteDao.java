package omis.detainernotification.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.detainernotification.domain.Detainer;
import omis.detainernotification.domain.DetainerNote;

/**
 * Detainer note data access object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 16, 2017)
 * @since OMIS 3.0
 */
public interface DetainerNoteDao extends GenericDao<DetainerNote> {
	
	/**
	 * Returns all detainer notes for the specified detainer.
	 * 
	 * @param detainer detainer
	 * @return list of detainer notes
	 */
	List<DetainerNote> findNotesByDetainer(Detainer detainer);

	/**
	 * Returns the detainer note with the specified detainer, date, and value.
	 * 
	 * @param detainer
	 * @param value
	 * @param date
	 * @return
	 */
	DetainerNote find(Detainer detainer, String value, Date date);
	
	/**
	 * Returns the detainer note with the specified detainer, date, and value,
	 * excluding the specified detainer note.
	 * 
	 * @param detainer detainer
	 * @param value value
	 * @param date date
	 * @param note detainer note to exclude
	 * @return matching detainer note
	 */
	DetainerNote findExcluding(Detainer detainer, String value, Date date,
			DetainerNote note);
}