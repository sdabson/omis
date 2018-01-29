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

import java.util.List;

import omis.dao.GenericDao;
import omis.hearinganalysis.domain.HearingAnalysis;
import omis.hearinganalysis.domain.HearingAnalysisTaskAssociation;
import omis.hearinganalysis.domain.ParoleHearingAnalysisTaskSource;
import omis.task.domain.Task;
import omis.task.domain.TaskTemplate;

/**
 * Data access object for hearing analysis task association.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 10, 2018)
 * @since OMIS 3.0
 */
public interface HearingAnalysisTaskAssociationDao 
		extends GenericDao<HearingAnalysisTaskAssociation> {

	/**
	 * Returns the hearing analysis task association matching the specified 
	 * task, task source, and hearing analysis.
	 * 
	 * @param task task
	 * @param hearingAnalysis hearing analysis
	 * @param taskSource task source
	 * @return hearing analysis task association
	 */
	HearingAnalysisTaskAssociation find(Task task, 
			HearingAnalysis hearingAnalysis,
			ParoleHearingAnalysisTaskSource taskSource);
	
	/**
	 * Returns the hearing analysis task association matching the specified 
	 * task, task source, and hearing analysis excluding the hearing analysis 
	 * task association.
	 * 
	 * @param task task
	 * @param hearingAnalysis hearing analysis
	 * @param taskSource task source
	 * @param excludedHearingAnalysisTaskAssociation excluded hearing analysis 
	 * task association
	 * @return hearing analysis task association
	 */
	HearingAnalysisTaskAssociation findExcluding(Task task, 
			HearingAnalysis hearingAnalysis,
			ParoleHearingAnalysisTaskSource taskSource,
			HearingAnalysisTaskAssociation 
					excludedHearingAnalysisTaskAssociation);
	
	/**
	 * Returns a list of hearing analysis task associations for the specified 
	 * hearing analysis.
	 * 
	 * @param hearingAnalysis hearing analysis
	 * @return list of hearing analysis task associations
	 */
	List<HearingAnalysisTaskAssociation> findByHearingAnalysis(
			HearingAnalysis hearingAnalysis);
	
	/**
	 * Returns the task for the specified task template and hearing analysis.
	 * 
	 * @param taskTemplate task template
	 * @param hearingAnalysis hearing analysis
	 * @return task
	 */
	Task findTaskByTaskTemplateAndHearingAnalysis(TaskTemplate taskTemplate, 
			HearingAnalysis hearingAnalysis);
}
