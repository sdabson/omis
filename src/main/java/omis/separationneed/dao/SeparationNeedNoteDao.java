package omis.separationneed.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.separationneed.domain.SeparationNeed;
import omis.separationneed.domain.SeparationNeedNote;

/**
 * Separation need note data access object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 18, 2015)
 * @since OMIS 3.0
 */
public interface SeparationNeedNoteDao extends GenericDao<SeparationNeedNote> {
	
	/**
	 * Returns a list of separation need notes with the specified separation
	 * need.
	 * 
	 * @param separationNeed separation need
	 * @return list of separation need notes
	 */
	List<SeparationNeedNote> findBySeparationNeed(
			SeparationNeed separationNeed);
	
	/**
	 * Returns the separation need note with the matching properties.
	 * 
	 * @param note note
	 * @param date date
	 * @param separationNeed separation need
	 * @return separation need
	 */
	SeparationNeedNote find(String note, Date date,
			SeparationNeed separationNeed);
	
	/**
	 * Returns the separation need note with the matching properties, excluding
	 * the specified separation need.
	 * 
	 * @param note note
	 * @param date date
	 * @param separationNeed separation need
	 * @param separationNeedNote separation need note
	 * @return separation need note
	 */
	SeparationNeedNote findExcluding(String note, Date date,
			SeparationNeed separationNeed,
			SeparationNeedNote separationNeedNote);
}