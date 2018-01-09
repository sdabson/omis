package omis.health.validator;

import omis.health.web.form.CancelInternalReferralForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form to cancel internal referral.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 20, 2014)
 * @since OMIS 3.0
 */
public class CancelInternalReferralFormValidator
		implements Validator {

	/** Validator for form to cancel internal referrals. */
	public CancelInternalReferralFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return CancelInternalReferralForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		CancelInternalReferralForm cancelInternalReferralForm
			= (CancelInternalReferralForm) target;
		if (cancelInternalReferralForm.getStatusReason() == null) {
			errors.rejectValue("statusReason", "statusReason.empty");
		}
	}
}