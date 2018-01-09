package omis.health.validator;

import omis.health.web.form.CancelLabWorkReferralForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Cancel lab work referral form validator.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 20, 2014)
 * @since OMIS 3.0
 *
 */
public class CancelLabWorkReferralFormValidator implements Validator {

	/**
	 * Instantiates a default instance of cancel lab work referral form
	 * validator.
	 */
	public CancelLabWorkReferralFormValidator() {
		//Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return CancelLabWorkReferralForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		CancelLabWorkReferralForm form = (CancelLabWorkReferralForm) target;
		if (form.getStatusReason() == null) {
			errors.rejectValue("statusReason", "statusReason.empty");
		}
	}
}