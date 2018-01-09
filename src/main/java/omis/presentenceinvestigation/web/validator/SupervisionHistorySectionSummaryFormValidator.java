package omis.presentenceinvestigation.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.presentenceinvestigation.web.form.PresentenceInvestigationItemOperation;
import omis.presentenceinvestigation.web.form.SupervisionHistoryNoteItem;
import omis.presentenceinvestigation.web.form.SupervisionHistorySectionSummaryForm;

/**
 * Supervision history section summary form validator.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 14, 2017)
 * @since OMIS 3.0
 */
public class SupervisionHistorySectionSummaryFormValidator 
	implements Validator {
	
	private static final String SUMMARY_TEXT_REQUIRED_MSG_KEY =
			"supervisionHistorySectionSummary.text.empty";
	
	private static final String DESCRIPTION_REQUIRED_MSG_KEY 
		= "description.required";
	
	private static final String DATE_REQUIRED_MSG_KEY = "date.required";
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return SupervisionHistorySectionSummaryForm
				.class.isAssignableFrom(clazz);
	}
	
	/** {@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		
		SupervisionHistorySectionSummaryForm form 
			= (SupervisionHistorySectionSummaryForm) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text",
				SUMMARY_TEXT_REQUIRED_MSG_KEY);
		
		if (form.getSupervisionHistoryNoteItems() != null) {
			int i = 0;
			for (SupervisionHistoryNoteItem item 
					: form.getSupervisionHistoryNoteItems()) {
				if(PresentenceInvestigationItemOperation.UPDATE.equals(
						item.getItemOperation())
				|| PresentenceInvestigationItemOperation.CREATE.equals(
						item.getItemOperation())){
					ValidationUtils.rejectIfEmptyOrWhitespace(errors,
							"supervisionHistoryNoteItems["+i+"]"
									+ ".description",
						DESCRIPTION_REQUIRED_MSG_KEY);
					ValidationUtils.rejectIfEmpty(errors,
						"supervisionHistoryNoteItems["+i+"].date",
						DATE_REQUIRED_MSG_KEY);
				}
				
				i++;
			}
		}
	}
}