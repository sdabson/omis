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
package omis.boardhearing.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.boardhearing.domain.BoardHearing;
import omis.boardhearing.domain.BoardHearingParticipant;
import omis.paroleboardmember.domain.ParoleBoardMember;

/**
 * Board Hearing Participant Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 28, 2017)
 *@since OMIS 3.0
 *
 */
public class BoardHearingParticipantImpl implements BoardHearingParticipant {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private ParoleBoardMember boardMember;
	
	private BoardHearing hearing;
	
	private Long number;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**{@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/**{@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/**{@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/**{@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}
	
	/**{@inheritDoc} */
	@Override
	public ParoleBoardMember getBoardMember() {
		return this.boardMember;
	}

	/**{@inheritDoc} */
	@Override
	public void setBoardMember(final ParoleBoardMember boardMember) {
		this.boardMember = boardMember;
	}

	/**{@inheritDoc} */
	@Override
	public BoardHearing getHearing() {
		return this.hearing;
	}

	/**{@inheritDoc} */
	@Override
	public void setHearing(final BoardHearing hearing) {
		this.hearing = hearing;
	}

	/**{@inheritDoc} */
	@Override
	public Long getNumber() {
		return this.number;
	}

	/**{@inheritDoc} */
	@Override
	public void setNumber(final Long number) {
		this.number = number;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BoardHearingParticipant)) {
			return false;
		}
		
		BoardHearingParticipant that = (BoardHearingParticipant) obj;
		
		if (this.getBoardMember() == null) {
			throw new IllegalStateException("Board Member required.");
		}
		if (this.getHearing() == null) {
			throw new IllegalStateException("Board Hearing required.");
		}
		
		if (!this.getBoardMember().equals(that.getBoardMember())) {
			return false;
		}
		if (!this.getHearing().equals(that.getHearing())) {
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getBoardMember() == null) {
			throw new IllegalStateException("BoardMember required.");
		}
		if (this.getHearing() == null) {
			throw new IllegalStateException("Board Hearing required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getBoardMember().hashCode();
		hashCode = 29 * hashCode + this.getHearing().hashCode();
		
		return hashCode;
	}
}
