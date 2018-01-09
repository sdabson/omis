package omis.task.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.task.dao.TaskTemplateDao;
import omis.task.domain.TaskTemplate;
import omis.task.domain.TaskTemplateGroup;

/**
 * TaskTemplateDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 12, 2017)
 *@since OMIS 3.0
 *
 */
public class TaskTemplateDaoHibernateImpl extends
		GenericHibernateDaoImpl<TaskTemplate> implements TaskTemplateDao {
	
	private static final String FIND_TASK_TEMPLATE_QUERY_NAME =
			"findTaskTemplate";
	
	private static final String FIND_TASK_TEMPLATE_QUERY_NAME_EXCLUDING =
			"findTaskTemplateExcluding";
	
	private static final String FIND_TASK_TEMPLATES_BY_GROUP_QUERY_NAME =
			"findTaskTemplatesByGroup";
	
	private static final String NAME_MODEL_KEY = "name";
	
	private static final String GROUP_MODEL_KEY = "group";
	
	private static final String CONTROLLER_NAME_MODEL_KEY = "controllerName";
	
	private static final String METHOD_NAME_MODEL_KEY = "methodName";
	
	private static final String EXCLUDED_TASK_TEMPLATES_MODEL_KEY =
			"excludedTaskTemplates";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected TaskTemplateDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	

	/**{@inheritDoc} */
	@Override
	public List<TaskTemplate> findByGroup(final TaskTemplateGroup group) {
		@SuppressWarnings("unchecked")
		List<TaskTemplate> taskTemplates = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_TASK_TEMPLATES_BY_GROUP_QUERY_NAME)
				.setParameter(GROUP_MODEL_KEY, group)
				.list();
		
		return taskTemplates;
	}

	/**{@inheritDoc} */
	@Override
	public TaskTemplate find(final TaskTemplateGroup group,
			final String name,
			final String controllerName,
			final String methodName) {
		TaskTemplate taskTemplate = (TaskTemplate) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_TASK_TEMPLATE_QUERY_NAME)
				.setParameter(NAME_MODEL_KEY, name)
				.setParameter(GROUP_MODEL_KEY, group)
				.setParameter(CONTROLLER_NAME_MODEL_KEY, controllerName)
				.setParameter(METHOD_NAME_MODEL_KEY, methodName)
				.uniqueResult();
		
		return taskTemplate;
	}

	/**{@inheritDoc} */
	@Override
	public TaskTemplate findExcluding(final TaskTemplateGroup group,
			final String name,
			final String controllerName,
			final String methodName,
			final TaskTemplate... excludedTaskTemplates) {
		TaskTemplate taskTemplate = (TaskTemplate) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_TASK_TEMPLATE_QUERY_NAME_EXCLUDING)
				.setParameter(NAME_MODEL_KEY, name)
				.setParameter(GROUP_MODEL_KEY, group)
				.setParameter(CONTROLLER_NAME_MODEL_KEY, controllerName)
				.setParameter(METHOD_NAME_MODEL_KEY, methodName)
				.setParameterList(EXCLUDED_TASK_TEMPLATES_MODEL_KEY,
						excludedTaskTemplates)
				.uniqueResult();
		
		return taskTemplate;
	}

}
