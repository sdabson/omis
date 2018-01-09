package omis.questionnaire.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.questionnaire.web.form.QuestionnaireSectionForm;

/**
 * QuestionnaireSectionFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 18, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionnaireSectionFormValidator implements Validator {

	private static final String TITLE_REQUIRED_MSG_KEY 
			= "questionnaireSection.title.empty";
	private static final String SECTION_NUMBER_REQUIRED_MSG_KEY
			= "questionnaireSection.sectionNumber.empty";
	private static final String SECTION_TYPE_REQUIRED_MSG_KEY
			= "questionnaireSection.sectionType.empty";

	/**{@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return QuestionnaireSectionForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "title", 
				TITLE_REQUIRED_MSG_KEY); 
		ValidationUtils.rejectIfEmpty(errors, "sectionNumber", 
				SECTION_NUMBER_REQUIRED_MSG_KEY);
		ValidationUtils.rejectIfEmpty(errors, "sectionType", 
				SECTION_TYPE_REQUIRED_MSG_KEY);
	}

}
