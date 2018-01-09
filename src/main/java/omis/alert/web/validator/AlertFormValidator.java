package omis.alert.web.validator;

import omis.alert.web.form.AlertForm;
import omis.web.validator.StringLengthChecks;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form for alerts.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Dec 11, 2013)
 * @since OMIS 3.0
 */
public class AlertFormValidator
		implements Validator {

	private final StringLengthChecks stringLengthChecks;
	
	/**
	 * Instantiates validator for form for alerts.
	 * 
	 * @param stringLengthChecks string length checks
	 */
	public AlertFormValidator(final StringLengthChecks stringLengthChecks) {
		this.stringLengthChecks = stringLengthChecks;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return AlertForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		AlertForm alertForm = (AlertForm) target;
		if (alertForm.getDescription() == null
				|| "".equals(alertForm.getDescription())) {
			errors.rejectValue("description", "alert.description.empty");
		}
		if (alertForm.getExpireDate() == null) {
			errors.rejectValue("expireDate", "alert.expireDate.empty");
		}
		stringLengthChecks.getLargeCheck().check(
				"description", alertForm.getDescription(), errors);
		stringLengthChecks.getLargeCheck().check(
				"resolveDescription",
				alertForm.getResolveDescription(), errors);
	}
}