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
package omis.earlyreleasetracking.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import omis.docket.domain.Docket;
import omis.earlyreleasetracking.domain.EarlyReleaseJudgeResponseCategory;
import omis.earlyreleasetracking.domain.EarlyReleaseStatusCategory;
import omis.user.domain.UserAccount;

/**
 * Early Release Request Form.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 13, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseRequestForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Docket docket;
	
	private EarlyReleaseStatusCategory releaseStatus;
	
	private Date requestDate;
	
	private UserAccount requestBy;
	
	private EarlyReleaseJudgeResponseCategory judgeResponse;
	
	private Date approvalDate;
	
	private String comments;
	
	private List<EarlyReleaseRequestInternalApprovalItem>
		earlyReleaseRequestInternalApprovalItems =
			new ArrayList<EarlyReleaseRequestInternalApprovalItem>();
	
	private List<EarlyReleaseRequestExternalOppositionItem>
		earlyReleaseRequestExternalOppositionItems =
			new ArrayList<EarlyReleaseRequestExternalOppositionItem>();
	
	private List<EarlyReleaseRequestNoteAssociationItem>
		earlyReleaseRequestNoteAssociationItems =
			new ArrayList<EarlyReleaseRequestNoteAssociationItem>();
	
	private List<EarlyReleaseRequestDocumentAssociationItem>
		earlyReleaseRequestDocumentAssociationItems =
			new ArrayList<EarlyReleaseRequestDocumentAssociationItem>();
	
	/**
	 * Default constructor for the EarlyReleaseRequestForm.
	 */
	public EarlyReleaseRequestForm() {
	}

	/**
	 * Returns the docket.
	 *
	 * @return docket
	 */
	public Docket getDocket() {
		return this.docket;
	}

	/**
	 * Sets the docket.
	 *
	 * @param docket - docket
	 */
	public void setDocket(final Docket docket) {
		this.docket = docket;
	}

	/**
	 * Returns the releaseStatus.
	 *
	 * @return releaseStatus
	 */
	public EarlyReleaseStatusCategory getReleaseStatus() {
		return this.releaseStatus;
	}

	/**
	 * Sets the releaseStatus.
	 *
	 * @param releaseStatus - releaseStatus
	 */
	public void setReleaseStatus(
			final EarlyReleaseStatusCategory releaseStatus) {
		this.releaseStatus = releaseStatus;
	}

	/**
	 * Returns the requestDate.
	 *
	 * @return requestDate
	 */
	public Date getRequestDate() {
		return this.requestDate;
	}

	/**
	 * Sets the requestDate.
	 *
	 * @param requestDate - requestDate
	 */
	public void setRequestDate(final Date requestDate) {
		this.requestDate = requestDate;
	}

	/**
	 * Returns the requestBy.
	 *
	 * @return requestBy
	 */
	public UserAccount getRequestBy() {
		return this.requestBy;
	}

	/**
	 * Sets the requestBy.
	 *
	 * @param requestBy - requestBy
	 */
	public void setRequestBy(final UserAccount requestBy) {
		this.requestBy = requestBy;
	}

	/**
	 * Returns the judgeResponse.
	 *
	 * @return judgeResponse
	 */
	public EarlyReleaseJudgeResponseCategory getJudgeResponse() {
		return this.judgeResponse;
	}

	/**
	 * Sets the judgeResponse.
	 *
	 * @param judgeResponse - judgeResponse
	 */
	public void setJudgeResponse(
			final EarlyReleaseJudgeResponseCategory judgeResponse) {
		this.judgeResponse = judgeResponse;
	}

	/**
	 * Returns the approvalDate.
	 *
	 * @return approvalDate
	 */
	public Date getApprovalDate() {
		return this.approvalDate;
	}

	/**
	 * Sets the approvalDate.
	 *
	 * @param approvalDate - approvalDate
	 */
	public void setApprovalDate(final Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	/**
	 * Returns the comments.
	 *
	 * @return comments
	 */
	public String getComments() {
		return this.comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments - comments
	 */
	public void setComments(final String comments) {
		this.comments = comments;
	}

	/**
	 * Returns the earlyReleaseRequestInternalApprovalItems.
	 *
	 * @return earlyReleaseRequestInternalApprovalItems
	 */
	public List<EarlyReleaseRequestInternalApprovalItem>
			getEarlyReleaseRequestInternalApprovalItems() {
		return this.earlyReleaseRequestInternalApprovalItems;
	}

	/**
	 * Sets the earlyReleaseRequestInternalApprovalItems.
	 *
	 * @param earlyReleaseRequestInternalApprovalItems Early Release Request
	 * Internal Approval Items
	 */
	public void setEarlyReleaseRequestInternalApprovalItems(
			final List<EarlyReleaseRequestInternalApprovalItem>
					earlyReleaseRequestInternalApprovalItems) {
		this.earlyReleaseRequestInternalApprovalItems =
				earlyReleaseRequestInternalApprovalItems;
	}

	/**
	 * Returns the earlyReleaseRequestExternalOppositionItems.
	 *
	 * @return earlyReleaseRequestExternalOppositionItems
	 */
	public List<EarlyReleaseRequestExternalOppositionItem>
					getEarlyReleaseRequestExternalOppositionItems() {
		return this.earlyReleaseRequestExternalOppositionItems;
	}

	/**
	 * Sets the earlyReleaseRequestExternalOppositionItems.
	 *
	 * @param earlyReleaseRequestExternalOppositionItems Early Release Request
	 * External Opposition Items
	 */
	public void setEarlyReleaseRequestExternalOppositionItems(
			final List<EarlyReleaseRequestExternalOppositionItem>
					earlyReleaseRequestExternalOppositionItems) {
		this.earlyReleaseRequestExternalOppositionItems =
				earlyReleaseRequestExternalOppositionItems;
	}

	/**
	 * Returns the earlyReleaseRequestNoteAssociationItems.
	 *
	 * @return earlyReleaseRequestNoteAssociationItems
	 */
	public List<EarlyReleaseRequestNoteAssociationItem>
					getEarlyReleaseRequestNoteAssociationItems() {
		return this.earlyReleaseRequestNoteAssociationItems;
	}

	/**
	 * Sets the earlyReleaseRequestNoteAssociationItems.
	 *
	 * @param earlyReleaseRequestNoteAssociationItems Early Release Request
	 * Note Association Items
	 */
	public void setEarlyReleaseRequestNoteAssociationItems(
			final List<EarlyReleaseRequestNoteAssociationItem>
					earlyReleaseRequestNoteAssociationItems) {
		this.earlyReleaseRequestNoteAssociationItems =
				earlyReleaseRequestNoteAssociationItems;
	}

	/**
	 * Returns the earlyReleaseRequestDocumentAssociationItems.
	 *
	 * @return earlyReleaseRequestDocumentAssociationItems
	 */
	public List<EarlyReleaseRequestDocumentAssociationItem>
					getEarlyReleaseRequestDocumentAssociationItems() {
		return this.earlyReleaseRequestDocumentAssociationItems;
	}

	/**
	 * Sets the earlyReleaseRequestDocumentAssociationItems.
	 *
	 * @param earlyReleaseRequestDocumentAssociationItems Early Release Request
	 * Document Association Items
	 */
	public void setEarlyReleaseRequestDocumentAssociationItems(
			final List<EarlyReleaseRequestDocumentAssociationItem>
						earlyReleaseRequestDocumentAssociationItems) {
		this.earlyReleaseRequestDocumentAssociationItems =
				earlyReleaseRequestDocumentAssociationItems;
	}
}
