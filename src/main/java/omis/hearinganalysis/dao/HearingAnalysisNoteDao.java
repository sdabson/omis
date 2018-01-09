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

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.hearinganalysis.domain.HearingAnalysis;
import omis.hearinganalysis.domain.HearingAnalysisNote;

/**
 * Data access object for hearing analysis note.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Dec 18, 2017)
 * @since OMIS 3.0
 */
public interface HearingAnalysisNoteDao 
	extends GenericDao<HearingAnalysisNote> {

	/**
	 * Returns the matching hearing analysis note for the specified hearing 
	 * analysis, date, and description.
	 * 
	 * @param hearingAnalysis hearing analysis
	 * @param date date
	 * @param description description
	 * @return hearing analysis
	 */
	HearingAnalysisNote find(HearingAnalysis hearingAnalysis, Date date, 
			String description);
	
	/**
	 * Returns the matching hearing analysis note for the specified hearing 
	 * analysis, date, and description excluding the specified hearing analysis 
	 * note.
	 * 
	 * @param hearingAnalysis hearing analysis
	 * @param date date
	 * @param description description
	 * @param excludedNote excluded hearing analysis note
	 * @return hearing analysis
	 */
	HearingAnalysisNote findExcluding(HearingAnalysis hearingAnalysis, 
			Date date, String description, HearingAnalysisNote excludedNote);

	/**
	 * Returns a list of hearing analysis notes for the specified hearing 
	 * analysis.
	 * 
	 * @param hearingAnalysis hearing analysis
	 * @return list of hearing analysis notes
	 */
	List<HearingAnalysisNote> findByHearingAnalysis(
			HearingAnalysis hearingAnalysis);
	
}
