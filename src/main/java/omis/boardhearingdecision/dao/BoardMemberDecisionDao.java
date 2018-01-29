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

import java.util.List;

import omis.boardhearing.domain.BoardHearingParticipant;
import omis.boardhearingdecision.domain.BoardHearingDecision;
import omis.boardhearingdecision.domain.BoardMemberDecision;
import omis.boardhearingdecision.domain.HearingDecisionReason;
import omis.dao.GenericDao;

/**
 * Data access object for board member decision.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 22, 2018)
 * @since OMIS 3.0
 */
public interface BoardMemberDecisionDao 
	extends GenericDao<BoardMemberDecision> {

	/**
	 * Returns the board member decision with the specified board hearing 
	 * decision, board hearing participant and hearing decision reason.
	 * 
	 * @param boardDecision board hearing decision
	 * @param boardHearingParticipant board hearing participant
	 * @param decisionReason hearing decision reason
	 * @return board member decision
	 */
	BoardMemberDecision find(BoardHearingDecision boardDecision, 
			BoardHearingParticipant boardHearingParticipant,
			HearingDecisionReason decisionReason);

	/**
	 * Returns the board member decision with the specified board hearing 
	 * decision, board hearing participant and hearing decision reason excluding 
	 * the specified board member decision.
	 * 
	 * @param boardDecision board hearing decision
	 * @param boardHearingParticipant board hearing participant
	 * @param decisionReason hearing decision reason
	 * @param excludedBoardMemberDecision board member decision
	 * @return board member decision
	 */
	BoardMemberDecision findExcluding(BoardHearingDecision boardDecision, 
			BoardHearingParticipant boardHearingParticipant,
			HearingDecisionReason decisionReason, 
			BoardMemberDecision excludedBoardMemberDecision);

	/**
	 * Returns a list of board member decisions for the specified board hearing 
	 * decision.
	 * 
	 * @param boardDecision board hearing decision
	 * @return list of board member decisions
	 */
	List<BoardMemberDecision> findByBoardDecision(
			BoardHearingDecision boardDecision);

}
