package omis.health.validator;

import omis.health.web.form.RequestExternalReferralAuthorizationForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form to request external referral authorizations.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 30, 2014)
 * @since OMIS 3.0
 */
public class RequestExternalReferralAuthorizationFormValidator
		implements Validator {

	/**
	 * Instantiates a validator for form to request external referral
	 * authorization.
	 */
	public RequestExternalReferralAuthorizationFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return RequestExternalReferralAuthorizationForm.class
				.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		RequestExternalReferralAuthorizationForm
			requestExternalReferralAuthorizationForm
				= (RequestExternalReferralAuthorizationForm) target;
		if (requestExternalReferralAuthorizationForm
				.getOffenderRequired() != null
				&& requestExternalReferralAuthorizationForm
				.getOffenderRequired()
				&& requestExternalReferralAuthorizationForm
					.getOffender() == null) {
			errors.rejectValue("offender", "offender.empty");
		}
		if (requestExternalReferralAuthorizationForm.getMedicalFacility() == null) {
			errors.rejectValue("medicalFacility", "medicalFacility.empty");
		}
		if (requestExternalReferralAuthorizationForm.getReason() == null) {
			errors.rejectValue("reason", "externalReferralReason.empty");
		}
		if (requestExternalReferralAuthorizationForm.getAuthorize() != null
				&& requestExternalReferralAuthorizationForm.getAuthorize()) {
			if (requestExternalReferralAuthorizationForm
					.getDecisionDate() == null) {
				errors.rejectValue("decisionDate",
						"externalReferralAuthorizationDecisionDate.empty");
			}
			if (requestExternalReferralAuthorizationForm
					.getAuthorizedBy() == null) {
				errors.rejectValue("authorizedByText",
						"externalReferralAuthorizationAuthorizedBy.empty");
			}
			if (requestExternalReferralAuthorizationForm.getStatus() == null) {
				errors.rejectValue("status",
						"externalReferralAuthorizationStatus.empty");
			}
			if (requestExternalReferralAuthorizationForm
						.getMedicalPanelReviewDecisionStatus() != null
					&& requestExternalReferralAuthorizationForm
						.getReviewDate() == null) {
					errors.rejectValue("reviewDate",
						"externalReferralAuthorizationReviewDate.empty");
			}
			if (requestExternalReferralAuthorizationForm
					.getMedicalPanelReviewDecisionStatus() == null
				&& requestExternalReferralAuthorizationForm
					.getReviewDate() != null) {
				errors.rejectValue("medicalPanelReviewDecisionStatus",
					"medicalPanelReviewDecisionStatus.empty");
			}
		}
	}
}