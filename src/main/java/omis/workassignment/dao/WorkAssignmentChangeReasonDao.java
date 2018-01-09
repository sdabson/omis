package omis.workassignment.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.workassignment.domain.WorkAssignmentChangeReason;

/**
 * Data access object for work assignment change reason.
 * 
 * @author Yidong Li
 * @author Josh Divine
 * @version 0.1.2 (Jul 18, 2017)
 * @since OMIS 3.0
 */
public interface WorkAssignmentChangeReasonDao
		extends GenericDao<WorkAssignmentChangeReason> {
	
	/**
	 * Returns a list of work assignment change reasons.
	 * 
	 * @return a list of work assignment change reasons.
	 */
	List<WorkAssignmentChangeReason> findAll();
	
	/**
	 * Returns the work assignment change reason with the specified name.
	 * 
	 * @param name name
	 * @return work assignment change reason with the specified name 
	 */
	WorkAssignmentChangeReason find(String name);
	
	/**
	 * Returns the work assignment change reason with the specified name, 
	 * excluding the specified work assignment change reason.
	 * 
	 * @param name name
	 * @param excludedWorkAssignmentChangeReason excluded work assignment change 
	 * reason
	 * @return work assignment change reason with the specified name, excluding 
	 * the specified work assignment change reason
	 */
	WorkAssignmentChangeReason findExcluding(String name,  
			WorkAssignmentChangeReason excludedWorkAssignmentChangeReason);
}