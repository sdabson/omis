package omis.prisonterm.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.offender.domain.Offender;
import omis.prisonterm.domain.PrisonTerm;
import omis.prisonterm.domain.PrisonTermStatus;
import omis.user.domain.UserAccount;

/**
 * Prison term implementation.
 * 
 * @author Trevor Isles
 * @author Josh Divine
 * @version 0.1.1 (Oct 17, 2017)
 * @since OMIS 3.0
 */

public class PrisonTermImpl implements PrisonTerm {
	
	private static final long serialVersionUID = 1L; 
	
	private Long id;
	
	private Offender offender;
	
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
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private UserAccount verificationUser;
	
	private Date verificationDate;
	
	/**
	 * Instantiates a default instance of prison term.
	 */
	public PrisonTermImpl() {
		//Default constructor.
	}
	
	/** {@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;		
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setOffender(Offender offender) {
		this.offender = offender;	
	}

	/** {@inheritDoc} */
	@Override
	public Offender getOffender() {
		return this.offender;
	}

	/** {@inheritDoc} */
	@Override
	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;		
	}

	/** {@inheritDoc} */
	@Override
	public Date getActionDate() {
		return this.actionDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setPreSentenceCredits(Integer preSentenceCredits) {
		this.preSentenceCredits = preSentenceCredits;
	}

	/** {@inheritDoc} */
	@Override
	public Integer getPreSentenceCredits() {
		return this.preSentenceCredits;
	}

	/** {@inheritDoc} */
	@Override
	public void setSentenceDate(Date sentenceDate) {
		this.sentenceDate = sentenceDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getSentenceDate() {
		return this.sentenceDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setSentenceTermYears(Integer sentenceTermYears) {
		this.sentenceTermYears = sentenceTermYears;
	}

	/** {@inheritDoc} */
	@Override
	public Integer getSentenceTermYears() {
		return this.sentenceTermYears;
	}

	/** {@inheritDoc} */
	@Override
	public void setSentenceTermDays(Integer sentenceTermDays) {
		this.sentenceTermDays = sentenceTermDays;
	}

	/** {@inheritDoc} */
	@Override
	public Integer getSentenceTermDays() {
		return this.sentenceTermDays;
	}

	/** {@inheritDoc} */
	@Override
	public void setParoleEligibilityDate(Date paroleEligibilityDate) {
		this.paroleEligibilityDate = paroleEligibilityDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getParoleEligibilityDate() {
		return this.paroleEligibilityDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setProjectedDischargeDate(Date projectedDischargeDate) {
		this.projectedDischargeDate = projectedDischargeDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getProjectedDischargeDate() {
		return this.projectedDischargeDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setMaximumDischargeDate(Date maximumDischargeDate) {
		this.maximumDischargeDate = maximumDischargeDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getMaximumDischargeDate() {
		return this.maximumDischargeDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setStatus(PrisonTermStatus status) {
		this.status = status;
	}

	/** {@inheritDoc} */
	@Override
	public PrisonTermStatus getStatus() {
		return this.status;
	}

	/** {@inheritDoc} */
	@Override
	public void setSentenceToFollow(Boolean sentenceToFollow) {
		this.sentenceToFollow = sentenceToFollow;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getSentenceToFollow() {
		return this.sentenceToFollow;
	}

	/** {@inheritDoc} */
	@Override
	public void setComments(String comments) {
		this.comments = comments;
	}

	/** {@inheritDoc} */
	@Override
	public String getComments() {
		return comments;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public UserAccount getVerificationUser() {
		return verificationUser;
	}

	/** {@inheritDoc} */
	@Override
	public void setVerificationUser(UserAccount verificationUser) {
		this.verificationUser = verificationUser;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getVerificationDate() {
		return verificationDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setVerificationDate(Date verificationDate) {
		this.verificationDate = verificationDate;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof PrisonTerm)) {
			return false;
		}
		
		PrisonTerm that = (PrisonTerm) o;
		
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		
		if (this.getActionDate() == null) {
			throw new IllegalStateException("Action date required.");
		}
		if (!this.getActionDate().equals(that.getActionDate())) {
			return false;
		}
		
		return true;
		
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		if (this.getActionDate() == null) {
			throw new IllegalStateException("Action date required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getActionDate().hashCode();
		return hashCode;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.format(
				"Offender: #%s, Action date: %s",
				this.getOffender(),
				this.getActionDate());
	}
	
}
