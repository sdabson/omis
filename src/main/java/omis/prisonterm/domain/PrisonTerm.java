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
package omis.prisonterm.domain;

import java.util.Date;
import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.offender.domain.OffenderAssociable;
import omis.user.domain.UserAccount;

/**
 * Prison term.
 * 
 * @author Trevor Isles
 * @author Josh Divine
 * @author Annie Wahl
 * @version 0.1.2 (Dec 18, 2018)
 * @since OMIS 3.0
 */

public interface PrisonTerm extends Creatable, Updatable, OffenderAssociable {

	/**
	 * Sets the ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the action date.
	 * 
	 * @param actionDate date and time
	 */
	void setActionDate(Date actionDate);
	
	/**
	 * Returns the action date.
	 * 
	 * @return actionDate
	 */
	Date getActionDate();
		
	/**
	 * Sets pre-sentence credits.
	 * 
	 * @param preSentenceCredits presentence credits
	 */
	void setPreSentenceCredits(Integer preSentenceCredits);
	
	/**
	 * Returns pre-sentence credits.
	 * 
	 * @return presentence credits
	 */
	Integer getPreSentenceCredits();
	
	/**
	 * Sets the sentence date.
	 * 
	 * @param sentenceDate sentence date
	 */
	void setSentenceDate(Date sentenceDate);
	
	/**
	 * Returns the sentence date.
	 * 
	 * @return sentenceDate
	 */
	Date getSentenceDate();
	
	/**
	 * Sets the sentence term years.
	 * 
	 * @param sentenceTermYears sentence term years
	 */
	void setSentenceTermYears(Integer sentenceTermYears);
	
	/**
	 * Returns the sentence term years.
	 * 
	 * @return sentence term years
	 */
	Integer getSentenceTermYears();
	
	/**
	 * Sets the sentence term days.
	 * 
	 * @param sentenceTermDays sentence term days
	 */
	void setSentenceTermDays(Integer sentenceTermDays);
	
	/**
	 * Returns the sentence term days.
	 * 
	 * @return sentence term days
	 */
	Integer getSentenceTermDays();
	
	/**
	 * Sets the parole eligibility date.
	 * 
	 * @param paroleEligibilityDate parole eligibility date
	 */
	void setParoleEligibilityDate(Date paroleEligibilityDate);
	
	/**
	 * Returns the parole eligibility date.
	 * 
	 * @return parole eligibility date
	 */
	Date getParoleEligibilityDate();
	
	/**
	 * Sets the projected discharge date.
	 * 
	 * @param projectedDischargeDate projected discharge date
	 */
	void setProjectedDischargeDate(Date projectedDischargeDate);
	
	/**
	 * Returns the projected discharge date.
	 * 
	 * @return projected discharge date
	 */
	Date getProjectedDischargeDate();
	
	/**
	 * Sets the maximum discharge date.
	 * 
	 * @param maximumDischargeDate maximum discharge date
	 */
	void setMaximumDischargeDate(Date maximumDischargeDate);
	
	/**
	 * Returns the maximum discharge date.
	 * 
	 * @return maximum discharge date
	 */
	Date getMaximumDischargeDate();
	
	/**
	 * Sets the status.
	 * 
	 * @param status status
	 */
	void setStatus(PrisonTermStatus status);
	
	/**
	 * Returns the status date.
	 * 
	 * @return status date
	 */
	PrisonTermStatus getStatus();
	
	/**
	 * Sets a sentence to follow.
	 * 
	 * @param sentenceToFollow sentence to follow
	 */
	void setSentenceToFollow(Boolean sentenceToFollow);
	
	/**
	 * Returns a sentence to follow.
	 * 
	 * @return sentence to follow
	 */
	Boolean getSentenceToFollow();
	
	/**
	 * Sets comments.
	 * 
	 * @param comments comments
	 */
	void setComments(String comments);
	
	/**
	 * Returns comments.
	 * 
	 * @return comments
	 */
	String getComments();
	
	/**
	 * Returns verification user.
	 * 
	 * @return verification user
	 */
	UserAccount getVerificationUser();
	
	/**
	 * Sets verification user.
	 * 
	 * @param verificationUser verification user
	 */
	void setVerificationUser(UserAccount verificationUser);
	
	/**
	 * Returns verification date.
	 * 
	 * @return verification date
	 */
	Date getVerificationDate();
	
	/**
	 * Sets verification date.
	 * 
	 * @param verificationDate verification date
	 */
	void setVerificationDate(Date verificationDate);
	
	/**
	 * Returns the Sentence Calculation for the Prison Term.
	 * 
	 * @return sentenceCalculation - Sentence Calculation
	 */
	PrisonTermDocumentAssociation getSentenceCalculation();
	
	/**
	 * Sets the Sentence Calculation for the Prison Term.
	 * 
	 * @param sentenceCalculation - Sentence Calculation
	 */
	void setSentenceCalculation(
			PrisonTermDocumentAssociation sentenceCalculation);
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}
