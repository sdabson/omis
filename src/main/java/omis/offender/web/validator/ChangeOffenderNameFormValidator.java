package omis.offender.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.offender.web.form.ChangeOffenderNameForm;

/**
 * ChangeOffenderNameFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 8, 2017)
 *@since OMIS 3.0
 *
 */
public class ChangeOffenderNameFormValidator implements Validator {
	
	private static final String LAST_NAME_REQUIRED_MSG_KEY = "lastName.empty";
	
	private static final String FIRST_NAME_REQUIRED_MSG_KEY = "firstName.empty";
	
	private static final String CATEGORY_REQUIRED_MSG_KEY = "category.empty";
	
	
	
	/**{@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return ChangeOffenderNameForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName",
				LAST_NAME_REQUIRED_MSG_KEY);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName",
				FIRST_NAME_REQUIRED_MSG_KEY);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "alternativeNameCategory",
				CATEGORY_REQUIRED_MSG_KEY);
		
	}

}
