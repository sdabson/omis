package omis.health.validator;

import omis.health.web.form.HealthRequestForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form for health requests.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 16, 2014)
 * @since OMIS 3.0
 */
public class HealthRequestFormValidator
		implements Validator {

	/** Instantiates a validator for form for health requests. */
	public HealthRequestFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return HealthRequestForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		HealthRequestForm healthRequestForm
			= (HealthRequestForm) target;
		if (healthRequestForm.getCategory() == null) {
			errors.rejectValue("category", "healthRequestCategory.empty");
		}
		if (healthRequestForm.getOffenderRequired() != null
				&& healthRequestForm.getOffenderRequired()
				&& healthRequestForm.getOffender() == null) {
			errors.rejectValue("offender", "offender.empty");
		}
	}
}