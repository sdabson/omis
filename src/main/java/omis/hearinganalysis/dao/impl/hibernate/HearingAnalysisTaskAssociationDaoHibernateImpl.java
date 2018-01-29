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
import omis.hearinganalysis.dao.HearingAnalysisTaskAssociationDao;
import omis.hearinganalysis.domain.HearingAnalysis;
import omis.hearinganalysis.domain.HearingAnalysisTaskAssociation;
import omis.hearinganalysis.domain.ParoleHearingAnalysisTaskSource;
import omis.task.domain.Task;
import omis.task.domain.TaskTemplate;

/**
 * Hibernate implementation of the hearing analysis task association data access 
 * object.
 *
 * @author Josh Divine
 * @version 0.1.0 (Jan 10, 2018)
 * @since OMIS 3.0
 */
public class HearingAnalysisTaskAssociationDaoHibernateImpl 
	extends GenericHibernateDaoImpl<HearingAnalysisTaskAssociation> 
	implements HearingAnalysisTaskAssociationDao {

	/* Queries. */
	
	private static final String FIND_QUERY_NAME = 
			"findHearingAnalysisTaskAssociation";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findHearingAnalysisTaskAssociationExcluding";
	
	private static final String FIND_BY_HEARING_ANALYSIS_QUERY_NAME = 
			"findHearingAnalysisTaskAssociationByHearingAnalysis";
	
	private static final String 
			FIND_TASK_BY_TASK_TEMPLATE_AND_HEARING_ANALYSIS_QUERY_NAME = 
			"findTaskByTaskTemplateAndHearingAnalysis";
	
	/* Parameters. */
	
	private static final String TASK_PARAM_NAME = "task";
	
	private static final String TASK_SOURCE_PARAM_NAME = "taskSource";
	
	private static final String HEARING_ANALYSIS_PARAM_NAME = "hearingAnalysis";
	
	private static final String TASK_TEMPLATE_PARAM_NAME = "taskTemplate";
	
	private static final String 
			EXCLUDED_HEARING_ANALYSIS_TASK_ASSOCIATION_PARAM_NAME =
			"excludedHearingAnalysisTaskAssociation";

	/** Instantiates a hibernate implementation of the data access object for 
	 *  hearing analysis task association.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public HearingAnalysisTaskAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public HearingAnalysisTaskAssociation find(final Task task, 
			final HearingAnalysis hearingAnalysis,
			final ParoleHearingAnalysisTaskSource taskSource) {
		HearingAnalysisTaskAssociation taskAssociation = 
				(HearingAnalysisTaskAssociation) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(TASK_PARAM_NAME, task)
				.setParameter(TASK_SOURCE_PARAM_NAME, taskSource)
				.setParameter(HEARING_ANALYSIS_PARAM_NAME, hearingAnalysis)
				.uniqueResult();
		return taskAssociation;
	}

	/** {@inheritDoc} */
	@Override
	public HearingAnalysisTaskAssociation findExcluding(final Task task, 
			final HearingAnalysis hearingAnalysis,
			final ParoleHearingAnalysisTaskSource taskSource,
			final HearingAnalysisTaskAssociation 
					excludedHearingAnalysisTaskAssociation) {
		HearingAnalysisTaskAssociation taskAssociation = 
				(HearingAnalysisTaskAssociation) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(TASK_PARAM_NAME, task)
				.setParameter(TASK_SOURCE_PARAM_NAME, taskSource)
				.setParameter(HEARING_ANALYSIS_PARAM_NAME, hearingAnalysis)
				.setParameter(
						EXCLUDED_HEARING_ANALYSIS_TASK_ASSOCIATION_PARAM_NAME, 
						excludedHearingAnalysisTaskAssociation)
				.uniqueResult();
		return taskAssociation;
	}

	/** {@inheritDoc} */
	@Override
	public List<HearingAnalysisTaskAssociation> findByHearingAnalysis(
			final HearingAnalysis hearingAnalysis) {
		@SuppressWarnings("unchecked")
		List<HearingAnalysisTaskAssociation> taskAssociations = 
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_HEARING_ANALYSIS_QUERY_NAME)
				.setParameter(HEARING_ANALYSIS_PARAM_NAME, hearingAnalysis)
				.list();
		return taskAssociations;
	}

	/** {@inheritDoc} */
	@Override
	public Task findTaskByTaskTemplateAndHearingAnalysis(
			final TaskTemplate taskTemplate, 
			final HearingAnalysis hearingAnalysis) {
		Task task = (Task) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
					FIND_TASK_BY_TASK_TEMPLATE_AND_HEARING_ANALYSIS_QUERY_NAME)
				.setParameter(TASK_TEMPLATE_PARAM_NAME, taskTemplate)
				.setParameter(HEARING_ANALYSIS_PARAM_NAME, hearingAnalysis)
				.uniqueResult();
		return task;
	}

}
