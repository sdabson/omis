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
package omis.boardhearingdecision.service.impl;

import java.util.Date;
import java.util.List;

import omis.boardhearing.domain.BoardHearing;
import omis.boardhearing.domain.BoardHearingParticipant;
import omis.boardhearing.service.delegate.BoardHearingParticipantDelegate;
import omis.boardhearingdecision.domain.BoardHearingDecision;
import omis.boardhearingdecision.domain.BoardHearingDecisionCategory;
import omis.boardhearingdecision.domain.BoardMemberDecision;
import omis.boardhearingdecision.domain.DecisionCategory;
import omis.boardhearingdecision.domain.HearingDecisionNote;
import omis.boardhearingdecision.domain.HearingDecisionReason;
import omis.boardhearingdecision.service.BoardHearingDecisionService;
import omis.boardhearingdecision.service.delegate.BoardHearingDecisionCategoryDelegate;
import omis.boardhearingdecision.service.delegate.BoardHearingDecisionDelegate;
import omis.boardhearingdecision.service.delegate.BoardMemberDecisionDelegate;
import omis.boardhearingdecision.service.delegate.HearingDecisionNoteDelegate;
import omis.boardhearingdecision.service.delegate.HearingDecisionReasonDelegate;
import omis.exception.DuplicateEntityFoundException;

/**
 * @author CID017
 * @version 0.1.0 (Jan 22, 2018)
 * @since OMIS 3.0
 */
