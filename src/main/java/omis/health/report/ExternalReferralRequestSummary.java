package omis.health.report;

import java.io.Serializable;
import java.util.Date;

import omis.health.domain.ExternalReferralAuthorizationStatus;
import omis.health.domain.ExternalReferralMedicalPanelReviewDecisionStatus;

/**
 * External referral request summary. 
 *
 * @author Stephen Abson
 * @version 0.0.1 (Oct 1, 2014)
 * @since OMIS 3.0
 */
public class ExternalReferralRequestSummary
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final String offenderLastName;
	
	private final String offenderFirstName;
	
	private final String offenderMiddleName;
	
	private final Integer offenderNumber;
	
	private final String medicalFacilityName;
	
	private final Boolean primaryProviderExists;
	
	private final String primaryProviderLastName;
	
	private final String primaryProviderFirstName;
	
	private final String primaryProviderTitleName;
	
	private final String primaryProviderTitleAbbreviation;
	
	private final String reasonName;
	
	private final String reasonNotes;
	
	private final Boolean referredByProviderExists;
	
	private final String referredByProviderLastName;
	
	private final String referredByProviderFirstName;
	
	private final String referredByProviderTitleName;
	
	private final String referredByProviderTitleAbbreviation;
	
	private final Date referredDate;
	
	private final Boolean authorized;
	
	private final Long authorizationId;
	
	private final Date decisionDate;
	
	private final String authorizedByLastName;
	
	private final String authorizedByFirstName;
	
	private final String authorizedByStaffTitleName;
	
	private final String authorizationNotes;
	
	private final ExternalReferralAuthorizationStatus authorizationStatus;
	
	private final Boolean panelReviewRequired;
	
	private final Date panelReviewDecisionDate;
	
	private final ExternalReferralMedicalPanelReviewDecisionStatus
	panelReviewDecisionStatus;
	
	/**
	 * Summary of external referral request.
	 * 
	 * @param id ID of request
	 * @param offenderLastName last name of offender
	 * @param offenderFirstName first name of offender
	 * @param offenderMiddleName middle name of offender
	 * @param offenderNumber offender number
	 * @param medicalFacilityName name of medical facility
	 * @param primaryProviderExists whether primary provider exists
	 * @param primaryProviderLastName last name of primary provider
	 * @param primaryProviderFirstName first name of primary provider
	 * @param primaryProviderTitleName name of provider title
	 * @param primaryProviderTitleAbbreviation abbreviation of provider title
	 * @param reasonName name of reason
	 * @param reasonNotes reason notes
	 * @param referredByProviderExists whether referring provider exists
	 * @param referredByProviderLastName last name of referring provider
	 * @param referredByProviderFirstName first name of referring provider
	 * @param referredByProviderTitleName name of referring provider title
	 * @param referredByProviderTitleAbbreviation abbreviation of provider title
	 * @param referredDate date request was referred
	 * @param authorized whether request is authorized
	 * @param authorizationId ID of request authorization
	 * @param decisionDate date of decision
	 * @param authorizedByLastName last name of person authorizing request
	 * @param authorizedByFirstName first name of person authorizing request
	 * @param authorizedByStaffTitleName title of person authorizing request
	 * @param authorizationStatus status of authorization
	 * @param panelReviewRequired whether a panel review was required
	 * @param panelReviewDecisionDate review date
	 * @param panelReviewDecisionStatus review status
	 */
	public ExternalReferralRequestSummary(
			final Long id,
			final String offenderLastName, final String offenderFirstName,
			final String offenderMiddleName, final Integer offenderNumber,
			final String medicalFacilityName,
			final Boolean primaryProviderExists,
			final String primaryProviderLastName,
			final String primaryProviderFirstName,
			final String primaryProviderTitleName,
			final String primaryProviderTitleAbbreviation,
			final String reasonName,
			final String reasonNotes,
			final Boolean referredByProviderExists,
			final String referredByProviderLastName,
			final String referredByProviderFirstName,			
			final String referredByProviderTitleName,
			final String referredByProviderTitleAbbreviation,
			final Date referredDate,
			final Boolean authorized,
			final Long authorizationId,
			final Date decisionDate,
			final String authorizedByLastName,
			final String authorizedByFirstName,
			final String authorizedByStaffTitleName,
			final String authorizationNotes,
			final ExternalReferralAuthorizationStatus authorizationStatus,
			final Boolean panelReviewRequired,
			final Date panelReviewDecisionDate,
			final ExternalReferralMedicalPanelReviewDecisionStatus
			panelReviewDecisionStatus) {
		this.id = id;
		this.offenderLastName = offenderLastName;
		this.offenderFirstName = offenderFirstName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderNumber = offenderNumber;
		this.medicalFacilityName = medicalFacilityName;
		this.primaryProviderExists = primaryProviderExists;
		this.primaryProviderLastName = primaryProviderLastName;
		this.primaryProviderFirstName = primaryProviderFirstName;
		this.primaryProviderTitleName = primaryProviderTitleName;
		this.primaryProviderTitleAbbreviation = primaryProviderTitleAbbreviation;
		this.reasonName = reasonName;
		this.reasonNotes = reasonNotes;
		this.referredByProviderExists = referredByProviderExists;
		this.referredByProviderLastName = referredByProviderLastName;
		this.referredByProviderFirstName = referredByProviderFirstName;
		this.referredByProviderTitleName = referredByProviderTitleName;
		this.referredByProviderTitleAbbreviation
			= referredByProviderTitleAbbreviation;
		this.referredDate = referredDate;
		this.authorized = authorized;
		this.authorizationId = authorizationId;
		this.decisionDate = decisionDate;
		this.authorizedByLastName = authorizedByLastName;
		this.authorizedByFirstName = authorizedByFirstName;
		this.authorizedByStaffTitleName = authorizedByStaffTitleName;
		this.authorizationNotes = authorizationNotes;
		this.authorizationStatus = authorizationStatus;
		this.panelReviewRequired = panelReviewRequired;
		this.panelReviewDecisionDate = panelReviewDecisionDate;
		this.panelReviewDecisionStatus = panelReviewDecisionStatus;
	}

	/**
	 * Returns the ID.
	 * 
	 * @return ID
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Returns the last name of offender.
	 * 
	 * @return last name of offender
	 */
	public String getOffenderLastName() {
		return this.offenderLastName;
	}

	/**
	 * Returns first name of offender.
	 * 
	 * @return first name of offender
	 */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}

	/**
	 * Returns middle name of offender.
	 * 
	 * @return middle name of offender
	 */
	public String getOffenderMiddleName() {
		return this.offenderMiddleName;
	}

	/**
	 * Returns offender number.
	 * 
	 * @return offender number
	 */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}

	/**
	 * Returns name of medical facility.
	 * 
	 * @return name of medical facility
	 */
	public String getMedicalFacilityName() {
		return this.medicalFacilityName;
	}

	/**
	 * Returns whether primary provider exists.
	 * 
	 * @return whether primary provider exists
	 */
	public Boolean getPrimaryProviderExists() {
		return this.primaryProviderExists;
	}

	/**
	 * Returns last name of primary provider.
	 * 
	 * @return last name of primary provider
	 */
	public String getPrimaryProviderLastName() {
		return this.primaryProviderLastName;
	}

	/**
	 * Returns first name of primary provider.
	 * 
	 * @return first name of primary provider
	 */
	public String getPrimaryProviderFirstName() {
		return this.primaryProviderFirstName;
	}

	/**
	 * Returns the name of the title.
	 * 
	 * @return name of title
	 */
	public String getPrimaryProviderTitleName() {
		return this.primaryProviderTitleName;
	}

	/**
	 * Returns abbreviation of primary provider title.
	 * 
	 * @return abbreviation of primary provider title
	 */
	public String getPrimaryProviderTitleAbbreviation() {
		return this.primaryProviderTitleAbbreviation;
	}

	/**
	 * Returns name of reason.
	 * 
	 * @return name of reason
	 */
	public String getReasonName() {
		return this.reasonName;
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
	 * Returns whether referring provider exists.
	 * 
	 * @return whether referring provider exists
	 */
	public Boolean getReferredByProviderExists() {
		return this.referredByProviderExists;
	}

	/**
	 * Returns last name of referring provider.
	 * 
	 * @return last name of referring provider
	 */
	public String getReferredByProviderLastName() {
		return this.referredByProviderLastName;
	}

	/**
	 * Returns first name of referring provider.
	 * 
	 * @return first name of referring provider
	 */
	public String getReferredByProviderFirstName() {
		return this.referredByProviderFirstName;
	}

	/**
	 * Returns name of referring provider title.
	 * 
	 * @return name of referring provider title
	 */
	public String getReferredByProviderTitleName() {
		return this.referredByProviderTitleName;
	}

	/**
	 * Returns abbreviation of referring provider.
	 * 
	 * @return abbreviation of referring provider
	 */
	public String getReferredByProviderTitleAbbreviation() {
		return this.referredByProviderTitleAbbreviation;
	}

	/**
	 * Returns date of referring provider.
	 * 
	 * @return date of referring provider
	 */
	public Date getReferredDate() {
		return this.referredDate;
	}

	/**
	 * Returns whether request is authorized.
	 * 
	 * @return whether request is authorized
	 */
	public Boolean getAuthorized() {
		return this.authorized;
	}

	/**
	 * Returns date of decision.
	 * 
	 * @return date of decision
	 */
	public Date getDecisionDate() {
		return this.decisionDate;
	}

	/**
	 * Returns last name of person authorizing request.
	 * 
	 * @return last name of person authorizing request
	 */
	public String getAuthorizedByLastName() {
		return this.authorizedByLastName;
	}

	/**
	 * Returns first name of person authorizing request.
	 * 
	 * @return first name of person authorizing request
	 */
	public String getAuthorizedByFirstName() {
		return this.authorizedByFirstName;
	}

	/**
	 * Returns name of title of person authorizing request.
	 * 
	 * @return name of title of person authorizing request
	 */
	public String getAuthorizedByStaffTitleName() {
		return this.authorizedByStaffTitleName;
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
	 * Returns ID of authorization.
	 * 
	 * @return ID of authorization
	 */
	public Long getAuthorizationId() {
		return this.authorizationId;
	}

	/**
	 * Returns authorization status.
	 * 
	 * @return authorization status
	 */
	public ExternalReferralAuthorizationStatus getAuthorizationStatus() {
		return this.authorizationStatus;
	}

	/**
	 * Returns whether panel review is required.
	 * 
	 * @return whether panel review is required
	 */
	public Boolean getPanelReviewRequired() {
		return this.panelReviewRequired;
	}

	/**
	 * Returns date of panel review.
	 * 
	 * @return date of panel review
	 */
	public Date getPanelReviewDecisionDate() {
		return this.panelReviewDecisionDate;
	}

	/**
	 * Returns status of medical review decision. 
	 * 
	 * @return status of medical review decision
	 */
	public ExternalReferralMedicalPanelReviewDecisionStatus
	getPanelReviewDecisionStatus() {
		return this.panelReviewDecisionStatus;
	}
}
