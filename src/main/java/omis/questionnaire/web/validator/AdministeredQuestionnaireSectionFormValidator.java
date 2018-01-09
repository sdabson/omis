package omis.questionnaire.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.questionnaire.domain.AnswerCardinality;
import omis.questionnaire.domain.QuestionCategory;
import omis.questionnaire.domain.QuestionConditionality;
import omis.questionnaire.web.form.AnswerValueItem;
import omis.questionnaire.web.form.QuestionAnswerItem;
import omis.questionnaire.web.form.AdministeredQuestionnaireSectionForm;

/**
 * QuestionnaireFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 13, 2016)
 *@since OMIS 3.0
 *
 */
public class AdministeredQuestionnaireSectionFormValidator implements Validator {

	private static final String ANSWER_REQUIRED_MSG_KEY = "answer.empty";
	
	private static final String ESSAY_REQUIRED_MSG_KEY = "essay.empty";

	/**{@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return AdministeredQuestionnaireSectionForm
				.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		
		AdministeredQuestionnaireSectionForm form =
				(AdministeredQuestionnaireSectionForm) target;
		if(form.getQuestionAnswerItems() != null){
			int index = 0;
			for(QuestionAnswerItem item : form.getQuestionAnswerItems()){
				if(item.getQuestionConditionality()
						.equals(QuestionConditionality.REQUIRED)){
					if(item.getQuestionCategory()
							.equals(QuestionCategory.TRUE_FALSE)
							|| item.getQuestionCategory()
							.equals(QuestionCategory.MULTIPLE_CHOICE)
							|| item.getQuestionCategory()
							.equals(QuestionCategory.MULTIPLE_CHOICE_ESSAY)){
						
						if(item.getAnswerCardinality()
								.equals(AnswerCardinality.SINGLE)){
							if(item.getAnswerValue() == null ){
								errors.rejectValue(
										"questionAnswerItems[" + index + "]"
												+ ".answerValue", 
												ANSWER_REQUIRED_MSG_KEY);
							}
						}
						else if(item.getAnswerCardinality()
								.equals(AnswerCardinality.MULTIPLE)){
							Boolean answered = false;
							checkAnswer : for(AnswerValueItem avItem 
									: item.getAnswerValueItems()){
								if(avItem.getAnswerValue() != null){
									answered = true;
									break checkAnswer;
								}
							}
							if(!(answered)){
								errors.rejectValue(
										"questionAnswerItems[" + index + "]"
												+ ".answerValueItems", 
												ANSWER_REQUIRED_MSG_KEY);
							}
						}
					}
					if(item.getQuestionCategory()
							.equals(QuestionCategory.MULTIPLE_CHOICE_ESSAY)
						|| item.getQuestionCategory()
						.equals(QuestionCategory.ESSAY)){
						if(item.getAnswerValueText() == null ||
								item.getAnswerValueText().length() < 1){
							errors.rejectValue(
									"questionAnswerItems[" + index + "]"
											+ ".answerValueText", 
											ESSAY_REQUIRED_MSG_KEY);
						}
					}
				}
				index++;
			}
		}
	}
}
