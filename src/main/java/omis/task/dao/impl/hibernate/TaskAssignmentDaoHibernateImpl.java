package omis.task.dao.impl.hibernate;

import java.util.Date;
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
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class TaskAssignmentDaoHibernateImpl
		extends GenericHibernateDaoImpl<TaskAssignment>
		implements TaskAssignmentDao {
	
	/* Query names. */
	
	private static final String FIND_BY_TASK_QUERY_NAME
		= "findTaskAssignmentsByTask";

	private static final String FIND_QUERY_NAME = "findTaskAssignment";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findTaskAssignmentExcluding";
	
	/* Parameter names. */
	
	private static final String TASK_PARAM_NAME = "task";

	private static final String ASSIGNEE_ACCOUNT_PARAM_NAME = "assigneeAccount";

	private static final String ASSIGNED_DATE_PARAM_NAME = "assignedDate";
	
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
	public TaskAssignment find(
			final Task task, final UserAccount assigneeAccount,
			final Date assignedDate) {
		TaskAssignment taskAssignment
			= (TaskAssignment) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(TASK_PARAM_NAME, task)
				.setParameter(ASSIGNEE_ACCOUNT_PARAM_NAME, assigneeAccount)
				.setTimestamp(ASSIGNED_DATE_PARAM_NAME, assignedDate)
				.uniqueResult();
		return taskAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public TaskAssignment findExcluding(
			final Task task, final UserAccount assigneeAccount,
			final Date assignedDate,
			final TaskAssignment... excludedTaskAssignments) {
		TaskAssignment taskAssignment
			= (TaskAssignment) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(TASK_PARAM_NAME, task)
				.setParameter(ASSIGNEE_ACCOUNT_PARAM_NAME, assigneeAccount)
				.setTimestamp(ASSIGNED_DATE_PARAM_NAME, assignedDate)
				.setParameterList(EXCLUDED_PARAM_NAME, excludedTaskAssignments)
				.uniqueResult();
		return taskAssignment;
	}
}