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
package omis.paroleeligibility.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.paroleeligibility.domain.AppearanceCategory;
import omis.paroleeligibility.domain.EligibilityStatusCategory;
import omis.paroleeligibility.domain.EligibilityStatusReason;
import omis.paroleeligibility.domain.component.ParoleEligibilityStatus;

/**
 * Form for parole eligibilities.
 *
 * @author Trevor Isles
 * @version 0.1.0 (Dec 5, 2017)
 * @since OMIS 3.0
 */
public class ParoleEligibilityForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Date hearingEligibilityDate;
	
	private EligibilityStatusCategory eligibilityStatusCategory;
	
	private AppearanceCategory appearanceCategory;
	
	private ParoleEligibilityStatus paroleEligibilityStatus;
	
	private Date paroleEligibilityStatusDate;
	
	private EligibilityStatusReason eligibilityStatusReason;
	
	private String statusReasonComment;
	
	private Date reviewDate;
	
	private List<ParoleEligibilityNoteItem>	paroleEligibilityNoteItems
		= new ArrayList<ParoleEligibilityNoteItem>();
	
	private Boolean showStatusFields;
	
	/** Instantiates a form for parole eligibilities. */
	public ParoleEligibilityForm() {
		// Default constructor.
	}
	
	/**
	 * Returns the hearing eligibility date.
	 * 
	 * @return hearingEligibilityDate hearing eligibility date
	 */
	public Date getHearingEligibilityDate() {
		return this.hearingEligibilityDate;
	}
	
	/**
	 * Sets the hearing eligibility date.
	 * 
	 * @param hearingEligibilityDate
	 */
	public void setHearingEligibilityDate(final Date hearingEligibilityDate) {
		this.hearingEligibilityDate = hearingEligibilityDate;
	}
	
	/**
	 * Returns the eligibility status category.
	 * 
	 * @return eligibilityStatusCategory eligibility status category
	 */
	public EligibilityStatusCategory getEligibilityStatusCategory() {
		return this.eligibilityStatusCategory;
	}
	
	/**
	 * Sets the eligibility status category.
	 * 
	 * @param eligibilityStatusCategory
	 */
	public void setEligibilityStatusCategory(
			final EligibilityStatusCategory eligibilityStatusCategory) {
		this.eligibilityStatusCategory = eligibilityStatusCategory;
	}
	
	
	public AppearanceCategory getAppearanceCategory() {
		return this.appearanceCategory;
	}
	
	public void setAppearanceCategory(final AppearanceCategory appearanceCategory) {
		this.appearanceCategory = appearanceCategory;
	}
	
	/**
	 * Returns the parole eligibility status.
	 * 
	 * @return eligibilityStatusCategory eligibility status category
	 */
	public ParoleEligibilityStatus getParoleEligibilityStatus() {
		return this.paroleEligibilityStatus;
	}
	
	/**
	 * Sets the parole eligibility status.
	 * 
	 * @param paroleEligibilityStatus
	 */
	public void setParoleEligibilityStatus(
			final ParoleEligibilityStatus paroleEligibilityStatus) {
		this.paroleEligibilityStatus = paroleEligibilityStatus;
	}
	
	/**
	 * Returns the parole eligibility status date.
	 * 
	 * @return paroleEligibilityStatusDate parole eligibility status date
	 */
	public Date getParoleEligibilityStatusDate() {
		return this.paroleEligibilityStatusDate;
	}
	
	/**
	 * Sets the parole eligibility status date.
	 * 
	 * @param paroleEligibilityStatusDate
	 */
	public void setParoleEligibilityStatusDate(
			final Date paroleEligibilityStatusDate) {
		this.paroleEligibilityStatusDate = paroleEligibilityStatusDate;
	}
	
	/**
	 * Returns the eligibility status reason.
	 * 
	 * @return eligibilityStatusReason eligibility status reason
	 */
	public EligibilityStatusReason getEligibilityStatusReason() {
		return this.eligibilityStatusReason;
	}
	
	/**
	 * Sets the eligibility status reason.
	 * 
	 * @param eligibilityStatusReason
	 */
	public void setEligibilityStatusReason(
			final EligibilityStatusReason eligibilityStatusReason) {
		this.eligibilityStatusReason = eligibilityStatusReason;
	}
	
	/**
	 * Returns the status reason comment.
	 * 
	 * @return statusReasonComment status reason comment
	 */
	public String getStatusReasonComment() {
		return this.statusReasonComment;
	}
	
	/**
	 * Sets the status reason comment.
	 * 
	 * @param statusReasonComment
	 */
	public void setStatusReasonComment(String statusReasonComment) {
		this.statusReasonComment = statusReasonComment;
	}
	
	/**
	 * Returns the review date.
	 * 
	 * @return reviewDate review date
	 */
	public Date getReviewDate() {
		return this.reviewDate;
	}
	
	/**
	 * Sets the review date.
	 * 
	 * @param reviewDate
	 */
	public void setReviewDate(final Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	
	/**
	 * Returns the parole eligibility note items.
	 * 
	 * @return list of parole eligibility note items
	 */
	public List<ParoleEligibilityNoteItem> getParoleEligibilityNoteItems() {
		return this.paroleEligibilityNoteItems;
	}
	
	/**
	 * Sets the parole eligibility note items.
	 * 
	 * @param paroleEligibilityNoteItems
	 */
	public void setParoleEligibilityNoteItems(
			final List<ParoleEligibilityNoteItem> paroleEligibilityNoteItems) {
		this.paroleEligibilityNoteItems = paroleEligibilityNoteItems;
	}

	/**
	 * Returns the show status fields.
	 * 
	 * @return showStatusFields show status fields
	 */
	public Boolean getShowStatusFields() {
		return this.showStatusFields;
	}

	/**
	 * Sets the show status fields
	 * 
	 * @param showStatusFields
	 */
	public void setShowStatusFields(final Boolean showStatusFields) {
		this.showStatusFields = showStatusFields;
	}
}