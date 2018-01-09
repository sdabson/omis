package omis.staff.web.validator;

import omis.staff.web.form.StaffTitleForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form for staff titles.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 5, 2014)
 * @since OMIS 3.0
 */
public class StaffTitleFormValidator
		implements Validator {

	/** Instantiates a validator for staff title form. */
	public StaffTitleFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return StaffTitleForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		StaffTitleForm staffTitleForm = (StaffTitleForm) target;
		if (staffTitleForm.getName() == null
				|| staffTitleForm.getName().length() == 0) {
			errors.rejectValue("name", "name.empty");
		}
		if (staffTitleForm.getSortOrder() == null) {
			errors.rejectValue("sortOrder", "sortOrder.empty");
		}
		if (staffTitleForm.getValid() == null) {
			errors.rejectValue("valid", "valid.empty");
		}
	}
}