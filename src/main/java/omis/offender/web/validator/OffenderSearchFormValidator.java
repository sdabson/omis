package omis.offender.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.offender.web.form.OffenderSearchForm;

/**
 * Validator for form to search offenders.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Mar 24, 2016)
 * @since OMIS 3.0
 */
public class OffenderSearchFormValidator implements Validator {

	/** Instantiates validator for form to search offenders. */
	public OffenderSearchFormValidator() {
		// Default instantiation
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return OffenderSearchForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		OffenderSearchForm offenderSearchForm = (OffenderSearchForm) target;
		if (offenderSearchForm.getDocIdNumber() == null 
				&& offenderSearchForm.getSocialSecurityNumber() == null 
				&& offenderSearchForm.getFirstName() == ""
				&& offenderSearchForm.getMiddleName() == ""
				&& offenderSearchForm.getLastName() == ""
				&& this.numberOfCriteria(offenderSearchForm) < 2) {
			errors.reject("search.fields.minimum");
		}	
	}
	
	/* Counts number of criteria used among name, . */
	private int numberOfCriteria(final OffenderSearchForm offenderSearchForm) {
		int result = 0;
		if (!"".equals(offenderSearchForm.getFirstName())) {
			result++;
		}
		if (!"".equals(offenderSearchForm.getMiddleName())) {
			result++;
		}
		if (!"".equals(offenderSearchForm.getLastName())) {
			result++;
		}
		if (offenderSearchForm.getSex() != null) {
			result++;
		}
		if (offenderSearchForm.getBirthDate() != null) {
			result++;
		}
		if (offenderSearchForm.getSocialSecurityNumber() != null) {
			result++;
		}
		return result;
	}
}