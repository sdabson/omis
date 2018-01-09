package omis.questionnaire.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.questionnaire.web.form.QuestionnaireForm;

/**
 * QuestionnaireFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 5, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionnaireFormValidator implements Validator {
	
	private static final String ASSESSOR_REQUIRED_MSG_KEY = "assessor.empty";
	
	/**{@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return QuestionnaireForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "assessor", 
				ASSESSOR_REQUIRED_MSG_KEY);
		
	}
}
