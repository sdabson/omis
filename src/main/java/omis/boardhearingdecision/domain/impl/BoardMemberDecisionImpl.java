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
package omis.boardhearingdecision.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.boardhearing.domain.BoardHearingParticipant;
import omis.boardhearingdecision.domain.BoardHearingDecision;
import omis.boardhearingdecision.domain.BoardMemberDecision;
import omis.boardhearingdecision.domain.HearingDecisionReason;

/**
 * Implementation of board member decision.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 22, 2018)
 * @since OMIS 3.0
 */
public class BoardMemberDecisionImpl implements BoardMemberDecision {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private BoardHearingDecision boardDecision;
	
	private BoardHearingParticipant boardHearingParticipant;
	
	private HearingDecisionReason decisionReason;
	
	private String comments;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;

	/** 
	 * Instantiates an implementation of board member decision. 
	 */
	public BoardMemberDecisionImpl() {
		// Default constructor.
	}
	
	/**{@inheritDoc} */
	@Override
	public Long getId() {
		return id;
	}

	/**{@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/**{@inheritDoc} */
	@Override
	public BoardHearingDecision getBoardDecision() {
		return boardDecision;
	}

	/**{@inheritDoc} */
	@Override
	public void setBoardDecision(final BoardHearingDecision boardDecision) {
		this.boardDecision = boardDecision;
	}

	/**{@inheritDoc} */
	@Override
	public BoardHearingParticipant getBoardHearingParticipant() {
		return boardHearingParticipant;
	}

	/**{@inheritDoc} */
	@Override
	public void setBoardHearingParticipant(
			final BoardHearingParticipant boardHearingParticipant) {
		this.boardHearingParticipant = boardHearingParticipant;
	}

	/**{@inheritDoc} */
	@Override
	public HearingDecisionReason getDecisionReason() {
		return decisionReason;
	}

	/**{@inheritDoc} */
	@Override
	public void setDecisionReason(final HearingDecisionReason decisionReason) {
		this.decisionReason = decisionReason;
	}

	/**{@inheritDoc} */
	@Override
	public String getComments() {
		return comments;
	}

	/**{@inheritDoc} */
	@Override
	public void setComments(final String comments) {
		this.comments = comments;
	}

	/**{@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return updateSignature;
	}

	/**{@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BoardMemberDecision)) {
			return false;
		}
		BoardMemberDecision that = (BoardMemberDecision) obj;
		if (this.getBoardDecision() == null) {
			throw new IllegalStateException("Board decision required");
		}
		if (!this.getBoardDecision().equals(that.getBoardDecision())) {
			return false;
		}
		if (this.getBoardHearingParticipant() == null) {
			throw new IllegalStateException(
					"Board hearing participant required");
		}
		if (!this.getBoardHearingParticipant().equals(
				that.getBoardHearingParticipant())) {
			return false;
		}
		if (this.getDecisionReason() == null) {
			throw new IllegalStateException("Decision reason required");
		}
		if (!this.getDecisionReason().equals(that.getDecisionReason())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getBoardDecision() == null) {
			throw new IllegalStateException("Board decision required");
		}
		if (this.getBoardHearingParticipant() == null) {
			throw new IllegalStateException(
					"Board hearing participant required");
		}
		if (this.getDecisionReason() == null) {
			throw new IllegalStateException("Decision reason required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getBoardDecision().hashCode();
		hashCode = 29 * hashCode + this.getBoardHearingParticipant().hashCode();
		hashCode = 29 * hashCode + this.getDecisionReason().hashCode();
		return hashCode;
	}
}
