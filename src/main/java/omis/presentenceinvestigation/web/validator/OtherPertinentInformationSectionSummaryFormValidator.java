package omis.presentenceinvestigation.web.validator;

import java.util.EnumSet;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.presentenceinvestigation.web.form.OtherPertinentInformationSectionSummaryForm;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationItemOperation;

/**
 * OtherPertinentInformationSectionSummaryFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 19, 2017)
 *@since OMIS 3.0
 *
 */
public class OtherPertinentInformationSectionSummaryFormValidator
	implements Validator {

	private static final String SUMMARY_DESCRIPTION_REQUIRED_MSG_KEY =
			"otherPertinentInformationSectionSummary.description.empty";
	
	private static final String DESCRIPTION_REQUIRED_MSG_KEY =
			"description.required";
	
	private static final String DATE_REQUIRED_MSG_KEY = "date.required";
	
	/**{@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return OtherPertinentInformationSectionSummaryForm.class
				.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		OtherPertinentInformationSectionSummaryForm form =
				(OtherPertinentInformationSectionSummaryForm) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
				SUMMARY_DESCRIPTION_REQUIRED_MSG_KEY);
		
		for(int i = 0; i <
				form.getOtherPertinentInformationSectionSummaryNoteItems().size();
				i++){
			if(EnumSet.of(PresentenceInvestigationItemOperation.CREATE,
					PresentenceInvestigationItemOperation.UPDATE).contains(
					form.getOtherPertinentInformationSectionSummaryNoteItems()
					.get(i).getItemOperation())){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"otherPertinentInformationSectionSummaryNoteItems["+i+"]"
							+ ".description",
					DESCRIPTION_REQUIRED_MSG_KEY);
				ValidationUtils.rejectIfEmpty(errors,
					"otherPertinentInformationSectionSummaryNoteItems["+i+"]"
							+ ".date",
					DATE_REQUIRED_MSG_KEY);
			}
		}
	}
}
