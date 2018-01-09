package omis.task.dao.impl.hibernate;


import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.task.dao.TaskTemplateGroupDao;
import omis.task.domain.TaskTemplateGroup;

/**
 * TaskTemplateGroupDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 6, 2017)
 *@since OMIS 3.0
 *
 */
public class TaskTemplateGroupDaoHibernateImpl
		extends GenericHibernateDaoImpl<TaskTemplateGroup>
		implements TaskTemplateGroupDao {
	
	private static final String FIND_TASK_TEMPLATE_GROUP_QUERY_NAME =
			"findTaskTemplateGroup";
	
	private static final String FIND_TASK_TEMPLATE_GROUP_EXCLUDING_QUERY_NAME =
			"findTaskTemplateGroupExcluding";
	
	private static final String NAME_PARAMETER_VALUE = "name";
	
	private static final String EXCLUDED_TASK_TEMPLATE_GROUPS_PARAMETER_NAME =
			"excludedTaskTemplateGroups";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected TaskTemplateGroupDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public TaskTemplateGroup find(final String name) {
		TaskTemplateGroup group = (TaskTemplateGroup) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_TASK_TEMPLATE_GROUP_QUERY_NAME)
				.setParameter(NAME_PARAMETER_VALUE, NAME_PARAMETER_VALUE)
				.uniqueResult();
		
		return group;
	}

	/**{@inheritDoc} */
	@Override
	public TaskTemplateGroup findExcluding(final String name,
			final TaskTemplateGroup... excludedGroups) {
		TaskTemplateGroup group = (TaskTemplateGroup) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_TASK_TEMPLATE_GROUP_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAMETER_VALUE, NAME_PARAMETER_VALUE)
				.setParameterList(EXCLUDED_TASK_TEMPLATE_GROUPS_PARAMETER_NAME,
						excludedGroups)
				.uniqueResult();
		
		return group;
	}

}
