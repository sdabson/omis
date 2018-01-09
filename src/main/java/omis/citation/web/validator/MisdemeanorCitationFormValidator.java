package omis.citation.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.citation.web.form.MisdemeanorCitationForm;

/**
 * Validator for misdemeanor citations.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Aug 16, 2016)
 * @since OMIS 3.0
 */

public class MisdemeanorCitationFormValidator implements Validator {
	
	/**
	 * Instantiates a default misdemeanor citation form validator.
	 * 
	 * @param stringLengthChecks check for string length
	 */
	public MisdemeanorCitationFormValidator() {
		// Default instantiation
	} 

	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return MisdemeanorCitationForm.class.isAssignableFrom(clazz);
	}
	
	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		MisdemeanorCitationForm citationForm = (MisdemeanorCitationForm) target;

		if (citationForm.getCreateNewOffense() == null) {
			errors.rejectValue("createNewOffense",
					"citation.createNewOffense.empty");
		} else {
			if (citationForm.getCreateNewOffense()) {
				if (citationForm.getOffenseName().isEmpty()) { 
					errors.rejectValue("offenseName", "citation.offenseName.empty");
				}
			} else {
				if (citationForm.getOffense() == null) {
					errors.rejectValue("offense", "citation.offense.empty");
				}
			}
		}
		
		if (citationForm.getPartialDate() == true) {
				if (citationForm.getYear() == null) {
					errors.rejectValue("year", "citation.year.empty");
			}
		} else {
			if (citationForm.getDate() == null) {
				errors.rejectValue("date", "citation.date.empty");
			}
		}
		
		if (citationForm.getCounts() == null) {
			errors.rejectValue("counts", "citation.counts.empty");
		}
	}
}
