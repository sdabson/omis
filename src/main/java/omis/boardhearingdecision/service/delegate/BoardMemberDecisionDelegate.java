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
package omis.boardhearingdecision.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.boardhearing.domain.BoardHearingParticipant;
import omis.boardhearingdecision.dao.BoardMemberDecisionDao;
import omis.boardhearingdecision.domain.BoardHearingDecision;
import omis.boardhearingdecision.domain.BoardMemberDecision;
import omis.boardhearingdecision.domain.HearingDecisionReason;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for board member decision.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 22, 2018)
 * @since OMIS 3.0
 */
public class BoardMemberDecisionDelegate {

	/* Data access objects. */
	
	private final BoardMemberDecisionDao boardMemberDecisionDao;

	/* Instance factories. */
	
	private final InstanceFactory<BoardMemberDecision> 
			boardMemberDecisionInstanceFactory;
	
	/* Component Retrievers. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates an implementation of board member decision delegate with the 
	 * specified data access object, instance factory and audit component 
	 * retriever.
	 * 
	 * @param boardMemberDecisionDao board member decision data access object
	 * @param boardMemberDecisionInstanceFactory board member decision instance 
	 * factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public BoardMemberDecisionDelegate(
			final BoardMemberDecisionDao boardMemberDecisionDao,
			final InstanceFactory<BoardMemberDecision> 
					boardMemberDecisionInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.boardMemberDecisionDao = boardMemberDecisionDao;
		this.boardMemberDecisionInstanceFactory = 
				boardMemberDecisionInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
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
	public BoardMemberDecision create(final BoardHearingDecision boardDecision,
			final BoardHearingParticipant boardHearingParticipant, 
			final HearingDecisionReason decisionReason, final String comments) 
					throws DuplicateEntityFoundException {
		if (this.boardMemberDecisionDao.find(boardDecision, 
				boardHearingParticipant, decisionReason) != null) {
			throw new DuplicateEntityFoundException(
					"Board member decision already exists.");
		}
		BoardMemberDecision boardMemberDecision = 
				this.boardMemberDecisionInstanceFactory.createInstance();
		populateBoardMemberDecision(boardMemberDecision, boardDecision, 
				boardHearingParticipant, decisionReason, comments);
		boardMemberDecision.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		return this.boardMemberDecisionDao.makePersistent(boardMemberDecision);
	}

	/**
	 * Updates a board member decision with the specified board hearing 
	 * decision, board hearing participant, hearing decision reasons and 
	 * comments.
	 * 
	 * @param boardMemberDecision board member decision
	 * @param boardDecision board hearing decision
	 * @param boardHearingParticipant board hearing participant
	 * @param decisionReason hearing decision reason
	 * @param comments comments
	 * @return board member decision
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public BoardMemberDecision update(
			final BoardMemberDecision boardMemberDecision, 
			final BoardHearingDecision boardDecision,
			final BoardHearingParticipant boardHearingParticipant, 
			final HearingDecisionReason decisionReason, final String comments) 
					throws DuplicateEntityFoundException {
		if (this.boardMemberDecisionDao.findExcluding(boardDecision, 
				boardHearingParticipant, decisionReason, boardMemberDecision) 
				!= null) {
			throw new DuplicateEntityFoundException(
					"Board member decision already exists.");
		}
		populateBoardMemberDecision(boardMemberDecision, boardDecision, 
				boardHearingParticipant, decisionReason, comments);
		return this.boardMemberDecisionDao.makePersistent(boardMemberDecision);
	}

	/**
	 * Removes the specified board member decision.
	 * 
	 * @param boardMemberDecision board member decision
	 */
	public void remove(BoardMemberDecision boardMemberDecision) {
		this.boardMemberDecisionDao.makeTransient(boardMemberDecision);
	}

	/**
	 * Returns a list of board member decisions for the specified board hearing 
	 * decision.
	 * 
	 * @param boardDecision board hearing decision
	 * @return list of board member decisions
	 */
	public List<BoardMemberDecision> findByBoardDecision(
			final BoardHearingDecision boardDecision) {
		return this.boardMemberDecisionDao.findByBoardDecision(boardDecision);
	}
	
	//Populates a board member decision
	private void populateBoardMemberDecision(
			final BoardMemberDecision boardMemberDecision,
			final BoardHearingDecision boardDecision, 
			final BoardHearingParticipant boardHearingParticipant,
			final HearingDecisionReason decisionReason, final String comments) {
		boardMemberDecision.setBoardDecision(boardDecision);
		boardMemberDecision.setBoardHearingParticipant(boardHearingParticipant);
		boardMemberDecision.setDecisionReason(decisionReason);
		boardMemberDecision.setComments(comments);
		boardMemberDecision.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
	}

}
