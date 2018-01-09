package omis.offense.validator;

import omis.offense.web.form.OffenseForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Offense Form Validator.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 30, 2013)
 * @since OMIS 3.0
 */
public class OffenseFormValidator implements Validator {

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return OffenseForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		
		OffenseForm offenseForm = (OffenseForm) target;
		
		if (offenseForm.getName() == null 
				|| offenseForm.getName().length() < 1) {
			errors.rejectValue("name", "offense.name.empty");
		}
		if (offenseForm.getViolationCode() == null 
				|| offenseForm.getName().length() < 1) {
			errors.rejectValue("violationCode", "offense.violationCode.empty");
		}
	}
}
