package omis.grievance.web.validator;

import omis.grievance.web.form.GrievanceSearchForm;
import omis.grievance.web.form.GrievanceSearchType;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form to search grievances.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jan 7, 2016)
 * @since OMIS 3.0
 */
public class GrievanceSearchFormValidator
		implements Validator {

	/** Instantiates validator for form to search grievances. */
	public GrievanceSearchFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return GrievanceSearchForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		GrievanceSearchForm grievanceSearchForm = (GrievanceSearchForm) target;
		if (grievanceSearchForm.getType() != null) {
			if (GrievanceSearchType.OFFENDER
					.equals(grievanceSearchForm.getType())) {
				if (grievanceSearchForm.getOffender() == null) {
					errors.rejectValue(
							"query", "grievanceSearchOffender.empty");
				}
			}
			if (GrievanceSearchType.LOCATION
					.equals(grievanceSearchForm.getType())) {
				if (grievanceSearchForm.getLocation() == null) {
					errors.rejectValue(
							"query", "grievanceSearchLocation.empty");
				}
			}
		} else {
			errors.rejectValue("type", "grievanceSearchType.empty");
		}
	}
}