package omis.staff.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.staff.web.form.StaffSearchForm;

/**
 * Validator for form to search for staff.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Mar 28, 2016)
 * @since OMIS 3.0
 */
public class StaffSearchFormValidator implements Validator {
	

	/** Instantiates validator for form to search for staff. */
	public StaffSearchFormValidator() {
		// Default instantiation
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return StaffSearchForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		StaffSearchForm staffSearchForm = (StaffSearchForm) target;
		if (staffSearchForm.getFirstName() == ""
				&& staffSearchForm.getLastName() == ""
				&& staffSearchForm.getWorkLocation() == null
				&& staffSearchForm.getDivision() == null) {
			errors.reject("staff.search.fields.empty");
		}
	/*	if (staffSearchForm.getSearchActiveStaff() == null) {
			errors.rejectValue("searchActiveStaff", "searchActiveStaff.empty");
		}*/
	}
}