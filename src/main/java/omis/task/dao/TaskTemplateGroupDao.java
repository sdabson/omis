package omis.task.dao;

import omis.dao.GenericDao;
import omis.task.domain.TaskTemplateGroup;

/**
 * Data access object for task template groups.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface TaskTemplateGroupDao
		extends GenericDao<TaskTemplateGroup> {

	/**
	 * Returns group.
	 * 
	 * @param name name
	 * @return group
	 */
	TaskTemplateGroup find(String name);
	
	/**
	 * Returns group.
	 * 
	 * @param name name
	 * @param excludedGroups groups to exclude
	 * @return group
	 */
	TaskTemplateGroup findExcluding(
			String name, TaskTemplateGroup... excludedGroups);
}