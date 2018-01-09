package omis.placement.web.validator;

import omis.placement.web.form.CorrectionalStatusChangeForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form to change correctional status.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Dec 19, 2014)
 * @since OMIS 3.0
 */
public class CorrectionalStatusChangeFormValidator
		implements Validator {

	/** Validator for form to change correctional status. */
	public CorrectionalStatusChangeFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return CorrectionalStatusChangeForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		CorrectionalStatusChangeForm correctionalStatusChangeForm
			= (CorrectionalStatusChangeForm) target;
		if (correctionalStatusChangeForm.getSupervisoryOrganizationRequired()
				&& correctionalStatusChangeForm
					.getSupervisoryOrganization() == null) {
			errors.rejectValue("supervisoryOrganization",
					"supervisoryOrganization.empty");
		}
		if (correctionalStatusChangeForm.getEffectiveDate() == null) {
			errors.rejectValue("effectiveDate", "effectiveDate.empty");
		}
		if (correctionalStatusChangeForm.getEffectiveTime() == null) {
			errors.rejectValue("effectiveTime", "effectiveTime.empty");
		}
		if (correctionalStatusChangeForm.getEndDateAllowed()
				&& correctionalStatusChangeForm.getEndDate() != null
				&& correctionalStatusChangeForm.getEffectiveDate() != null
				&& correctionalStatusChangeForm.getEffectiveDate().getTime()
					> correctionalStatusChangeForm.getEndDate().getTime()) {
			errors.rejectValue("endDate", "effectiveDate.greaterThanEndDate");
		}
		if (correctionalStatusChangeForm.getChangeReason() == null) {
			errors.rejectValue("changeReason",
					"correctionalStatusChange.changeReasonEmpty");
		}
		if (correctionalStatusChangeForm.getEndDate() != null
				&& correctionalStatusChangeForm.getEndTime() == null) {
			errors.rejectValue("endTime", "endTime.empty");
		}
		if (correctionalStatusChangeForm.getEndDate() == null
				&& correctionalStatusChangeForm.getEndTime() != null) {
			errors.rejectValue("endDate", "endDate.empty");
		}
		if (correctionalStatusChangeForm.getLocationRequired()
				&& correctionalStatusChangeForm.getLocation() == null) {
			errors.rejectValue("location",
					"correctionalStatusChange.locationEmpty");
		}
	}
}