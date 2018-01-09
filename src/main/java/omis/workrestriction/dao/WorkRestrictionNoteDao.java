package omis.workrestriction.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.workrestriction.domain.WorkRestriction;
import omis.workrestriction.domain.WorkRestrictionNote;

/**
 * WorkRestrictionNoteDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 25, 2016)
 *@since OMIS 3.0
 *
 */
public interface WorkRestrictionNoteDao extends GenericDao<WorkRestrictionNote> {
	
	/**
	 * Finds and returns a workRestrictionNote with specified parameters
	 * @param workRestriction
	 * @param date
	 * @param value - String
	 * @return workRestrictionNote with specified parameters
	 */
	WorkRestrictionNote find(WorkRestriction workRestriction, Date date, 
			String value);
	
	/**
	 *  Finds and returns a workRestrictionNote with specified parameters 
	 *  excluding specified workRestrictionNote
	 * @param excludedWorkRestrictionNote - workRestrictionNote to exclude
	 * @param workRestriction
	 * @param date
	 * @param value - String
	 * @return workRestrictionNote with specified parameters 
	 *  excluding specified workRestrictionNote
	 */
	WorkRestrictionNote findExcluding(
			WorkRestrictionNote excludedWorkRestrictionNote,
			WorkRestriction workRestriction, Date date, 
			String value);
	
	/**
	 * Finds and returns a list of all workRestrictionNotes by specified
	 * WorkRestriction
	 * @param workRestriction
	 * @return list of all workRestrictionNotes by specified WorkRestriction
	 */
	List<WorkRestrictionNote> findAllByWorkRestriction(
			WorkRestriction workRestriction);
}
