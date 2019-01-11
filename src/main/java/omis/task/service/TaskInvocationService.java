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
package omis.task.service;

import java.util.Date;
import java.util.List;

import omis.task.domain.Task;
import omis.task.domain.TaskAssignment;
import omis.task.domain.TaskParameterValue;
import omis.user.domain.UserAccount;

/**
 * Service for task invocation.
 *
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.1 (Sep 13, 2018)
 * @since OMIS 3.0
 */
public interface TaskInvocationService {

	/**
	 * Returns parameter values for task.
	 * 
	 * @param task task
	 * @return parameter values for task
	 */
	List<TaskParameterValue> findParameterValues(Task task);

	/**
	 * Returns task parameter value for task with order. 
	 * 
	 * @param task task
	 * @param order order
	 * @return task parameter value for task with order
	 */
	TaskParameterValue findParameterValueByOrder(Task task, Short order);
	
	/** Complete Task.
	 * @param completion Date.
	 * @return task. */
	Task completeTask(Task task, Date completionDate);

	/**
	 * Returns task assignment for the task and assignee.
	 * 
	 * @param task task
	 * @param assignee assignee
	 * @return task assignment
	 */
	TaskAssignment findTaskAssignmentByTaskAndAssignee(Task task, 
			UserAccount assignee);
	
	/**
	 * Updates the last invoked date for the task assignment.
	 * 
	 * @param taskAssignment task assignment
	 * @param lastInvoked last invoked
	 * @return task assignment
	 */
	TaskAssignment updateTaskAssignmentLastInvoked(
			TaskAssignment taskAssignment, Date lastInvoked);

	/**
	 * Returns the user account for the specified user name.
	 * 
	 * @param username user name
	 * @return user account
	 */
	UserAccount findUserAccountByUsername(String username);
}