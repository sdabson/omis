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
import omis.task.dao.TaskTemplateParameterValueDao;
import omis.task.domain.TaskTemplate;
import omis.task.domain.TaskTemplateParameterValue;

/**
 * Delegate for task template parameter values.
 *
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.0.2
 * @since OMIS 3.0
 */
public class TaskTemplateParameterValueDelegate {

	private final InstanceFactory<TaskTemplateParameterValue>
	taskTemplateParameterValueInstanceFactory;
	
	private final TaskTemplateParameterValueDao taskTemplateParameterValueDao;
	
	/**
	 * Instantiates delegate for task template parameter value.
	 * 
	 * @param taskTemplateParameterValueInstanceFactory instance factory for
	 * task template parameter value
	 * @param taskTemplateParameterValueDao data access object for task template
	 * parameter value
	 */
	public TaskTemplateParameterValueDelegate(
			final InstanceFactory<TaskTemplateParameterValue>
				taskTemplateParameterValueInstanceFactory,
			final TaskTemplateParameterValueDao taskTemplateParameterValueDao) {
		this.taskTemplateParameterValueInstanceFactory
			= taskTemplateParameterValueInstanceFactory;
		this.taskTemplateParameterValueDao = taskTemplateParameterValueDao;
	}
	
	/**
	 * Creates parameter value.
	 * 
	 * @param template template
	 * @param order order
	 * @param entityName entity name
	 * @return created parameter value
	 * @throws DuplicateEntityFoundException if parameter value exists
	 */
	public TaskTemplateParameterValue create(
			final TaskTemplate template, final Short order,
			final String entityName)
				throws DuplicateEntityFoundException {
		if (this.taskTemplateParameterValueDao.find(
				template, order, entityName) != null) {
			throw new DuplicateEntityFoundException("Parameter value exists");
		}
		TaskTemplateParameterValue parameterValue
			= this.taskTemplateParameterValueInstanceFactory
				.createInstance();
		parameterValue.setTypeName(entityName);
		parameterValue.setOrder(order);
		parameterValue.setTemplate(template);
		return this.taskTemplateParameterValueDao
				.makePersistent(parameterValue);
	}
	
	/**
	 * Updates parameter value.
	 * 
	 * @param parameterValue parameter value to update
	 * @param template template
	 * @param order order
	 * @param entityName entity name
	 * @return updated parameter value
	 * @throws DuplicateEntityFoundException if parameter value exists
	 */
	public TaskTemplateParameterValue update(
			final TaskTemplateParameterValue parameterValue,
			final TaskTemplate template,
			final Short order,
			final String entityName)
				throws DuplicateEntityFoundException {
		if (this.taskTemplateParameterValueDao.findExcluding(
				template, order, entityName, parameterValue) != null) {
			throw new DuplicateEntityFoundException("Parameter value exists");
		}
		parameterValue.setTypeName(entityName);
		parameterValue.setOrder(order);
		parameterValue.setTemplate(template);
		return this.taskTemplateParameterValueDao
				.makePersistent(parameterValue);
	}
	
	/**
	 * Removes parameter value.
	 * 
	 * @param parameterValue parameter value to remove
	 */
	public void remove(final TaskTemplateParameterValue parameterValue) {
		this.taskTemplateParameterValueDao.makeTransient(parameterValue);
	}
	
	/**
	 * Returns parameter values by template.
	 * 
	 * @param template template
	 * @return parameter values by template 
	 */
	public List<TaskTemplateParameterValue> findByTemplate(
			final TaskTemplate template) {
		return this.taskTemplateParameterValueDao.findByTemplate(template);
	}
}