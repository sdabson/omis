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

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.task.dao.TaskParameterValueDao;
import omis.task.domain.Task;
import omis.task.domain.TaskParameterValue;
import omis.task.domain.TaskTemplateParameterValue;

/**
 * Delegate for task parameter values.
 *
 * @author Stephen Abson
 * @author Annie Jacques
 * @author Josh Divine
 * @version 0.0.3
 * @since OMIS 3.0
 */
public class TaskParameterValueDelegate {

	/* Instance factories. */
	
	private final InstanceFactory<TaskParameterValue>
	taskParameterValueInstanceFactory;
	
	/* Data access objects. */
	
	private final TaskParameterValueDao taskParameterValueDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for task parameter values.
	 * 
	 * @param taskParameterInstanceFactory instance factory for task
	 * parameter values
	 * @param taskParameterValueDao data access object for task parameter values
	 */
	public TaskParameterValueDelegate(
			final InstanceFactory<TaskParameterValue>
			taskParameterValueInstanceFactory,
			final TaskParameterValueDao taskParameterValueDao) {
		this.taskParameterValueInstanceFactory
			= taskParameterValueInstanceFactory;
		this.taskParameterValueDao = taskParameterValueDao;
	}
	
	/* Methods. */
	
	/**
	 * Returns parameter values by task.
	 * 
	 * @param task task
	 * @return parameter values by task
	 */
	public List<TaskParameterValue> findByTask(final Task task) {
		return this.taskParameterValueDao.findByTask(task);
	}
	
	/**
	 * Creates task parameter value.
	 * 
	 * @param task task
	 * @param typeName entity name
	 * @param instanceValue instance value
	 * @param order order
	 * @return created task parameter value
	 * @throws DuplicateEntityFoundException if task parameter value exists
	 */
	public TaskParameterValue create(
			final Task task,
			final String typeName,
			final String instanceValue,
			final Short order)
				throws DuplicateEntityFoundException {
		if (this.taskParameterValueDao.find(task, typeName, order) != null) {
			throw new DuplicateEntityFoundException(
					"Task parameter value exists");
		}
		TaskParameterValue taskParameterValue
			= this.taskParameterValueInstanceFactory.createInstance();
		taskParameterValue.setTask(task);
		populate(taskParameterValue, instanceValue, typeName, order);
		return this.taskParameterValueDao.makePersistent(taskParameterValue);
	}
	
	/**
	 * Updates task parameter value.
	 * 
	 * @param taskParameterValue task parameter value
	 * @param typeName type name
	 * @param instanceValue instance value
	 * @param order order
	 * @return updated task parameter value
	 * @throws DuplicateEntityFoundException if task parameter value exists
	 */
	public TaskParameterValue update(
			final TaskParameterValue taskParameterValue,
			final String typeName,
			final String instanceValue,
			final Short order)
				throws DuplicateEntityFoundException {
		if (this.taskParameterValueDao.findExcluding(
				taskParameterValue.getTask(), typeName, order,
				taskParameterValue) != null) {
			throw new DuplicateEntityFoundException(
					"Task parameter value exists");
		}
		this.populate(taskParameterValue, instanceValue, typeName, order);
		return this.taskParameterValueDao.makePersistent(taskParameterValue);
	}
	
	/**
	 * Removes task parameter value.
	 * 
	 * @param taskParameterValue task parameter value to remove
	 */
	public void remove(final TaskParameterValue taskParameterValue) {
		this.taskParameterValueDao.makeTransient(taskParameterValue);
	}
	
	/**
	 * Returns a TaskParameterValue found with specified
	 * TaskTemplateParameterValue and Task
	 * @param taskTemplateParameterValue - TaskTemplateParameterValue
	 * @param task - Task
	 * @return TaskParameterValue found with specified
	 * TaskTemplateParameterValue and Task
	 */
	public TaskParameterValue findByTaskTemplateParameterValueAndTask(
			final TaskTemplateParameterValue taskTemplateParameterValue,
			final Task task) {
		return this.taskParameterValueDao
				.findByTaskTemplateParameterValueAndTask(
						taskTemplateParameterValue, task);
	}
	
	/**
	 * Returns by task and order.
	 * 
	 * @param task task
	 * @param order order
	 * @return parameter value with task and order
	 */
	public TaskParameterValue findByTaskAndOrder(
			final Task task, final Short order) {
		return this.taskParameterValueDao.findByTaskAndOrder(task, order);
	}
	
	/* Helper methods. */
	
	// Populates task parameter value
	private void populate(final TaskParameterValue taskParameterValue,
			final String instanceValue, final String typeName, 
			final Short order) {
		taskParameterValue.setInstanceValue(instanceValue);
		taskParameterValue.setTypeName(typeName);
		taskParameterValue.setOrder(order);
	}
}