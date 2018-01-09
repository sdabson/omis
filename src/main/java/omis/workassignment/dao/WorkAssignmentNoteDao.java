package omis.workassignment.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.workassignment.domain.WorkAssignment;
import omis.workassignment.domain.WorkAssignmentNote;

/**
 * Data access object for work assignment note.
 * 
 * @author Yidong Li
 * @version 0.1.1 (Aug 18, 2016)
 * @since OMIS 3.0
 */
public interface WorkAssignmentNoteDao
		extends GenericDao<WorkAssignmentNote> {
	/**
	 * Returns a list of work assignment notes.
	 * @param workAssignment work assignment
	 * @return a list of work assignment notes.
	 */
	List<WorkAssignmentNote> findNotes(WorkAssignment workAssignment);
	
	/**
	 * Returns an existing work assignment note.
	 * @param workAssignment work assignment
	 * @param date date 
	 * @param value value
	 * @return an existing work assignment note.
	 */
	WorkAssignmentNote findExistingWorkAssignmentNote(
		WorkAssignment workAssignment, Date date, String value);
	
	/**
	 * Returns the existing work assignment excluding the passed in "work assignment"
	 * if it matches the criteria.
	 * 
	 * @param workAssignment work assignment
	 * @param workAssignmentNote work assignment note
	 * @param value value
	 * @param date date
	 * @return work assignment
	 */
	WorkAssignmentNote findExcludedExistingWorkAssignmentNote(
		WorkAssignmentNote workAssignmentNote, WorkAssignment workAssignment, 
		Date date, String value);
}