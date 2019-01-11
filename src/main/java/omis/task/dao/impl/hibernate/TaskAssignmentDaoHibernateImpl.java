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
package omis.task.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.task.dao.TaskAssignmentDao;
import omis.task.domain.Task;
import omis.task.domain.TaskAssignment;
import omis.user.domain.UserAccount;

/**
 * Hibernate implementation of data access object for task assignments.
 *
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.1 (Sep 13, 2018)
 * @since OMIS 3.0
 */
public class TaskAssignmentDaoHibernateImpl
		extends GenericHibernateDaoImpl<TaskAssignment>
		implements TaskAssignmentDao {
	
	/* Query names. */
	
	private static final String FIND_BY_TASK_QUERY_NAME = 
			"findTaskAssignmentsByTask";

	private static final String FIND_QUERY_NAME = "findTaskAssignment";
	
	private static final String FIND_EXCLUDING_QUERY_NAME =
			"findTaskAssignmentExcluding";
	
	private static final String FIND_BY_TASK_AND_ASSIGNEE_QUERY_NAME = 
			"findTaskAssignmentByTaskAndAssignee";
	
	/* Parameter names. */
	
	private static final String TASK_PARAM_NAME = "task";

	private static final String ASSIGNEE_ACCOUNT_PARAM_NAME = "assigneeAccount";

	private static final String EXCLUDED_PARAM_NAME = "excludedTaskAssignments";
	
	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of data access object for task
	 * assignments.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public TaskAssignmentDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<TaskAssignment> findByTask(final Task task) {
		@SuppressWarnings("unchecked")
		List<TaskAssignment> assignments = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_BY_TASK_QUERY_NAME)
				.setParameter(TASK_PARAM_NAME, task)
				.list();
		return assignments;
	}

	/** {@inheritDoc} */
	@Override
	public TaskAssignment find(final Task task, 
			final UserAccount assigneeAccount) {
		TaskAssignment taskAssignment = (TaskAssignment) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(TASK_PARAM_NAME, task)
				.setParameter(ASSIGNEE_ACCOUNT_PARAM_NAME, assigneeAccount)
				.uniqueResult();
		return taskAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public TaskAssignment findExcluding(final Task task, 
			final UserAccount assigneeAccount,
			final TaskAssignment... excludedTaskAssignments) {
		TaskAssignment taskAssignment = (TaskAssignment) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(TASK_PARAM_NAME, task)
				.setParameter(ASSIGNEE_ACCOUNT_PARAM_NAME, assigneeAccount)
				.setParameterList(EXCLUDED_PARAM_NAME, excludedTaskAssignments)
				.uniqueResult();
		return taskAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public TaskAssignment findByTaskAndAssignee(final Task task,
			final UserAccount assignee) {
		TaskAssignment taskAssignment = (TaskAssignment) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_TASK_AND_ASSIGNEE_QUERY_NAME)
				.setParameter(TASK_PARAM_NAME, task)
				.setParameter(ASSIGNEE_ACCOUNT_PARAM_NAME, assignee)
				.uniqueResult();
		return taskAssignment;
	}
}