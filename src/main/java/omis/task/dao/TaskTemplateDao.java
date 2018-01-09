package omis.task.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.task.domain.TaskTemplate;
import omis.task.domain.TaskTemplateGroup;

/**
 * Data access object for task templates.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface TaskTemplateDao
		extends GenericDao<TaskTemplate> {

	/**
	 * Returns templates by group.
	 * 
	 * @param group group
	 * @return templates by group
	 */
	List<TaskTemplate> findByGroup(TaskTemplateGroup group);
	
	/**
	 * Returns task template.
	 * 
	 * @param group group
	 * @param name name
	 * @param controllerName controller name
	 * @param methodName method name
	 * @return task template
	 */
	TaskTemplate find(
			TaskTemplateGroup group, String name, String controllerName,
			String methodName);
	
	/**
	 * Returns task template.
	 * 
	 * @param group group
	 * @param name name
	 * @param controllerName controller name
	 * @param methodName method name
	 * @param excludedTaskTemplates templates to exclude
	 * @return task template
	 */
	TaskTemplate findExcluding(
			TaskTemplateGroup group, String name, String controllerName,
			String methodName, TaskTemplate... excludedTaskTemplates);
}