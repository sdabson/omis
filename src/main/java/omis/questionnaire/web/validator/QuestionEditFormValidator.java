package omis.questionnaire.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.questionnaire.web.form.AllowedAnswerItem;
import omis.questionnaire.web.form.ItemOperation;
import omis.questionnaire.web.form.QuestionAnswerEditItem;

/**
 * QuestionEditFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Dec 6, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionEditFormValidator implements Validator {
	
	private static final String QUESTION_NUMBER_REQUIRED_MSG_KEY =
			"question.number.required";
	private static final String QUESTION_REQUIRED_MSG_KEY =
			"question.required";
	private static final String QUESTION_TEXT_REQUIRED_MSG_KEY =
			"question.text.required";
	private static final String QUESTION_TYPE_REQUIRED_MSG_KEY =
			"question.type.required";
	private static final String ANSWER_VALUE_REQUIRED_MSG_KEY =
			"answerValue.required";
	private static final String ANSWER_VALUE_DESCRIPTION_REQUIRED_MSG_KEY =
			"answerValue.description.required";
	
	/**{@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return QuestionAnswerEditItem.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		
		QuestionAnswerEditItem question = (QuestionAnswerEditItem) target;
		
		ValidationUtils.rejectIfEmpty(errors, "questionNumber", 
				QUESTION_NUMBER_REQUIRED_MSG_KEY);
		
		if(question.getExistingQuestion()){
			ValidationUtils.rejectIfEmpty(errors, "question",
					QUESTION_REQUIRED_MSG_KEY);
		}
		else{
			ValidationUtils.rejectIfEmpty(errors, "questionText",
					QUESTION_TEXT_REQUIRED_MSG_KEY);
			ValidationUtils.rejectIfEmpty(errors, "questionCategory",
					QUESTION_TYPE_REQUIRED_MSG_KEY);
		}
		
		if(question.getAllowedAnswerItems() != null){
			int j = 0;
			for(AllowedAnswerItem answer 
					: question.getAllowedAnswerItems()){
				if(!answer.getOperation().equals(ItemOperation.REMOVE)){
					if(answer.getExistingAnswer()){
						ValidationUtils.rejectIfEmpty(errors,
							"allowedAnswerItems[" + j + "].answerValue", 
								ANSWER_VALUE_REQUIRED_MSG_KEY);
					}
					else{
						ValidationUtils.rejectIfEmpty(errors,
							"allowedAnswerItems[" + j + "].description", 
						ANSWER_VALUE_DESCRIPTION_REQUIRED_MSG_KEY);
					}
				}
				j++;
			}
		}
	}
}
