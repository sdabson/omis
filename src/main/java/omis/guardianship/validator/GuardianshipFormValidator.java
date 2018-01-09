package omis.guardianship.validator;

import omis.guardianship.web.form.GuardianshipForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Guardianship Form Validator.
 * @author Joel Norris
 * @version 0.1.0 (Sep 4, 2013)
 * @since OMIS 3.0
 */
public class GuardianshipFormValidator implements Validator {

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return GuardianshipForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		GuardianshipForm guardianshipForm = (GuardianshipForm) target;
		
		if (guardianshipForm.getDateRange().getStartDate() == null) {
			errors.rejectValue("dateRange.startDate", 
					"guardianship.startDate.empty");
		}
		if (guardianshipForm.getDependent() == null) {
			errors.rejectValue("dependent", "guardianship.dependent.empty");
		}
		if (guardianshipForm.getGuardian() == null) {
			errors.rejectValue("guardian", "guardianship.guardian.empty");
		}
	}
}