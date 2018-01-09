package omis.presentenceinvestigation.web.validator;

import java.util.EnumSet;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.presentenceinvestigation.web.form.EvaluationRecommendationSectionSummaryForm;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationItemOperation;

/**
 * Evaluation Recommendation Section Summary Form Validator
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 12, 2017)
 *@since OMIS 3.0
 *
 */
public class EvaluationRecommendationSectionSummaryFormValidator
		implements Validator {
	
	private static final String DESCRIPTION_REQUIRED_MSG_KEY =
			"description.required";
	
	private static final String DATE_REQUIRED_MSG_KEY = "date.required";
	
	/**{@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return EvaluationRecommendationSectionSummaryForm.class
				.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		EvaluationRecommendationSectionSummaryForm form =
				(EvaluationRecommendationSectionSummaryForm) target;
		
		for(int i = 0; i <
				form.getEvaluationRecommendationSectionSummaryNoteItems().size();
				i++){
			if(EnumSet.of(PresentenceInvestigationItemOperation.CREATE,
					PresentenceInvestigationItemOperation.UPDATE).contains(
					form.getEvaluationRecommendationSectionSummaryNoteItems()
					.get(i).getItemOperation())){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"evaluationRecommendationSectionSummaryNoteItems["+i+"]"
							+ ".description",
					DESCRIPTION_REQUIRED_MSG_KEY);
				ValidationUtils.rejectIfEmpty(errors,
					"evaluationRecommendationSectionSummaryNoteItems["+i+"]"
							+ ".date",
					DATE_REQUIRED_MSG_KEY);
			}
		}
	}
}
