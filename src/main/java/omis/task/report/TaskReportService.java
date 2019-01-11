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
package omis.task.report;

import java.util.List;

import omis.task.domain.Task;
import omis.user.domain.UserAccount;

/**
 * Report service for tasks.
 *
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.1 (Sep 13, 2018)
 * @since OMIS 3.0
 */
public interface TaskReportService {

	/**
	 * Summarizes tasks by source account username.
	 * 
	 * @param username source account username
	 * @return summaries of tasks by source account username
	 */
	List<TaskSummary> summarizeBySourceAccountUsername(String username);
	
	/**
	 * Returns the uninvoked task count by assignee.
	 * 
	 * @param assigneeAccount assignee
	 * @return uninvoked task count
	 */
	Long findUninvokedTaskCountByAssigneeUserAccount(
			UserAccount assigneeAccount);
	
	/**
	 * Summarizes a task.
	 * 
	 * @param task task
	 * @return task summary
	 */
	TaskSummary summarize(Task task);
	
	/**
	 * Returns a list of task assignment summaries by task.
	 * 
	 * @param task task
	 * @return list of task assignment summaries
	 */
	List<TaskAssignmentSummary> summarizeAssignmentsByTask(Task task);
}