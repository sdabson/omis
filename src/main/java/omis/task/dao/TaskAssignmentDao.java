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
import omis.task.domain.TaskAssignment;
import omis.user.domain.UserAccount;

/**
 * Data access object for tasks assignments.
 *
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.1 (Sep 13, 2018)
 * @since OMIS 3.0
 */
public interface TaskAssignmentDao
		extends GenericDao<TaskAssignment> {

	/**
	 * Returns assignments by tasks.
	 * 
	 * @param task task
	 * @return assignments by task
	 */
	List<TaskAssignment> findByTask(Task task);
	
	/**
	 * Returns task assignment.
	 * 
	 * <p>Returns {@code null} if task assignment does not exist
	 * 
	 * @param task task
	 * @param assigneeAccount user account of assignee
	 * @return task assignment or {@code null} if non-existent
	 */
	TaskAssignment find(Task task, UserAccount assigneeAccount);
	
	/**
	 * Returns task assignment.
	 * 
	 * <p>Returns {@code null} if task assignment does not exist or if matching
	 * assignment is excluded.
	 * 
	 * @param task task
	 * @param assigneeAccount user account of assignee
	 * @param excludedTaskAssignments task assignments to exclude
	 * @return task assignment or {@code null} if non-existent or excluded
	 */
	TaskAssignment findExcluding(Task task, UserAccount assigneeAccount, 
			TaskAssignment... excludedTaskAssignments);
	
	/**
	 * Returns task assignment for the task and assignee.
	 * 
	 * @param task task
	 * @param assignee assignee
	 * @return task assignment
	 */
	TaskAssignment findByTaskAndAssignee(Task task, UserAccount assignee);
}