public class BoardHearingDecisionServiceImpl 
	implements BoardHearingDecisionService {

	private final BoardHearingDecisionDelegate boardHearingDecisionDelegate;
	
	private final BoardMemberDecisionDelegate boardMemberDecisionDelegate;
	
	private final HearingDecisionNoteDelegate hearingDecisionNoteDelegate;
	
	private final BoardHearingParticipantDelegate 
			boardHearingParticipantDelegate;
	
	private final BoardHearingDecisionCategoryDelegate 
			boardHearingDecisionCategoryDelegate;
	
	private final HearingDecisionReasonDelegate hearingDecisionReasonDelegate;
	
	/**
	 * Instantiates a board hearing decision service implementation with the 
	 * specified delegates.
	 * 
	 * @param boardHearingDecisionDelegate board hearing decision delegate
	 * @param boardMemberDecisionDelegate board member decision delegate
	 * @param hearingDecisionNoteDelegate hearing decision note delegate
	 * @param boardHearingParticipantDelegate board hearing participant delegate
	 * @param boardHearingDecisionCategoryDelegate board hearing decision 
	 * category delegate
	 * @param hearingDecisionReasonDelegate hearing decision reason delegate
	 */
	public BoardHearingDecisionServiceImpl(
			final BoardHearingDecisionDelegate boardHearingDecisionDelegate,
			final BoardMemberDecisionDelegate boardMemberDecisionDelegate,
			final HearingDecisionNoteDelegate hearingDecisionNoteDelegate,
			final BoardHearingParticipantDelegate
					boardHearingParticipantDelegate,
			final BoardHearingDecisionCategoryDelegate 
					boardHearingDecisionCategoryDelegate,
			final HearingDecisionReasonDelegate hearingDecisionReasonDelegate) {
		this.boardHearingDecisionDelegate = boardHearingDecisionDelegate;
		this.boardMemberDecisionDelegate = boardMemberDecisionDelegate;
		this.hearingDecisionNoteDelegate = hearingDecisionNoteDelegate;
		this.boardHearingParticipantDelegate = boardHearingParticipantDelegate;
		this.boardHearingDecisionCategoryDelegate = 
				boardHearingDecisionCategoryDelegate;
		this.hearingDecisionReasonDelegate = hearingDecisionReasonDelegate;
	}
	
	/**{@inheritDoc} */
	@Override
	public BoardHearingDecision createBoardHearingDecision(
			final BoardHearing hearing, 
			final BoardHearingDecisionCategory category) 
					throws DuplicateEntityFoundException {
		return this.boardHearingDecisionDelegate.create(hearing, category);
	}

	/**{@inheritDoc} */
	@Override
	public BoardHearingDecision updateBoardHearingDecision(
			final BoardHearingDecision boardDecision, 
			final BoardHearingDecisionCategory category) 
					throws DuplicateEntityFoundException {
		return this.boardHearingDecisionDelegate.update(boardDecision,
				boardDecision.getHearing(), category);
	}

	/**{@inheritDoc} */
	@Override
	public void removeBoardHearingDecision(
			final BoardHearingDecision boardDecision) {
		this.boardHearingDecisionDelegate.remove(boardDecision);
	}

	/**{@inheritDoc} */
	@Override
	public BoardMemberDecision createBoardMemberDecision(
			final BoardHearingDecision boardDecision,
			final BoardHearingParticipant boardHearingParticipant, 
			final HearingDecisionReason decisionReason, final String comments)
					throws DuplicateEntityFoundException {
		return this.boardMemberDecisionDelegate.create(boardDecision, 
				boardHearingParticipant, decisionReason, comments);
	}

	/**{@inheritDoc} */
	@Override
	public BoardMemberDecision updateBoardMemberDecision(
			final BoardMemberDecision boardMemberDecision,
			final BoardHearingParticipant boardHearingParticipant,
			final HearingDecisionReason decisionReason, 
			final String comments) throws DuplicateEntityFoundException {
		return this.boardMemberDecisionDelegate.update(boardMemberDecision,
				boardMemberDecision.getBoardDecision(),
				boardHearingParticipant, decisionReason, comments);
	}

	/**{@inheritDoc} */
	@Override
	public void removeBoardMemberDecision(
			final BoardMemberDecision boardMemberDecision) {
		this.boardMemberDecisionDelegate.remove(boardMemberDecision);
	}

	/**{@inheritDoc} */
	@Override
	public HearingDecisionNote createHearingDecisionNote(
			final BoardHearingDecision boardDecision, final Date date,
			final String description) throws DuplicateEntityFoundException {
		return this.hearingDecisionNoteDelegate.create(boardDecision, date, 
				description);
	}

	/**{@inheritDoc} */
	@Override
	public HearingDecisionNote updateHearingDecisionNote(
			final HearingDecisionNote hearingDecisionNote,
			final Date date, final String description) 
					throws DuplicateEntityFoundException {
		return this.hearingDecisionNoteDelegate.update(hearingDecisionNote, 
				hearingDecisionNote.getBoardDecision(), date, description);
	}

	/**{@inheritDoc} */
	@Override
	public void removeHearingDecisionNote(
			final HearingDecisionNote hearingDecisionNote) {
		this.hearingDecisionNoteDelegate.remove(hearingDecisionNote);
	}

	/**{@inheritDoc} */
	@Override
	public BoardHearingDecision findBoardHearingDecisionByHearing(
			final BoardHearing hearing) {
		return this.boardHearingDecisionDelegate.findByHearing(hearing);
	}

	/**{@inheritDoc} */
	@Override
	public List<BoardHearingParticipant> findBoardHearingParticipantsByHearing(
			final BoardHearing hearing) {
		return this.boardHearingParticipantDelegate.findByHearing(hearing);
	}

	/**{@inheritDoc} */
	@Override
	public List<BoardMemberDecision> 
			findBoardMemberDecisionsByBoardHearingDecision(
					final BoardHearingDecision boardDecision) {
		return this.boardMemberDecisionDelegate.findByBoardDecision(
				boardDecision);
	}

	/**{@inheritDoc} */
	@Override
	public List<HearingDecisionNote> 
			findHearingDecisionNotesByBoardHearingDecision(
					final BoardHearingDecision boardDecision) {
		return this.hearingDecisionNoteDelegate.findByBoardDecision(
				boardDecision);
	}

	/**{@inheritDoc} */
	@Override
	public List<BoardHearingDecisionCategory> 
			findBoardHearingDecisionCategoriesByDecision(
					final DecisionCategory decision) {
		return this.boardHearingDecisionCategoryDelegate.findByDecision(
				decision);
	}

	/**{@inheritDoc} */
	@Override
	public List<HearingDecisionReason> 
			findHearingDecisionReasonsByDecisionCategory(
					final DecisionCategory decisionCategory) {
		return this.hearingDecisionReasonDelegate.findByDecisionCategory(
				decisionCategory);
	}

}
