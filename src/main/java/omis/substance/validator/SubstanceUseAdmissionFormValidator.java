package omis.substance.validator;

import omis.substance.web.form.SubstanceUseAdmissionForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Substance use admission form validator.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 24, 2013)
 * @since OMIS 3.0
 */
public class SubstanceUseAdmissionFormValidator implements Validator {
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return SubstanceUseAdmissionForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		SubstanceUseAdmissionForm form = (SubstanceUseAdmissionForm) target;
		
		if (form.getAdmission() == null) {
			errors.rejectValue("admission", "substanceUse.admission.empty");
		}
		if (form.getAdmissionDate() == null) {
			errors.rejectValue("admissionDate", 
					"substanceUse.admissionDate.empty");
		}
		if (form.getSubstance() == null) {
			errors.rejectValue("substance", "substanceUse.substance.empty");
		}
	}
}