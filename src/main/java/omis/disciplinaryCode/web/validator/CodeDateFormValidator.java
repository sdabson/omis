package omis.disciplinaryCode.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.disciplinaryCode.web.form.CodeDateForm;

/**
 * CodeDateFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 11, 2016)
 *@since OMIS 3.0
 *
 */
public class CodeDateFormValidator implements Validator {
	private static final String DATE_REQUIRED_MSG_KEY = "dateField.empty";
	
	private static final String TO_DATE_INVALID_MSG_KEY = "endDate.invalid";
	
	/**
	 * 
	 */
	public CodeDateFormValidator() {
	}
	
	
	/**{@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return CodeDateForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		CodeDateForm form = (CodeDateForm) target;
		
		if(form.getUsingEffectiveDate() == true){
			ValidationUtils.rejectIfEmpty(errors, "effectiveDate", 
					DATE_REQUIRED_MSG_KEY);
		}
		else{
			if(form.getToDate() == null && form.getFromDate() == null){
				errors.rejectValue("fromDate", DATE_REQUIRED_MSG_KEY);
			}
			else{
				if(form.getFromDate() != null && form.getToDate() != null){
					if(form.getToDate().getTime() < form.getFromDate().getTime()){
						errors.rejectValue("toDate", TO_DATE_INVALID_MSG_KEY);
					}
				}
			}
		}
	}
 
}
