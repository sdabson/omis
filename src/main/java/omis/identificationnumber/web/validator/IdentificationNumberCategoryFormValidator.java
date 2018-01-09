package omis.identificationnumber.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.identificationnumber.web.form.IdentificationNumberCategoryForm;

/**
 * IdentificationNumberCategoryFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 31, 2017)
 *@since OMIS 3.0
 *
 */
public class IdentificationNumberCategoryFormValidator implements Validator {

	private static final String NAME_FIELD_NAME = "name";
	
	private static final String NAME_EMPTY_MESSAGE_KEY =
			"identificationNumberCategory.name.empty";

	/**{@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return IdentificationNumberCategoryForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(
				errors, NAME_FIELD_NAME, NAME_EMPTY_MESSAGE_KEY);
	}

}
