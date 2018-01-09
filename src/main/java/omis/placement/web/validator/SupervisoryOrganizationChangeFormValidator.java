package omis.placement.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.placement.web.form.SupervisoryOrganizationChangeForm;

/**
 * Validator for form to change supervisory organizations.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class SupervisoryOrganizationChangeFormValidator
		implements Validator {

	/** Instantiates validator for form to chagne supervisory organizations. */
	public SupervisoryOrganizationChangeFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return SupervisoryOrganizationChangeForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(
			final Object target, final Errors errors) {
		SupervisoryOrganizationChangeForm changeForm
			= (SupervisoryOrganizationChangeForm) target;
		if (changeForm.getSupervisoryOrganization() == null) {
			errors.rejectValue(
					"supervisoryOrganization", "supervisoryOrganization.empty");
		}
		if (changeForm.getChangeReason() == null) {
			errors.rejectValue("changeReason",
					"supervisoryOrganizationChange.changeReasonEmpty");
		}
		if (changeForm.getEffectiveDate() == null) {
			errors.rejectValue("effectiveDate", "effectiveDate.empty");
		}
		if (changeForm.getEffectiveTime() == null) {
			errors.rejectValue("effectiveTime", "effectiveTime.empty");
		}
	}
}
