package omis.presentenceinvestigation.web.validator;

import java.util.EnumSet;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.presentenceinvestigation.web.form.PresentenceInvestigationItemOperation;
import omis.presentenceinvestigation.web.form.VictimSectionSummaryForm;

/**
 * VictimSectionSummaryFormValidator.java
 * 
 *@author Annie Jacques 
 *@author Trevor Isles
 *@version 0.1.1 (August 31, 2017)
 *@since OMIS 3.0
 *
 */
public class VictimSectionSummaryFormValidator implements Validator {
	private static final String TEXT_REQUIRED_MSG_KEY =
			"victimSectionSummary.text.empty";
	
	private static final String DESCRIPTION_REQUIRED_MSG_KEY =
			"description.required";
	
	private static final String DATE_REQUIRED_MSG_KEY = "date.required";

	/**{@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return VictimSectionSummaryForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		VictimSectionSummaryForm form =
				(VictimSectionSummaryForm) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text",
				TEXT_REQUIRED_MSG_KEY);
		
		for(int i = 0; i < form.getVictimSectionSummaryNoteItems().size(); i++){
			if(EnumSet.of(PresentenceInvestigationItemOperation.CREATE,
					PresentenceInvestigationItemOperation.UPDATE)
					.contains(form.getVictimSectionSummaryNoteItems().get(i)
							.getItemOperation())){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"victimSectionSummaryNoteItems["+i+"].description",
					DESCRIPTION_REQUIRED_MSG_KEY);
				ValidationUtils.rejectIfEmpty(errors,
					"victimSectionSummaryNoteItems["+i+"].date",
					DATE_REQUIRED_MSG_KEY);
			}
			
			i++;
		}
	}
}
