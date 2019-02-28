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
package omis.earlyreleasetracking.report;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Early Release Request Summary.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 11, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseRequestSummary implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final Long earlyReleaseRequestId;
	
	private final String earlyReleaseCategoryName;
	
	private final Date requestDate;
	
	private final String docketValue;
	
	private final String earlyReleaseJudgeResponseCategoryName;
	
	private final String earlyReleaseRequestStatusCategoryName;
	
	private final String offenderLastName;
	
	private final String offenderFirstName;
	
	private final String offenderMiddleName;
	
	private final Long offenderId;
	
	private final String offenderNumber;
	
	private final Date eligibilityDate;
	
	private final Long monthsOnProbation;
	
	private final Long monthsInResidence;
	
	private final Long monthsEmployed;

	/**
	 * @param earlyReleaseRequestId Early Release Request ID
	 * @param earlyReleaseCategoryName Early Release Category Name
	 * @param requestDate Request Date
	 * @param docketValue Docket Value
	 * @param earlyReleaseJudgeResponseCategoryName Early Release Judge
	 * Response Category Name
	 * @param earlyReleaseRequestStatusCategoryName Early Release Request
	 * Status Category Name
	 * @param offenderLastName Offender Last Name
	 * @param offenderFirstName Offender First Name
	 * @param offenderMiddleName Offender Middle Name
	 * @param offenderId Offender ID
	 * @param offenderNumber Offender Number
	 */
	public EarlyReleaseRequestSummary(final Long earlyReleaseRequestId,
			final String earlyReleaseCategoryName, final Date requestDate,
			final String docketValue,
			final String earlyReleaseJudgeResponseCategoryName,
			final String earlyReleaseRequestStatusCategoryName,
			final String offenderLastName, final String offenderFirstName,
			final String offenderMiddleName, final Long offenderId,
			final String offenderNumber) {
		this.earlyReleaseRequestId = earlyReleaseRequestId;
		this.earlyReleaseCategoryName = earlyReleaseCategoryName;
		this.requestDate = requestDate;
		this.docketValue = docketValue;
		this.earlyReleaseJudgeResponseCategoryName =
				earlyReleaseJudgeResponseCategoryName;
		this.earlyReleaseRequestStatusCategoryName =
				earlyReleaseRequestStatusCategoryName;
		this.offenderLastName = offenderLastName;
		this.offenderFirstName = offenderFirstName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderId = offenderId;
		this.offenderNumber = offenderNumber;
		this.eligibilityDate = null;
		this.monthsOnProbation = null;
		this.monthsInResidence = null;
		this.monthsEmployed = null;
	}
	
	/**
	 * @param earlyReleaseRequestId Early Release Request ID
	 * @param earlyReleaseCategoryName Early Release Category Name
	 * @param requestDate Request Date
	 * @param docketValue Docket Value
	 * @param earlyReleaseJudgeResponseCategoryName Early Release Judge
	 * Response Category Name
	 * @param earlyReleaseRequestStatusCategoryName Early Release Request
	 * Status Category Name
	 * @param offenderLastName Offender Last Name
	 * @param offenderFirstName Offender First Name
	 * @param offenderMiddleName Offender Middle Name
	 * @param offenderId Offender ID
	 * @param offenderNumber Offender Number
	 * @param eligibilityDate Eligibility Date
	 * @param probationStartDate
	 * @param residenceStartDate
	 * @param employmentStartDate
	 */
	public EarlyReleaseRequestSummary(final Long earlyReleaseRequestId,
			final String earlyReleaseCategoryName, final Date requestDate,
			final String docketValue,
			final String earlyReleaseJudgeResponseCategoryName,
			final String earlyReleaseRequestStatusCategoryName,
			final String offenderLastName, final String offenderFirstName,
			final String offenderMiddleName, final Long offenderId,
			final String offenderNumber,
			final Date eligibilityDate,
			final Date probationStartDate,
			final Date residenceStartDate,
			final Date employmentStartDate) {
		this.earlyReleaseRequestId = earlyReleaseRequestId;
		this.earlyReleaseCategoryName = earlyReleaseCategoryName;
		this.requestDate = requestDate;
		this.docketValue = docketValue;
		this.earlyReleaseJudgeResponseCategoryName =
				earlyReleaseJudgeResponseCategoryName;
		this.earlyReleaseRequestStatusCategoryName =
				earlyReleaseRequestStatusCategoryName;
		this.offenderLastName = offenderLastName;
		this.offenderFirstName = offenderFirstName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderId = offenderId;
		this.offenderNumber = offenderNumber;
		this.eligibilityDate = eligibilityDate;
		
		this.monthsOnProbation = this.monthsBetweenDates(
				probationStartDate, new Date());
		this.monthsInResidence = this.monthsBetweenDates(
				residenceStartDate, new Date());
		this.monthsEmployed = this.monthsBetweenDates(
				employmentStartDate, new Date());
	}
	
	/**
	 * Returns the earlyReleaseRequestId.
	 *
	 * @return earlyReleaseRequestId
	 */
	public Long getEarlyReleaseRequestId() {
		return this.earlyReleaseRequestId;
	}

	/**
	 * Returns the earlyReleaseCategoryName.
	 *
	 * @return earlyReleaseCategoryName
	 */
	public String getEarlyReleaseCategoryName() {
		return this.earlyReleaseCategoryName;
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
	 * Returns the docketValue.
	 *
	 * @return docketValue
	 */
	public String getDocketValue() {
		return this.docketValue;
	}

	/**
	 * Returns the earlyReleaseJudgeResponseCategoryName.
	 *
	 * @return earlyReleaseJudgeResponseCategoryName
	 */
	public String getEarlyReleaseJudgeResponseCategoryName() {
		return this.earlyReleaseJudgeResponseCategoryName;
	}

	/**
	 * Returns the earlyReleaseRequestStatusCategoryName.
	 *
	 * @return earlyReleaseRequestStatusCategoryName
	 */
	public String getEarlyReleaseRequestStatusCategoryName() {
		return this.earlyReleaseRequestStatusCategoryName;
	}

	/**
	 * Returns the offenderLastName.
	 *
	 * @return offenderLastName
	 */
	public String getOffenderLastName() {
		return this.offenderLastName;
	}

	/**
	 * Returns the offenderFirstName.
	 *
	 * @return offenderFirstName
	 */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}

	/**
	 * Returns the offenderMiddleName.
	 *
	 * @return offenderMiddleName
	 */
	public String getOffenderMiddleName() {
		return this.offenderMiddleName;
	}

	/**
	 * Returns the offenderId.
	 *
	 * @return offenderId
	 */
	public Long getOffenderId() {
		return this.offenderId;
	}

	/**
	 * Returns the offenderNumber.
	 *
	 * @return offenderNumber
	 */
	public String getOffenderNumber() {
		return this.offenderNumber;
	}

	/**
	 * Returns the eligibilityDate.
	 *
	 * @return eligibilityDate
	 */
	public Date getEligibilityDate() {
		return this.eligibilityDate;
	}

	/**
	 * Returns the monthsOnProbation.
	 *
	 * @return monthsOnProbation
	 */
	public Long getMonthsOnProbation() {
		return this.monthsOnProbation;
	}

	/**
	 * Returns the monthsInResidence.
	 *
	 * @return monthsInResidence
	 */
	public Long getMonthsInResidence() {
		return this.monthsInResidence;
	}

	/**
	 * Returns the monthsEmployed.
	 *
	 * @return monthsEmployed
	 */
	public Long getMonthsEmployed() {
		return this.monthsEmployed;
	}
	
	private Long monthsBetweenDates(final Date startDate, final Date endDate) {
		if (startDate != null && endDate != null) {
			return ChronoUnit.MONTHS.between(
					startDate.toInstant().atZone(ZoneId.systemDefault())
					.toLocalDate(), endDate.toInstant().atZone(
					ZoneId.systemDefault()).toLocalDate());
		} else {
			return null;
		}
	}
}
