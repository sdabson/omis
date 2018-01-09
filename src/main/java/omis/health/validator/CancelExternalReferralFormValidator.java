package omis.health.validator;

import omis.health.web.form.CancelExternalReferralForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form to cancel external referrals.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 25, 2014)
 * @since OMIS 3.0
 */
public class CancelExternalReferralFormValidator
		implements Validator {

	/** Instantiates a validator for form to cancel external referrals. */
	public CancelExternalReferralFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return CancelExternalReferralForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		CancelExternalReferralForm cancelExternalReferralForm
			= (CancelExternalReferralForm) target;
		if (cancelExternalReferralForm.getCancellationStatusReason() == null) {
			errors.rejectValue("cancellationStatusReason",
					"cancellationExternalReferralStatusReason.empty");
		}
	}
}