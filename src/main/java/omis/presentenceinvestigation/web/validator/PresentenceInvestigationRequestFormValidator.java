package omis.presentenceinvestigation.web.validator;

import java.util.EnumSet;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.presentenceinvestigation.web.form.PresentenceInvestigationItemOperation;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationRequestForm;


/** Validator for presentence investigation request form.
 * @author Ryan Johns
 * @author Annie Jacques
 * @version 0.1.2 (May 17, 2017)
 * @since OMIS 3.0 */
public class PresentenceInvestigationRequestFormValidator
	implements Validator {
	
		private static final String DOCKET_VALUE_REQUIRED_MSG_KEY =
				"request.docket.value.empty";
		
		private static final String COURT_REQUIRED_MSG_KEY =
				"request.docket.court.empty";
		
		private static final String EXPECTED_COMPLETION_DATE_REQUIRED_MSG_KEY =
				"request.expectedCompletionDate.empty";
		
		private static final String DATE_REQUIRED_MSG_KEY =
				"request.note.date.empty";
		
		private static final String DESCRIPTION_REQUIRED_MSG_KEY =
				"request.note.description.empty";
		
		private static final String REQUEST_DATE_REQUIRED_MSG_KEY =
				"request.requestDate.empty";
		
		private static final String ASSIGNED_USER_REQUIRED_MSG_KEY =
				"request.assignedUser.empty";
		
		private static final String CATEGORY_REQUIRED_MSG_KEY =
				"request.category.empty";
		
		private static final String PERSON_REQUIRED_MSG_KEY =
				"personFields.person.empty";
		
		private static final String LAST_NAME_REQUIRED_MSG_KEY =
				"personFields.lastName.empty";
		
		private static final String FIRST_NAME_REQUIRED_MSG_KEY =
				"personFields.firstName.empty";
		
	/** {@inheritDoc} */	
	@Override
	public boolean supports(final Class<?> clazz) {
		return PresentenceInvestigationRequestForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "docketValue",
				DOCKET_VALUE_REQUIRED_MSG_KEY);
		ValidationUtils.rejectIfEmpty(errors, "court",
				COURT_REQUIRED_MSG_KEY);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expectedCompletionDate", 
				EXPECTED_COMPLETION_DATE_REQUIRED_MSG_KEY);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "requestDate", 
				REQUEST_DATE_REQUIRED_MSG_KEY);
		ValidationUtils.rejectIfEmpty(errors, "assignedUserAccount", 
				ASSIGNED_USER_REQUIRED_MSG_KEY);
		ValidationUtils.rejectIfEmpty(errors, "category", 
				CATEGORY_REQUIRED_MSG_KEY);
		PresentenceInvestigationRequestForm form =
				(PresentenceInvestigationRequestForm) obj;
		if(form.getCreatePerson() != null && form.getCreatePerson() == true){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName",
					LAST_NAME_REQUIRED_MSG_KEY);
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName",
					FIRST_NAME_REQUIRED_MSG_KEY);
		}
		else{
			ValidationUtils.rejectIfEmpty(errors, "person",
					PERSON_REQUIRED_MSG_KEY);
		}
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
