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
import omis.hearinganalysis.dao.HearingAnalysisTaskAssociationDao;
import omis.hearinganalysis.domain.HearingAnalysis;
import omis.hearinganalysis.domain.HearingAnalysisTaskAssociation;
import omis.hearinganalysis.domain.ParoleHearingAnalysisTaskSource;
import omis.instance.factory.InstanceFactory;
import omis.task.domain.Task;
import omis.task.domain.TaskTemplate;

/**
 * Hearing analysis task association delegate.
 *
 * @author Josh Divine
 * @version 0.1.0 (Jan 10, 2018)
 * @since OMIS 3.0
 */
public class HearingAnalysisTaskAssociationDelegate {

	/* Data access objects. */
	
	private final HearingAnalysisTaskAssociationDao 
			hearingAnalysisTaskAssociationDao;

	/* Instance factories. */
	
	private final InstanceFactory<HearingAnalysisTaskAssociation> 
			hearingAnalysisTaskAssociationInstanceFactory;
	
	/**
	 * Instantiates an implementation of parole hearing analysis task source 
	 * delegate with the specified data access object and instance factory.
	 * 
	 * @param hearingAnalysisTaskAssociationDao parole hearing analysis task 
	 * source data access object
	 * @param hearingAnalysisTaskAssociationInstanceFactory parole hearing 
	 * analysis task source instance factory
	 */
	public HearingAnalysisTaskAssociationDelegate(
			final HearingAnalysisTaskAssociationDao 
					hearingAnalysisTaskAssociationDao,
			final InstanceFactory<HearingAnalysisTaskAssociation> 
					hearingAnalysisTaskAssociationInstanceFactory) {
		this.hearingAnalysisTaskAssociationDao = 
				hearingAnalysisTaskAssociationDao;
		this.hearingAnalysisTaskAssociationInstanceFactory = 
				hearingAnalysisTaskAssociationInstanceFactory;
	}
	
	/**
	 * Creates a new hearing analysis task association.
	 * 
	 * @param task task
	 * @param hearingAnalysis hearing analysis
	 * @param taskSource parole hearing analysis task source
	 * @return hearing analysis task association
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public HearingAnalysisTaskAssociation create(final Task task, 
			final HearingAnalysis hearingAnalysis,
			final ParoleHearingAnalysisTaskSource taskSource) 
					throws DuplicateEntityFoundException {
		if (this.hearingAnalysisTaskAssociationDao.find(task, hearingAnalysis,
				taskSource) != null) {
			throw new DuplicateEntityFoundException(
					"Hearing analysis task association already exists.");
		}
		HearingAnalysisTaskAssociation hearingAnalysisTaskAssociation = 
				this.hearingAnalysisTaskAssociationInstanceFactory
				.createInstance();
		populateHearingAnalysisTaskAssociation(hearingAnalysisTaskAssociation, 
				task, hearingAnalysis);
		hearingAnalysisTaskAssociation.setTaskSource(taskSource);
		return this.hearingAnalysisTaskAssociationDao.makePersistent(
				hearingAnalysisTaskAssociation);
	}

	/**
	 * Updates an existing hearing analysis task association.
	 * 
	 * @param hearingAnalysisTaskAssociation hearing analysis task association
	 * @param task task
	 * @param hearingAnalysis hearing analysis
	 * @param taskSource parole hearing analysis task source
	 * @return hearing analysis task association
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public HearingAnalysisTaskAssociation update(
			final HearingAnalysisTaskAssociation hearingAnalysisTaskAssociation,
			final Task task, final HearingAnalysis hearingAnalysis) 
					throws DuplicateEntityFoundException {
		if (this.hearingAnalysisTaskAssociationDao.findExcluding(task, 
				hearingAnalysis, hearingAnalysisTaskAssociation.getTaskSource(),
				hearingAnalysisTaskAssociation) != null) {
			throw new DuplicateEntityFoundException(
					"Hearing analysis task association already exists.");
		}
		populateHearingAnalysisTaskAssociation(hearingAnalysisTaskAssociation, 
				task, hearingAnalysis);
		return this.hearingAnalysisTaskAssociationDao.makePersistent(
				hearingAnalysisTaskAssociation);
	}

	/**
	 * Removes an existing hearing analysis task association.
	 * 
	 * @param hearingAnalysisTaskAssociation hearing analysis task association
	 */
	public void remove(
			final HearingAnalysisTaskAssociation hearingAnalysisTaskAssociation) {
		this.hearingAnalysisTaskAssociationDao.makeTransient(
				hearingAnalysisTaskAssociation);
	}

	/**
	 * Returns a list of hearing analysis task associations for the specified 
	 * hearing analysis.
	 * 
	 * @param hearingAnalysis hearing analysis
	 * @return list of hearing analysis task associations
	 */
	public List<HearingAnalysisTaskAssociation> findByHearingAnalysis(
			final HearingAnalysis hearingAnalysis) {
		return this.hearingAnalysisTaskAssociationDao.findByHearingAnalysis(
				hearingAnalysis);
	}

	/**
	 * Returns the task for the specified task template and hearing analysis.
	 * 
	 * @param taskTemplate task template
	 * @param hearingAnalysis hearing analysis
	 * @return task
	 */
	public Task findTaskByTaskTemplateAndHearingAnalysis(
			final TaskTemplate taskTemplate, 
			final HearingAnalysis hearingAnalysis) {
		return this.hearingAnalysisTaskAssociationDao
				.findTaskByTaskTemplateAndHearingAnalysis(taskTemplate, 
						hearingAnalysis);
	}

	// Populates hearing analysis task association
	private void populateHearingAnalysisTaskAssociation(
			final HearingAnalysisTaskAssociation hearingAnalysisTaskAssociation, 
			final Task task, final HearingAnalysis hearingAnalysis) {
		hearingAnalysisTaskAssociation.setTask(task);
		hearingAnalysisTaskAssociation.setHearingAnalysis(hearingAnalysis);
	}
}
