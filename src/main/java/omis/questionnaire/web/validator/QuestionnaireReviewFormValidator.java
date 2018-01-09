package omis.questionnaire.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.questionnaire.web.form.QuestionnaireReviewForm;
import omis.questionnaire.web.form.SectionReviewItem;

/**
 * QuestionnaireReviewFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 13, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionnaireReviewFormValidator implements Validator {
	
	private static final String SECTION_NOT_FINALIZED_MSG_KEY = "section.draft";
	
	/**{@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return QuestionnaireReviewForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		
		QuestionnaireReviewForm form = (QuestionnaireReviewForm) target;
		
		int index = 0;
		if(form.getSectionReviewItems() != null){
			for(SectionReviewItem item : form.getSectionReviewItems()){
				if(item.getSectionStatus().getDraft() == true){
					errors.rejectValue(
							"sectionReviewItems[" + index + "].sectionStatus", 
									SECTION_NOT_FINALIZED_MSG_KEY);
				}
				index++;
			}
		}
	}
}
