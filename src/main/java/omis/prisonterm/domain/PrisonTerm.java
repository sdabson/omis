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
 * @version 0.1.1 (Oct 17, 2017)
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
	 * Sets the action date
	 * @param actionDate date and time
	 */
	void setActionDate(Date actionDate);
	
	/**
	 * Returns the action date
	 * @return actionDate
	 */
	Date getActionDate();
		
	/**
	 * Sets pre-sentence credits
	 */
	void setPreSentenceCredits(Integer preSentenceCredits);
	
	/**
	 * Returns pre-sentence credits
	 */
	Integer getPreSentenceCredits();
	
	/**
	 * Sets the sentence date
	 * @param date sentence date
	 */
	void setSentenceDate(Date sentenceDate);
	
	/**
	 * Returns the sentence date
	 * @return sentenceDate
	 */
	Date getSentenceDate();
	
	/**
	 * Sets the sentence term years
	 * @param integer sentence term years
	 */
	void setSentenceTermYears(Integer sentenceTermYears);
	
	/**
	 * Returns the sentence term years
	 * @return sentence term years 	 
	 */
	Integer getSentenceTermYears();
	
	/**
	 * Sets the sentence term days
	 * @param integer sentence term days
	 */
	void setSentenceTermDays(Integer sentenceTermDays);
	
	/**
	 * Returns the sentence term days
	 * @return sentence term days 	 
	 */
	Integer getSentenceTermDays();
	
	/**
	 * Sets the parole eligibility date
	 * @param date parole eligibility date
	 */
	void setParoleEligibilityDate(Date paroleEligibilityDate);
	
	/**
	 * Returns the parole eligibility date
	 * @return parole eligibility date 	 
	 */
	Date getParoleEligibilityDate();
	
	/**
	 * Sets the projected discharge date
	 * @param date projected discharge date
	 */
	void setProjectedDischargeDate(Date projectedDischargeDate);
	
	/**
	 * Returns the projected discharge date
	 * @return projected discharge date 	 
	 */
	Date getProjectedDischargeDate();
	
	/**
	 * Sets the maximum discharge date
	 * @param date maximum discharge date
	 */
	void setMaximumDischargeDate(Date maximumDischargeDate);
	
	/**
	 * Returns the maximum discharge date
	 * @return maximum discharge date 	 
	 */
	Date getMaximumDischargeDate();
	
	/**
	 * Sets the status
	 * @param status status
	 */
	void setStatus(PrisonTermStatus status);
	
	/**
	 * Returns the status date
	 * @return status date 	 
	 */
	PrisonTermStatus getStatus();
	
	/**
	 * Sets a sentence to follow
	 * @param boolean sentence to follow
	 */
	void setSentenceToFollow(Boolean sentenceToFollow);
	
	/**
	 * Returns a sentence to follow
	 * @return sentence to follow
	 */
	Boolean getSentenceToFollow();
	
	/**
	 * Sets comments
	 * @param comments comments
	 */
	void setComments(String comments);
	
	/**
	 * Returns comments
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
	 * Sets updateSignature.
	 * 
	 * @return updateSignature
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
	 * @return verification date
	 */
	void setVerificationDate(Date verificationDate);
	
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
