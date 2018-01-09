package omis.presentenceinvestigation.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.presentenceinvestigation.web.form.JailAdjustmentSectionSummaryForm;
import omis.presentenceinvestigation.web.form.JailAdjustmentSectionSummaryNoteItem;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationItemOperation;

/**
 * JailAdjustmentSectionSummaryFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Feb 27, 2017)
 *@since OMIS 3.0
 *
 */
public class JailAdjustmentSectionSummaryFormValidator implements Validator {
	
	private static final String DESCRIPTION_REQUIRED_MSG_KEY = "description.required";
	
	private static final String DATE_REQUIRED_MSG_KEY = "date.required";
	
	
	/**{@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return JailAdjustmentSectionSummaryForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		JailAdjustmentSectionSummaryForm form =
				(JailAdjustmentSectionSummaryForm) target;
		
		if(form.getJailAdjustmentSectionSummaryNoteItems() != null){
			int i = 0;
			for(JailAdjustmentSectionSummaryNoteItem item :
				form.getJailAdjustmentSectionSummaryNoteItems()){
				if(PresentenceInvestigationItemOperation.UPDATE.equals(
						item.getItemOperation())
				|| PresentenceInvestigationItemOperation.CREATE.equals(
						item.getItemOperation())){
					ValidationUtils.rejectIfEmptyOrWhitespace(errors,
						"jailAdjustmentSectionSummaryNoteItems["+i+"].description",
						DESCRIPTION_REQUIRED_MSG_KEY);
					ValidationUtils.rejectIfEmpty(errors,
						"jailAdjustmentSectionSummaryNoteItems["+i+"].date",
						DATE_REQUIRED_MSG_KEY);
				}
				
				i++;
			}
		}
	}
}
