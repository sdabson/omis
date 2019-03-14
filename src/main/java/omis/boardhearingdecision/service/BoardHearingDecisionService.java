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
package omis.boardhearingdecision.service;

import java.util.Date;
import java.util.List;

import omis.boardhearing.domain.BoardHearing;
import omis.boardhearing.domain.BoardHearingParticipant;
import omis.boardhearingdecision.domain.BoardHearingDecision;
import omis.boardhearingdecision.domain.BoardHearingDecisionCategory;
import omis.boardhearingdecision.domain.BoardMemberDecision;
import omis.boardhearingdecision.domain.DecisionCategory;
import omis.boardhearingdecision.domain.HearingDecisionNote;
import omis.boardhearingdecision.domain.HearingDecisionReason;
import omis.exception.DuplicateEntityFoundException;

/**
 * Board hearing decision service.
 *
 * @author Josh Divine
 * @author Annie Wahl
 * @version 0.1.1 (Mar 13, 2019)
 * @since OMIS 3.0
 */
public interface BoardHearingDecisionService {

	/**
	 * Creates a board hearing decision with the specified board hearing and 
	 * board hearing decision category.
	 * 
	 * @param hearing board hearing
	 * @param category board hearing decision category
	 * @param rulingDetails ruling details
	 * @return board hearing decision
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	BoardHearingDecision createBoardHearingDecision(BoardHearing hearing, 
			BoardHearingDecisionCategory category, String rulingDetails) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates a board hearing decision with the specified board hearing and 
	 * board hearing decision category.
	 * 
	 * @param boardDecision board hearing decision
	 * @param category board hearing decision category
	 * @param rulingDetails ruling details
	 * @return board hearing decision
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	BoardHearingDecision updateBoardHearingDecision(
			BoardHearingDecision boardDecision, 
			BoardHearingDecisionCategory category, String rulingDetails) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified board hearing decision.
	 * 
	 * @param boardDecision board hearing decision
	 */
	void removeBoardHearingDecision(BoardHearingDecision boardDecision);
	
	/**
	 * Creates a board member decision with the specified board hearing 
	 * decision, board hearing participant, hearing decision reasons and 
	 * comments.
	 * 
	 * @param boardDecision board hearing decision
	 * @param boardHearingParticipant board hearing participant
	 * @param decisionReason hearing decision reason
	 * @param comments comments
	 * @return board member decision
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	BoardMemberDecision createBoardMemberDecision(
			BoardHearingDecision boardDecision, 
			BoardHearingParticipant boardHearingParticipant, 
			HearingDecisionReason decisionReason, String comments) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates a board member decision with the specified board hearing 
	 * decision, board hearing participant, hearing decision reasons and 
	 * comments.
	 * 
	 * @param boardMemberDecision board member decision
	 * @param boardHearingParticipant board hearing participant
	 * @param decisionReason hearing decision reason
	 * @param comments comments
	 * @return board member decision
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	BoardMemberDecision updateBoardMemberDecision(
			BoardMemberDecision boardMemberDecision, 
			BoardHearingParticipant boardHearingParticipant, 
			HearingDecisionReason decisionReason, String comments) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified board member decision.
	 * 
	 * @param boardMemberDecision board member decision
	 */
	void removeBoardMemberDecision(BoardMemberDecision boardMemberDecision);
	
	/**
	 * Creates a hearing decision note with the specified board hearing 
	 * decision, date and description.
	 * 
	 * @param boardDecision board hearing decision
	 * @param date date
	 * @param description description
	 * @return hearing decision note
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	HearingDecisionNote createHearingDecisionNote(
			BoardHearingDecision boardDecision, Date date, String description) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates a hearing decision note with the specified board hearing 
	 * decision, date and description.
	 * 
	 * @param hearingDecisionNote hearing decision note
	 * @param date date
	 * @param description description
	 * @return hearing decision note
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	HearingDecisionNote updateHearingDecisionNote(
			HearingDecisionNote hearingDecisionNote, 
			Date date, String description) throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified hearing decision note.
	 * 
	 * @param hearingDecisionNote hearing decision note
	 */
	void removeHearingDecisionNote(HearingDecisionNote hearingDecisionNote);
	
	/**
	 * Returns the board hearing decision for the specified board hearing.
	 * 
	 * @param hearing board hearing
	 * @return board hearing decision
	 */
	BoardHearingDecision findBoardHearingDecisionByHearing(
			BoardHearing hearing);
	
	/**
	 * Returns a list of board hearing participants for the specified board 
	 * hearing.
	 * 
	 * @param hearing board hearing
	 * @return list of board hearing participants
	 */
	List<BoardHearingParticipant> findBoardHearingParticipantsByHearing(
			BoardHearing hearing);
	
	/**
	 * Returns a list of board member decisions for the specified board hearing 
	 * decision.
	 * 
	 * @param boardDecision board hearing decision
	 * @return list of board member decisions
	 */
	List<BoardMemberDecision> findBoardMemberDecisionsByBoardHearingDecision(
			BoardHearingDecision boardDecision);
	
	/**
	 * Returns a list of hearing decision notes for the specified board hearing 
	 * decision.
	 * 
	 * @param boardDecision board hearing decision
	 * @return list of hearing decision notes
	 */
	List<HearingDecisionNote> findHearingDecisionNotesByBoardHearingDecision(
			BoardHearingDecision boardDecision);
	
	/**
	 * Returns a list of board hearing decision categories for the specified 
	 * decision category.
	 * 
	 * @param decision decision category
	 * @return list of board hearing decision categories
	 */
	List<BoardHearingDecisionCategory> 
			findBoardHearingDecisionCategoriesByDecision(
					DecisionCategory decision);
	
	/**
	 * Returns a list of hearing decision reasons for the specified decision 
	 * category.
	 * 
	 * @param decisionCategory decision category
	 * @return list of hearing decision reasons
	 */
	List<HearingDecisionReason> findHearingDecisionReasonsByDecisionCategory(
			DecisionCategory decisionCategory);
}
