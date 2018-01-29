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
import omis.hearinganalysis.domain.ParoleHearingAnalysisTaskSource;
import omis.task.domain.TaskTemplate;
import omis.task.domain.TaskTemplateGroup;

/**
 * Data access object for parole hearing analysis task source.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 10, 2018)
 * @since OMIS 3.0
 */
public interface ParoleHearingAnalysisTaskSourceDao 
		extends GenericDao<ParoleHearingAnalysisTaskSource> {

	/**
	 * Returns the parole hearing analysis task source that matches the 
	 * specified task template.
	 * 
	 * @param taskTemplate task template
	 * @return parole hearing analysis task source
	 */
	ParoleHearingAnalysisTaskSource find(TaskTemplate taskTemplate);

	/**
	 * Returns the parole hearing analysis task source that matches the 
	 * specified task template excluding the specified parole hearing analysis 
	 * task source.
	 * 
	 * @param taskTemplate task template
	 * @param excludedParoleHearingAnalysisTaskSource excluded parole hearing 
	 * analysis task source
	 * @return parole hearing analysis task source
	 */
	ParoleHearingAnalysisTaskSource findExcluding(TaskTemplate taskTemplate, 
			ParoleHearingAnalysisTaskSource 
				excludedParoleHearingAnalysisTaskSource);

	/**
	 * Returns a list of parole hearing analysis task sources for the specified 
	 * task template group.
	 * 
	 * @param taskTemplateGroup task template group
	 * @return list of parole hearing analysis task sources
	 */
	List<ParoleHearingAnalysisTaskSource> findByTaskTemplateGroup(
			TaskTemplateGroup taskTemplateGroup);

}
