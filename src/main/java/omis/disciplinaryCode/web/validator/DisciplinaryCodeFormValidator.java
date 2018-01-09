package omis.disciplinaryCode.web.validator;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.disciplinaryCode.web.form.DisciplinaryCodeForm;

/**
 * DisciplinaryCodeFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 9, 2016)
 *@since OMIS 3.0
 *
 */
public class DisciplinaryCodeFormValidator implements Validator {
	
	/* Message Keys */
	
	private static final String CODE_REQUIRED_MSG_KEY = "code.empty";
	
	private static final String DESCRIPTION_REQUIRED_MSG_KEY = "description.empty";
	
	private static final String START_DATE_REQUIRED_MSG_KEY = "startDate.empty";
	
	private static final String START_DATE_INVALID_MSG_KEY = "startDate.invalid";

	private static final String END_DATE_BEFORE_MSG_KEY = "endDate.before";

	private static final String END_DATE_SAME_MSG_KEY = "endDate.same";
	
	/**
	 *Default Constructor 
	 */
	public DisciplinaryCodeFormValidator() {
	}
	
	
	/**{@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return DisciplinaryCodeForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		DisciplinaryCodeForm form = (DisciplinaryCodeForm) target;
		
		if(form.getUseExistingCode()){
			ValidationUtils.rejectIfEmpty(errors, "disciplinaryCode", 
					CODE_REQUIRED_MSG_KEY);
		}
		
		if(!form.getUseExistingCode()){
			ValidationUtils.rejectIfEmpty(errors, "code", 
					CODE_REQUIRED_MSG_KEY);
			ValidationUtils.rejectIfEmpty(errors, "description", 
					DESCRIPTION_REQUIRED_MSG_KEY);
		}
		
		
		
		ValidationUtils.rejectIfEmpty(errors, "startDate", 
				START_DATE_REQUIRED_MSG_KEY);
		
		if(form.getStartDate() != null){
			if(form.getStartDate().getTime() > new Date().getTime()){
				//start date is greater than today's date
				errors.rejectValue("startDate", 
						START_DATE_INVALID_MSG_KEY);
			}
		}
		
		if (form.getStartDate() != null 
				&& form.getEndDate() != null) {
			//check if end date before start date
			if (form.getStartDate().getTime() 
					> form.getEndDate().getTime()) {
				errors.rejectValue("endDate",
						END_DATE_BEFORE_MSG_KEY);
			}
			if(form.getStartDate().getTime() == form.getEndDate().getTime()){
				errors.rejectValue("endDate",
						END_DATE_SAME_MSG_KEY);
			}
		}
		
		
		
	}

}
