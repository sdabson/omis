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

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.hearinganalysis.dao.ParoleHearingTaskGroupDao;
import omis.hearinganalysis.domain.HearingAnalysisCategory;
import omis.hearinganalysis.domain.ParoleHearingTaskGroup;
import omis.task.domain.TaskTemplateGroup;

/**
 * Hibernate implementation of the parole hearing task group data access object.
 *
 * @author Josh Divine
 * @version 0.1.0 (Jan 10, 2018)
 * @since OMIS 3.0
 */
public class ParoleHearingTaskGroupDaoHibernateImpl 
		extends GenericHibernateDaoImpl<ParoleHearingTaskGroup>
		implements ParoleHearingTaskGroupDao {

	/* Queries. */
	
	private static final String FIND_QUERY_NAME = 
			"findParoleHearingTaskGroup";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findParoleHearingTaskGroupExcluding";
	
	private static final String FIND_BY_HEARING_ANALYSIS_CATEGORY_QUERY_NAME = 
			"findParoleHearingTaskGroupByHearingAnalysisCategory";
	
	/* Parameters. */
	
	private static final String TASK_TEMPLATE_GROUP_PARAM_NAME = "group";

	private static final String HEARING_ANALYSIS_CATEGORY_PARAM_NAME = 
			"hearingAnalysisCategory";
	
	private static final String EXCLUDED_PAROLE_HEARING_TASK_GROUP_PARAM_NAME =
			"excludedParoleHearingTaskGroup";

	/** Instantiates a hibernate implementation of the data access object for 
	 *  parole hearing task group.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ParoleHearingTaskGroupDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public ParoleHearingTaskGroup find(final TaskTemplateGroup group, 
			final HearingAnalysisCategory hearingAnalysisCategory) {
		ParoleHearingTaskGroup paroleHearingTaskGroup = 
				(ParoleHearingTaskGroup) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(TASK_TEMPLATE_GROUP_PARAM_NAME, group)
				.setParameter(HEARING_ANALYSIS_CATEGORY_PARAM_NAME, 
						hearingAnalysisCategory)
				.uniqueResult();
		return paroleHearingTaskGroup;
	}

	/** {@inheritDoc} */
	@Override
	public ParoleHearingTaskGroup findExcluding(final TaskTemplateGroup group,
			final HearingAnalysisCategory hearingAnalysisCategory, 
			final ParoleHearingTaskGroup excludedParoleHearingTaskGroup) {
		ParoleHearingTaskGroup paroleHearingTaskGroup = 
				(ParoleHearingTaskGroup) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(TASK_TEMPLATE_GROUP_PARAM_NAME, group)
				.setParameter(HEARING_ANALYSIS_CATEGORY_PARAM_NAME, 
						hearingAnalysisCategory)
				.setParameter(EXCLUDED_PAROLE_HEARING_TASK_GROUP_PARAM_NAME, 
						excludedParoleHearingTaskGroup)
				.uniqueResult();
		return paroleHearingTaskGroup;
	}

	/** {@inheritDoc} */
	@Override
	public ParoleHearingTaskGroup findByHearingAnalysisCategory(
			final HearingAnalysisCategory hearingAnalysisCategory) {
		ParoleHearingTaskGroup paroleHearingTaskGroup = 
				(ParoleHearingTaskGroup) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_HEARING_ANALYSIS_CATEGORY_QUERY_NAME)
				.setParameter(HEARING_ANALYSIS_CATEGORY_PARAM_NAME, 
						hearingAnalysisCategory)
				.uniqueResult();
		return paroleHearingTaskGroup;
	}
}
