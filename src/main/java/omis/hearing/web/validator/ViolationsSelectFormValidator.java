package omis.hearing.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.hearing.web.form.ViolationSelectionItem;
import omis.hearing.web.form.ViolationsSelectForm;

/**
 * ViolationsSelectFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 20, 2017)
 *@since OMIS 3.0
 *
 */
public class ViolationsSelectFormValidator implements Validator {
	
	private static final String NO_VIOLATIONS_SELECTED_MSG_KEY =
			"violations.unselected";
	
	/**{@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ViolationsSelectForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		ViolationsSelectForm form = (ViolationsSelectForm) target;
		
		Boolean selected = false;
		for(ViolationSelectionItem item : form.getViolationSelectionItems()){
			if(item.getSelected() == true){
				selected = true;
				break;
			}
		}
		
		if(!selected){
			errors.rejectValue("violationSelectionItems",
					NO_VIOLATIONS_SELECTED_MSG_KEY);
		}
	}

}
