package omis.health.validator;

import omis.health.web.form.ScheduleAuthorizedExternalReferralForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form to schedule authorized external referrals.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 2, 2014)
 * @since OMIS 3.0
 */
public class ScheduleAuthorizedExternalReferralFormValidator
		implements Validator {

	/**
	 * Instantiates a default validator for form to schedule authorized
	 * external referrals.
	 */
	public ScheduleAuthorizedExternalReferralFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ScheduleAuthorizedExternalReferralForm.class
				.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		ScheduleAuthorizedExternalReferralForm
		scheduleAuthorizedExternalReferralForm
			= (ScheduleAuthorizedExternalReferralForm) target;
		if (scheduleAuthorizedExternalReferralForm.getDate() == null) {
			errors.rejectValue("date", "externalReferralDate.empty");
		}
		if (scheduleAuthorizedExternalReferralForm.getStatusReasonRequired()) {
			if (scheduleAuthorizedExternalReferralForm
					.getStatusReason() == null) {
				errors.rejectValue("statusReason",
						"externalReferralStatusReason.empty");
			}
		}
	}
}