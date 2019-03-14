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
package omis.earlyreleasetracking.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.earlyreleasetracking.domain.EarlyReleaseEligibleCorrectionalStatus;
import omis.supervision.domain.CorrectionalStatus;

/**
 * Early Release Eligible Correctional Status Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Mar 11, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseEligibleCorrectionalStatusImpl
		implements EarlyReleaseEligibleCorrectionalStatus {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private CorrectionalStatus correctionalStatus;
	
	private Boolean valid;
	
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

	/** {@inheritDoc} */
	@Override
	public CorrectionalStatus getCorrectionalStatus() {
		return this.correctionalStatus;
	}

	/** {@inheritDoc} */
	@Override
	public void setCorrectionalStatus(
			final CorrectionalStatus correctionalStatus) {
		this.correctionalStatus = correctionalStatus;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}

	/** {@inheritDoc} */
	@Override
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof EarlyReleaseEligibleCorrectionalStatus)) {
			return false;
		}
		
		EarlyReleaseEligibleCorrectionalStatus that =
				(EarlyReleaseEligibleCorrectionalStatus) obj;
		
		if (this.getCorrectionalStatus() == null) {
			throw new IllegalStateException("CorrectionalStatus required.");
		}
		
		if (!this.getCorrectionalStatus().equals(
				that.getCorrectionalStatus())) {
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getCorrectionalStatus() == null) {
			throw new IllegalStateException("CorrectionalStatus required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getCorrectionalStatus().hashCode();
		
		return hashCode;
	}
}
