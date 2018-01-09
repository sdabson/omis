package omis.hearing.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.hearing.domain.Hearing;
import omis.hearing.domain.HearingNote;

/**
 * HearingNoteDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Dec 27, 2016)
 *@since OMIS 3.0
 *
 */
public interface HearingNoteDao extends GenericDao<HearingNote> {
	
	/**
	 * Returns a hearing note with specified properties
	 * @param hearing - Hearing
	 * @param description - String
	 * @param date - Date
	 * @return hearing note with specified properties
	 */
	HearingNote find(Hearing hearing, String description, Date date);
	
	/**
	 * Returns a hearing note with specified properties excluding specified 
	 * HearingNote
	 * @param hearing - Hearing
	 * @param description - String
	 * @param date - Date
	 * @param hearingNote - HearingNote to exclude
	 * @return hearing note with specified properties excluding specified 
	 * HearingNote
	 */
	HearingNote findExcluding(Hearing hearing, String description, Date date,
			HearingNote hearingNote);
	
	/**
	 * Returns a list of all HearingNotes by hearing
	 * @param hearing
	 * @return list of all HearingNotes by hearing
	 */
	List<HearingNote> findByHearing(Hearing hearing);
	
	
}
