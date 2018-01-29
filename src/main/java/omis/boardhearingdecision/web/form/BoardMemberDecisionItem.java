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
package omis.boardhearingdecision.web.form;

import java.io.Serializable;

import omis.boardhearing.domain.BoardHearingParticipant;
import omis.boardhearingdecision.domain.BoardMemberDecision;
import omis.boardhearingdecision.domain.DecisionCategory;
import omis.boardhearingdecision.domain.HearingDecisionReason;

/**
 * @author Josh Divine
 * @version 0.1.0 (Jan 23, 2018)
 * @since OMIS 3.0
 */
public class BoardMemberDecisionItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private BoardMemberDecision boardMemberDecision;
	
	private BoardHearingParticipant boardHearingParticipant;
	
	private DecisionCategory category;
	
	private HearingDecisionReason decisionReason;
	
	private String comments;
	
	/** 
	 * Instantiates a default board member decision on a board hearing decision 
	 * form. 
	 */
	public BoardMemberDecisionItem() {
		// Default instantiation
	}

	/**
	 * Returns the board member decision.
	 *
	 * @return board member decision
	 */
	public BoardMemberDecision getBoardMemberDecision() {
		return boardMemberDecision;
	}

	/**
	 * Sets the board member decision.
	 *
	 * @param boardMemberDecision board member decision
	 */
	public void setBoardMemberDecision(
			final BoardMemberDecision boardMemberDecision) {
		this.boardMemberDecision = boardMemberDecision;
	}

	/**
	 * Returns the board hearing participant.
	 *
	 * @return board hearing participant
	 */
	public BoardHearingParticipant getBoardHearingParticipant() {
		return boardHearingParticipant;
	}

	/**
	 * Sets the board hearing participant.
	 *
	 * @param boardHearingParticipant board hearing participant
	 */
	public void setBoardHearingParticipant(
			final BoardHearingParticipant boardHearingParticipant) {
		this.boardHearingParticipant = boardHearingParticipant;
	}

	/**
	 * Returns the decision category.
	 *
	 * @return decision category
	 */
	public DecisionCategory getCategory() {
		return category;
	}

	/**
	 * Sets the decision category.
	 *
	 * @param category decision category
	 */
	public void setCategory(DecisionCategory category) {
		this.category = category;
	}

	/**
	 * Returns the hearing decision reason.
	 *
	 * @return hearing decision reason
	 */
	public HearingDecisionReason getDecisionReason() {
		return decisionReason;
	}

	/**
	 * Sets the hearing decision reason.
	 *
	 * @param decisionReason hearing decision reason
	 */
	public void setDecisionReason(HearingDecisionReason decisionReason) {
		this.decisionReason = decisionReason;
	}

	/**
	 * Returns the comments.
	 *
	 * @return comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

}
