package omis.workassignment.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.workassignment.domain.WorkAssignment;
import omis.workassignment.domain.WorkAssignmentCategory;

/**
 * Data access object for work assignment.
 * 
 * @author Yidong Li
 * @version 0.1.1 (Aug 18, 2016)
 * @since OMIS 3.0
 */
public interface WorkAssignmentDao
		extends GenericDao<WorkAssignment> {
	/**
	 * Returns a list of work assignment.
	 * 
	 * @param offender offender
	 * @return a list of work assignment
	 */
	List<WorkAssignment> findByOffender(Offender offender);
	
	/**
	 * Returns the existing work assignment if it matches the criteria.
	 * 
	 * @param category work assignment category
	 * @param assignedDate assigned date
	 * @param offender offender
	 * @return work assignment
	 */
	
	WorkAssignment findExistingWorkAssignment(Offender offender, 
		WorkAssignmentCategory category, Date assignedDate); 
	
	/**
	 * Returns the existing work assignment excluding the passed in "work assignment"
	 * if it matches the criteria.
	 * 
	 * @param category work assignment category
	 * @param assignedDate assigned date
	 * @param offender offender
	 * @return work assignment
	 */
	WorkAssignment findExcludedExistingWorkAssignment(WorkAssignment workAssignment, 
		Offender offender, WorkAssignmentCategory category,	
		Date assignedDate);
	
	/**
	 * Returns the existing work assignment if it is within the date range.
	 * 
	 * @param terminationDate termination date 
	 * @param assignedDate assigned date
	 * @param offender offender
	 * @return List of work assignments
	 */
	List<WorkAssignment> findExistingWorkAssignmentByDate(Offender offender, 
		Date assignedDate);
}