package omis.staff.web.validator;

import omis.staff.web.form.StaffAssignmentForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for staff assignments.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 5, 2014)
 * @since OMIS 3.0
 */
public class StaffAssignmentFormValidator
		implements Validator {

	/** Instantiates a validator for staff assignment forms. */
	public StaffAssignmentFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return StaffAssignmentForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		StaffAssignmentForm staffAssignmentForm =
				(StaffAssignmentForm) target;
		if (staffAssignmentForm.getStaffMember() == null) {
			errors.rejectValue("staffMember", "staffMember.empty");
		}
		if (staffAssignmentForm.getSupervisoryOrganization() == null) {
			errors.rejectValue("supervisoryOrganization",
					"supervisoryOrganization.empty");
		}
		if (staffAssignmentForm.getStartDate() != null
				&& staffAssignmentForm.getEndDate() != null
				&& staffAssignmentForm.getStartDate().getTime() >
					staffAssignmentForm.getEndDate().getTime()) {
			errors.rejectValue("startDate",
					"dateRange.startDateGreaterThanEndDate");
		}
	}
}