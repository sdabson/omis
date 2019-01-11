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
package omis.task.service.impl;

import java.util.Date;
import java.util.List;

import omis.task.domain.Task;
import omis.task.domain.TaskAssignment;
import omis.task.domain.TaskParameterValue;
import omis.task.service.TaskInvocationService;
import omis.task.service.delegate.TaskAssignmentDelegate;
import omis.task.service.delegate.TaskDelegate;
import omis.task.service.delegate.TaskParameterValueDelegate;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;

/**
 * Implementation of service to invoke tasks.
 *
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.1 (Sep 13, 2018)
 * @since OMIS 3.0
 */
public class TaskInvocationServiceImpl
		implements TaskInvocationService {
	
	private final TaskParameterValueDelegate taskParameterValueDelegate;
	
	private final TaskAssignmentDelegate taskAssignmentDelegate;
	
	private final TaskDelegate taskDelegate;

	private final UserAccountDelegate userAccountDelegate;
	
	/**
	 * Instantiates implementation of service to invoke tasks.
	 * 
	 * @param taskParameterValueDelegate delegate for task parameter value
	 */
	public TaskInvocationServiceImpl(
			final TaskParameterValueDelegate taskParameterValueDelegate,
			final TaskAssignmentDelegate taskAssignmentDelegate,
			final TaskDelegate taskDelegate,
			final UserAccountDelegate userAccountDelegate) {
		this.taskParameterValueDelegate = taskParameterValueDelegate;
		this.taskAssignmentDelegate = taskAssignmentDelegate;
		this.taskDelegate = taskDelegate;
		this.userAccountDelegate = userAccountDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<TaskParameterValue> findParameterValues(final Task task) {
		return this.taskParameterValueDelegate.findByTask(task);
	}

	/** {@inheritDoc} */
	@Override
	public TaskParameterValue findParameterValueByOrder(
			final Task task, final Short order) {
		return this.taskParameterValueDelegate.findByTaskAndOrder(task, order);
	}
	
	/** {@inheritDoc} */
	@Override
	public Task completeTask(final Task task, 
			final Date completionDate) {
		return this.taskDelegate.complete(task, completionDate);
	}

	/** {@inheritDoc} */
	@Override
	public TaskAssignment findTaskAssignmentByTaskAndAssignee(final Task task,
			final UserAccount assignee) {
		return this.taskAssignmentDelegate.findByTaskAndAssignee(task, 
				assignee);
	}

	/** {@inheritDoc} */
	@Override
	public TaskAssignment updateTaskAssignmentLastInvoked(
			final TaskAssignment taskAssignment, final Date lastInvoked) {
		return this.taskAssignmentDelegate.updateLastInvoked(taskAssignment, 
				lastInvoked);
	}

	/** {@inheritDoc} */
	@Override
	public UserAccount findUserAccountByUsername(final String username) {
		return this.userAccountDelegate.findByUsername(username);
	}
}