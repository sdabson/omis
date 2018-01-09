package omis.specialneed.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.specialneed.domain.SpecialNeed;
import omis.specialneed.domain.SpecialNeedNote;

/**
 * 
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 31, 2016)
 * @since OMIS 3.0
 */
public interface SpecialNeedNoteDao extends GenericDao<SpecialNeedNote> {
	
	/**
	 * Returns the special need note.
	 *
	 *
	 * @param value value
	 * @param date date
	 * @param specialNeed special need
	 * @return special need
	 */
	SpecialNeedNote find(String value, Date date, SpecialNeed specialNeed);
	
	/**
	 * Returns the special need note excluding the one in view.
	 *
	 *
	 * @param value value
	 * @param date date
	 * @param specialNeed special need
	 * @return special need
	 */
	SpecialNeedNote findExcluding(String value, Date date, 
			SpecialNeed specialNeed, SpecialNeedNote specialNeedNote);
	
	/**
	 * Return a list of special need notes associated with this special need.
	 *
	 *
	 * @param specialNeed special need
	 * @return special need notes
	 */
	List<SpecialNeedNote> findBySpecialNeed(SpecialNeed specialNeed);
}