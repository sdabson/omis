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
package omis.hearinganalysis.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.hearinganalysis.dao.ParoleHearingAnalysisTaskSourceDao;
import omis.hearinganalysis.domain.ParoleHearingAnalysisTaskSource;
import omis.hearinganalysis.domain.ParoleHearingTaskCategory;
import omis.instance.factory.InstanceFactory;
import omis.task.domain.TaskTemplate;
import omis.task.domain.TaskTemplateGroup;

/**
 * Parole hearing analysis task source delegate.
 *
 * @author Josh Divine
 * @version 0.1.0 (Jan 10, 2018)
 * @since OMIS 3.0
 */
public class ParoleHearingAnalysisTaskSourceDelegate {

	/* Data access objects. */
	
	private final ParoleHearingAnalysisTaskSourceDao 
			paroleHearingAnalysisTaskSourceDao;

	/* Instance factories. */
	
	private final InstanceFactory<ParoleHearingAnalysisTaskSource> 
			paroleHearingAnalysisTaskSourceInstanceFactory;
	
	/**
	 * Instantiates an implementation of parole hearing analysis task source 
	 * delegate with the specified data access object and instance factory.
	 * 
	 * @param paroleHearingAnalysisTaskSourceDao parole hearing analysis task 
	 * source data access object
	 * @param paroleHearingAnalysisTaskSourceInstanceFactory parole hearing 
	 * analysis task source instance factory
	 */
	public ParoleHearingAnalysisTaskSourceDelegate(
			final ParoleHearingAnalysisTaskSourceDao 
					paroleHearingAnalysisTaskSourceDao,
			final InstanceFactory<ParoleHearingAnalysisTaskSource> 
					paroleHearingAnalysisTaskSourceInstanceFactory) {
		this.paroleHearingAnalysisTaskSourceDao = 
				paroleHearingAnalysisTaskSourceDao;
		this.paroleHearingAnalysisTaskSourceInstanceFactory = 
				paroleHearingAnalysisTaskSourceInstanceFactory;
	}
	
	/**
	 * Creates a new parole hearing analysis task source.
	 * 
	 * @param taskTemplate task template
	 * @param category parole hearing task category
	 * @return parole hearing analysis task source
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public ParoleHearingAnalysisTaskSource create(
			final TaskTemplate taskTemplate, 
			final ParoleHearingTaskCategory category) 
					throws DuplicateEntityFoundException {
		if (this.paroleHearingAnalysisTaskSourceDao.find(taskTemplate) != null) {
			throw new DuplicateEntityFoundException(
					"Parole hearing analysis task source already exists.");
		}
		ParoleHearingAnalysisTaskSource paroleHearingAnalysisTaskSource = 
				this.paroleHearingAnalysisTaskSourceInstanceFactory
				.createInstance();
		populateParoleHearingAnalysisTaskSource(paroleHearingAnalysisTaskSource,
				taskTemplate, category);
		return this.paroleHearingAnalysisTaskSourceDao.makePersistent(
				paroleHearingAnalysisTaskSource);
	}
	
	/**
	 * Updates an existing parole hearing analysis task source.
	 * 
	 * @param paroleHearingAnalysisTaskSource parole hearing analysis task 
	 * source
	 * @param taskTemplate task template
	 * @param category parole hearing task category
	 * @return parole hearing analysis task source
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public ParoleHearingAnalysisTaskSource update(
			final ParoleHearingAnalysisTaskSource 
					paroleHearingAnalysisTaskSource,
			final TaskTemplate taskTemplate, 
			final ParoleHearingTaskCategory category) 
					throws DuplicateEntityFoundException {
		if (this.paroleHearingAnalysisTaskSourceDao.findExcluding(taskTemplate, 
				paroleHearingAnalysisTaskSource) != null) {
			throw new DuplicateEntityFoundException(
					"Parole hearing analysis task source already exists.");
		}
		populateParoleHearingAnalysisTaskSource(paroleHearingAnalysisTaskSource,
				taskTemplate, category);
		return this.paroleHearingAnalysisTaskSourceDao.makePersistent(
				paroleHearingAnalysisTaskSource);
	}

	/**
	 * Removes an existing parole hearing analysis task source.
	 * 
	 * @param paroleHearingAnalysisTaskSource parole hearing analysis task 
	 * source
	 */
	public void remove(
			final ParoleHearingAnalysisTaskSource 
					paroleHearingAnalysisTaskSource) {
		this.paroleHearingAnalysisTaskSourceDao.makeTransient(
				paroleHearingAnalysisTaskSource);
	}
	
	/**
	 * Returns a list of parole hearing analysis task sources for the specified 
	 * task template group.
	 * 
	 * @param taskTemplateGroup task template group
	 * @return list of parole hearing analysis task sources
	 */
	public List<ParoleHearingAnalysisTaskSource> findByTaskTemplateGroup(
			final TaskTemplateGroup taskTemplateGroup) {
		return this.paroleHearingAnalysisTaskSourceDao.findByTaskTemplateGroup(
				taskTemplateGroup);
	}

	// Populates a parole hearing analysis task source
	private void populateParoleHearingAnalysisTaskSource(
			final ParoleHearingAnalysisTaskSource 
					paroleHearingAnalysisTaskSource, 
			final TaskTemplate taskTemplate,
			final ParoleHearingTaskCategory category) {
		paroleHearingAnalysisTaskSource.setTaskTemplate(taskTemplate);
		paroleHearingAnalysisTaskSource.setCategory(category);
	}
}
