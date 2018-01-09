package omis.workassignment.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.workassignment.domain.WorkAssignmentCategory;
import omis.workassignment.domain.WorkAssignmentGroup;

/**
 * Data access object for work assignment category.
 * 
 * @author Yidong Li
 * @author Josh Divine
 * @version 0.1.2 (Jul 18, 2017)
 * @since OMIS 3.0
 */
public interface WorkAssignmentCategoryDao
		extends GenericDao<WorkAssignmentCategory> {
	/**
	 * Returns a list of fence restriction.
	 * 
	 * @return a list of fence restriction
	 */
	List<WorkAssignmentCategory> findAll();
	
	/**
	 * Returns the work assignment category with the specified name and work 
	 * assignment group.
	 * 
	 * @param name name
	 * @param workAssignmentGroup work assignment group
	 * @return work assignment category  with the specified name and work 
	 * assignment group
	 */
	WorkAssignmentCategory find(String name, 
			WorkAssignmentGroup workAssignmentGroup);
	
	/**
	 * Returns the work assignment category with the specified name and work 
	 * assignment group, excluding the specified work assignment category.
	 * 
	 * @param name name
	 * @param valid valid
	 * @param workAssignmentGroup work assignment group
	 * @param excludedWorkAssignmentCategory excluded work assignment category
	 * @return work assignment category  with the specified name and work 
	 * assignment group, excluding the specified work assignment category
	 */
	WorkAssignmentCategory findExcluding(String name, 
			WorkAssignmentGroup workAssignmentGroup, 
			WorkAssignmentCategory excludedWorkAssignmentCategory);
}