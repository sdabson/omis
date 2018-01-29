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

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.boardhearingdecision.domain.BoardHearingDecision;
import omis.boardhearingdecision.domain.HearingDecisionNote;

/**
 * Implementation of hearing decision note.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 22, 2018)
 * @since OMIS 3.0
 */
public class HearingDecisionNoteImpl implements HearingDecisionNote {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String description;
	
	private Date date;
	
	private BoardHearingDecision boardDecision;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/** 
	 * Instantiates an implementation of hearing decision note. 
	 */
	public HearingDecisionNoteImpl() {
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
	public String getDescription() {
		return description;
	}

	/**{@inheritDoc} */
	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

	/**{@inheritDoc} */
	@Override
	public Date getDate() {
		return date;
	}

	/**{@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date;
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
		if (!(obj instanceof HearingDecisionNote)) {
			return false;
		}
		HearingDecisionNote that = (HearingDecisionNote) obj;
		if (this.getBoardDecision() == null) {
			throw new IllegalStateException("Board decision required");
		}
		if (!this.getBoardDecision().equals(that.getBoardDecision())) {
			return false;
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		if (!this.getDate().equals(that.getDate())) {
			return false;
		}
		if (this.getDescription() == null) {
			throw new IllegalStateException("Description required");
		}
		if (!this.getDescription().equals(that.getDescription())) {
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
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		if (this.getDescription() == null) {
			throw new IllegalStateException("Description required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getBoardDecision().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		return hashCode;
	}
}
