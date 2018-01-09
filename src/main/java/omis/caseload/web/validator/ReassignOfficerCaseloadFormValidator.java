package omis.caseload.web.validator;

import omis.caseload.web.form.ReassignOfficerCaseloadForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Reassign officer caseload form validator.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Dec 27, 2016)
 * @since OMIS 3.0
 */
public class ReassignOfficerCaseloadFormValidator implements Validator {

	/** Instantiates an implementation of 
	 * reassign officer caseload form validator. */
	public ReassignOfficerCaseloadFormValidator() {
		// Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ReassignOfficerCaseloadForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		ReassignOfficerCaseloadForm form 
						= (ReassignOfficerCaseloadForm) target;
		if (form.getReassignDate() == null) {
			errors.rejectValue("reassignDate", 
							"reassignOfficer.reassignDate.empty");
		}
		if (form.getStaffMember() == null) {
			errors.rejectValue("staffMember", 
							"reassignOfficer.staffMember.empty");
		}
	}
}