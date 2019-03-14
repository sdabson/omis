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
import omis.boardhearing.domain.BoardHearing;
import omis.boardhearingdecision.domain.BoardHearingDecision;
import omis.boardhearingdecision.domain.BoardHearingDecisionCategory;

/**
 * Implementation of board hearing decision.
 * 
 * @author Josh Divine
 * @author Annie Wahl
 * @version 0.1.1 (Mar 13, 2019)
 * @since OMIS 3.0
 */
public class BoardHearingDecisionImpl implements BoardHearingDecision {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private BoardHearing hearing;
	
	private BoardHearingDecisionCategory category;
	
	private String rulingDetails;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/** 
	 * Instantiates an implementation of board hearing decision. 
	 */
	public BoardHearingDecisionImpl() {
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
	public BoardHearing getHearing() {
		return hearing;
	}

	/**{@inheritDoc} */
	@Override
	public void setHearing(final BoardHearing hearing) {
		this.hearing = hearing;
	}

	/**{@inheritDoc} */
	@Override
	public BoardHearingDecisionCategory getCategory() {
		return category;
	}

	/**{@inheritDoc} */
	@Override
	public void setCategory(final BoardHearingDecisionCategory category) {
		this.category = category;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getRulingDetails() {
		return this.rulingDetails;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setRulingDetails(final String rulingDetails) {
		this.rulingDetails = rulingDetails;
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
		if (!(obj instanceof BoardHearingDecision)) {
			return false;
		}
		BoardHearingDecision that = (BoardHearingDecision) obj;
		if (this.getHearing() == null) {
			throw new IllegalStateException("Board hearing required");
		}
		if (!this.getHearing().equals(that.getHearing())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getHearing() == null) {
			throw new IllegalStateException("Board hearing required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getHearing().hashCode();
		return hashCode;
	}
}
