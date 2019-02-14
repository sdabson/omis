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

import java.util.Date;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.earlyreleasetracking.domain.EarlyReleaseRequest;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestInternalApproval;
import omis.earlyreleasetracking.domain.InternalApprovalDecisionCategory;

/**
 * Early Release Request Internal Approval Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 7, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseRequestInternalApprovalImpl
		implements EarlyReleaseRequestInternalApproval {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private EarlyReleaseRequest earlyReleaseRequest;
	
	private InternalApprovalDecisionCategory decision;
	
	private Date date;
	
	private String name;
	
	private String narrative;
	
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
	public EarlyReleaseRequest getEarlyReleaseRequest() {
		return this.earlyReleaseRequest;
	}

	/** {@inheritDoc} */
	@Override
	public void setEarlyReleaseRequest(
			final EarlyReleaseRequest earlyReleaseRequest) {
		this.earlyReleaseRequest = earlyReleaseRequest;
	}

	/** {@inheritDoc} */
	@Override
	public InternalApprovalDecisionCategory getDecision() {
		return this.decision;
	}

	/** {@inheritDoc} */
	@Override
	public void setDecision(final InternalApprovalDecisionCategory decision) {
		this.decision = decision;
	}

	/** {@inheritDoc} */
	@Override
	public Date getDate() {
		return this.date;
	}

	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public void setName(final String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public String getNarrative() {
		return this.narrative;
	}

	/** {@inheritDoc} */
	@Override
	public void setNarrative(final String narrative) {
		this.narrative = narrative;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof EarlyReleaseRequestInternalApproval)) {
			return false;
		}
		
		EarlyReleaseRequestInternalApproval that =
				(EarlyReleaseRequestInternalApproval) obj;
		
		if (this.getEarlyReleaseRequest() == null) {
			throw new IllegalStateException("EarlyReleaseRequest required.");
		}
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		
		if (!this.getEarlyReleaseRequest().equals(
				that.getEarlyReleaseRequest())) {
			return false;
		}
		if (!this.getName().equals(that.getName())) {
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getEarlyReleaseRequest() == null) {
			throw new IllegalStateException("EarlyReleaseRequest required.");
		}
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getEarlyReleaseRequest().hashCode();
		hashCode = 29 * hashCode + this.getName().hashCode();
		
		return hashCode;
	}
}
