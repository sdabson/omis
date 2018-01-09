package omis.task.service.delegate;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.task.dao.TaskTemplateGroupDao;
import omis.task.domain.TaskTemplateGroup;

/**
 * Delegate for task template group.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class TaskTemplateGroupDelegate {

	private final InstanceFactory<TaskTemplateGroup>
	taskTemplateGroupInstanceFactory;
	
	private final TaskTemplateGroupDao taskTemplateGroupDao;
	
	/**
	 * Instantiates delegate for task template groups.
	 * 
	 * @param taskTemplateGroupInstanceFactory instance factory for task
	 * template groups
	 * @param taskTemplateGroupDao data access object for task template groups
	 */
	public TaskTemplateGroupDelegate(
			final InstanceFactory<TaskTemplateGroup>
				taskTemplateGroupInstanceFactory,
			final TaskTemplateGroupDao taskTemplateGroupDao) {
		this.taskTemplateGroupInstanceFactory
			= taskTemplateGroupInstanceFactory;
		this.taskTemplateGroupDao = taskTemplateGroupDao;
	}
	
	/**
	 * Creates group.
	 * 
	 * @param name name
	 * @return created group
	 * @throws DuplicateEntityFoundException if group exists
	 */
	public TaskTemplateGroup create(final String name)
			throws DuplicateEntityFoundException {
		if (this.taskTemplateGroupDao.find(name) != null) {
			throw new DuplicateEntityFoundException("Group exists");
		}
		TaskTemplateGroup group = this.taskTemplateGroupInstanceFactory
				.createInstance();
		group.setName(name);
		return this.taskTemplateGroupDao.makePersistent(group);
	}
	
	/**
	 * Updates group.
	 * 
	 * @param group group to update
	 * @param name name
	 * @return updated group
	 * @throws DuplicateEntityFoundException if group exists
	 */
	public TaskTemplateGroup update(final TaskTemplateGroup group,
			final String name)
				throws DuplicateEntityFoundException {
		if (this.taskTemplateGroupDao.findExcluding(name, group) != null) {
			throw new DuplicateEntityFoundException("Group exists");
		}
		group.setName(name);
		return this.taskTemplateGroupDao.makePersistent(group);
	}
	
	/**
	 * Removes group.
	 * 
	 * @param group group to remove
	 */
	public void remove(final TaskTemplateGroup group) {
		this.taskTemplateGroupDao.makeTransient(group);
	}
}