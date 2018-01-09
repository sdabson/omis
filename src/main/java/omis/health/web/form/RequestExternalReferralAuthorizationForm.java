package omis.health.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.health.domain.ExternalReferralAuthorizationStatus;
import omis.health.domain.ExternalReferralMedicalPanelReviewDecisionStatus;
import omis.health.domain.ExternalReferralReason;
import omis.health.domain.MedicalFacility;
import omis.health.domain.ProviderAssignment;
import omis.offender.domain.Offender;
import omis.person.domain.Person;

/**
 * Form to request authorization for external referrals.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 27, 2014)
 * @since OMIS 3.0
 */
public class RequestExternalReferralAuthorizationForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Boolean offenderRequired;
	
	private Offender offender;
		
	private ProviderAssignment providerAssignment;
	
	private MedicalFacility medicalFacility;
	
	private ExternalReferralReason reason;
	
	private String reasonNotes;
	
	private ProviderAssignment referredByProviderAssignment;
	
	private Date referredDate;
	
	private Boolean authorize;
	
	private Date decisionDate;
	
	private String authorizedByText;
	
	private Person authorizedBy;
	
	private String authorizedByLabel;
	
	private String authorizationNotes;
	
	private ExternalReferralAuthorizationStatus status;
	
	private	Date reviewDate;
	
	private ExternalReferralMedicalPanelReviewDecisionStatus
	medicalPanelReviewDecisionStatus;
	
	/** Instantiates form to request authorization for external referrals. */
	public RequestExternalReferralAuthorizationForm() {
		// Default instantiation
	}

	/**
	 * Returns whether offender is required.
	 * 
	 * @return whether offender is required
	 */
	public Boolean getOffenderRequired() {
		return this.offenderRequired;
	}

	/**
	 * Sets whether offender is required.
	 * 
	 * @param offenderRequired whether offender is required
	 */
	public void setOffenderRequired(final Boolean offenderRequired) {
		this.offenderRequired = offenderRequired;
	}

	/**
	 * Returns the offender.
	 * 
	 * @return offender
	 */
	public Offender getOffender() {
		return this.offender;
	}

	/**
	 * Sets the offender.
	 * 
	 * @param offender offender
	 */
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}

	/**
	 * Returns the provider assignment.
	 * 
	 * @return provider assignment
	 */
	public ProviderAssignment getProviderAssignment() {
		return this.providerAssignment;
	}

	/**
	 * Sets the provider assignment.
	 * 
	 * @param providerAssignment provider assignment
	 */
	public void setProviderAssignment(
			final ProviderAssignment providerAssignment) {
		this.providerAssignment = providerAssignment;
	}

	/**
	 * Returns the medical facility.
	 * 
	 * @return medical facility
	 */
	public MedicalFacility getMedicalFacility() {
		return this.medicalFacility;
	}

	/**
	 * Sets the medical facility.
	 * 
	 * @param medicalFacility medical facility
	 */
	public void setMedicalFacility(final MedicalFacility medicalFacility) {
		this.medicalFacility = medicalFacility;
	}

	/**
	 * Returns the reason.
	 * 
	 * @return reason
	 */
	public ExternalReferralReason getReason() {
		return this.reason;
	}

	/**
	 * Sets the reason.
	 * 
	 * @param reason reason
	 */
	public void setReason(ExternalReferralReason reason) {
		this.reason = reason;
	}

	/**
	 * Returns reason notes.
	 * 
	 * @return reason notes
	 */
	public String getReasonNotes() {
		return this.reasonNotes;
	}

	/**
	 * Sets reason notes.
	 * 
	 * @param reasonNotes reason notes
	 */
	public void setReasonNotes(final String reasonNotes) {
		this.reasonNotes = reasonNotes;
	}

	/**
	 * Returns assignment of provider that referred referral.
	 * 
	 * @return assignment of provider that referred referral
	 */
	public ProviderAssignment getReferredByProviderAssignment() {
		return this.referredByProviderAssignment;
	}

	/**
	 * Sets assignment of provider that referred referral.
	 * 
	 * @param referredByProviderAssignment assignment of provider that referred
	 * referral
	 */
	public void setReferredByProviderAssignment(
			final ProviderAssignment referredByProviderAssignment) {
		this.referredByProviderAssignment = referredByProviderAssignment;
	}

	/**
	 * Returns referred date.
	 * 
	 * @return referred date
	 */
	public Date getReferredDate() {
		return referredDate;
	}

	/**
	 * Sets referred date.
	 * 
	 * @param referredDate referred date
	 */
	public void setReferredDate(final Date referredDate) {
		this.referredDate = referredDate;
	}

	/**
	 * Returns whether to authorize request.
	 * 
	 * @return whether to authorize request
	 */
	public Boolean getAuthorize() {
		return this.authorize;
	}

	/**
	 * Sets whether to authorize request.
	 * 
	 * @param authorize whether to authorize request
	 */
	public void setAuthorize(final Boolean authorize) {
		this.authorize = authorize;
	}

	/**
	 * Returns the decision date.
	 * 
	 * @return decision date
	 */
	public Date getDecisionDate() {
		return this.decisionDate;
	}

	/**
	 * Sets the decision date.
	 * 
	 * @param decisionDate decision date
	 */
	public void setDecisionDate(final Date decisionDate) {
		this.decisionDate = decisionDate;
	}

	/**
	 * Returns the authorized by search text.
	 * 
	 * @return authorized by search text
	 */
	public String getAuthorizedByText() {
		return this.authorizedByText;
	}

	/**
	 * Sets the authorized by search text.
	 * 
	 * @param authorizedByText authorized by search text
	 */
	public void setAuthorizedByText(final String authorizedByText) {
		this.authorizedByText = authorizedByText;
	}

	/**
	 * Returns the person authorizing the request.
	 * 
	 * @return person authorizing request
	 */
	public Person getAuthorizedBy() {
		return this.authorizedBy;
	}

	/**
	 * Sets the person authorizing the request.
	 * 
	 * @param authorizedBy person authorizing request
	 */
	public void setAuthorizedBy(final Person authorizedBy) {
		this.authorizedBy = authorizedBy;
	}

	/**
	 * Returns authorized by label.
	 * 
	 * @return authorized by label
	 */
	public String getAuthorizedByLabel() {
		return this.authorizedByLabel;
	}

	/**
	 * Sets authorized by label.
	 * 
	 * @param authorized by label
	 */
	public void setAuthorizedByLabel(final String authorizedByLabel) {
		this.authorizedByLabel = authorizedByLabel;
	}

	/**
	 * Returns authorization notes.
	 * 
	 * @return authorization notes
	 */
	public String getAuthorizationNotes() {
		return this.authorizationNotes;
	}

	/**
	 * Sets authorization notes.
	 * 
	 * @param authorizationNotes authorization notes
	 */
	public void setAuthorizationNotes(final String authorizationNotes) {
		this.authorizationNotes = authorizationNotes;
	}

	/**
	 * Returns the status.
	 * 
	 * @return status
	 */
	public ExternalReferralAuthorizationStatus getStatus() {
		return this.status;
	}

	/**
	 * Sets the status.
	 * 
	 * @param status status
	 */
	public void setStatus(
			final ExternalReferralAuthorizationStatus status) {
		this.status = status;
	}

	/**
	 * Returns the MRP review date.
	 * 
	 * @return MRP review date
	 */
	public Date getReviewDate() {
		return this.reviewDate;
	}

	/**
	 * Sets the MRP review date.
	 * 
	 * @param reviewDate MRP review date
	 */
	public void setReviewDate(final Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	/**
	 * Returns medical panel review decision status.
	 * 
	 * @return medical panel review decision status
	 */
	public ExternalReferralMedicalPanelReviewDecisionStatus
	getMedicalPanelReviewDecisionStatus() {
		return this.medicalPanelReviewDecisionStatus;
	}

	/**
	 * Sets medical panel review decision status.
	 * 
	 * @param medicalPanelReviewDecisionStatus medical panel review decision
	 * status
	 */
	public void setMedicalPanelReviewDecisionStatus(
			final ExternalReferralMedicalPanelReviewDecisionStatus
				medicalPanelReviewDecisionStatus) {
		this.medicalPanelReviewDecisionStatus
			= medicalPanelReviewDecisionStatus;
	}
}