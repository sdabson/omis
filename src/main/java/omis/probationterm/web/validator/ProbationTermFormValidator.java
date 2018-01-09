package omis.probationterm.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.probationterm.web.form.ProbationTermForm;

/**
 * Validator for prison terms.
 * 
 * @author Trevor Isles
 * @author Stephen Abson
 * @version 0.1.1 (November 21, 2017)
 * @since OMIS 3.0
 */

public class ProbationTermFormValidator implements Validator {

	/**
	 * Instantiates a default probation term form validator.
	 */
	public ProbationTermFormValidator() {
		// Default instantiation
	} 
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ProbationTermForm.class.isAssignableFrom(clazz);
	}
	
	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		ProbationTermForm probationTermForm = (ProbationTermForm) target;
		
		if (probationTermForm.getStartDate() == null) {
			errors.rejectValue("startDate", "probationTerm.startDate.empty");
		}
		if (probationTermForm.getSentenceDays() == null) {
			errors.rejectValue("sentenceDays", 
					"probationTerm.sentenceDays.empty");
		}
	}	
}
