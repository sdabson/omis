
package omis.presentenceinvestigation.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.presentenceinvestigation.web.form.EducationSectionSummaryForm;
import omis.presentenceinvestigation.web.form.EducationSectionSummaryNoteItem;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationItemOperation;

/**
 * EducationSectionSummaryFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 16, 2017)
 *@since OMIS 3.0
 *
 */
public class EducationSectionSummaryFormValidator implements Validator {
	
	private static final String SUMMARY_REQUIRED_MSG_KEY =
			"educationSectionSummary.summary.required";
	
	private static final String DESCRIPTION_REQUIRED_MSG_KEY =
			"description.required";
	
	private static final String DATE_REQUIRED_MSG_KEY = "date.required";
	
	
	/**{@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return EducationSectionSummaryForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		EducationSectionSummaryForm form =
				(EducationSectionSummaryForm) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text",
				SUMMARY_REQUIRED_MSG_KEY);
		
		if(form.getEducationSectionSummaryNoteItems() != null){
			int i = 0;
			for(EducationSectionSummaryNoteItem item :
				form.getEducationSectionSummaryNoteItems()){
				if(PresentenceInvestigationItemOperation.UPDATE.equals(
						item.getItemOperation())
				|| PresentenceInvestigationItemOperation.CREATE.equals(
						item.getItemOperation())){
					ValidationUtils.rejectIfEmptyOrWhitespace(errors,
						"educationSectionSummaryNoteItems["+i+"].description",
						DESCRIPTION_REQUIRED_MSG_KEY);
					ValidationUtils.rejectIfEmpty(errors,
						"educationSectionSummaryNoteItems["+i+"].date",
						DATE_REQUIRED_MSG_KEY);
				}
				
				i++;
			}
		}
	}

}
