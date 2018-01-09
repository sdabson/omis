package omis.task.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.task.dao.TaskParameterValueDao;
import omis.task.domain.Task;
import omis.task.domain.TaskParameterValue;
import omis.task.domain.TaskTemplateParameterValue;

/**
 * Hibernate implementation of data access object for task parameter values.
 *
 * @author Stephen Abson
 * @author Annie Jacques
 * @version 0.0.2
 * @since OMIS 3.0
 */
public class TaskParameterValueDaoHibernateImpl
		extends GenericHibernateDaoImpl<TaskParameterValue>
		implements TaskParameterValueDao {
	
	/* Query names. */
	
	private static final String FIND_BY_TASK_QUERY_NAME
		= "findTaskParameterValuesByTask";
	
	private static final String FIND_QUERY_NAME = "findTaskParameterValue";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findTaskParameterValueExcluding";

	private static final String
		FIND_BY_TASK_TEMPLATE_PARAMETER_VALUE_AND_TASK_QUERY_NAME =
			"findTaskParameterValueByTaskTemplateParameterValueAndTask";
	
	/* Parameter names. */
	
	private static final String TASK_PARAM_NAME = "task";

	private static final String TYPE_NAME_PARAM_NAME = "typeName";

	private static final String ORDER_PARAM_NAME = "order";
	
	private static final String EXCLUDED_TASK_PARAMETER_VALUES_PARAM_NAME
			= "excludedTaskParameterValues";

	private static final String FIND_BY_TASK_AND_ORDER_QUERY_NAME
			= "findTaskParameterValueByTaskAndOrder";

	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of data access object for task
	 * parameter value.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public TaskParameterValueDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<TaskParameterValue> findByTask(final Task task) {
		@SuppressWarnings("unchecked")
		List<TaskParameterValue> parameterValues
			= this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_TASK_QUERY_NAME)
				.setParameter(TASK_PARAM_NAME, task)
				.list();
		return parameterValues;
	}
	
	/**
	 * Throws {@code UnsupportedOperationExceptions}.
	 * 
	 * @return does not return
	 */
	@Override
	public List<TaskParameterValue> findAll() {
		throw new UnsupportedOperationException("Criteria required");
	}

	/** {@inheritDoc} */
	@Override
	public TaskParameterValue find(
			final Task task, final String typeName, final Short order) {
		TaskParameterValue parameterValue
				= (TaskParameterValue) this.getSessionFactory()
					.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
					.setParameter(TASK_PARAM_NAME, task)
					.setParameter(TYPE_NAME_PARAM_NAME, typeName)
					.setParameter(ORDER_PARAM_NAME, order)
					.uniqueResult();
		return parameterValue;
	}

	/** {@inheritDoc} */
	@Override
	public TaskParameterValue findExcluding(
			final Task task, final String typeName, final Short order,
			final TaskParameterValue... excludedTaskParameterValues) {
		TaskParameterValue parameterValue
				= (TaskParameterValue) this.getSessionFactory()
					.getCurrentSession().getNamedQuery(
							FIND_EXCLUDING_QUERY_NAME)
					.setParameter(TASK_PARAM_NAME, task)
					.setParameter(TYPE_NAME_PARAM_NAME, typeName)
					.setParameter(ORDER_PARAM_NAME, order)
					.setParameterList(EXCLUDED_TASK_PARAMETER_VALUES_PARAM_NAME,
							excludedTaskParameterValues)
					.uniqueResult();
		return parameterValue;
	}

	/**{@inheritDoc} */
	@Override
	public TaskParameterValue findByTaskTemplateParameterValueAndTask(
			final TaskTemplateParameterValue taskTemplateParameterValue,
			final Task task) {
		TaskParameterValue taskParameterValue = (TaskParameterValue)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_BY_TASK_TEMPLATE_PARAMETER_VALUE_AND_TASK_QUERY_NAME)
				.setParameter(TYPE_NAME_PARAM_NAME, taskTemplateParameterValue
						.getTypeName())
				.setParameter(ORDER_PARAM_NAME, taskTemplateParameterValue
						.getOrder())
				.setParameter(TASK_PARAM_NAME, task)
				.uniqueResult();
		
		return taskParameterValue;
	}

	/** {@inheritDoc} */
	@Override
	public TaskParameterValue findByTaskAndOrder(
			final Task task, final Short order) {
		TaskParameterValue parameterValue
			= (TaskParameterValue) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_TASK_AND_ORDER_QUERY_NAME)
				.setParameter(TASK_PARAM_NAME, task)
				.setParameter(ORDER_PARAM_NAME, order)
				.uniqueResult();
		return parameterValue;
	}
}