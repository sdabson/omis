package omis.health.domain.impl;

import java.util.Date;

import omis.health.domain.ExternalReferralAuthorization;
import omis.health.domain.ExternalReferralAuthorizationRequest;
import omis.health.domain.ExternalReferralAuthorizationStatus;
import omis.health.domain.component.ExternalReferralMedicalPanelReviewDecision;
import omis.person.domain.Person;

/**
 * Implementation of authorization for external referrals.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 7, 2014)
 * @since OMIS 3.0
 */
public class ExternalReferralAuthorizationImpl
		implements ExternalReferralAuthorization {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private ExternalReferralAuthorizationRequest request;
	
	private Long decisionDate;
	
	private Person authorizedBy;
	
	private String notes;
	
	private ExternalReferralMedicalPanelReviewDecision panelReviewDecision;
	
	private ExternalReferralAuthorizationStatus status;
	
	/**
	 * Instantiates an implementation of authorization for external referrals.
	 */
	public ExternalReferralAuthorizationImpl() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setRequest(
			final ExternalReferralAuthorizationRequest request) {
		this.request = request;
	}

	/** {@inheritDoc} */
	@Override
	public ExternalReferralAuthorizationRequest getRequest() {
		return this.request;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getDecisionDate() {
		if (this.decisionDate != null) {
			return new Date(this.decisionDate);
		} else {
			return null;
		}
	}

	/** {@inheritDoc} */
	@Override
	public void setDecisionDate(final Date decisionDate) {
		if (decisionDate != null) {
			this.decisionDate = decisionDate.getTime();
		} else {
			this.decisionDate = null;
		}
	}

	/** {@inheritDoc} */
	@Override
	public Person getAuthorizedBy() {
		return this.authorizedBy;
	}

	/** {@inheritDoc} */
	@Override
	public void setAuthorizedBy(final Person authorizedBy) {
		this.authorizedBy = authorizedBy;
	}

	/** {@inheritDoc} */
	@Override
	public void setNotes(final String notes) {
		this.notes = notes;
	}

	/** {@inheritDoc} */
	@Override
	public String getNotes() {
		return this.notes;
	}
	
	/** {@inheritDoc} */
	@Override
	public ExternalReferralMedicalPanelReviewDecision getPanelReviewDecision() {
		return this.panelReviewDecision;
	}

	/** {@inheritDoc} */
	@Override
	public void setPanelReviewDecision(
			final ExternalReferralMedicalPanelReviewDecision
			panelReviewDecision) {
		this.panelReviewDecision = panelReviewDecision;
	}

	/** {@inheritDoc} */
	@Override
	public ExternalReferralAuthorizationStatus getStatus() {
		return this.status;
	}

	/** {@inheritDoc} */
	@Override
	public void setStatus(final ExternalReferralAuthorizationStatus status) {
		this.status = status;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof ExternalReferralAuthorization)) {
			return false;
		}
		ExternalReferralAuthorization that
				= (ExternalReferralAuthorization) obj;
		if (this.getRequest() == null) {
			throw new IllegalStateException("Request required");
		}
		if (!this.getRequest().equals(that.getRequest())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getRequest() == null) {
			throw new IllegalStateException("Request required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getRequest().hashCode();
		return hashCode;
	}
}