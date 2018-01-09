package omis.residence.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.residence.web.form.ResidenceSearchForm;

/**
 * Validator for form to search for residence term.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Apr 25, 2016)
 * @since OMIS 3.0
 */

public class ResidenceSearchFormValidator implements Validator {

	/** Instantiates validator for form to search for residence term. */
	public ResidenceSearchFormValidator() {
		// Default instantiation
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return ResidenceSearchForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		ResidenceSearchForm residenceSearchForm = (ResidenceSearchForm) target;
		if (residenceSearchForm.getValue() == ""
				&& residenceSearchForm.getState() == null
				&& residenceSearchForm.getCity() == null
				&& residenceSearchForm.getEffectiveDate() == null) {
			errors.reject("residence.search.fields.empty");
		}
	}
}