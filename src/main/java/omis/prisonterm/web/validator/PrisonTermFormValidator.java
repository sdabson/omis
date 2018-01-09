package omis.prisonterm.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.prisonterm.web.form.PrisonTermForm;

/**
 * Validator for prison terms.
 * 
 * @author Trevor Isles
 * @author Josh Divine
 * @version 0.1.1 (Oct 17, 2017)
 * @since OMIS 3.0
 */

public class PrisonTermFormValidator implements Validator {

	/**
	 * Instantiates a default prison term form validator.
	 * 
	 * @param stringLengthChecks check for string length
	 */
	public PrisonTermFormValidator() {
		// Default instantiation
	} 
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return PrisonTermForm.class.isAssignableFrom(clazz);
	}
	
	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		PrisonTermForm prisonTermForm = (PrisonTermForm) target;
		
		if (prisonTermForm.getActionDate() == null) {
			errors.rejectValue("actionDate", "prisonTerm.actionDate.empty");
		}
		if (prisonTermForm.getStatus() == null) {
			errors.rejectValue("status", "prisonTerm.status.empty");
		}
	}	
}
