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
import omis.task.dao.TaskTemplateParameterValueDao;
import omis.task.domain.TaskTemplate;
import omis.task.domain.TaskTemplateParameterValue;

/**
 * TaskTemplateParameterValueDaoHibernateImpl.java
 * 
 * @author Annie Jacques 
 * @author Josh Divine
 * @version 0.1.1 (Jan 3, 2018)
 * @since OMIS 3.0
 *
 */
public class TaskTemplateParameterValueDaoHibernateImpl
		extends GenericHibernateDaoImpl<TaskTemplateParameterValue>
		implements TaskTemplateParameterValueDao {
	
	private static final String FIND_TASK_TEMPLATE_PARAMETER_VALUE_QUERY_NAME =
			"findTaskTemplateParameterValue";
	
	private static final String
		FIND_TASK_TEMPLATE_PARAMETER_VALUE_EXCLUDING_QUERY_NAME =
			"findTaskTemplateParameterValueExcluding";
	
	private static final String
		FIND_BY_TASK_TEMPLATE_QUERY_NAME =
			"findTaskTemplateParameterValuesByTaskTemplate";
	
	private static final String TASK_TEMPLATE_PARAM_NAME = "template";
	
	private static final String ORDER_PARAM_NAME = "order";
	
	private static final String TYPE_NAME_PARAM_NAME = "typeName";
	
	private static final String EXCLUDED_PARAMETER_VALUES =
			"excludedTaskTemplateParameterValues";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected TaskTemplateParameterValueDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public List<TaskTemplateParameterValue>
		findByTemplate(final TaskTemplate template) {
		@SuppressWarnings("unchecked")
		List<TaskTemplateParameterValue> parameterValues =
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_TASK_TEMPLATE_QUERY_NAME)
				.setParameter(TASK_TEMPLATE_PARAM_NAME, template)
				.list();
		
		return parameterValues;
	}

	/**{@inheritDoc} */
	@Override
	public TaskTemplateParameterValue find(final TaskTemplate template,
			final Short order,
			final String typeName) {
		TaskTemplateParameterValue taskTemplateParameterValue =
				(TaskTemplateParameterValue) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_TASK_TEMPLATE_PARAMETER_VALUE_QUERY_NAME)
				.setParameter(TASK_TEMPLATE_PARAM_NAME, template)
				.setParameter(ORDER_PARAM_NAME, order)
				.setParameter(TYPE_NAME_PARAM_NAME, typeName)
				.uniqueResult();
		
		return taskTemplateParameterValue;
	}

	/**{@inheritDoc} */
	@Override
	public TaskTemplateParameterValue findExcluding(final TaskTemplate template,
			final Short order,
			final String typeName,
			final TaskTemplateParameterValue... excludedParameterValues) {
		TaskTemplateParameterValue taskTemplateParameterValue =
				(TaskTemplateParameterValue) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_TASK_TEMPLATE_PARAMETER_VALUE_EXCLUDING_QUERY_NAME)
				.setParameter(TASK_TEMPLATE_PARAM_NAME, template)
				.setParameter(ORDER_PARAM_NAME, order)
				.setParameter(TYPE_NAME_PARAM_NAME, typeName)
				.setParameterList(EXCLUDED_PARAMETER_VALUES,
						excludedParameterValues)
				.uniqueResult();
		
		return taskTemplateParameterValue;
	}

}
