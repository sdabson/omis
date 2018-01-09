package omis.task.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.task.dao.TaskTemplateDao;
import omis.task.domain.TaskTemplate;
import omis.task.domain.TaskTemplateGroup;

/**
 * Delegate for task templates. 
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class TaskTemplateDelegate {
	
	private final InstanceFactory<TaskTemplate> taskTemplateInstanceFactory;
	
	private final TaskTemplateDao taskTemplateDao;
	
	/**
	 * Delegate for task templates.
	 * 
	 * @param taskTemplateInstanceFactory instance factory for task templates
	 * @param taskTemplateDao data access object for task templates
	 */
	public TaskTemplateDelegate(
			final InstanceFactory<TaskTemplate> taskTemplateInstanceFactory,
			final TaskTemplateDao taskTemplateDao) {
		this.taskTemplateInstanceFactory = taskTemplateInstanceFactory;
		this.taskTemplateDao = taskTemplateDao;
	}

	/**
	 * Creates task template.
	 * 
	 * @param group group
	 * @param name name
	 * @param controllerName controller name
	 * @param methodName method name
	 * @return newly created task template
	 * @throws DuplicateEntityFoundException if template already exists
	 */
	public TaskTemplate create(final TaskTemplateGroup group,
			final String name, final String controllerName,
			final String methodName)
				throws DuplicateEntityFoundException {
		if (this.taskTemplateDao
				.find(group, name, controllerName, methodName) != null) {
			throw new DuplicateEntityFoundException("Template exists");
		}
		TaskTemplate template = this.taskTemplateInstanceFactory
				.createInstance();
		template.setControllerName(controllerName);
		template.setMethodName(methodName);
		template.setGroup(group);
		template.setName(name);
		return this.taskTemplateDao.makePersistent(template);
	}
	
	/**
	 * Updates task template.
	 * 
	 * @param template template to update
	 * @param group group
	 * @param name name
	 * @param controllerName controller name
	 * @param methodName method name
	 * @return updated task template
	 * @throws DuplicateEntityFoundException if template already exists
	 */
	public TaskTemplate update(final TaskTemplate template,
			final TaskTemplateGroup group, final String name,
			final String controllerName, final String methodName)
				throws DuplicateEntityFoundException {
		if (this.taskTemplateDao.findExcluding(
				group, name, controllerName, methodName, template) != null) {
			throw new DuplicateEntityFoundException("Template exists");
		}
		template.setGroup(group);
		template.setName(methodName);
		template.setControllerName(controllerName);
		template.setMethodName(methodName);
		return this.taskTemplateDao.makePersistent(template);
	}
	
	/**
	 * Removes template.
	 * 
	 * @param template template to remove
	 */
	public void remove(final TaskTemplate template) {
		this.taskTemplateDao.makeTransient(template);
	}
	
	/**
	 * Returns templates by group.
	 * 
	 * @param group group
	 * @return templates by group
	 */
	public List<TaskTemplate> findByGroup(final TaskTemplateGroup group) {
		return this.taskTemplateDao.findByGroup(group);
	}
}