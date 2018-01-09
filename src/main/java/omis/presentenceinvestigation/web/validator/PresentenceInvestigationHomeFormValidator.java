package omis.presentenceinvestigation.web.validator;

import java.util.EnumSet;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.presentenceinvestigation.web.form.PresentenceInvestigationHomeForm;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationItemOperation;

/**
 * PresentenceInvestigationHomeFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 3, 2017)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationHomeFormValidator implements Validator {
	
	private static final String DATE_REQUIRED_MSG_KEY =
			"request.note.date.empty";
	
	private static final String DESCRIPTION_REQUIRED_MSG_KEY =
			"request.note.description.empty";
	
	/**{@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return PresentenceInvestigationHomeForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		PresentenceInvestigationHomeForm form =
				(PresentenceInvestigationHomeForm) target;
		for(int i = 0; i <
				form.getPresentenceInvestigationRequestNoteItems().size(); i++){
			if(EnumSet.of(PresentenceInvestigationItemOperation.CREATE,
					PresentenceInvestigationItemOperation.UPDATE).contains(
					form.getPresentenceInvestigationRequestNoteItems().get(i)
					.getItemOperation())){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors,
						"presentenceInvestigationRequestNoteItems[" + i + "]"
								+ ".date", DATE_REQUIRED_MSG_KEY);
				ValidationUtils.rejectIfEmptyOrWhitespace(errors,
						"presentenceInvestigationRequestNoteItems[" + i + "]"
								+ ".description", DESCRIPTION_REQUIRED_MSG_KEY);
			}
		}
	}

}
