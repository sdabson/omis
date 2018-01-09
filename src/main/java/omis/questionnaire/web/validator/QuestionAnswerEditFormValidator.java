package omis.questionnaire.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.questionnaire.web.form.AllowedAnswerItem;
import omis.questionnaire.web.form.ItemOperation;
import omis.questionnaire.web.form.QuestionAnswerEditForm;
import omis.questionnaire.web.form.QuestionAnswerEditItem;

/**
 * QuestionAnswerEditFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 18, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionAnswerEditFormValidator implements Validator {

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
	public boolean supports(final Class<?> clazz) {
		return QuestionAnswerEditForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		QuestionAnswerEditForm form = (QuestionAnswerEditForm) target;
		
		if(form.getQuestionAnswerEditItems() != null){
			int i = 0;
			for(QuestionAnswerEditItem question
					: form.getQuestionAnswerEditItems()){
				if(question.getOperation().equals(ItemOperation.UPDATE) 
						|| question.getOperation().equals(ItemOperation.CREATE)){
					ValidationUtils.rejectIfEmpty(errors,
							"questionAnswerEditItems[" + i + "].questionNumber", 
							QUESTION_NUMBER_REQUIRED_MSG_KEY);
					if(question.getExistingQuestion()){
						ValidationUtils.rejectIfEmpty(errors,
								"questionAnswerEditItems[" + i + "].question", 
								QUESTION_REQUIRED_MSG_KEY);
					}
					else{
						ValidationUtils.rejectIfEmpty(errors,
							"questionAnswerEditItems[" + i + "].questionText", 
								QUESTION_TEXT_REQUIRED_MSG_KEY);
						ValidationUtils.rejectIfEmpty(errors,
								"questionAnswerEditItems[" + i + "]"
										+ ".questionCategory", 
								QUESTION_TYPE_REQUIRED_MSG_KEY);
					}
					if(question.getAllowedAnswerItems() != null){
						int j = 0;
						for(AllowedAnswerItem answer 
								: question.getAllowedAnswerItems()){
							if(!answer.getOperation().equals(ItemOperation.REMOVE)){
								if(answer.getExistingAnswer()){
									ValidationUtils.rejectIfEmpty(errors,
										"questionAnswerEditItems[" + i + "]"
											+ ".allowedAnswerItems[" + j + "]"
															+ ".answerValue", 
											ANSWER_VALUE_REQUIRED_MSG_KEY);
								}
								else{
									ValidationUtils.rejectIfEmpty(errors,
										"questionAnswerEditItems[" + i + "]"
											+ ".allowedAnswerItems[" + j + "]"
															+ ".description", 
									ANSWER_VALUE_DESCRIPTION_REQUIRED_MSG_KEY);
								}
							}
							j++;
						}
					}
				}
				i++;
			}
		}
	}
}
