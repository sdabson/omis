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
package omis.boardhearingdecision.dao;

import java.util.Date;
import java.util.List;

import omis.boardhearingdecision.domain.BoardHearingDecision;
import omis.boardhearingdecision.domain.HearingDecisionNote;
import omis.dao.GenericDao;

/**
 * Data access object for hearing decision note.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 22, 2018)
 * @since OMIS 3.0
 */
public interface HearingDecisionNoteDao 
	extends GenericDao<HearingDecisionNote> {

	/**
	 * Returns the hearing decision note that matches the specified board 
	 * hearing decision, date and description.
	 * 
	 * @param boardDecision board hearing decision
	 * @param date date
	 * @param description description
	 * @return hearing decision note
	 */
	HearingDecisionNote find(BoardHearingDecision boardDecision, Date date, 
			String description);

	/**
	 * Returns the hearing decision note that matches the specified board 
	 * hearing decision, date and description excluding the specified hearing 
	 * decision note.
	 * 
	 * @param boardDecision board hearing decision
	 * @param date date
	 * @param description description
	 * @param excludedHearingDecisionNote hearing decision note
	 * @return hearing decision note
	 */
	HearingDecisionNote findExcluding(BoardHearingDecision boardDecision, 
			Date date, String description, 
			HearingDecisionNote excludedHearingDecisionNote);

	/**
	 * Returns a list of hearing decision notes for the specified board hearing 
	 * decision.
	 * 
	 * @param boardDecision board hearing decision
	 * @return list of hearing decision notes
	 */
	List<HearingDecisionNote> findByBoardDecision(
			BoardHearingDecision boardDecision);

}
