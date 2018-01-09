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
package omis.paroleeligibility.report;

import java.io.Serializable;
import java.util.Date;

import omis.paroleeligibility.domain.EligibilityStatusCategory;


public class ParoleEligibilitySummary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long paroleEligibilityId;
	
	private final Date hearingEligibilityDate;
	
	private final String appearanceCategoryName;
	
	private final EligibilityStatusCategory statusCategory;
	
	private final Date statusDate;
	
	private final Date reviewDate;
	
	private final String reasonName;
	
	/**
	 * Summary of parole eligibility.
	 * 
	 * @param paroleEligibilityId
	 * @param hearingEligibilityDate
	 * @param appearanceCategoryName
	 * @param eligibilityStatusCategory
	 * @param statusDate
	 * @param reviewDate
	 * @param reasonName
	 */
	public ParoleEligibilitySummary(
			final Long paroleEligibilityId,
			final Date hearingEligibilityDate,
			final String appearanceCategoryName,
			final EligibilityStatusCategory statusCategory,
			final Date statusDate,
			final Date reviewDate,
			final String reasonName) {
		this.paroleEligibilityId = paroleEligibilityId;
		this.hearingEligibilityDate = hearingEligibilityDate;
		this.appearanceCategoryName = appearanceCategoryName;
		this.statusCategory = statusCategory;
		this.statusDate = statusDate;
		this.reviewDate = reviewDate;
		this.reasonName = reasonName;
	}
	
	/**
	 * Returns ID.
	 * 
	 * @return ID
	 */
	public Long getParoleEligibilityId() {
		return this.paroleEligibilityId;
	}

	/**
	 * Returns offender last name.
	 * 
	 * @return offender last name
	 */
	public Date getHearingEligibilityDate() {
		return this.hearingEligibilityDate;
	}

	/**
	 * Returns offender first name.
	 * 
	 * @return offender first name
	 */
	public String getAppearanceCategoryName() {
		return this.appearanceCategoryName;
	}

	/**
	 * Returns offender middle name.
	 * 
	 * @return offender middle name
	 */
	public EligibilityStatusCategory getStatusCategory() {
		return this.statusCategory;
	}
	
	/**
	 * Returns hearing eligibility date of a parole eligibility.
	 * 
	 * @return hearing eligibility date
	 */
	public Date getStatusDate() {
		return this.statusDate;
	}
	
	/**
	 * Returns review date of a parole eligibility.
	 * 
	 * @return review date
	 */
	public Date getReviewDate() {
		return this.reviewDate;
	}
	
	/**
	 * Returns the date of a parole eligibility status.
	 * 
	 * @return eligibility status date
	 */
	public String getReasonName() {
		return this.reasonName;
	}
	
}

