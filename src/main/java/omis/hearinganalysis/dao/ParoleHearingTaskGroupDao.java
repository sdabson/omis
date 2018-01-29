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
package omis.hearinganalysis.dao;

import omis.dao.GenericDao;
import omis.hearinganalysis.domain.HearingAnalysisCategory;
import omis.hearinganalysis.domain.ParoleHearingTaskGroup;
import omis.task.domain.TaskTemplateGroup;

/**
 * Data access object for parole hearing analysis task source.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 10, 2018)
 * @since OMIS 3.0
 */
public interface ParoleHearingTaskGroupDao 
		extends GenericDao<ParoleHearingTaskGroup> {

	/**
	 * Returns the parole hearing task group that matches the specified task 
	 * template group and hearing analysis category.
	 * 
	 * @param group task template group
	 * @param hearingAnalysisCategory hearing analysis category
	 * @return  parole hearing task group
	 */
	ParoleHearingTaskGroup find(TaskTemplateGroup group, 
			HearingAnalysisCategory hearingAnalysisCategory);
	
	/**
	 * Returns the parole hearing task group that matches the specified task 
	 * template group and hearing analysis category excluding the specified 
	 * parole hearing task group.
	 * 
	 * @param group task template group
	 * @param hearingAnalysisCategory hearing analysis category
	 * @param excludedParoleHearingTaskGroup excluded parole hearing task group
	 * @return  parole hearing task group
	 */
	ParoleHearingTaskGroup findExcluding(TaskTemplateGroup group, 
			HearingAnalysisCategory hearingAnalysisCategory,
			ParoleHearingTaskGroup excludedParoleHearingTaskGroup);
	
	/**
	 * Returns the parole hearing task group for the specified hearing analysis 
	 * category.
	 * 
	 * @param hearingAnalysisCategory hearing analysis category
	 * @return parole hearing task group
	 */
	ParoleHearingTaskGroup findByHearingAnalysisCategory(
			HearingAnalysisCategory hearingAnalysisCategory);

}
