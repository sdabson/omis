/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.task.service.delegate;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.task.dao.TaskDao;
import omis.task.domain.Task;
import omis.task.domain.TaskTemplate;
import omis.user.domain.UserAccount;

/**
 * Delegate for tasks.
 *
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.1 (Sep 17, 2018)
 * @since OMIS 3.0
 */
public class TaskDelegate {

	/* Instance factories. */
	
	private final InstanceFactory<Task> taskInstanceFactory;
	
	/* DAOs. */
	
	private final TaskDao taskDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for tasks.
	 * 
	 * @param taskInstanceFactory instance factory for tasks
	 * @param taskDao data access object for tasks
	 */
	public TaskDelegate(
			final InstanceFactory<Task> taskInstanceFactory,
			final TaskDao taskDao) {
		this.taskInstanceFactory = taskInstanceFactory;
		this.taskDao = taskDao;
	}
	
	/* Methods. */
	
	/**
	 * Creates task.
	 * 
	 * @param controllerName controller name
	 * @param methodName method name
	 * @param description description
	 * @param sourceAccount source user account
	 * @param originationDate origination date
	 * @param completionDate completion date
	 * @return created task
	 * @throws DuplicateEntityFoundException if task exists
	 */
	public Task create(
			final String controllerName, final String methodName,
			final String description, final UserAccount sourceAccount,
			final Date originationDate, final Date completionDate)
				throws DuplicateEntityFoundException {
		if (this.taskDao.find(controllerName, methodName, description,
				sourceAccount, originationDate) != null) {
			throw new DuplicateEntityFoundException("Task exists"); 
		}
		Task task = this.taskInstanceFactory.createInstance();
		this.populate(task, controllerName, methodName, description,
				sourceAccount, originationDate, completionDate);
		return this.taskDao.makePersistent(task);
	}
	
	/**
	 * Updates task.
	 * 
	 * @param task task
	 * @param controllerName controller name
	 * @param methodName method name
	 * @param description description
	 * @param sourceAccount source user account
	 * @param originationDate origination date
	 * @param completionDate completion date
	 * @return updated task
	 * @throws DuplicateEntityFoundException if task exists
	 */
	public Task update(
			final Task task, final String controllerName,
			final String methodName, final String description,
			final UserAccount sourceAccount, final Date originationDate,
			final Date completionDate)
				throws DuplicateEntityFoundException {
		if (this.taskDao.findExcluding(controllerName, methodName,
				description, sourceAccount, originationDate, task) != null) {
			throw new DuplicateEntityFoundException("Task exists");
		}
		this.populate(task, controllerName, methodName, description,
				sourceAccount, originationDate, completionDate);
		return this.taskDao.makePersistent(task);
	}
	
	/**
	 * Removes task.
	 * 
	 * @param task task
	 */
	public void remove(final Task task) {
		this.taskDao.makeTransient(task);
	}
	
	/* Helper methods. */
	
	// Populates task
	private void populate(final Task task, final String controllerName,
			final String methodName, final String description,
			final UserAccount sourceAccount, final Date originationDate,
			final Date completionDate) {
		task.setControllerName(controllerName);
		task.setMethodName(methodName);
		task.setDescription(description);
		task.setSourceAccount(sourceAccount);
		task.setOriginationDate(originationDate);
		task.setCompletionDate(completionDate);
	}
	
	/**
	 * Returns a list of Tasks found by specified TaskTemplate
	 * @param taskTemplate - TaskTemplate
	 * @return List of tasks found by specified TaskTemplate
	 */
	public List<Task> findByTaskTemplate(TaskTemplate taskTemplate){
		return this.taskDao.findByTaskTemplate(taskTemplate);
	}

	/**
	 * Sets the completion date for a task.
	 * 
	 * @param task task
	 * @param completionDate completion date
	 * @return task
	 */
	public Task complete(final Task task, final Date completionDate) {
		task.setCompletionDate(completionDate);
		return this.taskDao.makePersistent(task);
	}
}