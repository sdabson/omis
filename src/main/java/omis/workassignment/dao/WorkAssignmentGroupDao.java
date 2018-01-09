package omis.workassignment.dao;

import omis.dao.GenericDao;
import omis.workassignment.domain.WorkAssignmentGroup;

/**
 * Data access object for work assignment group.
 * 
 * @author Yidong Li
 * @author Josh Divine
 * @version 0.1.2 (Jul 18, 2017)
 * @since OMIS 3.0
 */
public interface WorkAssignmentGroupDao
		extends GenericDao<WorkAssignmentGroup> {

	/**
	 * Returns the work assignment group with the specified name.
	 * 
	 * @param name name
	 * @return work assignment group with the specified name
	 */
	WorkAssignmentGroup find(String name);
	
	/**
	 * Returns the work assignment group with the specified name, excluding the 
	 * specified work assignment group.
	 * 
	 * @param name name
	 * @param excludedWorkAssignmentGroup excluded work assignment group
	 * @return work assignment group with the specified name, excluding the 
	 * specified work assignment group
	 */
	WorkAssignmentGroup findExcluding(String name,  
			WorkAssignmentGroup excludedWorkAssignmentGroup);
}