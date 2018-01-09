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
package omis.task.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.task.domain.Task;
import omis.task.domain.TaskParameterValue;
import omis.task.domain.TaskTemplateParameterValue;

/**
 * Data access object for task parameter value.
 *
 * @author Stephen Abson
 * @author Annie Jacques
 * @author Josh Divine
 * @version 0.0.3
 * @since OMIS 3.0
 */
public interface TaskParameterValueDao
		extends GenericDao<TaskParameterValue> {

	/**
	 * Returns tasks parameter values by task.
	 * 
	 * @param task task
	 * @return task parameter values by task
	 */
	List<TaskParameterValue> findByTask(Task task);
	
	/**
	 * Returns task parameter value.
	 * 
	 * <p>Returns {@code null} if not found.
	 * 
	 * @param task task
	 * @param typeName type name
	 * @param order order
	 * @return task parameter value or {@code null} if not found
	 */
	TaskParameterValue find(Task task, String typeName, Short order);
	
	/**
	 * Returns task parameter value.
	 * 
	 * <p>Returns {@code null} if not found or excluded.
	 * 
	 * @param task task
	 * @param typeName type name
	 * @param order order
	 * @param excludedTaskParameterValues excluded task parameter values
	 * @return task parameter value or {@code null} if not found or excluded
	 */
	TaskParameterValue findExcluding(Task task, String typeName, Short order,
			TaskParameterValue... excludedTaskParameterValues);
	
	/**
	 * Returns a TaskParameterValue found with specified
	 * TaskTemplateParameterValue and Task
	 * @param taskTemplateParameterValue - TaskTemplateParameterValue
	 * @param task - Task
	 * @return TaskParameterValue found with specified
	 * TaskTemplateParameterValue and Task
	 */
	TaskParameterValue findByTaskTemplateParameterValueAndTask(
			TaskTemplateParameterValue taskTemplateParameterValue, Task task);

	/**
	 * Returns task parameter value by task and order.
	 * 
	 * @param task task
	 * @param order order
	 * @return task parameter value by task and order
	 */
	TaskParameterValue findByTaskAndOrder(Task task, Short order);
}