package omis.health.domain.component;

import java.io.Serializable;
import java.util.Date;

import omis.health.domain.ExternalReferralMedicalPanelReviewDecisionStatus;

/**
 * External Referral Medical Panel Review Decision.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Mar 31, 2014)
 * @since OMIS 3.0
 */
public class ExternalReferralMedicalPanelReviewDecision 
	implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date reviewDate;
	
	private ExternalReferralMedicalPanelReviewDecisionStatus status;
	
	/**
	 * Instantiates a default instance of external referral medical panel
	 * review decision.
	 */
	public ExternalReferralMedicalPanelReviewDecision() {
		//Default constructor.
	}

	/**
	 * Returns the review date.
	 * 
	 * @return review date
	 */
	public Date getReviewDate() {
		return this.reviewDate;
	}

	/**
	 * Sets the review date.
	 * 
	 * @param reviewDate review date
	 */
	public void setReviewDate(final Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	/**
	 * Returns the status.
	 * 
	 * @return status
	 */
	public ExternalReferralMedicalPanelReviewDecisionStatus getStatus() {
		return this.status;
	}

	/**
	 * Sets the status.
	 * 
	 * @param status status
	 */
	public void setStatus(
			final ExternalReferralMedicalPanelReviewDecisionStatus status) {
		this.status = status;
	}
}