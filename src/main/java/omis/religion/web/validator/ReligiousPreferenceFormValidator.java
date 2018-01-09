package omis.religion.web.validator;

import omis.religion.web.form.ReligiousPreferenceForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form for religious preferences.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 24, 2014)
 * @since OMIS 3.0
 */
public class ReligiousPreferenceFormValidator
		implements Validator {
	
	/** Instantiates a validator for form for religious preferences. */
	public ReligiousPreferenceFormValidator() {
		// Default instantiation
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ReligiousPreferenceForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		ReligiousPreferenceForm religiousPreferenceForm
			= (ReligiousPreferenceForm) target;
		if (religiousPreferenceForm.getReligion() == null) {
			errors.rejectValue("religion", "religion.empty");
		}
		if (religiousPreferenceForm.getStartDate() == null) {
			errors.rejectValue("startDate", "startDate.empty");
		} else if (religiousPreferenceForm.getEndDate() != null
				&& religiousPreferenceForm.getStartDate().getTime()
				> religiousPreferenceForm.getEndDate().getTime()) {
			errors.rejectValue("startDate",
					"dateRange.startDateGreaterThanEndDate");
		}
		if (religiousPreferenceForm.getVerificationResult() != null
				&& religiousPreferenceForm.getVerificationResult()) {
			if (religiousPreferenceForm.getVerificationUserAccount() == null) {
				errors.rejectValue("verificationUserAccount",
						"verificationUserAccount.empty");
			}
			if (religiousPreferenceForm.getVerificationDate() == null) {
				errors.rejectValue("verificationDate",
						"verificationDate.empty");
			}
			if (religiousPreferenceForm.getVerificationMethod() == null) {
				errors.rejectValue("verificationMethod",
						"verificationMethod.empty");
			}
		} else {
			if (religiousPreferenceForm.getVerificationUserAccount() != null) {
				errors.rejectValue("verificationUserAccount",
						"verificationSignature.notEmpty");
			}
			if (religiousPreferenceForm.getVerificationDate() != null) {
				errors.rejectValue("verificationDate",
						"verificationSignature.notEmpty");
			}
			if (religiousPreferenceForm.getVerificationMethod() != null) {
				errors.rejectValue("verificationMethod",
						"verificationSignature.notEmpty");
			}
		}
	}
}