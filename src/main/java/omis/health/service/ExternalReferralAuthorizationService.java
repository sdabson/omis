package omis.health.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.domain.ExternalReferralAuthorization;
import omis.health.domain.ExternalReferralAuthorizationRequest;
import omis.health.domain.ExternalReferralMedicalPanelReviewDecisionStatus;
import omis.health.domain.ExternalReferralReason;
import omis.health.domain.HealthRequest;
import omis.health.domain.MedicalFacility;
import omis.health.domain.ProviderAssignment;
import omis.health.exception.HealthRequestException;
import omis.health.exception.ProviderException;
import omis.health.exception.ReferralAuthorizationException;
import omis.offender.domain.Offender;
import omis.person.domain.Person;

/**
 * Service to authorize external referrals.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 7, 2014)
 * @since OMIS 3.0
 */
public interface ExternalReferralAuthorizationService {

	/**
	 * Requests authorization for an external referral.
	 * 
	 * @param offender offender
	 * @param date date of appointment for referral
	 * @param providerAssignment assignment of provider
	 * @param medicalFacility medical facility
	 * @param facility facility
	 * @param reason reason
	 * @param reasonNotes reason notes
	 * @param referredByProviderAssignment assignment of the provider that
	 * referred referred
	 * @param referredDate referred date
	 * @return request for authorization
	 * @throws DuplicateEntityFoundException if the request already exists
	 */
	ExternalReferralAuthorizationRequest requestAuthorization(
			Offender offender, Date date, ProviderAssignment providerAssignment,
			MedicalFacility medicalFacility, Facility facility,
			ExternalReferralReason reason, String reasonNotes,
			ProviderAssignment referredByProviderAssignment, Date referredDate)
					throws DuplicateEntityFoundException;
	
	/**
	 * Request authorization for an external referral for which a health request
	 * exists.
	 * 
	 * @param healthRequest existing health request
	 * @param date date
	 * @param providerAssignment
	 * @param medicalFacility medical facility
	 * @param reason reason
	 * @param reasonNotes reason notes
	 * @param referredByProviderAssignment assignment of the provider that
	 * referred referred
	 * @param referredDate referred date
	 * @return request for authorization
	 * @throws DuplicateEntityFoundException if the request already exists
	 * @throws HealthRequestException if the health request is already resolved
	 * by an external referral or if the request is not for an external referral
	 */
	ExternalReferralAuthorizationRequest requestAuthorizationFromHealthRequest(
			HealthRequest healthRequest, Date date,
			ProviderAssignment providerAssignment,
			MedicalFacility medicalFacility, ExternalReferralReason reason,
			String reasonNotes, ProviderAssignment referredByProviderAssignment,
			Date referredDate)
					throws DuplicateEntityFoundException,
					HealthRequestException;
	
	/**
	 * Updates a request for authorization of an external referral. 
	 * 
	 * @param request request
	 * @param providerAssignment provider assignment
	 * @param medicalFacility medical facility
	 * @param reason reason
	 * @param reasonNotes reason notes
	 * @param referredByProviderAssignment assignment of the provider that
	 * referred referred
	 * @param referredDate referred date
	 * @return request for authorization
	 * @throws DuplicateEntityFoundException if the request already exists
	 */
	ExternalReferralAuthorizationRequest updateRequest(
			ExternalReferralAuthorizationRequest request,
			ProviderAssignment providerAssignment,
			MedicalFacility medicalFacility,
			ExternalReferralReason reason, String reasonNotes,
			ProviderAssignment referredByProviderAssignment, Date referredDate)
					throws DuplicateEntityFoundException;
	
	/**
	 * Authorizes a request for an external referral.
	 * 
	 * @param request request to authorize
	 * @param decisionDate decision date
	 * @param authorizedBy person authorizing request
	 * @param notes notes
	 * @return authorization for request
	 * @throws ReferralAuthorizationException if the request is already
	 * authorized or denied
	 */
	ExternalReferralAuthorization authorizeRequest(
			ExternalReferralAuthorizationRequest request, Date decisionDate,
			Person authorizedBy, String notes)
					throws ReferralAuthorizationException;
	
	/**
	 * Updates authorization to authorized status.
	 * 
	 * @param authorization authorization to update
	 * @param decisionDate decision date
	 * @param authorizedBy person authorizing request
	 * @param notes notes
	 * @return updated authorization
	 */
	ExternalReferralAuthorization authorize(
			ExternalReferralAuthorization authorization, Date decisionDate,
			Person authorizedBy, String notes);
	
	/**
	 * Removes authorization of request.
	 * 
	 * @param request request
	 */
	void removeRequestAuthorization(
			ExternalReferralAuthorizationRequest request);
	
	/**
	 * Approves internal alternative treatment for request.
	 * 
	 * @param request request
	 * @param decisionDate decision date
	 * @param approvedBy person approving request
	 * @param notes notes
	 * @return approval for internal alternative treatment
	 * @throws ReferralAuthorizationException if the request is already
	 * approved
	 */
	ExternalReferralAuthorization
	approveInternalReferralAlternativeTreatmentFromRequest(
			ExternalReferralAuthorizationRequest request, Date decisionDate,
			Person approvedBy, String notes)
					throws ReferralAuthorizationException;
	
