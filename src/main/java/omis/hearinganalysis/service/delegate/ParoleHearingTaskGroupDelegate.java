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

import omis.exception.DuplicateEntityFoundException;
import omis.hearinganalysis.dao.ParoleHearingTaskGroupDao;
import omis.hearinganalysis.domain.HearingAnalysisCategory;
import omis.hearinganalysis.domain.ParoleHearingTaskGroup;
import omis.instance.factory.InstanceFactory;
import omis.task.domain.TaskTemplateGroup;

/**
 * Parole hearing task group delegate.
 *
 * @author Josh Divine
 * @version 0.1.0 (Jan 10, 2018)
 * @since OMIS 3.0
 */
public class ParoleHearingTaskGroupDelegate {

	/* Data access objects. */
	
	private final ParoleHearingTaskGroupDao paroleHearingTaskGroupDao;

	/* Instance factories. */
	
	private final InstanceFactory<ParoleHearingTaskGroup> 
			paroleHearingTaskGroupInstanceFactory;
	
	/**
	 * Instantiates an implementation of parole hearing task group delegate with 
	 * the specified data access object and instance factory.
	 * 
	 * @param paroleHearingTaskGroupDao parole hearing task group data access 
	 * object
	 * @param paroleHearingTaskGroupInstanceFactory parole hearing task group 
	 * instance factory
	 */
	public ParoleHearingTaskGroupDelegate(
			final ParoleHearingTaskGroupDao 
					paroleHearingTaskGroupDao,
			final InstanceFactory<ParoleHearingTaskGroup> 
					paroleHearingTaskGroupInstanceFactory) {
		this.paroleHearingTaskGroupDao = paroleHearingTaskGroupDao;
		this.paroleHearingTaskGroupInstanceFactory = 
				paroleHearingTaskGroupInstanceFactory;
	}
	
	/**
	 * Creates a new parole hearing task group.
	 * 
	 * @param group task template group
	 * @param hearingAnalysisCategory hearing analysis category
	 * @return parole hearing task group
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public ParoleHearingTaskGroup create(final TaskTemplateGroup group, 
			final HearingAnalysisCategory hearingAnalysisCategory) 
					throws DuplicateEntityFoundException {
		if (this.paroleHearingTaskGroupDao.find(group, hearingAnalysisCategory) 
				!= null) {
			throw new DuplicateEntityFoundException(
					"Parole hearing task group already exists.");
		}
		ParoleHearingTaskGroup paroleHearingTaskGroup = this
				.paroleHearingTaskGroupInstanceFactory.createInstance();
		populateParoleHearingTaskGroup(paroleHearingTaskGroup, group, 
				hearingAnalysisCategory);
		return this.paroleHearingTaskGroupDao.makePersistent(
				paroleHearingTaskGroup);
	}
	
	/**
	 * Updates an existing parole hearing task group.
	 * 
	 * @param paroleHearingTaskGroup parole hearing task group
	 * @param group task template group
	 * @param hearingAnalysisCategory hearing analysis category
	 * @return parole hearing task group
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public ParoleHearingTaskGroup update(
			final ParoleHearingTaskGroup paroleHearingTaskGroup, 
			final TaskTemplateGroup group, 
			final HearingAnalysisCategory hearingAnalysisCategory) 
					throws DuplicateEntityFoundException {
		if (this.paroleHearingTaskGroupDao.findExcluding(group, 
				hearingAnalysisCategory, paroleHearingTaskGroup) != null) {
			throw new DuplicateEntityFoundException(
					"Parole hearing task group already exists.");
		}
		populateParoleHearingTaskGroup(paroleHearingTaskGroup, group, 
				hearingAnalysisCategory);
		return this.paroleHearingTaskGroupDao.makePersistent(
				paroleHearingTaskGroup);
	}

	/**
	 * Removes an existing parole hearing task group.
	 * 
	 * @param paroleHearingTaskGroup parole hearing task group
	 */
	public void remove(final ParoleHearingTaskGroup paroleHearingTaskGroup) {
		this.paroleHearingTaskGroupDao.makeTransient(paroleHearingTaskGroup);
	}
	
	/**
	 * Returns the parole hearing task group for the specified hearing analysis 
	 * category.
	 * 
	 * @param hearingAnalysisCategory hearing analysis category
	 * @return parole hearing task group
	 */
	public ParoleHearingTaskGroup findByHearingAnalysisCategory(
			final HearingAnalysisCategory hearingAnalysisCategory) {
		return this.paroleHearingTaskGroupDao.findByHearingAnalysisCategory(
				hearingAnalysisCategory);
	}
	
	// Populates a parole hearing task group
	private void populateParoleHearingTaskGroup(
			final ParoleHearingTaskGroup paroleHearingTaskGroup,
			final TaskTemplateGroup group, 
			final HearingAnalysisCategory hearingAnalysisCategory) {
		paroleHearingTaskGroup.setGroup(group);
		paroleHearingTaskGroup.setHearingAnalysisCategory(
				hearingAnalysisCategory);
	}
}
