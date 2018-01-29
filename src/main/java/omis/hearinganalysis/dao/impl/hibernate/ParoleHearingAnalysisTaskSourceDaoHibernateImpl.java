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
package omis.hearinganalysis.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.hearinganalysis.dao.ParoleHearingAnalysisTaskSourceDao;
import omis.hearinganalysis.domain.ParoleHearingAnalysisTaskSource;
import omis.task.domain.TaskTemplate;
import omis.task.domain.TaskTemplateGroup;

/**
 * Hibernate implementation of the parole hearing analysis task source data 
 * access object.
 *
 * @author Josh Divine
 * @version 0.1.0 (Jan 10, 2018)
 * @since OMIS 3.0
 */
public class ParoleHearingAnalysisTaskSourceDaoHibernateImpl 
	extends GenericHibernateDaoImpl<ParoleHearingAnalysisTaskSource>
		implements ParoleHearingAnalysisTaskSourceDao {

	/* Queries. */
	
	private static final String FIND_QUERY_NAME = 
			"findParoleHearingAnalysisTaskSource";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findParoleHearingAnalysisTaskSourceExcluding";
	
	private static final String FIND_BY_TASK_TEMPLATE_GROUP_QUERY_NAME = 
			"findParoleHearingAnalysisTaskSourceByTaskTemplateGroup";
	
	
	/* Parameters. */
	
	private static final String TASK_TEMPLATE_PARAM_NAME = "taskTemplate";
	
	private static final String TASK_TEMPLATE_GROUP_PARAM_NAME = 
			"taskTemplateGroup";
	
	private static final String 
			EXCLUDED_PAROLE_HEARING_ANALYSIS_TASK_SOURCE_PARAM_NAME =
			"excludedParoleHearingAnalysisTaskSource";

	/** Instantiates a hibernate implementation of the data access object for 
	 *  parole hearing analysis task source.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ParoleHearingAnalysisTaskSourceDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public ParoleHearingAnalysisTaskSource find(
			final TaskTemplate taskTemplate) {
		ParoleHearingAnalysisTaskSource taskSource = 
				(ParoleHearingAnalysisTaskSource) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(TASK_TEMPLATE_PARAM_NAME, taskTemplate)
				.uniqueResult();
		return taskSource;
	}

	/** {@inheritDoc} */
	@Override
	public ParoleHearingAnalysisTaskSource findExcluding(
			final TaskTemplate taskTemplate,
			final ParoleHearingAnalysisTaskSource 
					excludedParoleHearingAnalysisTaskSource) {
		ParoleHearingAnalysisTaskSource taskSource = 
				(ParoleHearingAnalysisTaskSource) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(TASK_TEMPLATE_PARAM_NAME, taskTemplate)
				.setParameter(
						EXCLUDED_PAROLE_HEARING_ANALYSIS_TASK_SOURCE_PARAM_NAME, 
						excludedParoleHearingAnalysisTaskSource)
				.uniqueResult();
		return taskSource;
	}

	/** {@inheritDoc} */
	@Override
	public List<ParoleHearingAnalysisTaskSource> findByTaskTemplateGroup(
			final TaskTemplateGroup taskTemplateGroup) {
		@SuppressWarnings("unchecked")
		List<ParoleHearingAnalysisTaskSource> taskSources = this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_TASK_TEMPLATE_GROUP_QUERY_NAME)
				.setParameter(TASK_TEMPLATE_GROUP_PARAM_NAME, taskTemplateGroup)
				.list();
		return taskSources;
	}

}
