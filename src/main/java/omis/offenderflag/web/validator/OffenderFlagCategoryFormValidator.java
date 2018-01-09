package omis.offenderflag.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.offenderflag.web.form.OffenderFlagCategoryForm;

/**
 * Offender flag category form validator.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 27, 2017)
 * @since OMIS 3.0
 */
public class OffenderFlagCategoryFormValidator implements Validator {

	/** Instantiates an implementation of OffenderFlagCategoryFormValidator */
	public OffenderFlagCategoryFormValidator() {
		// Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return OffenderFlagCategoryForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		OffenderFlagCategoryForm form = (OffenderFlagCategoryForm) target;
		if (form.getName() == null || "".equals(form.getName())) {
			errors.rejectValue("name", "offenderFlagCategory.name.empty");
		}
		if (form.getRequired() == null) {
			errors.rejectValue(
					"required", "offenderFlagCategory.required.empty");
		}
		if (form.getDescription() == null || "".equals(form.getDescription())) {
			errors.rejectValue(
					"description", "offenderFlagCategory.description.empty");
		}		
	}
}