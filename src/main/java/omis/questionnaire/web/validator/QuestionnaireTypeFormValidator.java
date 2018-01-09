package omis.questionnaire.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.questionnaire.web.form.QuestionnaireTypeForm;

/**
 * questionnaireTypeFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 18, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionnaireTypeFormValidator implements Validator {

	private static final String TITLE_REQUIRED_MSG_KEY =
			"questionnaireType.title.empty";
	private static final String CATEGORY_REQUIRED_MSG_KEY =
			"questionnaireType.category.empty";
	private static final String CYCLE_REQUIRED_MSG_KEY =
			"questionnaireType.cycle.empty";
	private static final String START_DATE_REQUIRED_MSG_KEY =
			"questionnaireType.startDate.empty";
	private static final String END_DATE_BEFORE_START_DATE_MSG_KEY =
			"questionnaireType.endDate.before";
	private static final String END_DATE_ALONE_START_DATE_MSG_KEY =
			"questionnaireType.endDate.alone";

	/**{@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return QuestionnaireTypeForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		QuestionnaireTypeForm form = (QuestionnaireTypeForm) target;
		
		ValidationUtils.rejectIfEmpty(errors, "title", 
				TITLE_REQUIRED_MSG_KEY); 
		ValidationUtils.rejectIfEmpty(errors, "category", 
				CATEGORY_REQUIRED_MSG_KEY); 
		ValidationUtils.rejectIfEmpty(errors, "cycle", 
				CYCLE_REQUIRED_MSG_KEY);
		ValidationUtils.rejectIfEmpty(errors, "startDate", 
				START_DATE_REQUIRED_MSG_KEY);
		if(form.getEndDate() != null){
			if(form.getStartDate() == null){
				errors.rejectValue("endDate",
						END_DATE_ALONE_START_DATE_MSG_KEY);
			}
			else if(form.getEndDate().getTime() < form.getStartDate().getTime()){
				errors.rejectValue("endDate",
						END_DATE_BEFORE_START_DATE_MSG_KEY);
			}
		}
	}

}
