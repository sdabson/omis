package omis.task.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.task.dao.TaskDao;
import omis.task.domain.Task;
import omis.task.domain.TaskTemplate;
import omis.user.domain.UserAccount;

/**
 * Hibernate implementation of data access object for tasks.
 *
 * @author Stephen Abson
 * @author Annie Jacques
 * @version 0.0.2
 * @since OMIS 3.0
 */
public class TaskDaoHibernateImpl
		extends GenericHibernateDaoImpl<Task>
		implements TaskDao {

	/* Query names. */
	
	private static final String FIND_QUERY_NAME = "findTask";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = "findTaskExcluding";

	private static final String FIND_TASKS_BY_TASK_TEMPLATE_QUERY_NAME =
			"findTasksByTaskTemplate";
	
	/* Parameter names. */
	
	private static final String CONTROLLER_NAME_PARAM_NAME = "controllerName";
	
	private static final String METHOD_NAME_PARAM_NAME = "methodName";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String SOURCE_ACCOUNT_PARAM_NAME = "sourceAccount";
	
	private static final String ORIGINATION_DATE_PARAM_NAME = "originationDate";
	
	private static final String EXCLUDED_TASKS_PARAM_NAME = "excludedTasks";
	
	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of data access object for tasks.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public TaskDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/**
	 * Throws {@code UnsupportedOperationExceptions}.
	 * 
	 * @return does not return
	 */
	@Override
	public List<Task> findAll() {
		throw new UnsupportedOperationException("Criteria required");
	}

	/** {@inheritDoc }*/
	@Override
	public Task find(final String controllerName, final String methodName,
			final String description, final UserAccount sourceAccount,
			final Date originationDate) {
		Task task = (Task) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(CONTROLLER_NAME_PARAM_NAME, controllerName)
				.setParameter(METHOD_NAME_PARAM_NAME, methodName)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(SOURCE_ACCOUNT_PARAM_NAME, sourceAccount)
				.setParameter(ORIGINATION_DATE_PARAM_NAME, originationDate)
				.uniqueResult();
		return task;
	}

	/** {@inheritDoc }*/
	@Override
	public Task findExcluding(final String controllerName,
			final String methodName, final String description,
			final UserAccount sourceAccount, final Date originationDate,
			final Task... excludedTasks) {
		Task task = (Task) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(CONTROLLER_NAME_PARAM_NAME, controllerName)
				.setParameter(METHOD_NAME_PARAM_NAME, methodName)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(SOURCE_ACCOUNT_PARAM_NAME, sourceAccount)
				.setParameter(ORIGINATION_DATE_PARAM_NAME, originationDate)
				.setParameterList(EXCLUDED_TASKS_PARAM_NAME, excludedTasks)
				.uniqueResult();
		return task;
	}

	/**{@inheritDoc} */
	@Override
	public List<Task> findByTaskTemplate(TaskTemplate taskTemplate) {
		@SuppressWarnings("unchecked")
		List<Task> tasks = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_TASKS_BY_TASK_TEMPLATE_QUERY_NAME)
				.setParameter(CONTROLLER_NAME_PARAM_NAME,
						taskTemplate.getControllerName())
				.setParameter(METHOD_NAME_PARAM_NAME,
						taskTemplate.getMethodName())
				.list();
		return tasks;
	}
}