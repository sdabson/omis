package omis.workassignment.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.workassignment.domain.FenceRestriction;
import omis.workassignment.domain.WorkAssignment;
import omis.workassignment.domain.WorkAssignmentCategory;
import omis.workassignment.domain.WorkAssignmentChangeReason;
import omis.workassignment.domain.WorkAssignmentNote;

/**
 * Service for work assignment.
 * 
 * @author Yidong Li
 * @version 0.1.1 (Aug 18, 2016)
 * @since OMIS 3.0
 */
public interface WorkAssignmentService {

	/**
	 * Returns work assignment.
	 * 
	 * @param offender offender
	 * @param fenceRestriction fence restriction
	 * @param category work assignment category
	 * @param changeReason work assignment change reason
	 * @param asignedDate assigned date
	 * @param terminationDate termination date
	 * @param comments comments
	 * @return workAssignment
	 * @throws DuplicateEntityFoundException if work assignment already exists
	 */
	WorkAssignment create(Offender offender, FenceRestriction fenceRestriction,
		WorkAssignmentCategory category, WorkAssignmentChangeReason changeReason,
		Date assignedDate, Date terminationDate, String notes, Boolean endExisting)
			throws DuplicateEntityFoundException;
	
	/**
	 * Returns work assignment.
	 * 
	 * @param workAssignment work assignment
	 * @param fenceRestriction fence restriction
	 * @param category work assignment category
	 * @param changeReason work assignment change reason
	 * @param assignedDate assigned date
	 * @param terminationDate termination date
	 * @param comments comments
	 * @return work assignment
	 * @throws DuplicateEntityFoundException if work assignment already exists
	 */
	WorkAssignment update(WorkAssignment workAssignment, FenceRestriction 
		fenceRestriction, WorkAssignmentCategory category, 
		WorkAssignmentChangeReason changeReason, Date assignedDate, 
		Date terminationDate, String notes, Boolean endExisting)
			throws DuplicateEntityFoundException;
	
	/**
	 * Returns a list of work assignment.
	 * @param offender offender
	 * @return a list of work assignment
	 */
	List<WorkAssignment> findByOffender(Offender offender);
	
	/**
	 * Returns void 
	 * @return void
	 */
	void remove(WorkAssignment workAssignment);
	
	/**
	 * Returns a list of fence restriction.
	 * 
	 * @return a list of fence restriction
	 */
	List<FenceRestriction> findFenceRestrictions();
	
	/**
	 * Returns a list of work assignment categories.
	 * 
	 * @return a list of work assignment categories
	 */
	List<WorkAssignmentCategory> findCategories();
	
	/**
	 * Returns a list of work assignment change reason
	 * 
	 * @return a list of work assignment
	 */
	List<WorkAssignmentChangeReason> findChangeReasons();
	
	/**
	 * Returns a work assignment note.
	 * 
	 * @param workAssignment work assignment
	 * @param date date
	 * @param notes notes
	 * @return a work assignment note
	 * @throws DuplicateEntityFoundException if work assignment note exists
	 */
	WorkAssignmentNote addNote(WorkAssignment workAssignment, Date date, 
		String value)throws DuplicateEntityFoundException;

	/**
	 * Returns work assignment note.
	 * @param workAssignmentNote work assignment note
	 * @param date date
	 * @param notes notes
	 * @return a work assignment note
	 * @throws DuplicateEntityFoundException if work assignment note exists
	 */
	WorkAssignmentNote updateNote(WorkAssignmentNote note, Date date, 
		String value)throws DuplicateEntityFoundException;
	
	/**
	 * Returns void.
	 * 
	 * @param note work assignment note
	 * @return void
	 */
	void removeNote(WorkAssignmentNote note);
	
	/**
	 * Return a list of work assignment notes.
	 * 
	 * @param workAssignment work assignment
	 * @return a list of work assignments
	 */
	List<WorkAssignmentNote> findNotes(WorkAssignment workAssignment);
}