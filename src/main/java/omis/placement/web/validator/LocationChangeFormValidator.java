package omis.placement.web.validator;

import omis.placement.web.form.LocationChangeForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form to change locations.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Mar 6, 2015)
 * @since OMIS 3.0
 */
public class LocationChangeFormValidator
		implements Validator {

	/** Instantiates a validator for form to change locations. */
	public LocationChangeFormValidator() {
		// Default instantiation
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return LocationChangeForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		LocationChangeForm locationChangeForm = (LocationChangeForm) target;
		if (locationChangeForm.getEffectiveDate() == null) {
			errors.rejectValue("effectiveDate", "effectiveDate.empty");
		}
		if (locationChangeForm.getEffectiveTime() == null) {
			errors.rejectValue("effectiveTime", "effectiveTime.empty");
		}
		if (locationChangeForm.getLocation() == null) {
			errors.rejectValue("location", "location.empty");
		}
		if (locationChangeForm.getEndDate() != null
				&& locationChangeForm.getEndTime() == null) {
			errors.rejectValue("endTime", "endTime.empty");
		}
		if (locationChangeForm.getEndDate() == null
				&& locationChangeForm.getEndTime() != null) {
			errors.rejectValue("endDate", "endDate.empty");
		}
	}
}