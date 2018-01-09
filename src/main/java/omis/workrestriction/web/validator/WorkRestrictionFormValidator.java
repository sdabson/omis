package omis.workrestriction.web.validator;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.workrestriction.web.form.ItemOperation;
import omis.workrestriction.web.form.NoteItem;
import omis.workrestriction.web.form.WorkRestrictionForm;

/**
 * WorkRestrictionFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 17, 2016)
 *@since OMIS 3.0
 *
 */
public class WorkRestrictionFormValidator implements Validator {

	private static final String AUTHORIZED_BY_USER_REQUIRED_MSG_KEY 
		= "authorizedByUser.empty";
	private static final String AUTHORIZATION_DATE_REQUIRED_MSG_KEY 
		= "authorizationDate.empty";
	private static final String CATEGORY_REQUIRED_MSG_KEY 
		= "category.empty";
	private static final String AUTHORIZATION_DATE_INVALID_MSG_KEY 
		= "authorizationDate.invalid";
	
	private static final String NOTE_DATE_REQUIRED_MSG_KEY = "date.empty";
	
	private static final String NOTE_VALUE_REQUIRED_MSG_KEY 
		= "value.empty";

	/**{@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return WorkRestrictionForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		WorkRestrictionForm form = (WorkRestrictionForm) target;
		
		ValidationUtils.rejectIfEmpty(errors, "authorizedByUser", 
				AUTHORIZED_BY_USER_REQUIRED_MSG_KEY);
		
		ValidationUtils.rejectIfEmpty(errors, "authorizationDate", 
				AUTHORIZATION_DATE_REQUIRED_MSG_KEY);
		
		ValidationUtils.rejectIfEmpty(errors, "category", 
				CATEGORY_REQUIRED_MSG_KEY);
		
		if(form.getAuthorizationDate() != null && 
				form.getAuthorizationDate().getTime() > new Date().getTime()){
			errors.rejectValue("authorizationDate", 
					AUTHORIZATION_DATE_INVALID_MSG_KEY);
		}
		if(form.getNoteItems() != null){
			//check notes list
			int index = 0;
			for (NoteItem item : form.getNoteItems()) {
				if (ItemOperation.CREATE.equals(item
						.getOperation()) || ItemOperation
						.UPDATE.equals(item.getOperation())) {
					if (item.getDate() == null) {
						errors.rejectValue("noteItems[" + index + "].date", 
								NOTE_DATE_REQUIRED_MSG_KEY);
					}
					if (item.getValue() == null || 
							item.getValue().length() < 1) {
						errors.rejectValue("noteItems[" + index + "].value", 
								NOTE_VALUE_REQUIRED_MSG_KEY);
					}
				}
				index++;
			}
		}
	}
	

}
