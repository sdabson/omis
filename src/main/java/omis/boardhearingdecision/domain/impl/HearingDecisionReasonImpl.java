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
import omis.boardhearingdecision.domain.DecisionCategory;
import omis.boardhearingdecision.domain.HearingDecisionReason;

/**
 * Implementation of hearing decision reason.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 22, 2018)
 * @since OMIS 3.0
 */
public class HearingDecisionReasonImpl implements HearingDecisionReason {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String reasonName;
	
	private DecisionCategory category;
	
	private Boolean valid;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/** 
	 * Instantiates an implementation of hearing decision reason. 
	 */
	public HearingDecisionReasonImpl() {
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
	public String getReasonName() {
		return reasonName;
	}

	/**{@inheritDoc} */
	@Override
	public void setReasonName(final String reasonName) {
		this.reasonName = reasonName;
	}

	/**{@inheritDoc} */
	@Override
	public DecisionCategory getCategory() {
		return category;
	}

	/**{@inheritDoc} */
	@Override
	public void setCategory(final DecisionCategory category) {
		this.category = category;
	}

	/**{@inheritDoc} */
	@Override
	public Boolean getValid() {
		return valid;
	}

	/**{@inheritDoc} */
	@Override
	public void setValid(final Boolean valid) {
		this.valid = valid;
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
		if (!(obj instanceof HearingDecisionReason)) {
			return false;
		}
		HearingDecisionReason that = (HearingDecisionReason) obj;
		if (this.getReasonName() == null) {
			throw new IllegalStateException("Reason name required");
		}
		if (!this.getReasonName().equals(that.getReasonName())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getReasonName() == null) {
			throw new IllegalStateException("Reason name required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getReasonName().hashCode();
		return hashCode;
	}
}
