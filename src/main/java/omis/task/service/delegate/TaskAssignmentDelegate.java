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
import omis.task.dao.TaskAssignmentDao;
import omis.task.domain.Task;
import omis.task.domain.TaskAssignment;
import omis.user.domain.UserAccount;

/**
 * Delegate for task assignments.
 *
 * @author Stephen Abson
 * @author Annie Wahl
 * @author Josh Divine
 * @version 0.1.2 (Sep 13, 2018)
 * @since OMIS 3.0
 */
public class TaskAssignmentDelegate {

	/* Data access objects. */
	
	private final InstanceFactory<TaskAssignment> taskAssignmentInstanceFactory;
	
	private final TaskAssignmentDao taskAssignmentDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for task assignments.
	 * 
	 * @param taskAssignmentInstanceFactory task assignment instance factory
	 * @param taskAssignmentDao data access object for task assignments
	 */
	public TaskAssignmentDelegate(
			final InstanceFactory<TaskAssignment> taskAssignmentInstanceFactory,
			final TaskAssignmentDao taskAssignmentDao) {
		this.taskAssignmentInstanceFactory = taskAssignmentInstanceFactory;
		this.taskAssignmentDao = taskAssignmentDao;
	}
	
	/* Methods. */
	
	/**
	 * Returns assignments by task.
	 * 
	 * @param task task
	 * @return assignments by task
	 */
	public List<TaskAssignment> findByTask(final Task task) {
		return this.taskAssignmentDao.findByTask(task);
	}
	
	/**
	 * Creates task assignment.
	 * 
	 * @param task task
	 * @param assignedDate assigned date
	 * @param assigneeAccount assignee account
	 * @return created task assignment
	 * @throws DuplicateEntityFoundException if task assignment exists
	 */
	public TaskAssignment create(
			final Task task,
			final Date assignedDate,
			final UserAccount assigneeAccount)
				throws DuplicateEntityFoundException {
		if (this.taskAssignmentDao.find(task, assigneeAccount) != null) {
			throw new DuplicateEntityFoundException("Task assignment exists");
		}
		TaskAssignment taskAssignment = this.taskAssignmentInstanceFactory
				.createInstance();
		taskAssignment.setTask(task);
		this.populate(taskAssignment, assignedDate, assigneeAccount);
		return this.taskAssignmentDao.makePersistent(taskAssignment);
	}
	
	/**
	 * Updates task assignment.
	 * 
	 * @param taskAssignment task assignment to update
	 * @param assignedDate assigned date
	 * @param assigneeAccount assignee account
	 * @param lastInvokedDate last invoked date
	 * @return updated task assignment
	 * @throws DuplicateEntityFoundException if task assignment exists
	 */
	public TaskAssignment update(
			final TaskAssignment taskAssignment,
			final Date assignedDate,
			final UserAccount assigneeAccount,
			final Date lastInvokedDate)
				throws DuplicateEntityFoundException {
		if (this.taskAssignmentDao.findExcluding(taskAssignment.getTask(), 
				assigneeAccount, taskAssignment) != null) {
			throw new DuplicateEntityFoundException("Task assignment exists");
		}
		taskAssignment.setLastInvokedDate(lastInvokedDate);
		this.populate(taskAssignment, assignedDate, assigneeAccount);
		return this.taskAssignmentDao.makePersistent(taskAssignment);
	}
	
	/**
	 * Removes task assignment.
	 * 
	 * @param taskAssignment task assignment to remove
	 */
	public void remove(final TaskAssignment taskAssignment) {
		this.taskAssignmentDao.makeTransient(taskAssignment);
	}
	
	/**
	 * Returns task assignment for the task and assignee.
	 * 
	 * @param task task
	 * @param assignee assignee
	 * @return task assignment
	 */
	public TaskAssignment findByTaskAndAssignee(final Task task, 
			final UserAccount assignee) {
		return this.taskAssignmentDao.findByTaskAndAssignee(task, assignee);
	}

	/**
	 * Updates the last invoked date for the task assignment.
	 * 
	 * @param taskAssignment task assignment
	 * @param lastInvoked last invoked
	 * @return task assignment
	 */
	public TaskAssignment updateLastInvoked(final TaskAssignment taskAssignment,
			final Date lastInvoked) {
		taskAssignment.setLastInvokedDate(lastInvoked);
		return this.taskAssignmentDao.makePersistent(taskAssignment);
	}
	
	/* Helper methods. */
	
	// Populates task assignment
	private void populate(
			final TaskAssignment taskAssignment,
			final Date assignedDate,
			final UserAccount assigneeAccount) {
		taskAssignment.setAssignedDate(assignedDate);
		taskAssignment.setAssigneeAccount(assigneeAccount);
	}
}