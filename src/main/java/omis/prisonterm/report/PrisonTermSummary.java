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
package omis.prisonterm.report;

import java.util.Date;

import omis.prisonterm.domain.PrisonTermStatus;

/**
 * Summary for prison term.
 * 
 * @author Trevor Isles
 * @author Josh Divine
 * @author Annie Wahl
 * @version 0.1.2 (Dec 18, 2018)
 * @since OMIS 3.0
 */
public class PrisonTermSummary {
	private final Long id;
	
	private final Long offenderId;
	
	private final String offenderLastName;
	
	private final String offenderFirstName;
	
	private final String offenderMiddleName;
	
	private final String offenderSuffix;
	
	private final Integer offenderNumber;
	
	private final Date actionDate;
	
	private final Integer preSentenceCredits;
	
	private final Date sentenceDate;
	
	private final Integer sentenceTermYears;
	
	private final Integer sentenceTermDays;
	
	private final Date paroleEligibilityDate;
	
	private final Date projectedDischargeDate;
	
	private final Date maximumDischargeDate;
	
	private final Boolean sentenceToFollow;
	
	private final String comments;
	
	private final PrisonTermStatus status;
	
	private final String verificationUserLastName;
	
	private final String verificationUserFirstName;
	
	private final String verificationUserMiddleName;
	
	private final String verificationUserUsername;
	
	private final Date verificationDate;
	
	private final Long sentenceCalculationDocumentId;
	
	/**
	 * Instantiates a prison term summary with the specified values.
	 * 
	 * @param id
	 * @param offenderId
	 * @param offenderLastName
	 * @param offenderFirstName
	 * @param offenderMiddleName
	 * @param offenderSuffix
	 * @param offenderNumber
	 * @param actionDate
	 * @param preSentenceCredits
	 * @param sentenceDate
	 * @param sentenceTermYears
	 * @param sentenceTermDays
	 * @param paroleEligibilityDate
	 * @param projectedDischargeDate
	 * @param maximumDischargeDate
	 * @param sentenceToFollow
	 * @param comments
	 * @param prisonTermStatus
	 * @param verificationUser
	 * @param verificationUserLastName
	 * @param verificationUserFirstName
	 * @param verificationDate
	 * @param sentenceCalculationDocumentId
	 */
	public PrisonTermSummary(
			final Long id,
			final Long offenderId,
			final String offenderLastName,
			final String offenderFirstName,
			final String offenderMiddleName,
			final String offenderSuffix,
			final Integer offenderNumber,
			final Date actionDate,
			final Integer preSentenceCredits,
			final Date sentenceDate,
			final Integer sentenceTermYears,
			final Integer sentenceTermDays,
			final Date paroleEligibilityDate,
			final Date projectedDischargeDate,
			final Date maximumDischargeDate,
			final Boolean sentenceToFollow,
			final String comments,
			final PrisonTermStatus prisonTermStatus,
			final String verificationUserLastName,
			final String verificationUserFirstName,
			final String verificationUserMiddleName,
			final String verificationUserUsername,
			final Date verificationDate,
			final Long sentenceCalculationDocumentId) {
		this.id = id;
		this.offenderId = offenderId;
		this.offenderLastName = offenderLastName;
		this.offenderFirstName = offenderFirstName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderSuffix = offenderSuffix;
		this.offenderNumber = offenderNumber;
		this.actionDate = actionDate;
		this.preSentenceCredits = preSentenceCredits;
		this.sentenceDate = sentenceDate;
		this.sentenceTermYears = sentenceTermYears;
		this.sentenceTermDays = sentenceTermDays;
		this.paroleEligibilityDate = paroleEligibilityDate;
		this.projectedDischargeDate = projectedDischargeDate;
		this.maximumDischargeDate = maximumDischargeDate;
		this.sentenceToFollow = sentenceToFollow;
		this.comments = comments;
		this.status = prisonTermStatus;
		this.verificationUserLastName = verificationUserLastName;
		this.verificationUserFirstName = verificationUserFirstName;
		this.verificationUserMiddleName = verificationUserMiddleName;
		this.verificationUserUsername = verificationUserUsername;
		this.verificationDate = verificationDate;
		this.sentenceCalculationDocumentId = sentenceCalculationDocumentId;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the offenderId
	 */
	public Long getOffenderId() {
		return offenderId;
	}

	/**
	 * @return the offenderLastName
	 */
	public String getOffenderLastName() {
		return offenderLastName;
	}

	/**
	 * @return the offenderFirstName
	 */
	public String getOffenderFirstName() {
		return offenderFirstName;
	}

	/**
	 * @return the offenderMiddleName
	 */
	public String getOffenderMiddleName() {
		return offenderMiddleName;
	}

	/**
	 * @return the offenderSuffix
	 */
	public String getOffenderSuffix() {
		return offenderSuffix;
	}

	/**
	 * @return the offenderNumber
	 */
	public Integer getOffenderNumber() {
		return offenderNumber;
	}

	/**
	 * @return the actionDate
	 */
	public Date getActionDate() {
		return actionDate;
	}

	/**
	 * @return the preSentenceCredits
	 */
	public Integer getPreSentenceCredits() {
		return preSentenceCredits;
	}

	/**
	 * @return the sentenceDate
	 */
	public Date getSentenceDate() {
		return sentenceDate;
	}

	/**
	 * @return the sentenceTermYears
	 */
	public Integer getSentenceTermYears() {
		return sentenceTermYears;
	}

	/**
	 * @return the sentenceTermDays
	 */
	public Integer getSentenceTermDays() {
		return sentenceTermDays;
	}

	/**
	 * @return the paroleEligibilityDate
	 */
	public Date getParoleEligibilityDate() {
		return paroleEligibilityDate;
	}

	/**
	 * @return the projectedDischargeDate
	 */
	public Date getProjectedDischargeDate() {
		return projectedDischargeDate;
	}

	/**
	 * @return the maximumDischargeDate
	 */
	public Date getMaximumDischargeDate() {
		return maximumDischargeDate;
	}

	/**
	 * @return the sentenceToFollow
	 */
	public Boolean getSentenceToFollow() {
		return sentenceToFollow;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @return the prisonTermStatus
	 */
	public PrisonTermStatus getStatus() {
		return status;
	}
	
	/**
	 * Returns the verificationUserLastName.
	 *
	 * @return verificationUserLastName
	 */
	public String getVerificationUserLastName() {
		return this.verificationUserLastName;
	}

	/**
	 * Returns the verificationUserFirstName.
	 *
	 * @return verificationUserFirstName
	 */
	public String getVerificationUserFirstName() {
		return this.verificationUserFirstName;
	}

	/**
	 * Returns the verificationUserMiddleName.
	 *
	 * @return verificationUserMiddleName
	 */
	public String getVerificationUserMiddleName() {
		return this.verificationUserMiddleName;
	}

	/**
	 * Returns the verificationUserUsername.
	 *
	 * @return verificationUserUsername
	 */
	public String getVerificationUserUsername() {
		return this.verificationUserUsername;
	}

	/**
	 * Returns the verificationDate.
	 *
	 * @return verificationDate
	 */
	public Date getVerificationDate() {
		return this.verificationDate;
	}

	/**
	 * Returns the sentenceCalculationDocumentId.
	 *
	 * @return sentenceCalculationDocumentId
	 */
	public Long getSentenceCalculationDocumentId() {
		return this.sentenceCalculationDocumentId;
	}
}
