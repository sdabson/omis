package omis.presentenceinvestigation.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.presentenceinvestigation.web.form.PresentenceInvestigationAddOffenderForm;

/**
 * PresentenceInvestigationAddOffenderFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 3, 2016)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationAddOffenderFormValidator 
	implements Validator {
	
	private static final String LAST_NAME_REQUIRED_MSG_KEY
		= "lastName.required";
	
	private static final String FIRST_NAME_REQUIRED_MSG_KEY 
		= "firstName.required";
	
	private static final String OFFENDER_REQUIRED_MSG_KEY 
		= "offender.required";
	
	
	/**{@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return PresentenceInvestigationAddOffenderForm
				.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		PresentenceInvestigationAddOffenderForm form 
			= (PresentenceInvestigationAddOffenderForm) target;
		
		if(form.getNewOffender()){
			ValidationUtils.rejectIfEmpty(errors, "lastName", 
					LAST_NAME_REQUIRED_MSG_KEY);
			ValidationUtils.rejectIfEmpty(errors, "firstName", 
					FIRST_NAME_REQUIRED_MSG_KEY);
		}
		else{
			ValidationUtils.rejectIfEmpty(errors, "offender", 
					OFFENDER_REQUIRED_MSG_KEY);
		}
		
	}

}
