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
import omis.docket.domain.Docket;
import omis.earlyreleasetracking.domain.EarlyReleaseJudgeResponseCategory;
import omis.earlyreleasetracking.domain.EarlyReleaseRequest;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestCategory;
import omis.earlyreleasetracking.domain.EarlyReleaseStatusCategory;
import omis.user.domain.UserAccount;

/**
 * Early Release Request Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 7, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseRequestImpl implements EarlyReleaseRequest {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Docket docket;
	
	private Date requestDate;
	
	private EarlyReleaseStatusCategory requestStatus;
	
	private EarlyReleaseJudgeResponseCategory judgeResponse;
	
	private EarlyReleaseRequestCategory category;
	
	private UserAccount requestBy;
	
	private Date approvalDate;
	
	private String comments;
	
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
	public Docket getDocket() {
		return this.docket;
	}

	/** {@inheritDoc} */
	@Override
	public void setDocket(final Docket docket) {
		this.docket = docket;
	}

	/** {@inheritDoc} */
	@Override
	public Date getRequestDate() {
		return this.requestDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setRequestDate(final Date requestDate) {
		this.requestDate = requestDate;
	}

	/** {@inheritDoc} */
	@Override
	public EarlyReleaseStatusCategory getRequestStatus() {
		return this.requestStatus;
	}

	/** {@inheritDoc} */
	@Override
	public void setRequestStatus(
			final EarlyReleaseStatusCategory requestStatus) {
		this.requestStatus = requestStatus;
	}

	/** {@inheritDoc} */
	@Override
	public EarlyReleaseJudgeResponseCategory getJudgeResponse() {
		return this.judgeResponse;
	}

	/** {@inheritDoc} */
	@Override
	public void setJudgeResponse(
			final EarlyReleaseJudgeResponseCategory judgeResponse) {
		this.judgeResponse = judgeResponse;
	}

	/** {@inheritDoc} */
	@Override
	public EarlyReleaseRequestCategory getCategory() {
		return this.category;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(final EarlyReleaseRequestCategory category) {
		this.category = category;
	}

	/** {@inheritDoc} */
	@Override
	public UserAccount getRequestBy() {
		return this.requestBy;
	}

	/** {@inheritDoc} */
	@Override
	public void setRequestBy(final UserAccount requestBy) {
		this.requestBy = requestBy;
	}

	/** {@inheritDoc} */
	@Override
	public Date getApprovalDate() {
		return this.approvalDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setApprovalDate(final Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	/** {@inheritDoc} */
	@Override
	public String getComments() {
		return this.comments;
	}

	/** {@inheritDoc} */
	@Override
	public void setComments(final String comments) {
		this.comments = comments;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof EarlyReleaseRequest)) {
			return false;
		}
		
		EarlyReleaseRequest that = (EarlyReleaseRequest) obj;
		
		if (this.getDocket() == null) {
			throw new IllegalStateException("Docket required.");
		}
		if (this.getRequestDate() == null) {
			throw new IllegalStateException("Request Date required.");
		}
		
		if (!this.getDocket().equals(that.getDocket())) {
			return false;
		}
		if (!this.getRequestDate().equals(that.getRequestDate())) {
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getDocket() == null) {
			throw new IllegalStateException("Docket required.");
		}
		if (this.getRequestDate() == null) {
			throw new IllegalStateException("Request Date required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getDocket().hashCode();
		hashCode = 29 * hashCode + this.getRequestDate().hashCode();
		
		return hashCode;
	}
}
