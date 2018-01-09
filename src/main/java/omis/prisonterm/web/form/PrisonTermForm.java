package omis.prisonterm.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.prisonterm.domain.PrisonTerm;
import omis.prisonterm.domain.PrisonTermStatus;
import omis.user.domain.UserAccount;

/**
 * Used to capture prison term information.
 * 
 * @author Trevor Isles
 * @author Josh Divine
 * @version 0.1.1 (Oct 17, 2017)
 * @since OMIS 3.0
 */

public class PrisonTermForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Date actionDate; 
	
	private Integer preSentenceCredits; 
	
	private Date sentenceDate; 
	
	private Integer sentenceTermYears; 
	
	private Integer sentenceTermDays; 
	
	private Date paroleEligibilityDate;
	
	private Date projectedDischargeDate; 
	
	private Date maximumDischargeDate; 
	
	private PrisonTermStatus status; 
	
	private Boolean sentenceToFollow; 
	
	private String comments;
	
	private PrisonTerm prisonTerm;
	
	private UserAccount verificationUser;
	
	private String verificationUserInput;
	
	private Date verificationDate;
	
	/**
	 * Instantiates a default prison term form. */
	public PrisonTermForm() {
		// Default instantiation
	}

	/**
	 * @return the actionDate
	 */
	public Date getActionDate() {
		return actionDate;
	}

	/**
	 * @param actionDate the actionDate to set
	 */
	public void setActionDate(final Date actionDate) {
		this.actionDate = actionDate;
	}

	/**
	 * @return the preSentenceCredits
	 */
	public Integer getPreSentenceCredits() {
		return preSentenceCredits;
	}

	/**
	 * @param preSentenceCredits the preSentenceCredits to set
	 */
	public void setPreSentenceCredits(final Integer preSentenceCredits) {
		this.preSentenceCredits = preSentenceCredits;
	}

	/**
	 * @return the sentenceDate
	 */
	public Date getSentenceDate() {
		return sentenceDate;
	}

	/**
	 * @param sentenceDate the sentenceDate to set
	 */
	public void setSentenceDate(final Date sentenceDate) {
		this.sentenceDate = sentenceDate;
	}

	/**
	 * @return the sentenceTermYears
	 */
	public Integer getSentenceTermYears() {
		return sentenceTermYears;
	}

	/**
	 * @param sentenceTermYears the sentenceTermYears to set
	 */
	public void setSentenceTermYears(final Integer sentenceTermYears) {
		this.sentenceTermYears = sentenceTermYears;
	}

	/**
	 * @return the sentenceTermDays
	 */
	public Integer getSentenceTermDays() {
		return sentenceTermDays;
	}

	/**
	 * @param sentenceTermDays the sentenceTermDays to set
	 */
	public void setSentenceTermDays(final Integer sentenceTermDays) {
		this.sentenceTermDays = sentenceTermDays;
	}

	/**
	 * @return the paroleEligibilityDate
	 */
	public Date getParoleEligibilityDate() {
		return paroleEligibilityDate;
	}

	/**
	 * @param paroleEligibilityDate the paroleEligibilityDate to set
	 */
	public void setParoleEligibilityDate(final Date paroleEligibilityDate) {
		this.paroleEligibilityDate = paroleEligibilityDate;
	}

	/**
	 * @return the projectedDischargeDate
	 */
	public Date getProjectedDischargeDate() {
		return projectedDischargeDate;
	}

	/**
	 * @param projectedDischargeDate the projectedDischargeDate to set
	 */
	public void setProjectedDischargeDate(final Date projectedDischargeDate) {
		this.projectedDischargeDate = projectedDischargeDate;
	}

	/**
	 * @return the maximumDischargeDate
	 */
	public Date getMaximumDischargeDate() {
		return maximumDischargeDate;
	}

	/**
	 * @param maximumDischargeDate the maximumDischargeDate to set
	 */
	public void setMaximumDischargeDate(final Date maximumDischargeDate) {
		this.maximumDischargeDate = maximumDischargeDate;
	}

	/**
	 * @return the status
	 */
	public PrisonTermStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(final PrisonTermStatus status) {
		this.status = status;
	}

	/**
	 * @return the sentenceToFollow
	 */
	public Boolean getSentenceToFollow() {
		return sentenceToFollow;
	}

	/**
	 * @param sentenceToFollow the sentenceToFollow to set
	 */
	public void setSentenceToFollow(final Boolean sentenceToFollow) {
		this.sentenceToFollow = sentenceToFollow;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(final String comments) {
		this.comments = comments;
	}

	/**
	 * @return the prisonTerm
	 */
	public PrisonTerm getPrisonTerm() {
		return prisonTerm;
	}

	/**
	 * @param prisonTerm the prisonTerm to set
	 */
	public void setPrisonTerm(final PrisonTerm prisonTerm) {
		this.prisonTerm = prisonTerm;
	}

	/**
	 * @return the verificationUser
	 */
	public UserAccount getVerificationUser() {
		return verificationUser;
	}

	/**
	 * @param verificationUser the verificationUser to set
	 */
	public void setVerificationUser(final UserAccount verificationUser) {
		this.verificationUser = verificationUser;
	}

	/**
	 * @return the verificationDate
	 */
	public Date getVerificationDate() {
		return verificationDate;
	}

	/**
	 * @param verificationDate the verificationDate to set
	 */
	public void setVerificationDate(final Date verificationDate) {
		this.verificationDate = verificationDate;
	}

	/**
	 * @return the verificationUserInput
	 */
	public String getVerificationUserInput() {
		return verificationUserInput;
	}

	/**
	 * @param verificationUserInput the verificationUserInput to set
	 */
	public void setVerificationUserInput(String verificationUserInput) {
		this.verificationUserInput = verificationUserInput;
	}

}
