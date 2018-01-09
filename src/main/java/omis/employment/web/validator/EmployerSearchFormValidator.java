package omis.employment.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.employment.web.form.SearchEmployerForm;

/**
 * 
 * Validator for form to search for employer.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Apr 5, 2016)
 * @since OMIS 3.0
 */

public class EmployerSearchFormValidator implements Validator {

	/** Instantiates validator for form to search for employer. */
	public EmployerSearchFormValidator() {
		//Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return SearchEmployerForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		SearchEmployerForm employerSearchForm = (SearchEmployerForm) target;
		if (employerSearchForm.getEmployerName() == ""
				&& employerSearchForm.getCity() == null
				&& employerSearchForm.getState() == null) {
			errors.reject("employer.search.fields.empty");
		}
	}
}