	/**
	 * Updates authorization to approve internal alternative treatment.
	 * 
	 * @param authorization authorization to update
	 * @param decisionDate decision date
	 * @return approval for internal referral alternative treatment
	 * @param notes notes
	 * @return updated authorization
	 */
	ExternalReferralAuthorization approveInternalReferralAlternativeTreatment(
			ExternalReferralAuthorization authorization, Date decisionDate,
			Person approvedBy, String notes);
	
	/**
	 * Approves external alternative treatment for request.
	 * 
	 * @param request request
	 * @param decisionDate decision date
	 * @param approvedBy person approving request
	 * @param notes notes
	 * @return approval for external alternative treatment
	 * @throws ReferralAuthorizationException if the request is already
	 * approved
	 */
	ExternalReferralAuthorization
	approveExternalReferralAlternativeTreatmentFromRequest(
			ExternalReferralAuthorizationRequest request, Date decisionDate,
			Person approvedBy, String notes)
					throws ReferralAuthorizationException;
	
	/**
	 * Updates authorization to approve external alternative treatment.
	 * 
	 * @param authorization authorization to update
	 * @param decisionDate decision date
	 * @return approval for external referral alternative treatment
	 * @param notes notes
	 * @return updated authorization
	 */
	ExternalReferralAuthorization approveExternalReferralAlternativeTreatment(
			ExternalReferralAuthorization authorization, Date decisionDate,
			Person approvedBy, String notes);
	
	/**
	 * Requests that a request for an external referral authorization be
	 * reviewed by a medical panel.
	 * 
	 * @param request request
	 * @param decisionDate decision date
	 * @param requiredBy person requiring that request be review
	 * @param notes notes
	 * @return authorization requiring review
	 * @throws ReferralAuthorizationException if the request is already
	 * authorized or denied 
	 */
	ExternalReferralAuthorization requireRequestReview(
			ExternalReferralAuthorizationRequest request, Date decisionDate,
			Person requiredBy, String notes)
					throws ReferralAuthorizationException;
	
	/**
	 * Updates authorization to requiring panel review status.
	 * 
	 * @param authorization authorization to update
	 * @param decisionDate decision date
	 * @param requiredBy person requiring that request be review
	 * @param notes notes
	 * @return updated authorization
	 */
	ExternalReferralAuthorization requireReview(
			ExternalReferralAuthorization authorization, Date decisionDate,
			Person requiredBy, String notes);
	
	/**
	 * Reviews an authorization. 
	 * 
	 * @param authorization authorization to review
	 * @param reviewDate review date
	 * @param status review decision status
	 * @throws ReferralAuthorizationException if the request is already
	 * reviewed
	 */
	void reviewAuthorization(ExternalReferralAuthorization authorization,
			Date reviewDate,
			ExternalReferralMedicalPanelReviewDecisionStatus status) 
					throws ReferralAuthorizationException;
	
	/**
	 * Returns provider assignment for facility on date.
	 * 
	 * @param facility facility
	 * @param date date
	 * @return provider assignments for facility on date
	 */
	List<ProviderAssignment> findProviderAssignmentsByFacility(
			Facility facility, Date date);
	
	/**
	 * Returns external referral reasons.
	 * 
	 * @return external referral reasons
	 */
	List<ExternalReferralReason> findReasons();
	
	/**
	 * Returns medical facilities for an external provider.
	 * 
	 * @param providerAssignment assignment of provider
	 * @return medical facilities for external provider
	 * @throws ProviderException if the provider of the assignment is not
	 * an external provider
	 */
	List<MedicalFacility> findProviderMedicalFacilities(
			ProviderAssignment providerAssignment)
				throws ProviderException;
	
	/**
	 * Returns assignments of external providers by medical facility on date.
	 * 
	 * @param medicalFacility medical facility
	 * @param date date
	 * @return assignments of external providers by medical facility on date
	 */
	List<ProviderAssignment> findProviderAssignmentsByMedicalFacility(
			MedicalFacility medicalFacility, Date date);
	
	/**
	 * Returns assignments of internal providers by facility on date.
	 * 
	 * @param facility facility
	 * @param date date
	 * @return assignments of internal providers by facility on date
	 */
	List<ProviderAssignment> findInternalProviderAssignmentsByFacility(
			Facility facility, Date date);
	
	/**
	 * Returns medical facilities.
	 * 
	 * @return medical facilities
	 */
	List<MedicalFacility> findMedicalFacilities();
	
	/**
	 * Returns whether the request has an authorization.
	 * 
	 * @param request request
	 * @return whether request has an authorization
	 */
	boolean hasAuthorization(ExternalReferralAuthorizationRequest request);
	
	/**
	 * Return the authorization for the request.
	 * 
	 * @param request authorization for request; {@code this} null if 
	 * @return
	 */
	ExternalReferralAuthorization findAuthorization(
			ExternalReferralAuthorizationRequest request);
}